package com.goblin.domain

import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "ledgers", schema = "ledger")
data class Ledgers(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ledger_id", nullable = false)
    var ledgerId: UUID? = null,

    @Column(name = "name", unique = true, nullable = false)
    var name: String? = null,

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    var type: LedgerType? = null,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: LedgerStatus? = null,

    @Column(name = "active_balance")
    var activeBalance: BigDecimal? = null,

    @Column(name = "user_id")
    var userId: String? = null,

    @Column(name = "created_at")
    @CreationTimestamp
    var createdAt: Instant? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: Instant? = null,

    @Column(name = "deleted_at")
    var deletedAt: Instant? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ledgers")
    val transactions: List<Transactions> = emptyList()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Ledgers

        return ledgerId != null && ledgerId == other.ledgerId
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(ledgerId = $ledgerId , name = $name , type = $type , status = $status , activeBalance = $activeBalance , userId = $userId , createdAt = $createdAt , updatedAt = $updatedAt , deletedAt = $deletedAt )"
    }

    enum class LedgerType(value: String) {
        DEBIT("DEBIT"),
        CREDIT("CREDIT"),
        NORMAL("NORMAL"),
    }

    enum class LedgerStatus(value: String) {
        ACTIVE("ACTIVE"),
        INACTIVE("INACTIVE")
    }
}
