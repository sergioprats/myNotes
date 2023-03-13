package com.example.mynotes.ui.detail


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.mynotes.Note

import com.example.mynotes.domain.GetNoteByIdUseCase
import com.example.mynotes.domain.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
     savedStateHandle: SavedStateHandle
    ) :
    ViewModel() {

    private val _state = MutableStateFlow(Note(0,"", ""))
    val state: StateFlow<Note> = _state.asStateFlow()

    private val noteId = requireNotNull( savedStateHandle.get<Int>(DetailActivity.EXTRA_NOTE_ID))

    init{
        viewModelScope.launch {
            val note = getNoteByIdUseCase(noteId)
            if (note != null){
                    _state.value = note
            }
        }
    }

    fun save(title: String,descrition: String){
        viewModelScope.launch {
            val note = _state.value.copy(title = title, description = descrition)
            saveNoteUseCase(note)
        }
    }
}

