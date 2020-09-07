package com.example.pedometer.presentation.launcher

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
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
    var running = false
    private var sensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLauncherBinding = DataBindingUtil.setContentView(this, R.layout.activity_launcher)
        launcherViewModel = provideActivityViewModelProvider(viewModelProvider)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

    }

    override fun onResume() {
        super.onResume()
        running = true
        val stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if (stepsSensor == null) {
            Toast.makeText(this, "sensor not found", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(event: SensorEvent) {
        if (running) {
            activityLauncherBinding.tvSteps.text = "${event.values[0]}"
        }
    }
}