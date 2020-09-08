package com.example.pedometer.di

import com.example.pedometer.presentation.sensor.SensorActivity
import com.example.pedometer.presentation.sensor.SensorModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            SensorModule::class
        ]
    )
    abstract fun contributeLauncherActivity(): SensorActivity
}