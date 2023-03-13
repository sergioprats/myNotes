package com.example.mynotes.domain

import com.example.mynotes.Note
import com.example.mynotes.data.NotesRespository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCurrentNoteUseCase @Inject constructor(private val notesRespository: NotesRespository){

    operator  fun invoke() : Flow<List<Note>> = notesRespository.currentNotes
}