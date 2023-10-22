package com.virusbear.tinn.serialization
/*
import com.virusbear.tinn.color.Color
import com.virusbear.tinn.serialization.Serializer
import com.virusbear.tinn.serialization.compound
import com.virusbear.tinn.serialization.double
import com.virusbear.tinn.serialization.encoded

val ColorSerializer = Serializer(
    serializer = { value ->
        compound {
            it["r"] = value.r.encoded
            it["g"] = value.g.encoded
            it["b"] = value.b.encoded
            it["a"] = value.a.encoded
        }
    },
    deserializer = { value ->
        value.compound {
            Color(
                it["r"].double,
                it["g"].double,
                it["b"].double,
                it["a"].double
            )
        }
    }
)*/