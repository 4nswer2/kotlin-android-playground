package jp.co.example.playground

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationManagerCompat
import timber.log.Timber
import android.app.Application as BaseApplication

class Application : BaseApplication() {
	companion object {
		const val NOTIFICATION_CHANNEL_GENERAL = "general"
	}

	private val manager by lazy { getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager }

	override fun onCreate() {
		super.onCreate()

		if (BuildConfig.DEBUG) { Timber.plant(Timber.DebugTree()) }

		if (moreThanApiLevel(Build.VERSION_CODES.O)) {
			manager.createNotificationChannel(NotificationChannel(NOTIFICATION_CHANNEL_GENERAL, "General Notifications", NotificationManager.IMPORTANCE_DEFAULT))
		}

		Service.start(this)
	}
}