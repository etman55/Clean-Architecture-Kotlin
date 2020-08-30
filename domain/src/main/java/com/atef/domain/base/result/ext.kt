package com.atef.domain.base.result

import com.atef.domain.base.errorhandling.ErrorHandler
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */

fun <T> Single<T>.toResult(errorHandler: ErrorHandler): Single<Result<T>> = this
    .map {
        return@map Result.Success(it) as Result<T>
    }
    .onErrorReturn {
        return@onErrorReturn Result.Error(errorHandler.getError(it))
    }

fun <T> Flowable<T>.toResult(errorHandler: ErrorHandler): Flowable<Result<T>> = this
    .map {
        return@map Result.Success(it) as Result<T>
    }
    .onErrorReturn {
        return@onErrorReturn Result.Error(errorHandler.getError(it))
    }

fun <T> Observable<T>.toResult(errorHandler: ErrorHandler): Observable<Result<T>> = this
    .map {
        return@map Result.Success(it) as Result<T>
    }
    .onErrorReturn {
        return@onErrorReturn Result.Error(errorHandler.getError(it))
    }
