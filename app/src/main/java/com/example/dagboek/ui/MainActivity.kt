package com.example.dagboek.ui

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.dagboek.ui.add.AddFragment
import com.example.dagboek.R
import com.example.dagboek.databinding.ActivityMainBinding
import com.example.dagboek.ui.detail.DetailFragment
import com.example.dagboek.ui.list.ListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("onCreate", "onCreate called")
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)



        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            // Success! There's a magnetometer.
            println("Success! There's a magnetometer.")
        } else {
            // Failure! No magnetometer.
            println("Failure! No magnetometer.")
        }


        val listFragment = ListFragment.newInstance()
        // supportFragmentManager is within the context class
        // so u can add fragments to the activity
        supportFragmentManager.beginTransaction()
                .add(R.id.activeFragment, listFragment)
                .commit()
    }

    // go to new item view
    fun itemView() {
        val newItemFragment = AddFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.activeFragment, newItemFragment)
                .addToBackStack(null)
                .commit()
    }

    // show details item
    @SuppressLint("ApplySharedPref")
    fun showDetail(ItemId: Int) {
        val detailFragment = DetailFragment.newInstance(ItemId)
        supportFragmentManager.beginTransaction()
                .replace(R.id.activeFragment, detailFragment)
                .addToBackStack(null)
                .commit()
    }

}