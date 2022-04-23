package com.goblin.repository

import com.goblin.domain.Ledgers
import com.goblin.domain.Transactions
import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import java.util.UUID

@Repository
interface TransactionRepository : KotlinCrudRepository<Transactions, UUID> {
    fun findByLedgersOrderByTransactionDateDesc(ledgers: Ledgers, pageable: Pageable): Page<Transactions>
}
