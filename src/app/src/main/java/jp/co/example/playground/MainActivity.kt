package jp.co.example.playground

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.media.session.PlaybackState
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	private val mediaSessionManager by lazy { getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager }
	private val component by lazy { ComponentName(this, NotificationListener::class.java) }
	private val onActiveSessionManager by lazy {
			MediaSessionManager.OnActiveSessionsChangedListener {
				it.forEach { d("[listener]${it.packageName}") }
				currentController = it.firstOrNull()
			}
	}

	private var currentController: MediaController? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		play_pause.setOnClickListener {
			when (currentController?.playbackState?.state) {
				PlaybackState.STATE_PLAYING -> currentController?.transportControls?.pause().run { d("[pause] ${currentController?.packageName}") }
				else                        -> currentController?.transportControls?.play().run { d("[play] ${currentController?.packageName}") }
			}
		}

		previous.setOnClickListener { currentController?.transportControls?.skipToPrevious().run { d("[previous] ${currentController?.packageName}") } }
		next.setOnClickListener { currentController?.transportControls?.skipToNext().run { d("[next] ${currentController?.packageName}") } }
	}

	override fun onStart() {
		super.onStart()

		when (NotificationListener.isEnabled(this)) {
			false -> startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
			true  -> {
				mediaSessionManager.addOnActiveSessionsChangedListener(onActiveSessionManager, component)
				currentController = mediaSessionManager.getActiveSessions(component).firstOrNull()
			}
		}
	}
}
