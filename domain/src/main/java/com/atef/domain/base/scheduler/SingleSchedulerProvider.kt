package com.atef.domain.base.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */

class SingleSchedulerProvider : BaseSchedulerProvider {
    override fun io(): Scheduler = Schedulers.single()

    override fun computation(): Scheduler = Schedulers.single()

    override fun ui(): Scheduler = Schedulers.single()
}
