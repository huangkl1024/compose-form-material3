package com.github.huangkl1024.composeform.material3.fields

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import com.github.huangkl1024.composeform.material3.Field
import com.github.huangkl1024.composeform.material3.FieldState
import com.github.huangkl1024.composeform.material3.Form
import com.github.huangkl1024.composeform.material3.components.TextFieldComponent
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class DatePickerField(
    label: String,
    form: Form,
    fieldState: FieldState<LocalDate?>,
    modifier: Modifier? = Modifier,
    isEnabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    formatter: ((raw: LocalDate?) -> String)? = null,
    changed: ((v: LocalDate?) -> Unit)? = null
) : Field<LocalDate>(
    label = label,
    form = form,
    fieldState = fieldState,
    isEnabled = isEnabled,
    modifier = modifier,
    imeAction = imeAction,
    formatter = formatter,
    changed = changed
) {

    /**
     * Returns a composable representing the DateField / Picker for this field
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Field() {
        this.updateComposableValue()
        if (!fieldState.isVisible()) {
            return
        }

        val focusRequester = FocusRequester()
        val focusManager = LocalFocusManager.current
        var showDatePickerDialog by remember { mutableStateOf(false) }
        if (showDatePickerDialog) {
            val date = value.value
            val initialSelectedDateMillis = remember(date) {
                if (date != null) {
                    val dateTime = LocalDateTime(date.year, date.month, date.dayOfMonth, 0, 0, 0)
                    dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
                } else {
                    null
                }
            }
            val datePickerState = rememberDatePickerState(initialSelectedDateMillis)
            val confirmEnabled = remember {
                derivedStateOf { datePickerState.selectedDateMillis != null }
            }
            DatePickerDialog(
                onDismissRequest = {
                    showDatePickerDialog = false
                    focusManager.clearFocus()
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDatePickerDialog = false
                            focusManager.clearFocus()
                            val selectedDate =
                                Instant.fromEpochMilliseconds(datePickerState.selectedDateMillis!!)
                                    .toLocalDateTime(TimeZone.currentSystemDefault()).date
                            value.value = selectedDate
                            this.onChange(selectedDate, form)
                        },
                        enabled = confirmEnabled.value
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDatePickerDialog = false
                            focusManager.clearFocus()
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        var trailingIcon: @Composable (() -> Unit)? = null
        if (value.value != null) {
            trailingIcon = {
                IconButton({ this.onChange(null, form) }) {
                    Icon(Icons.Outlined.Cancel, contentDescription = null)
                }
            }
        }
        TextFieldComponent(
            modifier = modifier ?: Modifier,
            isEnabled = isEnabled,
            label = label,
            text = formatter?.invoke(value.value) ?: value.value.toString(),
            hasError = fieldState.hasError(),
            errorText = fieldState.errorText,
            isReadOnly = true,
            focusRequester = focusRequester,
            focusChanged = {
                if (it.isFocused) {
                    showDatePickerDialog = true
                }
            },
            trailingIcon = trailingIcon
        )
    }
}
