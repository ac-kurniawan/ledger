package com.goblin.domain

import org.hibernate.Hibernate
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.EnumType

@Entity
@Table(name = "transactions", schema = "ledger")
data class Transactions(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id", nullable = false)
    var transactionId: UUID? = null,

    @Column(name = "transaction_code")
    var transactionCode: String? = null,

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    val type: TransactionType? = null,

    @Column(name = "amount", nullable = false)
    val amount: BigDecimal,

    @Column(name = "transaction_date", nullable = false)
    val transactionDate: Instant? = null,

    @ManyToOne(optional = false)
    @JoinColumn(name = "ledger_id", nullable = false)
    val ledgers: Ledgers? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Transactions

        return transactionId != null && transactionId == other.transactionId
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(transactionId = $transactionId , transactionCode = $transactionCode , type = $type , amount = $amount , transactionDate = $transactionDate )"
    }

    enum class TransactionType(value: String) {
        DEBIT("DEBIT"),
        CREDIT("CREDIT")
    }
}
