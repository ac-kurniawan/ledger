package com.goblin.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.goblin.domain.Ledgers
import io.micronaut.core.annotation.Introspected
import java.io.Serializable

@Introspected
data class LedgersRequest(
    @field:JsonProperty("name", required = true)
    val name: String,
    @field:JsonProperty("userId", required = true)
    val userId: String
) : Serializable {
    fun toEntity(): Ledgers = Ledgers(
        name = name,
        userId = userId
    )
}
