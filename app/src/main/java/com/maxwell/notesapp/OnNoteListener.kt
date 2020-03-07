package com.maxwell.notesapp

interface OnNoteListener {
    fun onNoteAdded(note:String)
    fun onNoteDeleted(note:Note)
    fun onNoteModified()
    fun onNoteSelected(note:Note)
}