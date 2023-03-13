package com.example.mynotes.main

import androidx.lifecycle.*
import com.example.mynotes.Note
import com.example.mynotes.NotesDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val db:NotesDatabase) : ViewModel() {

    val state = db.notesDao().getAll()


    fun onNoteDelete(note: Note){
        viewModelScope.launch{
            db.notesDao().delete(note)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val db:NotesDatabase) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(db) as T
    }
} // solo se crea la primera vez que se llama a la clase