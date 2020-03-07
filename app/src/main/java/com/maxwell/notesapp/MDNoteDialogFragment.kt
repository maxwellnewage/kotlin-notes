package com.maxwell.notesapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.modify_delete_note.*
import kotlinx.android.synthetic.main.modify_delete_note.view.*

class MDNoteDialogFragment(val onNoteListener: OnNoteListener, val note: Note) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        val v: View = LayoutInflater.from(this.activity).inflate(R.layout.modify_delete_note, null, false)

        v.etMDNote.setText(note.title)

        v.btModify.setOnClickListener {
            val sNote = v.etMDNote.text.toString()

            note.title = sNote

            onNoteListener.onNoteModified()

            dismiss()
        }

        v.btDelete.setOnClickListener {

            onNoteListener.onNoteDeleted(note)

            dismiss()
        }

        builder.setView(v)
        return builder.create()
    }

}