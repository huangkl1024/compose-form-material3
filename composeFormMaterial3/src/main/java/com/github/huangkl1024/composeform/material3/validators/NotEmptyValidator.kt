package com.github.huangkl1024.composeform.material3.validators

import com.github.huangkl1024.composeform.material3.Validator

class NotEmptyValidator<T>(errorText: String? = null) : Validator<T>(
    validate = {
        it != null
    },
    errorText = errorText ?: "This field should not be empty"
)