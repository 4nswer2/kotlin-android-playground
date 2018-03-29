package jp.co.example.playground

import timber.log.Timber

fun d(text: String) = Timber.tag("__DEBUG__").d(text)
