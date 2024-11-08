package com.github.huangkl1024.composeform.material3.app

import androidx.compose.runtime.mutableStateOf
import com.edorex.mobile.composeForm.models.Country
import com.github.huangkl1024.composeform.material3.FieldState
import com.github.huangkl1024.composeform.material3.Form
import com.github.huangkl1024.composeform.material3.FormField
import com.github.huangkl1024.composeform.material3.app.di.ResourcesProvider
import com.github.huangkl1024.composeform.material3.validators.DateValidator
import com.github.huangkl1024.composeform.material3.validators.EmailValidator
import com.github.huangkl1024.composeform.material3.validators.IsEqualValidator
import com.github.huangkl1024.composeform.material3.validators.LocalDateValidator
import com.github.huangkl1024.composeform.material3.validators.LocalTimeValidator
import com.github.huangkl1024.composeform.material3.validators.MinLengthValidator
import com.github.huangkl1024.composeform.material3.validators.NotEmptyValidator
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import java.util.Date

class MainForm(resourcesProvider: ResourcesProvider) : Form() {
    override fun self(): Form {
        return this
    }

    @FormField
    val name = FieldState(
        state = mutableStateOf(null),
        validators = mutableListOf(
            NotEmptyValidator(),
            MinLengthValidator(
                minLength = 3,
                errorText = resourcesProvider.getString(R.string.error_min_length)
            )
        )
    )

    @FormField
    val lastName = FieldState(
        state = mutableStateOf<String?>(null)
    )

    @FormField
    val password = FieldState(
        state = mutableStateOf(null),
        validators = mutableListOf(
            NotEmptyValidator(),
            MinLengthValidator(
                minLength = 8,
                errorText = resourcesProvider.getString(R.string.error_min_length)
            )
        )
    )

    @FormField
    val passwordConfirm = FieldState(
        state = mutableStateOf(null),
        isVisible = { password.state.value != null && password.state.value!!.isNotEmpty() },
        validators = mutableListOf(
            IsEqualValidator({ password.state.value })
        )
    )

    @FormField
    val email = FieldState(
        state = mutableStateOf(null),
        validators = mutableListOf(
            EmailValidator()
        )
    )

    @FormField
    val country = FieldState(
        state = mutableStateOf(null),
        options = mutableListOf(
            Country(code = "CH", name = "Switzerland"),
            Country(code = "DE", name = "Germany"),
            Country(code = "FR", name = "France"),
            Country(code = "US", name = "United States"),
            Country(code = "ES", name = "Spain"),
            Country(code = "BR", name = "Brazil"),
            Country(code = "CN", name = "China"),
        ),
        optionItemFormatter = { "${it?.name} (${it?.code})" },
        validators = mutableListOf(
            NotEmptyValidator()
        )
    )

    @FormField
    val countryNotSearchable = FieldState(
        state = mutableStateOf(null),
        options = mutableListOf(
            null,
            Country(code = "CH", name = "Switzerland"),
            Country(code = "DE", name = "Germany")
        )
    ) {
        if (it != null) {
            "${it.name} (${it.code})"
        } else {
            "All"
        }
    }

    @FormField
    val startDate = FieldState(
        state = mutableStateOf<Date?>(null),
        validators = mutableListOf(
            NotEmptyValidator()
        )
    )

    @FormField
    val endDate = FieldState(
        state = mutableStateOf(null),
        validators = mutableListOf(
            NotEmptyValidator(),
            DateValidator(
                minDateTime = { startDate.state.value?.time ?: 0 },
                errorText = resourcesProvider.getString(R.string.error_date_after_start_date)
            )
        )
    )

    @FormField
    val startDate1 = FieldState(
        state = mutableStateOf<LocalDate?>(null),
        validators = mutableListOf(
            NotEmptyValidator()
        )
    )

    @FormField
    val endDate1 = FieldState(
        state = mutableStateOf(null),
        validators = mutableListOf(
            NotEmptyValidator(),
            LocalDateValidator(
                minDate = { startDate1.state.value ?: LocalDate(1970, 1, 1) },
                errorText = resourcesProvider.getString(R.string.error_date_after_start_date)
            )
        )
    )

    @FormField
    val startTime = FieldState(
        state = mutableStateOf<LocalTime?>(null),
        validators = mutableListOf(
            NotEmptyValidator()
        )
    )

    @FormField
    val endTime = FieldState(
        state = mutableStateOf(null),
        validators = mutableListOf(
            NotEmptyValidator(),
            LocalTimeValidator(
                minTime = { startTime.state.value ?: LocalTime(0, 0) },
                errorText = resourcesProvider.getString(R.string.error_date_after_start_date)
            )
        )
    )


    @FormField
    val agreeWithTerms = FieldState(
        state = mutableStateOf(null),
        validators = mutableListOf(
            IsEqualValidator({ true })
        )
    )
}