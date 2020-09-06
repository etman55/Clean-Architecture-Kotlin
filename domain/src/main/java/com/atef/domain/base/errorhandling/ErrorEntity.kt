package com.atef.domain.base.errorhandling

/**
 * @author Atef Etman
 * @email etman850@gmail.com
 * @mobile +201090705106
 */
sealed class ErrorEntity {
    object Network : ErrorEntity()
    object NotFound : ErrorEntity()
    object FileTooLarge : ErrorEntity()
    object AccessDenied : ErrorEntity()
    object ServiceUnavailable : ErrorEntity()
    object ServiceError : ErrorEntity()
    object Unknown : ErrorEntity()
    data class ApiError(val errorMessage: String? = null) : ErrorEntity()
}
