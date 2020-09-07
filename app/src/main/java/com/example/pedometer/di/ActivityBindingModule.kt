package com.example.pedometer.di

import com.example.pedometer.presentation.launcher.LauncherActivity
import com.example.pedometer.presentation.launcher.LauncherModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            LauncherModule::class
        ]
    )
    abstract fun contributeLauncherActivity(): LauncherActivity
}