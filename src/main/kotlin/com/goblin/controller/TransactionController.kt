package com.goblin.controller

import com.goblin.common.SuccessResponse
import com.goblin.controller.dto.TransactionsRequest
import com.goblin.controller.dto.TransactionsResponse
import com.goblin.controller.dto.fromEntityToTransactionResponse
import com.goblin.service.TransactionService
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import java.util.UUID

@Controller("/ledger")
class TransactionController(
    private val transactionService: TransactionService
) {
    @Get("/{ledgerId}/transactions")
    @Secured(SecurityRule.IS_ANONYMOUS)
    fun getTransactions(ledgerId: UUID, @QueryValue page: Int?, @QueryValue size: Int?): HttpResponse<SuccessResponse<List<TransactionsResponse>>> {
        val pageSize = size ?: 10
        val result = transactionService.findTransactionByLedgerId(ledgerId, page?.let { Pageable.from(it, pageSize) })
        return HttpResponse.ok(SuccessResponse("success", result.content.map { fromEntityToTransactionResponse(it) }, result.totalPages))
    }

    @Post("/{ledgerId}/credit")
    @Secured(SecurityRule.IS_ANONYMOUS)
    fun creditBalance(ledgerId: UUID, @Body transactionsRequest: TransactionsRequest): HttpResponse<SuccessResponse<TransactionsResponse>> {
        val result = transactionService.doCredit(ledgerId, transactionsRequest.transactionCode, transactionsRequest.amount)
        return HttpResponse.ok(SuccessResponse("success", fromEntityToTransactionResponse(result)))
    }

    @Post("/{ledgerId}/debit")
    @Secured(SecurityRule.IS_ANONYMOUS)
    fun debitBalance(ledgerId: UUID, @Body transactionsRequest: TransactionsRequest): HttpResponse<SuccessResponse<TransactionsResponse>> {
        val result = transactionService.doDebit(ledgerId, transactionsRequest.transactionCode, transactionsRequest.amount)
        return HttpResponse.ok(SuccessResponse("success", fromEntityToTransactionResponse(result)))
    }
}
