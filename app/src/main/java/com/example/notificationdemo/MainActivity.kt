package com.example.notificationdemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ClipDescription
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    private val channelID="com.example.notificationdemo.channel1"
    private var notificationManager:NotificationManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID,"DemoChannel", "this is a demo")
        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            displayNotification()
        }
    }
    private fun displayNotification(){
        var notifcationId=45
        val notification = NotificationCompat.Builder(this@MainActivity, channelID)
            .setContentTitle("Demo Title")
            .setContentText("this is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager?.notify(notifcationId, notification)
    }
    private fun createNotificationChannel(id:String, name:String, channelDescription:String){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            var importance:Int = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id,name,importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }
}