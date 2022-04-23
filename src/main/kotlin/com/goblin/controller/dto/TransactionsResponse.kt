package com.goblin.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.goblin.domain.Transactions
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

data class TransactionsResponse(
    @field:JsonProperty("transactionId")
    val transactionId: UUID? = null,
    @field:JsonProperty("transactionCode")
    val transactionCode: String? = null,
    @field:JsonProperty("type")
    val type: Transactions.TransactionType? = null,
    @field:JsonProperty("amount")
    val amount: BigDecimal? = null,
    @field:JsonProperty("transactionDate")
    val transactionDate: String? = null
) : Serializable

fun fromEntityToTransactionResponse(transactions: Transactions): TransactionsResponse = TransactionsResponse(
    transactionId = transactions.transactionId,
    transactionCode = transactions.transactionCode,
    type = transactions.type,
    amount = transactions.amount,
    transactionDate = LocalDateTime.ofInstant(transactions.transactionDate, ZoneOffset.UTC).toString()
)
