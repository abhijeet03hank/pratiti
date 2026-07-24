package com.devdatt.pratiti

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application entry point required by Hilt.
 * Must be registered as `android:name` on `<application>` in the manifest.
 */
@HiltAndroidApp
class PratitiApp : Application()
