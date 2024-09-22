package com.example.movie_detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.movie_detail.databinding.FragmentDetailBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = checkNotNull(_binding) { "FragmentDetailBinding = null " }

    private val viewModel by viewModel<MovieDetailViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.initState()
        viewModel.loadMovie()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { movieUiState ->
                when (movieUiState) {
                    is DetailMovieUiState.Success -> {
                        with(movieUiState.movieDetailEntity) {
                            if (movieUiState.movieDetailEntity.imageUrl.isNotEmpty()) {
                                Glide.with(binding.filmPoster)
                                    .load(movieUiState.movieDetailEntity.imageUrl)
                                    .into(binding.filmPoster)
                            }
                            requireActivity().title = localizedName
                            binding.titleMovie.text = localizedName
                            binding.genreYear.show(genres, year)
                            binding.rating.text = rating.toString()
                            binding.description.text = description
                        }
                    }

                    else -> {}
                }
            }
        }

    }
}