package com.atef.data.data.base.errorhandling

import com.atef.data.remote.base.model.RemoteException
import com.atef.domain.base.errorhandling.ErrorEntity
import com.atef.domain.base.errorhandling.ErrorHandler
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketException
import java.util.concurrent.TimeoutException
import retrofit2.HttpException

class ErrorHandlerImpl : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is IOException,
            is SocketException,
            is TimeoutException -> ErrorEntity.Network
            is HttpException -> {
                when (throwable.code()) {
                    // not found
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound

                    // access denied
                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied

                    HttpURLConnection.HTTP_ENTITY_TOO_LARGE -> ErrorEntity.FileTooLarge

                    // unavailable service
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable

                    HttpURLConnection.HTTP_INTERNAL_ERROR -> ErrorEntity.ServiceError
                    // all the others will be treated as unknown error
                    else -> ErrorEntity.Unknown
                }
            }
            is RemoteException -> {
                when (throwable.kind) {
                    is RemoteException.Kind.HTTP,
                    is RemoteException.Kind.HTTP422WithDATA,
                    is RemoteException.Kind.AUTHENTICATION -> throwable.errorData?.errors?.get(0)
                        ?.let { ErrorEntity.ApiError(it) }
                        ?: run { ErrorEntity.ApiError("Oops! An error has occurred, Please try again.") }
                    else -> ErrorEntity.Unknown
                }
            }
            else -> ErrorEntity.Unknown
        }
    }
}
