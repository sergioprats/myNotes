package com.example.mynotes.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mynotes.*
import com.example.mynotes.data.NotesRespository
import com.example.mynotes.data.NotesRoomDataSource
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.domain.DeleteNoteUsesCase
import com.example.mynotes.domain.GetCurrentNoteUseCase
import com.example.mynotes.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter : NotesAdapter

    //acceso al viewmodel
    private val vm by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            notesAdapter = NotesAdapter(
                onNoteClick = { DetailActivity.navigate(this@MainActivity, it.id) },
                onNoteDelte = {vm.onNoteDelete(it)  }
            )
            recyclerView.adapter = notesAdapter
            fab.setOnClickListener {
                DetailActivity.navigate(this@MainActivity)
            }
           lifecycleScope.launch(){
               repeatOnLifecycle(Lifecycle.State.STARTED){
                   vm.state.collect{
                       notesAdapter.submitList(it)
                   }
               }
           }
        }
    }


}