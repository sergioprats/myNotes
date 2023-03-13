package com.example.mynotes.domain

import com.example.mynotes.Note
import com.example.mynotes.data.NotesRespository
import javax.inject.Inject

class DeleteNoteUsesCase @Inject constructor(private val notesRespository: NotesRespository) {

    suspend operator fun invoke(note: Note){
        notesRespository.delete(note)
    }
}
