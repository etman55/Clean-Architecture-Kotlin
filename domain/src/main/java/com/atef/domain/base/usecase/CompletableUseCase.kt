package com.atef.domain.base.usecase

import com.atef.domain.base.scheduler.BaseSchedulerProvider
import io.reactivex.Completable

/**
 *  * @author  Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 *
 * Abstract class for a use case, representing an execution unit of asynchronous work.
 * This use case type uses [Completable] as the return type.
 * Upon subscription a use case will execute its job in the thread specified by the [schedulerProvider.io()].
 * and will post the result to the thread specified by [schedulerProvider.ui()].
 */
abstract class CompletableUseCase<in Params>(
    private val schedulerProvider: BaseSchedulerProvider
) {

    /**
     * Create a [Completable] for this use case.
     */
    protected abstract fun createUseCase(params: Params? = null): Completable

    /**
     * Build a use case with the provided execution thread and post execution thread
     */
    public fun buildCompletable(params: Params? = null): Completable {
        return createUseCase(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }
}
