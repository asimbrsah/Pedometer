package com.example.pedometer.presentation.sensor

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.pedometer.R
import com.example.pedometer.databinding.ActivitySensorBinding
import com.example.pedometer.domain.factory.provideActivityViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SensorActivity : DaggerAppCompatActivity(), SensorEventListener {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    private lateinit var activitySensorBinding: ActivitySensorBinding
    private lateinit var sensorViewModel: SensorViewModel
    private lateinit var sensorManager: SensorManager
    private lateinit var stepCounter: Sensor
    private var stepCounts = ArrayList<String>()
    private lateinit var stepsAdapter: StepsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySensorBinding = DataBindingUtil.setContentView(this, R.layout.activity_sensor)
        sensorViewModel = provideActivityViewModelProvider(viewModelProvider)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) == null) {
            Toast.makeText(this, "sensor not found", Toast.LENGTH_SHORT).show()
        } else {
            stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACTIVITY_RECOGNITION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            //ask for permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                requestPermissions(arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), 1)
            }
        }

        stepsAdapter = StepsAdapter()
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        //If sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        //If there is a new sensor data
        if (event.sensor == stepCounter) {
            stepCounts.add(event.values[0].toInt().toString())
        }
        if (stepCounts.isNotEmpty()) {
            stepsAdapter.submitList(stepCounts)
            activitySensorBinding.rvSteps.adapter = stepsAdapter
        }
    }

    //register
    override fun onResume() {
        super.onResume()
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) == null) {
            Toast.makeText(this, "sensor not found", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    //unregister
    override fun onPause() {
        super.onPause()
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) == null) {
            Toast.makeText(this, "sensor not found", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager.unregisterListener(this)
        }
    }
}