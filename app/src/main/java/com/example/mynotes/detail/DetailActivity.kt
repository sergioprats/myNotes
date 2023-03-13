package com.example.mynotes.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mynotes.Note
import com.example.mynotes.NotesApplication
import com.example.mynotes.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private val vm : DetailViewModel by viewModels{
        val database = (application as NotesApplication).notesDatabase
        val noteId = intent.getIntExtra(EXTRA_NOTE_ID, 0)
        DetailViewModelFactory(database, noteId)
    }

    companion object {
        const val EXTRA_NOTE_ID = "note_id"

        fun navigate(activity: AppCompatActivity, noteId: Int = -1) {
            val intent = Intent(activity, DetailActivity::class.java).apply {
                putExtra(EXTRA_NOTE_ID, noteId)
            }
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            ActivityDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)
                buttonSave.setOnClickListener {
                    val title = editTextTitle.text.toString()
                    val description = editTextDescription.text.toString()
                    vm.save(title, description)
                    finish()
                }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    vm.state.collect{
                        editTextTitle.setText(it.title)
                        editTextDescription.setText(it.description)
                    }
                }

            }
        }
    }
}