package com.example.mynotes

import android.app.Application
import androidx.room.Room

/* NoteApplication with access to database instance*/
class NotesApplication : Application(){
    lateinit var notesDatabase: NotesDatabase
    private set

    override fun onCreate() {
        super.onCreate()
        notesDatabase = Room.databaseBuilder(this, NotesDatabase::class.java, "notes.db")
            .allowMainThreadQueries().build()
    }

}