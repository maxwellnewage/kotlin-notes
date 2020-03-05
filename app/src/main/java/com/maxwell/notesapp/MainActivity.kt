package com.maxwell.notesapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.SearchView
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.iAddNote -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val searchView = menu?.findItem(R.id.iSearchNote)?.actionView as SearchView
        val serviceManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.setSearchableInfo(serviceManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchNote(p0)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    fun searchNote(term:String?){

    }
}
