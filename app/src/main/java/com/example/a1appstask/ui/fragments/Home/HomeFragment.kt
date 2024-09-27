package com.example.a1appstask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a1appstask.R
import com.example.a1appstask.databinding.FragmentHomeBinding
import com.example.a1appstask.models.HomeViewModel
import com.example.a1appstask.ui.adapter.MangaAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mangaAdapter = MangaAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mangaAdapter
        }

        lifecycleScope.launch {
            homeViewModel.mangaList.collect { mangaList ->
                mangaAdapter.updateData(mangaList)
            }
        }
        lifecycleScope.launch{
            delay(2000)
            findNavController().navigate(R.id.action_homeFragment_to_favouriteScreenFragment)
        }

        homeViewModel.fetchMangaList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
