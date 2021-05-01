package com.cse.medscape.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.cse.medscape.R
import com.cse.medscape.activities.MyDiagnosisActivity

class ReminderBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val channelId = "reminderChannel"
        Log.d("reminder", "onReceive: braodcast")
        if (intent != null) {
            val title = intent.extras?.getString("name")
            val main = Intent(context, MyDiagnosisActivity::class.java)

            val m2 = PendingIntent.getActivity(
                context, 0,
                main, 0
            )

            val builder = NotificationCompat.Builder(context!!, channelId)
                .setContentTitle("Remedy Reminder")
                .setContentText(title)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(m2)
                .setPriority(NotificationCompat.PRIORITY_MAX)

            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    "Todo Reminder",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }

            notificationManager.notify(0, builder.build())
        }
    }
}