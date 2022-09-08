package com.example.android.datastore.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DataSource {
    companion object {
        fun getNotes(): Flow<List<Notes>> {
            return flowOf(
                listOf(
                    Notes(
                        "Open codelab",
                        priority = NotesPriority.LOW,
                        completed = true
                    ),
                    Notes(
                        "Import project",
                        priority = NotesPriority.MEDIUM,
                        completed = true
                    ),
                    Notes(
                        "Check out the code",
                        priority = NotesPriority.LOW
                    ),
                    Notes(
                        "Read about DataStore",
                        priority = NotesPriority.HIGH
                    ),
                    Notes(
                        "Implement each step",
                        priority = NotesPriority.MEDIUM
                    ),
                    Notes(
                        "Understand how to use DataStore",
                        priority = NotesPriority.HIGH
                    ),
                    Notes(
                        "Understand how to migrate to DataStore",
                        priority = NotesPriority.HIGH
                    )
                ).shuffled()
            )
        }

    }
}