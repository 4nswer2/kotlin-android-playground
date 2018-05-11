package jp.co.example.playground.extensions

import android.app.Notification
import android.content.Context
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import jp.co.example.playground.R


fun Context.makeNotification(title: CharSequence, text: CharSequence, channel: String): Notification {
	return NotificationCompat.Builder(this, channel)
			.setSmallIcon(R.mipmap.ic_launcher)
			.setContentTitle(title)
			.setContentText(text)
			.build()
}

fun Context.notify(id: Int, title: CharSequence, text: CharSequence, channel: String) {
	val manager = NotificationManagerCompat.from(this)
	val notification = makeNotification(title, text, channel)
	manager.notify(id, notification)
}