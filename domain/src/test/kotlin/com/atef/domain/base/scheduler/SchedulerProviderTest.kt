package com.atef.domain.base.scheduler

import io.reactivex.Single
import org.junit.Test

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
class SchedulerProviderTest {

    @Test
    fun `emit and complete immediately with blocking scheduler provider`() {
        val schedulerProvider = TrampolineSchedulerProvider()

        val testObserver = Single.fromCallable { 3 }
            .subscribeOn(schedulerProvider.io())
            .flatMap {
                Single.fromCallable {
                    it * it
                }
                    .subscribeOn(schedulerProvider.computation())
            }
            .observeOn(schedulerProvider.ui()).test()

        testObserver.assertValue(9)
            .assertComplete()
    }

    @Test
    fun `does not emit or terminate immediately with non-blocking scheduler provider`() {
        val schedulerProvider = SingleSchedulerProvider()

        val testObserver = Single.fromCallable { 3 }
            .subscribeOn(schedulerProvider.io())
            .flatMap {
                Single.fromCallable {
                    it * it
                }
                    .subscribeOn(schedulerProvider.computation())
            }
            .observeOn(schedulerProvider.ui()).test()

        testObserver.assertNoValues()
            .assertNotComplete()
            .assertNoErrors()
    }
}
