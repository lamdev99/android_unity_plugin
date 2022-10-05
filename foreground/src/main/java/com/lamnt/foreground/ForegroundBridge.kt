package com.lamnt.foreground

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Intent
import android.os.Build

class ForegroundBridge : Application() {
    companion object{
        fun startForegroundService(activity: Activity){
            if (!isServiceRunning(ForegroundService::class.java.name, activity)) {
                val intentService = Intent(activity, ForegroundService::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    activity.startForegroundService(intentService)
                }else{
                    activity.startService(intentService)
                }
            }
        }
        @Suppress("DEPRECATION")
        private fun isServiceRunning(serviceName: String, activity: Activity): Boolean {
            var serviceRunning = false
            val am = activity.getSystemService(ACTIVITY_SERVICE) as ActivityManager
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
}