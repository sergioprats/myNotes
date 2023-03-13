package com.example.mynotes.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mynotes.Note
import com.example.mynotes.NotesDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val database:NotesDatabase,private val noteId: Int ) :
    ViewModel() {

    private val _state = MutableStateFlow(Note(0,"", ""))
    val state: StateFlow<Note> = _state.asStateFlow()

    init{
        viewModelScope.launch {
            val note = database.notesDao().getById(noteId)
            if (note != null){
                    _state.value = note
            }
        }
    }

    fun save(title: String,descrition: String){
        viewModelScope.launch {
            val note = _state.value.copy(title = title, description = descrition)
            database.notesDao().insert(note)
        }
    }
}

class DetailViewModelFactory(private val database:NotesDatabase,private val noteId: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(database,noteId) as T
    }
} // solo se crea la primera vez que se llama a la clase