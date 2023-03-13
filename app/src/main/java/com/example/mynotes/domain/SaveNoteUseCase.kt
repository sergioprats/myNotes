package com.example.mynotes.domain

import com.example.mynotes.Note
import com.example.mynotes.data.NotesRespository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(private val notesRespository: NotesRespository) {

    suspend operator fun invoke(note: Note){
        notesRespository.save(note)
    }
}