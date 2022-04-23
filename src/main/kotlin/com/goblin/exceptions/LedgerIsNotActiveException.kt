package com.goblin.exceptions

import com.goblin.common.ErrorResponse
import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import java.util.UUID

class LedgerIsNotActiveException(ledgerId: UUID) : Exception("ledger with id $ledgerId is not active")

@Produces
@Primary
@Singleton
@Requires(classes = [LedgerIsNotActiveException::class, ExceptionHandler::class])
class LedgerIsNotActiveExceptionHandler : ExceptionHandler<LedgerIsNotActiveException, HttpResponse<ErrorResponse>> {
    override fun handle(request: HttpRequest<*>?, exception: LedgerIsNotActiveException): HttpResponse<ErrorResponse> {
        val response = ErrorResponse("error", exception.message!!)
        return HttpResponse.notFound(response)
    }
}
