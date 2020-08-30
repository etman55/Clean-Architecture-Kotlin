package com.atef.domain.base.exception

import java.lang.RuntimeException

/**
 * @author Atef Etman.
 * @email etman850@gmail.com.
 * @phone +201090705106.
 */
class InvalidCodeException(errorMessage: String = "Invalid verification code") :
    RuntimeException(errorMessage)
