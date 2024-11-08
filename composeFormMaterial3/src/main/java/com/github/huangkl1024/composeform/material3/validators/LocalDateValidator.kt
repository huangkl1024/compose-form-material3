package com.github.huangkl1024.composeform.material3.validators

import com.github.huangkl1024.composeform.material3.Validator
import kotlinx.datetime.LocalDate

class LocalDateValidator(minDate: () -> LocalDate, errorText: String? = null) :
    Validator<LocalDate?>(
        validate = {
            it == null || it >= minDate()
        },
        errorText = errorText ?: "This field is not valid."
    )