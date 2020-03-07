package com.maxwell.notesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(var noteList : ArrayList<Note>, val onNoteListener: OnNoteListener) : RecyclerView.Adapter<NoteVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)

        return NoteVH(v)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteVH, position: Int) {
        holder.bindItems(noteList[position])

        // I'm not sure if this is a good practice...
        holder.itemView.setOnClickListener {
            onNoteListener.onNoteSelected(noteList[position])
        }
    }

}