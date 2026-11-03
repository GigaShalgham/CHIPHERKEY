package org.cipherkey.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * CipherKey application entry point.
 *
 * Hilt dependency graph root. The application deliberately holds no
 * network-facing components: CipherKey is local-first and every release
 * artifact operates without any backend.
 */
@HiltAndroidApp
class CipherKeyApplication : Application()
