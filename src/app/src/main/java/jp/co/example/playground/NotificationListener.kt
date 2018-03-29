package jp.co.example.playground

import android.content.Context
import android.service.notification.NotificationListenerService
import android.support.v4.app.NotificationManagerCompat


class NotificationListener : NotificationListenerService() {
	companion object {
		fun isEnabled(context: Context): Boolean {
			return NotificationManagerCompat
					.getEnabledListenerPackages(context)
					.contains(context.packageName)
		}
	}
}