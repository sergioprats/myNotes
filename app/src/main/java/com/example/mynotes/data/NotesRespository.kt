package com.example.mynotes.data

import com.example.mynotes.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRespository @Inject constructor(private val noteDataSource: NotesLocalDataSource) {

    val currentNotes: Flow<List<Note>> = noteDataSource.currentNotes

    suspend  fun delete (note: Note){
        noteDataSource.delete(note)
    }

    suspend fun getById(noteId: Int): Note? = noteDataSource.getById(noteId)

    fun save(note: Note) {
        noteDataSource.save(note)
    }


}