package jp.co.example.playground

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import jp.co.example.playground.extensions.notify


class BootCompletedReceiver : BroadcastReceiver() {

	override fun onReceive(context: Context?, intent: Intent?) {
		context ?: return kotlin.run{ d("context is null") }
		context.notify(2, "receive", "ACTION_BOOT_COMPLETED", Application.NOTIFICATION_CHANNEL_GENERAL)
		if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
			context.let { Service.start(it) }
		}
	}
}