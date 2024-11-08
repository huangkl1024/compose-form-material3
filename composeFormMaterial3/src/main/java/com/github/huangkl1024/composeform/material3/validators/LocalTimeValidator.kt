package com.github.huangkl1024.composeform.material3.validators

import com.github.huangkl1024.composeform.material3.Validator
import kotlinx.datetime.LocalTime

class LocalTimeValidator(minTime: () -> LocalTime, errorText: String? = null) :
    Validator<LocalTime?>(
        validate = {
            it == null || it >= minTime()
        },
        errorText = errorText ?: "This field is not valid."
    )