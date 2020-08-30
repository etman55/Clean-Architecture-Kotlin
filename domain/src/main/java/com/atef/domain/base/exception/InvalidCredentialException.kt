package com.atef.domain.base.exception

import java.lang.Exception

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
class InvalidCredentialException(errorMessage: String = "Invalid credentials") :
    Exception(errorMessage)
