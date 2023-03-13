package com.example.mynotes.ui.main

import androidx.lifecycle.*
import com.example.mynotes.Note
import com.example.mynotes.NotesDatabase
import com.example.mynotes.data.NotesRespository
import com.example.mynotes.domain.DeleteNoteUsesCase
import com.example.mynotes.domain.GetCurrentNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getCurrentNotesUseCase: GetCurrentNoteUseCase,
    private val deleteNoteUsesCase : DeleteNoteUsesCase
)
    : ViewModel() {

    val state = getCurrentNotesUseCase()


    fun onNoteDelete(note: Note){
        viewModelScope.launch{
            deleteNoteUsesCase(note)
        }
    }
}


