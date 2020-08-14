package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object{
        private const val TAG = "MyFCMService"
    }

    //TODO: Step 3.2 log registration token
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG,"From : ${remoteMessage?.from}")

        // TODO: Step 3.5 check messages for data
        // Check if message contains a data payload
        remoteMessage?.data?.let{
            Log.d(TAG,"Message data payload : " +remoteMessage.data)
        }

        // TODO: Step 3.6 check messages for notification and call sendNotification
        // Check if message contains a notification payload.
        //fungsi dari baris kode ini adalah untuk mengirimkan notifikasi ketika dalam foreground dan background,bukan hanya pada background
        remoteMessage?.notification?.let{
            Log.d(TAG,"Message Notification Body : ${it.body}")
            sendNotification(it.body!!)
        }

    }
    //TODO: Step 3.7 Create sendNotification() method to show a simple the received message
    private fun sendNotification(messageBody: String) {
        val notificationManager = ContextCompat.getSystemService(applicationContext,NotificationManager::class.java)
        notificationManager?.sendNotification(messageBody,applicationContext)
    }

    override fun onNewToken(token: String?) {
        Log.d(TAG,"Refreshed token : $token")
        sendRegistrationTokenToServer(token)

    }


    private fun sendRegistrationTokenToServer(token: String?) {
        //TODO: Implement this method to send token to your app server
        Log.d(TAG,"sendRegistrationToken value : $token")
    }


}