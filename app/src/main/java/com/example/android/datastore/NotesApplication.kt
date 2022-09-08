package com.example.android.datastore

import android.app.Application
import com.example.android.datastore.data.SettingsDatastore

class NotesApplication : Application() {
    val settingsDatastore by lazy { SettingsDatastore(this) }
}