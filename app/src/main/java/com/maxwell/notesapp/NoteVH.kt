package com.maxwell.notesapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteVH(view: View) : RecyclerView.ViewHolder(view){
    fun bindItems(data:Note){
        var title = itemView.findViewById<TextView>(R.id.tvNoteTitle)

        title.text = data.title
    }
}