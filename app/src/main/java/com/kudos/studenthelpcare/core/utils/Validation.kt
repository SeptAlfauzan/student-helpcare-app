package com.kudos.studenthelpcare.core.utils

import java.util.regex.Pattern


val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)
fun String.validateEmailValid(): Boolean = EMAIL_ADDRESS_PATTERN.matcher(this).matches()