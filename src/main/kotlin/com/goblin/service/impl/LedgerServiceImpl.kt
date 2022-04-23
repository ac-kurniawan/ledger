package com.goblin.service.impl

import com.goblin.domain.Ledgers
import com.goblin.exceptions.LedgerDoesNotExistException
import com.goblin.repository.LedgerRepository
import com.goblin.service.LedgerService
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.util.UUID

@Singleton
class LedgerServiceImpl(private val ledgerRepository: LedgerRepository) : LedgerService {
    private val log: Logger = LoggerFactory.getLogger(LedgerServiceImpl::class.java)

    override fun createLedger(ledgers: Ledgers): Ledgers {
        log.info("create ledger: $ledgers")
        ledgers.type = Ledgers.LedgerType.NORMAL
        ledgers.status = Ledgers.LedgerStatus.ACTIVE
        ledgers.activeBalance = BigDecimal("0")
        return ledgerRepository.save(ledgers)
    }

    override fun findLedger(ledgerId: UUID, withTransaction: Boolean): Ledgers {
        log.info("find ledger with id: $ledgerId")
        return ledgerRepository.findById(ledgerId) ?: throw LedgerDoesNotExistException(ledgerId)
    }

    override fun updateLedger(ledgers: Ledgers): Ledgers {
        TODO("Not yet implemented")
    }

    override fun deleteLedger(ledgerId: UUID) {
        TODO("Not yet implemented")
    }
}
