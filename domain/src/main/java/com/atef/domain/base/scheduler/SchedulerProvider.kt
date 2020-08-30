package com.atef.domain.base.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
class SchedulerProvider @Inject constructor() : BaseSchedulerProvider {
    override fun io(): Scheduler = Schedulers.io()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun ui(): Scheduler {
        return Schedulers.computation()
    }
}
