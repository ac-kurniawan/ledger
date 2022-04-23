package com.goblin.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.goblin.domain.Ledgers
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

data class LedgersDetailsResponse(
    @field:JsonProperty("ledgerId")
    val ledgerId: UUID? = null,
    @field:JsonProperty("name")
    val name: String? = null,
    @field:JsonProperty("type")
    val type: Ledgers.LedgerType? = null,
    @field:JsonProperty("status")
    val status: Ledgers.LedgerStatus? = null,
    @field:JsonProperty("activeBalance")
    val activeBalance: BigDecimal? = null,
    @field:JsonProperty("userId")
    val userId: String? = null,
    @field:JsonProperty("createdAt")
    val createdAt: String? = null,
    @field:JsonProperty("updatedAt")
    val updatedAt: String? = null,
    @field:JsonProperty("deletedAt")
    val deletedAt: String? = null,
    @field:JsonProperty("transactions")
    val transactions: List<TransactionsResponse> = emptyList()
) : Serializable

fun fromEntityToLedgersDetailsResponse(ledgers: Ledgers): LedgersDetailsResponse = LedgersDetailsResponse(
    ledgerId = ledgers.ledgerId,
    name = ledgers.name,
    type = ledgers.type,
    status = ledgers.status,
    activeBalance = ledgers.activeBalance,
    userId = ledgers.userId,
    createdAt = if (ledgers.createdAt !== null) LocalDateTime.ofInstant(ledgers.createdAt, ZoneOffset.UTC).toString() else null,
    updatedAt = if (ledgers.updatedAt !== null) LocalDateTime.ofInstant(ledgers.updatedAt, ZoneOffset.UTC).toString() else null,
    deletedAt = if (ledgers.deletedAt !== null) LocalDateTime.ofInstant(ledgers.deletedAt, ZoneOffset.UTC).toString() else null,
    transactions = ledgers.transactions.map { fromEntityToTransactionResponse(it) }
)
