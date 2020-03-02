package com.example.thpun.networktesting

import android.app.*
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import java.util.*
import kotlin.concurrent.fixedRateTimer

class service : Service() {
    var timer = Timer()

    companion object {
        const val CHANNEL_ID = "ForegroundNetworkServiceChannel"
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            "Foreground Network Service Channel",
            NotificationManager.IMPORTANCE_LOW
        );
        val manager: NotificationManager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel();
        val pendingIntent: PendingIntent = Intent(this, MainActivity::class.java)
            .let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, 0)
            }

        val notification: Notification = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("Testing Title")
            .setContentText("Testing message")
            .setSmallIcon(R.drawable.notification_template_icon_bg)
            .setContentIntent(pendingIntent)
            .build()


        startForeground(1, notification)

        timer = fixedRateTimer("heartbeat", initialDelay = 0, period = 15000) {
            CallAPI().execute()
        }

        Toast.makeText(this, "Service started by user", Toast.LENGTH_LONG).show()
//        return super.onStartCommand(intent, flags, startId)
        return START_NOT_STICKY;
    }


    override fun onDestroy() {
        super.onDestroy();
        timer.cancel()
        Toast.makeText(this, "Service destroyed by user", Toast.LENGTH_LONG).show()
        stopForeground(true)
    }
}
