package com.example.thpun.networktesting

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.telecom.Call
import android.widget.Toast
import java.util.*
import kotlin.concurrent.fixedRateTimer

class service : Service() {
    var timer = Timer()

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        timer = fixedRateTimer("heartbeat", initialDelay = 0, period = 15000) {
            CallAPI().execute()
        }

        Toast.makeText(this, "Service started by user", Toast.LENGTH_LONG).show()
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onDestroy() {
        super.onDestroy();
        timer.cancel()
        Toast.makeText(this, "Service destroyed by user", Toast.LENGTH_LONG).show()
    }
}
