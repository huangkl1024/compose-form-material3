package com.github.huangkl1024.composeform.material3.app.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.github.huangkl1024.composeform.material3.app.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        ResourcesProvider(androidContext())
    }
    viewModel {
        MainViewModel(get())
    }
}

@RequiresApi(Build.VERSION_CODES.O)
val appModules = listOf(
    appModule
)