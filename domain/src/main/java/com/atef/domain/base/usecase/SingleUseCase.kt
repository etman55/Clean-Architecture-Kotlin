package com.atef.domain.base.usecase

import com.atef.domain.base.errorhandling.ErrorHandler
import com.atef.domain.base.result.Result
import com.atef.domain.base.result.toResult
import com.atef.domain.base.scheduler.BaseSchedulerProvider
import io.reactivex.Single

/**
 *
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 *
 * Abstract class for a use case, representing an execution unit of asynchronous work.
 * This use case type uses [Single] as the return type.
 * Upon subscription a use case will execute its job in the thread specified by the [schedulerProvider.io()].
 * and will post the result to the thread specified by [schedulerProvider.ui()].
 */
abstract class SingleUseCase<in Params, T>(
    private val schedulerProvider: BaseSchedulerProvider,
    private val errorHandler: ErrorHandler
) {

    /**
     * Create a [Single] for this use case.
     */
    protected abstract fun createUseCase(params: Params? = null): Single<T>

    /**
     * Build a use case with the provided execution thread and post execution thread
     * @param params - parameters required for this use case
     */
    public fun buildSingle(params: Params? = null): Single<Result<T>> {
        return createUseCase(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .toResult(errorHandler)
    }
}
