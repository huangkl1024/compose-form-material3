package com.github.huangkl1024.composeform.material3.fields

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.github.huangkl1024.composeform.material3.Field
import com.github.huangkl1024.composeform.material3.FieldState
import com.github.huangkl1024.composeform.material3.Form
import com.github.huangkl1024.composeform.material3.components.CheckboxComponent

class CheckboxField(
    label: String,
    form: Form,
    modifier: Modifier? = Modifier,
    fieldState: FieldState<Boolean?>,
    isEnabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    formatter: ((raw: Boolean?) -> String)? = null,
    changed: ((v: Boolean?) -> Unit)? = null
) : Field<Boolean>(
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
        CheckboxComponent(
            modifier = modifier ?: Modifier,
            checked = fieldState.state.value == true,
            onCheckedChange = {
                this.onChange(it, form)
            },
            label = label,
            hasError = fieldState.hasError(),
            errorText = fieldState.errorText
        )
    }
}
