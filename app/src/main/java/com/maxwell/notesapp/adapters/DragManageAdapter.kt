package com.maxwell.notesapp.adapters

import android.content.Context
import android.graphics.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.maxwell.notesapp.Note
import com.maxwell.notesapp.OnNoteListener
import com.maxwell.notesapp.R

class DragManageAdapter(private val context: Context,
                        private val onNoteListener: OnNoteListener,
                        private val noteList: ArrayList<Note>) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val icon: Bitmap
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val itemView = viewHolder.itemView
            val height = itemView.bottom.toFloat() - itemView.top.toFloat()
            val width = height / 3
            val p = Paint()
            p.color = Color.parseColor("#D42721")
            val background = RectF(
                itemView.right.toFloat() + dX,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat()
            )
            c.drawRect(background, p)
            icon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_delete_note)
            val iconDest = RectF(
                itemView.right.toFloat() - 2 * width,
                itemView.top.toFloat() + width,
                itemView.right.toFloat() - width,
                itemView.bottom.toFloat() - width
            )
            c.drawBitmap(icon, null, iconDest, p)
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onNoteListener.onNoteDeleted(noteList[viewHolder.adapterPosition])
    }
}