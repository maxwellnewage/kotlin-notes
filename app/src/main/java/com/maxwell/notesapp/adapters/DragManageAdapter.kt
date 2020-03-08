package com.maxwell.notesapp.adapters

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.maxwell.notesapp.Note
import com.maxwell.notesapp.OnNoteListener

class DragManageAdapter(private val onNoteListener: OnNoteListener, private val noteList: ArrayList<Note>) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onNoteListener.onNoteDeleted(noteList[viewHolder.adapterPosition])
    }
}