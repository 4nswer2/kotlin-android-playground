package jp.co.example.playground

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import jp.co.example.playground.extensions.makeNotification
import org.joda.time.format.ISODateTimeFormat
import android.app.Service as BaseService


class Service : BaseService() {

	companion object {
		fun start(context: Context) {
			ContextCompat.startForegroundService(context, Intent(context, Service::class.java))
		}
	}
	override fun onBind(p0: Intent?): IBinder = Binder()

	override fun onCreate() {
		d("onCreate")
		super.onCreate()
	}

	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		d("onStartCommand")
		startForeground(1, makeNotification("サービス起動", ISODateTimeFormat.dateTime().print(System.currentTimeMillis()), Application.NOTIFICATION_CHANNEL_GENERAL))
		return BaseService.START_STICKY_COMPATIBILITY
	}

	override fun onDestroy() {
		d("onDestroy")
		super.onDestroy()
		startService(Intent(this, Service::class.java))
	}

}