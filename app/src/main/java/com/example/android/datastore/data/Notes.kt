package com.example.android.datastore.data

import java.text.SimpleDateFormat
import java.util.*

enum class NotesPriority {
    HIGH,
    MEDIUM,
    LOW
}

data class Notes(
    val title: String,
    val priority: NotesPriority = NotesPriority.LOW,
    val completed: Boolean = false,
    val timeStamp: Long = System.currentTimeMillis()
)

fun Notes.getDate(): String {
    val formatter = SimpleDateFormat("dd MMM yyyy hh:mm", Locale.getDefault())
    return formatter.format(timeStamp)
}
