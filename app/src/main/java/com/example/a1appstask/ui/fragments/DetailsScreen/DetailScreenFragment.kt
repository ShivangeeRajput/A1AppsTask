package com.example.a1appstask.ui.fragments.DetailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.a1appstask.R
import com.example.a1appstask.databinding.FragmentDetailScreenBinding


class DetailScreenFragment : Fragment() {

    private lateinit var binding: FragmentDetailScreenBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailScreenBinding.inflate(inflater, container, false)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView.load(mainViewModel.detailScreenManga.thumb) {

        }


        mainViewModel.favoriteList.observe(viewLifecycleOwner, Observer {
            binding.favoriteButton.isChecked = mainViewModel.isInFavorite()
        })


        binding.favoriteButton.setOnClickListener {
            if (binding.favoriteButton.isChecked) {
                Toast.makeText(requireContext(), "Added to favorite", Toast.LENGTH_SHORT).show()
                mainViewModel.addFavorite(mainViewModel.detailScreenManga.toFavorite())

            } else {
                Toast.makeText(requireContext(), "Removed from favorite", Toast.LENGTH_SHORT).show()
                mainViewModel.deleteFavorite(mainViewModel.detailScreenManga.toFavorite())
            }
            binding.favoriteButton.startAnimation(
                AnimationUtils.loadAnimation(
                    context,
                    R.anim.popup_animation
                )
            )

        }
    }

}