package com.github.huangkl1024.composeform.material3

abstract class Validator<T>(
    val validate: (s: T?) -> Boolean,
    val errorText: String
)
