package com.maxwell.notesapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteVH(view: View) : RecyclerView.ViewHolder(view){
    var tvNoteTitle : TextView? = null
    var ivShare : ImageView? = null

    fun bindItems(data:Note){
        tvNoteTitle = itemView.findViewById(R.id.tvNoteTitle)
        ivShare = itemView.findViewById(R.id.ivShareNote)

        tvNoteTitle?.text = data.title
    }
}