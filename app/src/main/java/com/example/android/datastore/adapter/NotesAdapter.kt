package com.example.android.datastore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.datastore.R
import com.example.android.datastore.data.Notes
import com.example.android.datastore.databinding.ListItemBinding

class NotesAdapter : ListAdapter<Notes, NotesAdapter.NotesViewHolder>(DiffCallback) {
    class NotesViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notes) {
            binding.note = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return newItem == oldItem
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return newItem == oldItem
        }
    }

}
