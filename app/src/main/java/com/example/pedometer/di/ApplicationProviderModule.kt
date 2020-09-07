package com.example.pedometer.di

import android.content.Context
import com.example.pedometer.BaseApplication
import dagger.Module
import dagger.Provides

@Module
object ApplicationProviderModule {

    /**
     * Application context
     * */
    @Provides
    fun provideApplicationContext(application: BaseApplication): Context {
        return application.applicationContext
    }
}