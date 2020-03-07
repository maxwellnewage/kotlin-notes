package com.maxwell.notesapp

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , OnNoteListener {
    var adapter:NoteAdapter? = null
    var noteList:ArrayList<Note>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteList = arrayListOf()

        rvNotes.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        adapter = NoteAdapter(noteList!!)

        rvNotes.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.iAddNote -> {
                AddNoteDialogFragment(MainActivity@this).show(supportFragmentManager, "")
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

        val filteredList = noteList?.filter { note ->
            note.title.contains(term!!)
        }

        adapter?.noteList = filteredList as ArrayList<Note>
        adapter?.notifyDataSetChanged()
    }

    override fun onNoteAdded(note: String) {
        noteList?.add(0, Note(note))

        adapter?.notifyDataSetChanged()
    }
}
