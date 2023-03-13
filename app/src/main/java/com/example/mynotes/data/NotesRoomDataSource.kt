package com.example.mynotes.data

import com.example.mynotes.Note
import com.example.mynotes.NotesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NotesLocalDataSource {
    val currentNotes: Flow<List<Note>>
    fun delete(note: Note)
    suspend fun getById(noteId: Int): Note?
    fun save(note: Note)
}

class NotesRoomDataSource @Inject constructor(private val notesDao: NotesDao) : NotesLocalDataSource {
    override val currentNotes: Flow<List<Note>> = notesDao.getAll()

    override fun delete(note: Note) {
        notesDao.delete(note)
    }

    override suspend fun getById(noteId: Int): Note? =   notesDao.getById(noteId)

    override fun save(note: Note) {
        notesDao.insert(note)
    }
}