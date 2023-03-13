package com.example.mynotes

/*Create a notes database using room with all crud*/
/*Create a notes adapter using recycler view and diff util*/
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}

@Dao
interface NotesDao {

    @Query("SELECT * FROM Note")
     fun getAll(): Flow<List<Note>>
     //Flow es un tipo de dato que se puede observar y nos informa de los cambios que se producen en la base de datos

    @Query("SELECT * FROM Note WHERE id = :id")
    suspend fun getById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)
}