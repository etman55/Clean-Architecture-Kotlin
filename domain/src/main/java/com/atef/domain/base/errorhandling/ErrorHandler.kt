package com.atef.domain.base.errorhandling

/**
 * @author Atef Etman
 * @email etman850@gmail.com
 * @mobile +201090705106
 *
 * helper interface according to inversion of control propagating errors from data layer
 * to presentation layer. So the implementation of this interface should be in data layer as
 * According to Clean Architecture, domain layer contains enterprise business rules and
 * application business rules, so errors can be something that belongs to application
 * business rules.
 *
 * [https://proandroiddev.com/android-error-handling-in-clean-architecture-844a7fc0dc03]
 */

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}
