package com.maxwell.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noteList = arrayListOf<Note>()

        for(i in 1..5){
            noteList.add(Note("testing a note $i"))
        }

        rvNotes.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val adapter = NoteAdapter(noteList)

        rvNotes.adapter = adapter
    }
}
