package com.atef.data.remote.base.factory

import android.app.Application
import com.atef.data.data.feature.usermanagement.contract.UserManagementCache
import com.atef.data.remote.base.interceptor.ConnectivityInterceptor
import com.atef.data.remote.base.interceptor.NetworkInterceptor
import com.atef.data.remote.base.interceptor.RequestHeaders
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author Atef Etman on 10/29/19.
 * @company AFAQY CO
 * @email atef.etman@afaqy.com
 */
object RetrofitFactory {

    fun makeServiceHandler(
        baseUrl: String,
        classTarget: Class<*>,
        isDebug: Boolean,
        userManagementCache: UserManagementCache,
        application: Application
    ): Any {
        val okHttpClient = makeOkHttpClient(
            makeHttpCache(application),
            makeNetworkInterceptor(userManagementCache, application),
            makeLoggingInterceptor(isDebug),
            makeConnectivityInterceptor(application)
        )
        return makeApiHandler(
            baseUrl,
            okHttpClient,
            makeMoshi(),
            classTarget
        )
    }

    private fun makeApiHandler(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        classTarget: Class<*>
    ): Any {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(ErrorHandlingCallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(classTarget)
    }

    private fun makeOkHttpClient(
        cache: Cache,
        networkInterceptor: NetworkInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        connectivityInterceptor: ConnectivityInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(connectivityInterceptor)
            .addNetworkInterceptor(networkInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun makeMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

    private fun makeHttpCache(appContext: Application): Cache {
        val cacheDir = appContext.cacheDir
        val httpCacheSize: Long = 10 * 1024 * 1024 // 10 MB
        val httpCacheDirectory = File(cacheDir, "offlineCache")
        return Cache(httpCacheDirectory, httpCacheSize)
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    private fun makeConnectivityInterceptor(application: Application) =
        ConnectivityInterceptor(application)

    private fun makeNetworkInterceptor(
        userManagementCache: UserManagementCache,
        application: Application
    ) = NetworkInterceptor(RequestHeaders(userManagementCache, application))
}
