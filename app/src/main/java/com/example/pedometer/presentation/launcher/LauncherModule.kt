package com.example.pedometer.presentation.launcher

import androidx.lifecycle.ViewModel
import com.example.pedometer.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LauncherModule {

    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel::class)
    internal abstract fun bindLauncherViewModel(launcherViewModel: LauncherViewModel): ViewModel
}