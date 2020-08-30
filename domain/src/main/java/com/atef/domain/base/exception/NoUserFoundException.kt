package com.atef.domain.base.exception

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
class NoUserFoundException(errorMessage: String = "No Logged in Users found ") :
    Exception(errorMessage)
