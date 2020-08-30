package com.atef.data.data.base.errorhandling

import com.atef.domain.base.errorhandling.ErrorEntity
import com.atef.domain.base.errorhandling.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class ErrorHandlerImpl : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is IOException -> ErrorEntity.Network
            is HttpException -> {
                when (throwable.code()) {
                    // not found
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound

                    // access denied
                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied

                    HttpURLConnection.HTTP_UNAUTHORIZED -> ErrorEntity.UnAuthorized

                    UNPROCESSABLE_ENTITY -> ErrorEntity.UnProcessableEntity

                    HttpURLConnection.HTTP_ENTITY_TOO_LARGE -> ErrorEntity.FileTooLarge

                    // unavailable service
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable

                    HttpURLConnection.HTTP_INTERNAL_ERROR -> ErrorEntity.ServiceError
                    // all the others will be treated as unknown error
                    else -> ErrorEntity.Unknown
                }
            }
            else -> ErrorEntity.Unknown
        }
    }

    companion object {
        const val UNPROCESSABLE_ENTITY = 422
    }
}