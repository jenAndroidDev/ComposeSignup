package com.example.composesignup

fun tag(clazz: Class<*>): String {
    val simpleName = clazz.simpleName
    return simpleName.substring(0, simpleName.length.coerceAtMost(23))
}