package com.virusbear.tinn.extensions

import com.virusbear.tinn.registry.Registries
import com.virusbear.tinn.registry.Registries.registry
import com.virusbear.tinn.registry.Registry
import com.virusbear.tinn.serialization.Serializer
import kotlin.reflect.KClass

val Registries.Serializers: Registry<Pair<KClass<*>, Serializer<*>>> by registry()