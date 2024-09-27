package com.example.a1appstask.ui.fragments.FavouriteScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1appstask.R
import com.example.a1appstask.adapter.FavoriteRvAdapter
import com.example.a1appstask.databinding.FragmentFavouriteScreenBinding
import com.prplmnstr.a1appstask.model.Favorite

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteScreenBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var favoriteRvAdapter: FavouriteRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecycler()
        loadFavorites()
    }

    private fun loadFavorites() {
        mainViewModel.favoriteList.observe(viewLifecycleOwner, Observer {
            favoriteRvAdapter.setList(it)
        })
    }

    private fun initializeRecycler() {
        binding.recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycler.setHasFixedSize(true)
        favoriteRvAdapter = FavoriteRvAdapter(
            requireActivity(),
            { selectedMangas: List<Favorite> ->
                removeMultipleFromFavorites(
                    selectedMangas
                )
            },
            { selectedManga: Favorite -> itemClick(selectedManga) },
        )
        binding.recycler.adapter = favoriteRvAdapter

    }

    private fun itemClick(selectedManga: Favorite) {

    }

    private fun removeMultipleFromFavorites(selectedMangas: List<Favorite>) {

        for (item in selectedMangas) {
            mainViewModel.deleteFavorite(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        favoriteRvAdapter.closeActionMode()
    }

}