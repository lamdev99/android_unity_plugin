package com.lamnt.vibration

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.*

class VibrationBridge : Application() {

    companion object{
        fun vibrateDevice(tempActivity: Activity){
            val vibration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                (tempActivity.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
            } else {
                tempActivity.getSystemService(VIBRATOR_SERVICE) as Vibrator
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                vibration.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            }else{
                vibration.vibrate(500)
            }
        }
    }
}