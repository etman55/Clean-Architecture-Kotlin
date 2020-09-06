package com.atef.sample.base

import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription

/**
 * @author Atef Etman
 * @email etman850@gmail.com
 * @phone +201090705106
 *
 * A base view model class that deals with RX threading, dispose them when the view model is cleared and also gives
 * the ability to start more than observable in the same time by adding them in the disposables object.
 */
open class BaseViewModel : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    protected fun <T> execute(
        loadingConsumer: Consumer<Disposable>,
        resultConsumer: Consumer<T>,
        useCase: Single<T>
    ) {
        val observable = useCase
            .doOnSubscribe(loadingConsumer)
        addDisposable(observable.subscribe(resultConsumer))
    }

    protected fun <T> execute(
        loadingConsumer: Consumer<Subscription>,
        resultConsumer: Consumer<T>,
        useCase: Flowable<T>
    ) {
        val observable = useCase
            .doOnSubscribe(loadingConsumer)
        addDisposable(observable.subscribe(resultConsumer))
    }

    protected fun execute(
        loadingConsumer: Consumer<Disposable>,
        completeAction: Action,
        throwableConsumer: Consumer<Throwable>,
        useCase: Completable
    ) {
        val observable = useCase
            .doOnSubscribe(loadingConsumer)
        addDisposable(observable.subscribe(completeAction, throwableConsumer))
    }

    protected fun <T> execute(
        loadingConsumer: Consumer<Disposable>,
        resultConsumer: Consumer<T>,
        useCase: Observable<T>
    ) {
        val observable = useCase
            .doOnSubscribe(loadingConsumer)
        addDisposable(observable.subscribe(resultConsumer))
    }

    private fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }
}
