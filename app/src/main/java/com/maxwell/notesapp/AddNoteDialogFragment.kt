package com.maxwell.notesapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.add_note.view.*

class AddNoteDialogFragment(val onNoteListener: OnNoteListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        val v: View = LayoutInflater.from(this.activity).inflate(R.layout.add_note, null, false)

        v.btAdd.setOnClickListener {
            val note = v.etAddNote.text.toString()

            onNoteListener.onNoteAdded(note)

            dismiss()
        }

        v.btCancel.setOnClickListener {
            dismiss()
        }

        builder.setView(v)
        return builder.create()
    }

}