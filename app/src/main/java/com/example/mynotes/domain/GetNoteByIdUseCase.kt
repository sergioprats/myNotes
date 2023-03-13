package com.example.mynotes.domain

import com.example.mynotes.data.NotesRespository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(private val notesRespository: NotesRespository) {

    suspend operator fun invoke(noteId : Int) = notesRespository.getById(noteId)
}