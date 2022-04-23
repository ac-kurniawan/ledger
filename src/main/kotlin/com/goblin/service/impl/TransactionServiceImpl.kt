package com.goblin.service.impl

import com.goblin.domain.Ledgers
import com.goblin.domain.Transactions
import com.goblin.exceptions.LedgerDoesNotExistException
import com.goblin.exceptions.LedgerIsNotActiveException
import com.goblin.repository.LedgerRepository
import com.goblin.repository.TransactionRepository
import com.goblin.service.TransactionService
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import jakarta.inject.Singleton
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.UUID

@Singleton
class TransactionServiceImpl(
    private val ledgerRepository: LedgerRepository,
    private val transactionRepository: TransactionRepository
) : TransactionService {
    private val dateFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm:ss")

    private fun findValidateLedger(ledgerId: UUID): Ledgers {
        val ledger = ledgerRepository.findById(ledgerId) ?: throw LedgerDoesNotExistException(ledgerId)

        if (ledger.status !== Ledgers.LedgerStatus.ACTIVE) throw LedgerIsNotActiveException(ledgerId)

        return ledger
    }

    override fun findTransactionByLedgerId(ledgerId: UUID, pageable: Pageable?): Page<Transactions> {
        val page = pageable ?: Pageable.from(0, 10)
        return transactionRepository.findByLedgersOrderByTransactionDateDesc(Ledgers(ledgerId = ledgerId), page)
    }

    override fun doCredit(ledgerId: UUID, transactionCode: String?, amount: BigDecimal, transactionDate: String?): Transactions {
        val ledger = findValidateLedger(ledgerId)

        if (ledger.type === Ledgers.LedgerType.DEBIT || ledger.type === Ledgers.LedgerType.NORMAL) {
            ledger.activeBalance = ledger.activeBalance?.subtract(amount)
        } else {
            ledger.activeBalance = ledger.activeBalance?.add(amount)
        }
        ledgerRepository.update(ledger)

        val txDate = if (transactionDate == null) Instant.now() else LocalDateTime.parse(
            transactionDate,
            dateFormat
        )
            .atZone(ZoneOffset.UTC)
            .toInstant()

        val tx = Transactions(
            transactionCode = transactionCode,
            amount = amount,
            ledgers = Ledgers(ledgerId),
            type = Transactions.TransactionType.CREDIT,
            transactionDate = txDate
        )

        return transactionRepository.save(tx)
    }

    override fun doDebit(ledgerId: UUID, transactionCode: String?, amount: BigDecimal, transactionDate: String?): Transactions {
        val ledger = findValidateLedger(ledgerId)

        if (ledger.type === Ledgers.LedgerType.CREDIT) {
            ledger.activeBalance = ledger.activeBalance?.subtract(amount)
        } else {
            ledger.activeBalance = ledger.activeBalance?.add(amount)
        }
        ledgerRepository.update(ledger)

        val txDate = if (transactionDate == null) Instant.now() else LocalDateTime.parse(
            transactionDate,
            dateFormat
        )
            .atZone(ZoneOffset.UTC)
            .toInstant()
        val tx = Transactions(
            transactionCode = transactionCode,
            amount = amount,
            ledgers = Ledgers(ledgerId),
            type = Transactions.TransactionType.DEBIT,
            transactionDate = txDate
        )

        return transactionRepository.save(tx)
    }
}
