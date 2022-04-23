package com.goblin.repository

import io.micronaut.core.annotation.Blocking
import io.micronaut.data.repository.GenericRepository
import io.micronaut.validation.Validated

@Blocking
@Validated
interface KotlinCrudRepository<E, ID> : GenericRepository<E, ID> {
    fun count(): kotlin.Long

    fun delete(entity: E): kotlin.Int

    fun deleteAll(): kotlin.Int

    fun deleteAll(entities: kotlin.collections.Iterable<E>): kotlin.Int

    fun deleteById(id: ID): kotlin.Int

    fun existsById(id: ID): kotlin.Boolean

    fun findAll(): List<E>

    fun findById(id: ID): E?

    fun <S : E> save(entity: S): S

    fun <S : E> saveAll(entities: kotlin.collections.Iterable<S>): List<S>

    fun <S : E> update(entity: S): S

    fun <S : E> updateAll(entities: kotlin.collections.Iterable<S>): List<S>
}
