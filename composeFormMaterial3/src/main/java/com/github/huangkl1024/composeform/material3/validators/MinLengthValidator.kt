package com.github.huangkl1024.composeform.material3.validators

import com.github.huangkl1024.composeform.material3.Validator

class MinLengthValidator(minLength: Int, errorText: String? = null) : Validator<String?>(
    validate = {
        (it?.length ?: -1) >= minLength
    },
    errorText = errorText ?: "This field is too short"
)