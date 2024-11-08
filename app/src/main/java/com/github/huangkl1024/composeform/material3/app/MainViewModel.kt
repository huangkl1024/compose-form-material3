package com.github.huangkl1024.composeform.material3.app

import android.util.Log
import androidx.lifecycle.ViewModel
import com.github.huangkl1024.composeform.material3.app.di.ResourcesProvider
import com.github.huangkl1024.composeform.material3.validators.NotEmptyValidator

class MainViewModel(
    resourcesProvider: ResourcesProvider
): ViewModel() {
    var form = MainForm(resourcesProvider)

    fun validate() {
        form.validate(true)
        form.logRawValue()
        Log.d("MainViewModel", "Submit (form is valid: ${form.isValid})")
    }

    fun doSomething() {
        form.name.validators.removeIf { it::class == NotEmptyValidator::class }
        form.name.state.value = "Benji"
    }
}
