package com.goblin.repository

import com.goblin.domain.Ledgers
import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Repository
import java.util.UUID

@Repository
interface LedgerRepository : KotlinCrudRepository<Ledgers, UUID> {
    @Join("transactions")
    fun findByLedgerId(ledgerId: UUID): Ledgers?
}
