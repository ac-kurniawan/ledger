package com.goblin.controller

import com.goblin.common.SuccessResponse
import com.goblin.controller.dto.LedgersRequest
import com.goblin.controller.dto.LedgersResponse
import com.goblin.controller.dto.fromEntityToLedgerResponse
import com.goblin.service.LedgerService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import java.util.UUID

@Controller("/ledger")
class LedgerController(private val ledgerService: LedgerService) {
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post("/")
    fun createLedger(@Body ledgersRequest: LedgersRequest): HttpResponse<SuccessResponse<LedgersResponse>> {
        val result = ledgerService.createLedger(ledgersRequest.toEntity())
        return HttpResponse.created(SuccessResponse("success", fromEntityToLedgerResponse(result)))
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{ledgerId}")
    fun getLedgerById(ledgerId: UUID): HttpResponse<SuccessResponse<LedgersResponse>> {
        val result = ledgerService.findLedger(ledgerId, false)
        return HttpResponse.ok(SuccessResponse("success", fromEntityToLedgerResponse(result)))
    }
}
