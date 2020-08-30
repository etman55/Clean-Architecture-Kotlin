package com.atef.domain.base.result

import com.atef.domain.base.errorhandling.ErrorEntity

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 *
 *
 *  helper class to propagate result of every use case from domain layer to presentation layer
 */

sealed class Result<T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Error<T>(val error: ErrorEntity, val errorBody: T? = null) : Result<T>()
}
