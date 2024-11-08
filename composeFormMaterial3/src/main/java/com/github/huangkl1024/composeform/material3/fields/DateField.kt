package com.github.huangkl1024.composeform.material3.fields

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import com.github.huangkl1024.composeform.material3.Field
import com.github.huangkl1024.composeform.material3.FieldState
import com.github.huangkl1024.composeform.material3.Form
import com.github.huangkl1024.composeform.material3.components.TextFieldComponent
import java.util.Calendar
import java.util.Date

class DateField(
    label: String,
    form: Form,
    modifier: Modifier? = Modifier,
    fieldState: FieldState<Date?>,
    isEnabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    formatter: ((raw: Date?) -> String)? = null,
    private val themeResId: Int = 0,
    changed: ((v: Date?) -> Unit)? = null
) : Field<Date>(
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
    @Composable
    override fun Field() {
        this.updateComposableValue()
        if (!fieldState.isVisible()) {
            return
        }

        val focusRequester = FocusRequester()
        val focusManager = LocalFocusManager.current
        val context = LocalContext.current
        val year: Int
        val month: Int
        val day: Int

        val calendar = Calendar.getInstance()
        calendar.time = value.value ?: Date()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)

        val date = remember { mutableStateOf("") }
        val datePickerDialog = DatePickerDialog(
            context,
            themeResId,
            { _: DatePicker, yyyy: Int, mm: Int, dd: Int ->
                val c = Calendar.getInstance()
                c.set(yyyy, mm, dd, 0, 0);
                val d = c.time
                date.value = d.toString()
                value.value = d
                this.onChange(d, form)
            },
            year,
            month,
            day
        )

        datePickerDialog.setOnDismissListener {
            focusManager.clearFocus()
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
                    datePickerDialog.show()
                }
            }
        )
    }
}
