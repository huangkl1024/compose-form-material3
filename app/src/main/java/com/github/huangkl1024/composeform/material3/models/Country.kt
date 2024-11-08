package com.edorex.mobile.composeForm.models

import com.github.huangkl1024.composeform.material3.fields.PickerValue

data class Country(
    val code: String,
    val name: String
): PickerValue() {
    override fun searchFilter(query: String): Boolean {
        return this.name.startsWith(query)
    }
}
