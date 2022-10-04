package com.lamnt.android_unity_plugin

import android.app.ActivityManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!isServiceRunning(ForegroundService::class.java.name)) {
            val intentService = Intent(this, ForegroundService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intentService)
            }else{
                startService(intentService)
            }
        }
    }
    @Suppress("DEPRECATION")
    private fun isServiceRunning(serviceName: String): Boolean {
        var serviceRunning = false
        val am = this.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val l = am.getRunningServices(50)
        val i: Iterator<ActivityManager.RunningServiceInfo> = l.iterator()
        while (i.hasNext()) {
            val runningServiceInfo = i
                .next()
            if (runningServiceInfo.service.className == serviceName) {
                serviceRunning = true
            }
        }
        return serviceRunning
    }
}