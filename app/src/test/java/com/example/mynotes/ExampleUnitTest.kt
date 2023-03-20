package com.example.mynotes

import com.example.mynotes.data.NotesLocalDataSource
import com.example.mynotes.data.NotesRespository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun  `When note is saved, the note is returned in the flow`() = runBlocking{

        val ds = FakeLocalDataSource()
        val repository = NotesRespository(ds)

        repository.save(Note( 1, "title 1", "Description 1"))

        val result = repository.currentNotes.first()

        assertEquals(1, result[0].id)
    }

    @Test
    fun  `when a note is deleted, the repository the list without the note`() = runBlocking{

        val note = Note( 1, "title 1", "Description 1")
        val ds = FakeLocalDataSource(listOf(note))
        val repository = NotesRespository(ds)

        repository.delete(note)

        val result = repository.currentNotes.first()
        assertEquals(emptyList<Note>(),result)
    }
}

class FakeLocalDataSource(initialNotes : List<Note> = emptyList()) : NotesLocalDataSource {

    private val mutableNotes = MutableStateFlow(initialNotes)
    override val currentNotes : Flow<List<Note>> = mutableNotes

    override fun delete(note: Note) {
        mutableNotes.value = mutableNotes.value - note
    }

    override suspend fun getById(noteId: Int): Note? = mutableNotes.value.find { it.id == noteId }

    override fun save(note: Note) {

        val list = mutableNotes.value.toMutableList()

        val index = mutableNotes.value.indexOfFirst { it.id == note.id }
        if(index >= 0){
            list[index] = note
        }
        else{
            list.add(note)
        }
        mutableNotes.value = list
    }
}