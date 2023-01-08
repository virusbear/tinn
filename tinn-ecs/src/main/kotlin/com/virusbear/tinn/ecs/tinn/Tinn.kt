package com.virusbear.tinn.ecs.tinn

import com.virusbear.tinn.ecs.api.ComponentId
import com.virusbear.tinn.ecs.api.Entity
import com.virusbear.tinn.ecs.api.EntityId
import com.virusbear.tinn.ecs.api.Registry
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Tinn: Registry {
    private val entityIndex = HashMap<EntityId, Record>()
    private val componentIndex = HashMap<ComponentId<*>, ArchetypeMap>()
    private val archetypeIndex = HashMap<Type, Archetype>()
    private val entityReference = HashMap<EntityId, Entity>()
    private val archetypeIds = BitSet()
    private val entityOccupancy = BitSet()

    override fun entity(): Entity {
        val id = entityOccupancy.nextClearBit(0)
        entityOccupancy.set(id)

        val entity = TinnEntity(this, id.toLong())
        entityReference[id.toLong()] = entity
        return entity
    }

    override fun minusAssign(entity: Entity) {
        entityIndex -= entity.id
        entityOccupancy.clear(entity.id.toInt())
        removeEntityFromArchetype(entity.id)
        entityReference -= entity.id
    }

    internal fun <C> getComponent(entity: EntityId, component: ComponentId<C>): C? {
        val record = entityIndex[entity] ?: return null
        val archetypes = componentIndex[component] ?: return null
        val column = archetypes[record.archetype.id] ?: return null
        @Suppress("UNCHECKED_CAST")
        return record.archetype.columns[column.column][record.row] as? C
    }

    internal fun hasComponent(entity: EntityId, component: ComponentId<*>): Boolean {
        val record = entityIndex[entity] ?: return false
        val archetypes = componentIndex[component] ?: return false
        return archetypes[record.archetype.id] != null
    }

    internal fun setComponent(entity: EntityId, key: ComponentId<*>, component: Any?) {
        val record = entityIndex[entity] ?: return
        val archetypes = componentIndex[key] ?: return
        val column = archetypes[record.archetype.id] ?: return
        record.archetype.columns[column.column][record.row] = component
    }

    internal fun addComponent(entity: EntityId, key: ComponentId<*>) {
        val record = entityIndex[entity] ?: return
        val archetype = record.archetype
        val edge = archetype.edges.computeIfAbsent(key) { ArchetypeEdge(null, null) }

        if(edge.add == null) {
            val newType = addComponentToType(archetype.type, key)
            val newArchetype = newArchetype(newType)
            edge.add = newArchetype
            newArchetype.edges.computeIfAbsent(key) { ArchetypeEdge(null, null) }.remove = archetype
        }

        val targetArchetype = edge.add ?: return
        moveEntity(entity, archetype, targetArchetype)
    }

    internal fun removeComponent(entity: EntityId, key: ComponentId<*>) {
        val record = entityIndex[entity] ?: return
        val archetype = record.archetype
        val edge = archetype.edges.computeIfAbsent(key) { ArchetypeEdge(null, null) }

        if(edge.remove == null) {
            val newType = removeComponentFromType(archetype.type, key)
            val newArchetype = newArchetype(newType)
            edge.remove = newArchetype
            newArchetype.edges.computeIfAbsent(key) { ArchetypeEdge(null, null) }.add = archetype
        }

        val targetArchetype = edge.remove ?: return
        moveEntity(entity, archetype, targetArchetype)
    }

    private fun addComponentToType(type: Type, key: ComponentId<*>): Array<ComponentId<*>> {
        val idx = type.indexOf(key)
        if(idx >= 0) return type

        return Array(type.size + 1) {
            if(it in type.indices) {
                type[it]
            } else {
                key
            }
        }
    }

    private fun removeComponentFromType(type: Type, key: ComponentId<*>): Array<ComponentId<*>> {
        val idx = type.indexOf(key)
        if(idx < 0) return type

        return Array(type.size - 1) {
            type[if(it >= idx) it + 1 else it]
        }
    }

    private fun newArchetype(type: Type): Archetype {
        val existingArchetype = archetypeIndex[type]
        if(existingArchetype != null) {
            return existingArchetype
        }

        val archetype =  Archetype(
            id = archetypeIds.nextClearBit(0).also { archetypeIds.set(it) }.toLong(),
            type
        )

        archetypeIndex[type] = archetype
        for(idx in type.indices) {
            //This line automatically adds new record to componentindex. no need for additional loop
            val archetypes = componentIndex.computeIfAbsent(type[idx]) { ArchetypeMap() }
            archetypes[archetype.id] = ArchetypeRecord(idx)
        }

        return archetype
    }

    private fun moveEntity(entity: EntityId, source: Archetype, target: Archetype) {
        val record = entityIndex[entity] ?: return

        val row = addEntity(target)
        for(component in source.type) {
            val value = getComponent(entity, component)

            val archetypes = componentIndex[component] ?: continue
            val column = archetypes[target.id]?.column ?: continue
            target.columns[column][row] = value
        }
        removeEntityFromArchetype(entity)

        record.archetype = target
        record.row = row
    }

    private fun addEntity(archetype: Archetype): Int {
        val row = archetype.occupancy.nextClearBit(0)
        archetype.occupancy.set(row)
        return row
    }

    private fun removeEntityFromArchetype(entity: EntityId) {
        val record = entityIndex[entity] ?: return
        val archetype = record.archetype

        archetype.occupancy.clear(record.row)
        for(component in archetype.type) {
            val archetypes = componentIndex[component] ?: continue
            val archetypeRecord = archetypes[archetype.id] ?: continue
            val column = archetype.columns[archetypeRecord.column]
            column[record.row] = null
        }
    }
}

class TinnEntity(
    private val owner: Tinn,
    override val id: EntityId
) : Entity {
    override fun minusAssign(key: ComponentId<*>) {
        owner.removeComponent(id, key)
    }

    override fun <C> set(key: ComponentId<C>, component: C) {
        if(!owner.hasComponent(id, key)) {
            owner.addComponent(id, key)
        }

        owner.setComponent(id, key, component)
    }

    override fun <C> get(key: ComponentId<C>): C? =
        owner.getComponent(id, key)

    override fun contains(key: ComponentId<*>): Boolean =
        owner.hasComponent(id, key)
}

typealias ArchetypeId = Long
typealias ArchetypeMap = HashMap<ArchetypeId, ArchetypeRecord>
typealias Type = Array<ComponentId<*>>
typealias Column = ArrayList<Any?>

@JvmInline
value class ArchetypeRecord(val column: Int)

data class Archetype(
    val id: ArchetypeId,
    val type: Type,
    val occupancy: BitSet = BitSet(),
    val columns: Array<Column> = Array(type.size) { ArrayList() },
    val edges: HashMap<ComponentId<*>, ArchetypeEdge> = HashMap()
)

data class ArchetypeEdge(
    var add: Archetype?,
    var remove: Archetype?
)

data class Record(
    var archetype: Archetype,
    var row: Int
)