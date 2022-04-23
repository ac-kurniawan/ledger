package com.goblin.common

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected

@Introspected
data class SuccessResponse<T>(
    @field:JsonProperty("status", required = true)
    val status: String,

    @field:JsonProperty("data", required = true)
    val data: T,

    @field:JsonProperty("totalPage")
    var totalPage: Int? = null
)
