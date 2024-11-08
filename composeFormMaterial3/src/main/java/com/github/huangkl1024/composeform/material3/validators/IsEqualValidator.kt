package com.github.huangkl1024.composeform.material3.validators

import com.github.huangkl1024.composeform.material3.Validator

class IsEqualValidator<T>(expectedValue: () -> T, errorText: String? = null) : Validator<T>(
    validate = {
        it == expectedValue()
    },
    errorText = errorText ?: "This field's value is not as expected."
)