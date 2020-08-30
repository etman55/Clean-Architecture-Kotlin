package com.atef.domain.base.scheduler

import io.reactivex.Scheduler

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
interface BaseSchedulerProvider {

    fun io(): Scheduler

    fun computation(): Scheduler

    fun ui(): Scheduler
}
