package com.maxwell.notesapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maxwell.notesapp.adapters.DragManageAdapter
import com.maxwell.notesapp.adapters.NoteAdapter
import com.maxwell.notesapp.dialogs.AddNoteDialogFragment
import com.maxwell.notesapp.dialogs.MDNoteDialogFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , OnNoteListener {
    private var adapter: NoteAdapter? = null
    var noteList:ArrayList<Note>? = null
    var prefs:PreferencesManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = PreferencesManager(this)

        noteList = prefs!!.getNotes()

        rvNotes.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        adapter = NoteAdapter(noteList!!, this)

        rvNotes.adapter = adapter

        ItemTouchHelper(DragManageAdapter(this, noteList!!)).attachToRecyclerView(rvNotes)

        updateListStatus()
    }

    fun updateListStatus(){
        if(noteList!!.isEmpty()) {
            rvNotes.visibility = View.GONE
            llEmptyNoteListContainer.visibility = View.VISIBLE
        } else {
            rvNotes.visibility = View.VISIBLE
            llEmptyNoteListContainer.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.iAddNote -> {
                AddNoteDialogFragment(MainActivity@this)
                    .show(supportFragmentManager, "")
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
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                searchNote(p0)
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

        prefs?.setNotes(noteList!!)

        adapter?.notifyDataSetChanged()

        updateListStatus()
    }

    override fun onNoteDeleted(note: Note) {
        noteList?.remove(note)

        prefs?.setNotes(noteList!!)

        adapter?.notifyDataSetChanged()

        updateListStatus()
    }

    override fun onNoteModified() {
        prefs?.setNotes(noteList!!)

        adapter?.notifyDataSetChanged()

        updateListStatus()
    }

    override fun onNoteSelected(note: Note) {
        MDNoteDialogFragment(
            MainActivity@this,
            note
        ).show(supportFragmentManager, "")
    }

    override fun onNoteShared(note: Note) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.share_note_title))
        sharingIntent.putExtra(Intent.EXTRA_TEXT, note.title)
        startActivity(
            Intent.createChooser(
                sharingIntent,
                resources.getString(R.string.share_note_title)
            )
        )
    }
}
