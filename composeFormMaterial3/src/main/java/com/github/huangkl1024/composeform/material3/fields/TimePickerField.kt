package com.github.huangkl1024.composeform.material3.fields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.huangkl1024.composeform.material3.Field
import com.github.huangkl1024.composeform.material3.FieldState
import com.github.huangkl1024.composeform.material3.Form
import com.github.huangkl1024.composeform.material3.components.TextFieldComponent
import kotlinx.datetime.LocalTime

class TimePickerField(
    label: String,
    form: Form,
    fieldState: FieldState<LocalTime?>,
    modifier: Modifier? = Modifier,
    isEnabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    formatter: ((raw: LocalTime?) -> String)? = null,
    changed: ((v: LocalTime?) -> Unit)? = null,
    private val is24Hour: Boolean = true,
    private val confirmButtonText: String = "OK",
    private val dismissButtonText: String = "Cancel"
) : Field<LocalTime>(
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

        var showTimePickerDialog by remember { mutableStateOf(false) }
        if (showTimePickerDialog) {
            val initHour = value.value?.hour ?: 0
            val initialMinute = value.value?.minute ?: 0
            val timePickerState = rememberTimePickerState(initHour, initialMinute, is24Hour)
            TimePickerDialog(
                onDismissRequest = {
                    showTimePickerDialog = false
                    focusManager.clearFocus()
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showTimePickerDialog = false
                            focusManager.clearFocus()
                            val selectedTime =
                                LocalTime(timePickerState.hour, timePickerState.minute)
                            value.value = selectedTime
                            this.onChange(selectedTime, form)
                        },
                    ) {
                        Text(confirmButtonText)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showTimePickerDialog = false
                            focusManager.clearFocus()
                        }
                    ) {
                        Text(dismissButtonText)
                    }
                },
                content = {
                    TimePicker(state = timePickerState)
                }
            )
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
                    showTimePickerDialog = true
                }
            },
            trailingIcon = trailingIcon
        )
    }
}

@Composable
fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier =
            modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton()
                    confirmButton()
                }
            }
        }
    }
}
