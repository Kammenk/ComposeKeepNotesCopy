package com.example.composekeepnotescopy

import android.app.Application
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KeepNotesApplication : Application() {

    init {
        setTheme()
    }

    fun setTheme() {
        //applicationContext.resources.configuration
        //isDarkTheme = this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
    }

    companion object {
        var isDarkTheme = false
    }
}