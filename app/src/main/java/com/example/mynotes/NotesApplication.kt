package com.example.mynotes

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp

/* NoteApplication with access to database instance*/
@HiltAndroidApp
class NotesApplication : Application(){
    lateinit var notesDatabase: NotesDatabase
    private set

    override fun onCreate() {
        super.onCreate()
    }

}