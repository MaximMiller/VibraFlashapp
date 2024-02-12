package com.example.vibraflashapp

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.Button
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {
    private lateinit var flashBtn: Button
    private lateinit var cameraManager: CameraManager
    private lateinit var cameraId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            cameraId = cameraManager.cameraIdList[0]
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
        flashBtn = findViewById(/* id = */ R.id.flashBtn)
        flashBtn.setOnClickListener() {
            switchFlashLight()
            vibratePhone()
        }
    }

    private fun switchFlashLight() {
        try {
            cameraManager.setTorchMode(cameraId, true)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }

    }

    @SuppressLint("ServiceCast")
    private fun vibratePhone() {
        val vibroManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as Vibrator

        try {
            vibroManager.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        }
        catch (e:Exception){
            e.printStackTrace()
        }

    }
}


