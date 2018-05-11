package jp.co.example.playground

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import jp.co.example.playground.extensions.notify


class BootCompletedReceiver : BroadcastReceiver() {
	override fun onReceive(context: Context?, intent: Intent?) {
		context ?: return.run{ d("context is null") }
		context?.notify(2, "receive", "ACTION_BOOT_COMPLETED", "fuga")
		if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
			context?.let { Service.start(it) }
		}
	}
}