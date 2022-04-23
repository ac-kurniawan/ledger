package com.goblin.service

import com.goblin.domain.Transactions
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import java.math.BigDecimal
import java.util.UUID

interface TransactionService {
    fun findTransactionByLedgerId(ledgerId: UUID, pageable: Pageable?): Page<Transactions>
    fun doCredit(ledgerId: UUID, transactionCode: String?, amount: BigDecimal, transactionDate: String? = null): Transactions
    fun doDebit(ledgerId: UUID, transactionCode: String?, amount: BigDecimal, transactionDate: String? = null): Transactions
}
