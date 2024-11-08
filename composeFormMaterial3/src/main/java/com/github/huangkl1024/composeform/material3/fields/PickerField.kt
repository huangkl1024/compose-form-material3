package com.github.huangkl1024.composeform.material3.fields

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.github.huangkl1024.composeform.material3.Field
import com.github.huangkl1024.composeform.material3.FieldState
import com.github.huangkl1024.composeform.material3.Form
import com.github.huangkl1024.composeform.material3.components.SingleSelectDialogComponent
import com.github.huangkl1024.composeform.material3.components.TextFieldComponent

abstract class PickerValue {
    abstract fun searchFilter(query: String): Boolean
}

class PickerField<T: PickerValue>(
    label: String,
    form: Form,
    modifier: Modifier? = Modifier,
    fieldState: FieldState<T?>,
    isEnabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    formatter: ((raw: T?) -> String)? = null,
    private val isSearchable: Boolean = true,
    changed: ((v: T?) -> Unit)? = null
) : Field<T>(
    label = label,
    form = form,
    fieldState = fieldState,
    isEnabled = isEnabled,
    modifier = modifier,
    imeAction = imeAction,
    formatter = formatter,
    changed = changed
) {

    @Composable
    override fun Field() {
        this.updateComposableValue()
        if (!fieldState.isVisible()) {
            return
        }

        var isDialogVisible by remember { mutableStateOf(false) }
        val focusRequester = FocusRequester()
        val focusManager = LocalFocusManager.current

        TextFieldComponent(
            modifier = modifier ?: Modifier,
            isEnabled = isEnabled,
            label = label,
            text = fieldState.selectedOptionText() ?: "",
            hasError = fieldState.hasError(),
            errorText = fieldState.errorText,
            isReadOnly = true,
            trailingIcon = {
                Icon(
                    if (isDialogVisible) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    null
                )
            },
            focusRequester = focusRequester,
            focusChanged = {
                isDialogVisible = it.isFocused
            }
        )

        if (isDialogVisible) {
            SingleSelectDialogComponent(
                title = label,
                optionsList = fieldState.options!!,
                optionItemFormatter = fieldState.optionItemFormatter,
                defaultSelected = fieldState.state.value,
                submitButtonText = stringResource(id = android.R.string.ok),
                onSubmitButtonClick = {
                    isDialogVisible = false
                    this.onChange(it, form)
                    focusManager.clearFocus()
                },
                onDismissRequest = {
                    isDialogVisible = false
                    focusManager.clearFocus()
                },
                search = if (isSearchable) {
                    { options, query ->
                        options.filter { c -> c?.searchFilter(query) == true }
                    }
                } else {
                    null
                }
            )
        }
    }

}
