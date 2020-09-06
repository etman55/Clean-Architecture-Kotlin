package com.atef.data.remote.base.factory

import com.atef.data.remote.base.model.RemoteException
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import java.io.IOException
import java.lang.reflect.Type
import java.net.SocketException
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Wraps "regular" Remote errors in custom RemoteException class
 */
class ErrorHandlingCallAdapterFactory private constructor() : CallAdapter.Factory() {

    private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    companion object {
        fun create(): CallAdapter.Factory =
            ErrorHandlingCallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *> {
        val wrapped = original.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>
        return RxCallAdapterWrapper(
            retrofit,
            wrapped
        )
    }

    private class RxCallAdapterWrapper<R>(
        private val retrofit: Retrofit,
        private val wrapped: CallAdapter<R, *>
    ) : CallAdapter<R, Any> {

        override fun responseType(): Type {
            return wrapped.responseType()
        }

        override fun adapt(call: Call<R>): Any {
            val adaptedCall = wrapped.adapt(call)

            if (adaptedCall is Completable) {
                return adaptedCall.onErrorResumeNext {
                    Completable.error(convertToRemoteException(it))
                }
            }

            if (adaptedCall is Single<*>) {
                return adaptedCall.onErrorResumeNext {
                    Single.error(convertToRemoteException(it))
                }
            }

            if (adaptedCall is Observable<*>) {
                return adaptedCall.onErrorResumeNext(Function {
                    Observable.error(convertToRemoteException(it))
                })
            }

            if (adaptedCall is Flowable<*>) {
                return adaptedCall.onErrorResumeNext(Function {
                    Flowable.error(convertToRemoteException(it))
                })
            }

            if (adaptedCall is Maybe<*>) {
                return adaptedCall.onErrorResumeNext(Function {
                    Maybe.error(convertToRemoteException(it))
                })
            }

            throw RuntimeException("Observable Type not supported")
        }

        private fun convertToRemoteException(throwable: Throwable): Throwable {
            return when (throwable) {
                is HttpException -> handleHttpException(throwable)
                is IOException -> throwable
                is SocketException -> throwable
                else ->
                    // We don't know what happened. We need to simply convert to an unknown error
                    RemoteException.unexpectedError(throwable)
            }
        }

        /**
         * We had non-200 http error
         */
        private fun handleHttpException(throwable: HttpException): Throwable {
            return when (throwable.code()) {
                400 -> RemoteException.authenticationError(throwable, retrofit)
                422 -> {
                    // on out api 422's get metadata in the response. Adjust logic here based on your needs
                    RemoteException.httpErrorWithObject(throwable, retrofit)
                }
                else -> RemoteException.httpError(throwable, retrofit)
            }
        }
    }
}
