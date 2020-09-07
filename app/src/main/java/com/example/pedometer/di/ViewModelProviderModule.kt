package com.example.pedometer.di

import androidx.lifecycle.ViewModelProvider
import com.example.pedometer.domain.factory.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelProviderModule {

    @Binds
    internal abstract fun bindViewModelProviderFactory(viewModelProviderFactory: ViewModelProviderFactory):ViewModelProvider.Factory
}