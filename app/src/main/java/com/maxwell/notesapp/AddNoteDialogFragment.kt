package com.maxwell.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.add_note.view.*

class AddNoteDialogFragment(val onNoteListener: OnNoteListener) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_note, container)
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        v.btAdd.setOnClickListener {
            val note = v.etAddNote.text.toString()

            onNoteListener.onNoteAdded(note)

            dismiss()
        }

        v.btCancel.setOnClickListener {
            dismiss()
        }
    }
}