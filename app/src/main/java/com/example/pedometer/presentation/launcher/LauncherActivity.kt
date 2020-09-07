package com.example.pedometer.presentation.launcher

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.pedometer.R
import com.example.pedometer.databinding.ActivityLauncherBinding
import com.example.pedometer.domain.factory.provideActivityViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LauncherActivity : DaggerAppCompatActivity(), SensorEventListener {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    private lateinit var activityLauncherBinding: ActivityLauncherBinding
    private lateinit var launcherViewModel: LauncherViewModel
    private lateinit var sensorManager: SensorManager
    private lateinit var mGravity: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLauncherBinding = DataBindingUtil.setContentView(this, R.layout.activity_launcher)
        launcherViewModel = provideActivityViewModelProvider(viewModelProvider)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        //gravity sensor
        mGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        //If sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        //If there is a new sensor data
        activityLauncherBinding.tvStepsCount.text = "${event.values[0]}"
    }

    //register
    override fun onResume() {
        super.onResume()
        mGravity.also { gravity ->
            sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    //unregister
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}