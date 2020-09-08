package com.example.pedometer.presentation.sensor

import androidx.lifecycle.ViewModel
import com.example.pedometer.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SensorModule {

    @Binds
    @IntoMap
    @ViewModelKey(SensorViewModel::class)
    internal abstract fun bindSensorViewModel(sensorViewModel: SensorViewModel): ViewModel
}