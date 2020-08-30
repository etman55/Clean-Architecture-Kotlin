package com.atef.domain.base.usecase

import com.atef.domain.base.errorhandling.ErrorHandler
import com.atef.domain.base.result.Result
import com.atef.domain.base.result.toResult
import com.atef.domain.base.scheduler.BaseSchedulerProvider
import io.reactivex.Observable

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */

abstract class ObservableUseCase<in Params, T>(
    private val schedulerProvider: BaseSchedulerProvider,
    private val errorHandler: ErrorHandler
) {

    /**
     * Create a [Observable] for this use case.
     */
    protected abstract fun createUseCase(params: Params? = null): Observable<T>

    /**
     * Build a use case with the provided execution thread and post execution thread
     */
    public fun buildObservable(params: Params? = null): Observable<Result<T>> {
        return createUseCase(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .toResult(errorHandler)
    }
}
