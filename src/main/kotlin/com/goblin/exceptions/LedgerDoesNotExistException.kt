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

class LedgerDoesNotExistException(ledgerId: UUID) : Exception("ledger with id $ledgerId not found")

@Produces
@Primary
@Singleton
@Requires(classes = [LedgerDoesNotExistException::class, ExceptionHandler::class])
class LedgerDoesNotExistExceptionHandler : ExceptionHandler<LedgerDoesNotExistException, HttpResponse<ErrorResponse>> {
    override fun handle(
        request: HttpRequest<*>,
        exception: LedgerDoesNotExistException
    ): HttpResponse<ErrorResponse> {
        val response = ErrorResponse("error", exception.message!!)
        return HttpResponse.notFound(response)
    }
}
