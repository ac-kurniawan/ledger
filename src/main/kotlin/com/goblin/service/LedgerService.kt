package com.goblin.service

import com.goblin.domain.Ledgers
import java.util.UUID

interface LedgerService {
    fun createLedger(ledgers: Ledgers): Ledgers
    fun findLedger(ledgerId: UUID, withTransaction: Boolean): Ledgers
    fun updateLedger(ledgers: Ledgers): Ledgers
    fun deleteLedger(ledgerId: UUID)
}
