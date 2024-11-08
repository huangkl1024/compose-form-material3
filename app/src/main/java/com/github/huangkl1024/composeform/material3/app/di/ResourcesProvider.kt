package com.github.huangkl1024.composeform.material3.app.di

import android.content.Context
import androidx.annotation.StringRes

class ResourcesProvider(private val context: Context
) {
    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }
}