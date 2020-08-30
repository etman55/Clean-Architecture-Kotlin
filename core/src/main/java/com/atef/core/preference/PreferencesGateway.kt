package com.atef.core.preference

import android.content.SharedPreferences
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */

class PreferencesGateway @Inject constructor(val prefs: SharedPreferences) {


    inline fun <reified T : Any> save(key: String, value: T): Single<T> {
        return Single.fromCallable {
            prefs
                .edit()
                .apply { putValue(key, value) }
                .apply()
            value
        }
    }

    @Throws(ClassCastException::class)
    inline fun <reified T : Any> load(key: String, defaultValue: T): Single<T> {
        return Single.fromCallable {
            prefs.run { getValue(key, defaultValue) }
        }
    }

    fun isSaved(key: String): Single<Boolean> {
        return Single.fromCallable {
            prefs.contains(key)
        }
    }

    fun remove(key: String): Completable {
        return Completable.fromAction {
            prefs
                .edit()
                .remove(key)
                .apply()
        }
    }

    companion object {
        const val DEFAULT_VALUE = ""
    }
}

inline fun <reified T : Any> Any.fromObjectToString(targetClass: Class<T>): String {
    return Gson().toJson(this, targetClass)
}

fun String.fromStringToObject(targetClass: Class<*>): Any? {
    return Gson().fromJson(this, targetClass)
}

inline fun <reified T : Any> SharedPreferences.Editor.putValue(
    key: String,
    value: T
) {
    when (T::class) {
        Boolean::class -> putBoolean(key, value as Boolean)
        Int::class -> putInt(key, value as Int)
        Long::class -> putLong(key, value as Long)
        Float::class -> putFloat(key, value as Float)
        String::class -> putString(key, value as String)
        else -> throw UnsupportedOperationException("not supported preferences type")
    }
}

inline fun <reified T : Any> SharedPreferences.getValue(
    key: String,
    defaultValue: T
): T {
    return when (T::class) {
        Boolean::class -> getBoolean(key, defaultValue as Boolean) as T
        Int::class -> getInt(key, defaultValue as Int) as T
        Long::class -> getLong(key, defaultValue as Long) as T
        Float::class -> getFloat(key, defaultValue as Float) as T
        String::class -> getString(key, defaultValue as String) as T
        else -> throw UnsupportedOperationException("not supported preferences type")
    }
}
