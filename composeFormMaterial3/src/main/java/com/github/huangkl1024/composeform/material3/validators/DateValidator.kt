package com.github.huangkl1024.composeform.material3.validators

import com.github.huangkl1024.composeform.material3.Validator
import java.util.*

class DateValidator(minDateTime: () -> Long, errorText: String? = null) : Validator<Date?>(
    validate = {
        (it?.time ?: -1) >= minDateTime()
    },
    errorText = errorText ?: "This field is not valid."
)