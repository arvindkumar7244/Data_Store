package com.example.android.datastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.datastore.data.DataSource
import com.example.android.datastore.data.SettingsDatastore
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class NotesViewModel(private val settingsDatastore: SettingsDatastore) : ViewModel() {

    val sortByName = settingsDatastore.sortByNameFlow.asLiveData()
    val sortByPriority = settingsDatastore.sortByPriorityFlow.asLiveData()
    val showCompleted = settingsDatastore.showCompletedFlow.asLiveData()

    val sortedList = DataSource.getNotes()
        .combine(settingsDatastore.sortByNameFlow) { list, sortOrder -> if (sortOrder) list.sortedBy { it.title } else list }
        .combine(settingsDatastore.sortByPriorityFlow) { list, sortOrder -> if (sortOrder) list.sortedBy { it.priority } else list }
        .combine(settingsDatastore.showCompletedFlow) { list, showCompleted ->
            if (!showCompleted) list.filter { !it.completed }
                .sortedByDescending { it.completed } else list.sortedByDescending { it.completed }
        }
        .asLiveData()


    fun setSortName() {
        viewModelScope.launch {
            settingsDatastore.saveSortByNameToPref(sortByName.value == false)
        }
    }

    fun setSortPriority() {
        viewModelScope.launch {
            settingsDatastore.saveSortByPriorityToPref(sortByPriority.value == false)
        }
    }

    fun setCompleted() {
        viewModelScope.launch {
            settingsDatastore.saveShowCompletedToPref(showCompleted.value == false)
        }
    }

}

class NotesViewModelFactory(private val settingsDatastore: SettingsDatastore) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(NotesViewModel::class.java))
            return NotesViewModel(settingsDatastore) as T
        else
            throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

