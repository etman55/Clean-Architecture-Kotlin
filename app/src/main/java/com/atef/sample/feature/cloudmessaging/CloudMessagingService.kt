package com.atef.sample.feature.cloudmessaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.atef.sample.R
import com.atef.sample.base.state.Resource
import com.atef.sample.feature.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import timber.log.Timber

@AndroidEntryPoint
class CloudMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var statusManager: StatusManager
    //
    // @Inject
    // lateinit var updateTokenCM: UpdateTokenCM

    companion object {
        fun getToken() {
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Timber.w(task.exception, "getInstanceId failed")
                        return@OnCompleteListener
                    }
                    // Get new Instance ID token
                    val token = task.result?.token
                    Timber.i("Current token: $token")
                })
        }

        fun getToken(onCompleteListener: OnCompleteListener<InstanceIdResult>) {
            FirebaseInstanceId.getInstance()
                .instanceId
                .addOnCompleteListener(onCompleteListener)
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // log the getting message from firebase
        Timber.d("From: %s", remoteMessage.from)
        //  if remote message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Timber.d("Message data payload: %s", remoteMessage.data)
            val data = remoteMessage.data
            handleNow(data)
        }
        // if message contains a notification payload.
        remoteMessage.notification?.let {
            Timber.d("Message Notification Body: %s", remoteMessage.notification!!.body)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    /**
     * Persist token on third-party servers using your Retrofit APIs client.
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String) {
        // make a own server request here using your http client
        // updateTokenCM.execute(UpdateTokenCM.Params(token))
    }

    private fun handleNow(data: Map<String, String>) {
        if (data.containsKey("title") && data.containsKey("message"))
            sendNotification(data["title"], data["message"])
        if (data.containsKey("app_status")) {
            Timber.i("app_status payload: ${data["app_status"]}")
            val status =
                CaseStatus.readStatus(data["app_status"])
            statusManager.updateStatus(
                Resource.success(
                    status
                ), true
            )
        }
    }

    /**
     * Create and show notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private fun sendNotification(title: String?, messageBody: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0 /* Request code */,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // Message Services handle notification
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setSmallIcon(R.mipmap.ic_launcher)
            //
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
        val manager = NotificationManagerCompat.from(applicationContext)
        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Channel human readable title
            val channel = NotificationChannel(
                channelId,
                "Cloud Messaging Service",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }
        manager.notify(0 /* ID of notification */, notificationBuilder.build())
    }
}
