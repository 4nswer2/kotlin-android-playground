package jp.co.example.playground

import android.content.Intent
import timber.log.Timber
import android.app.Application as BaseApplication

class Application : BaseApplication() {

	override fun onCreate() {
		super.onCreate()

		if (BuildConfig.DEBUG) { Timber.plant(Timber.DebugTree()) }

		Service.start(this)
	}
}