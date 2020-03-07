package com.maxwell.notesapp

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class PreferencesManager {
    val PREF = "notesapp_prefs"
    val PREF_NOTE_LIST = "notesapp_prefs_note_list"
    var prefs : SharedPreferences? = null

    constructor(ctx:Context){
        prefs = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
    }

    fun setNotes(noteList:ArrayList<Note>) {
        val editor: Editor = prefs!!.edit()
        val json = Gson().toJson(noteList)
        editor.putString(PREF_NOTE_LIST, json)
        editor.apply()
    }

    fun getNotes(): ArrayList<Note> {
        val sNotes = prefs?.getString(PREF_NOTE_LIST, null) ?: return ArrayList()

        return Gson().fromJson<ArrayList<Note>>(sNotes)
    }

    inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<T>() {}.type)
}