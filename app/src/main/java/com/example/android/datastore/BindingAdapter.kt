package com.example.android.datastore

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.datastore.adapter.NotesAdapter
import com.example.android.datastore.data.Notes
import com.example.android.datastore.data.NotesPriority

@BindingAdapter("bindAdapter")
fun RecyclerView.bindAdapter(list: List<Notes>?) {
    val adapter = this.adapter as NotesAdapter
    adapter.submitList(list)
}

@BindingAdapter("bindText")
fun TextView.bindText(priority: NotesPriority) {
    this.text = context.getString(R.string.priority,priority)
    val textColor = when (priority) {
        NotesPriority.HIGH -> R.color.red
        NotesPriority.MEDIUM -> R.color.yellow
        NotesPriority.LOW -> R.color.green
    }

    this.setTextColor(ContextCompat.getColor(context, textColor))
}

@BindingAdapter("bindColor")
fun ConstraintLayout.bindColor(completed: Boolean) {
    val color = if (completed) R.color.greyAlpha else R.color.white
    this.setBackgroundColor(
        ContextCompat.getColor(
            context,
            color
        )
    )
}
