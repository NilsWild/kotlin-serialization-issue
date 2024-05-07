package com.example

import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.util.*

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
sealed interface Id {
    val value: UUID
}

@JvmInline
value class PersonId(override val value: UUID) : Id {
}

@JvmInline
value class ThingId(override val value: UUID) : Id {
}

data class PersonOrThing(val id: Id) {
}