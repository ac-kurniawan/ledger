package com.goblin.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.math.BigDecimal

data class TransactionsRequest(
    @field: JsonProperty("transactionCode", required = true)
    val transactionCode: String,
    @field: JsonProperty("amount", required = true)
    val amount: BigDecimal
) : Serializable
