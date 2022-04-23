package com.goblin.common

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected

@Introspected
data class ErrorResponse(
    @field:JsonProperty("status", required = true)
    val status: String,

    @field:JsonProperty("message", required = true)
    val message: String
)
