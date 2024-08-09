package com.kudos.studenthelpcare.core.domain
fun interface Mapper<in From, out To> {
    fun map(from: From): To
}