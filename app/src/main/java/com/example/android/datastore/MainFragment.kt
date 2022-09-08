package com.example.android.datastore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.datastore.adapter.NotesAdapter
import com.example.android.datastore.databinding.FragmentMainBinding
import com.example.android.datastore.viewmodel.NotesViewModel
import com.example.android.datastore.viewmodel.NotesViewModelFactory

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NotesViewModel by viewModels { NotesViewModelFactory((activity?.application as NotesApplication).settingsDatastore) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.recyclerView.adapter = NotesAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}