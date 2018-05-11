package jp.co.example.playground

import android.os.Build
import timber.log.Timber

fun d(text: String) = Timber.tag("__DEBUG__").d(text)

fun lessThanApiLevel(apiLevel: Int): Boolean = Build.VERSION.SDK_INT < apiLevel

fun moreThanApiLevel(apiLevel: Int): Boolean = Build.VERSION.SDK_INT >= apiLevel