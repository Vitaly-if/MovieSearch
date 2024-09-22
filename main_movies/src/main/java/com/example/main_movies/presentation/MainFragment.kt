package com.example.main_movies.presentation

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.main_movies.MoviesViewModel
import com.example.main_movies.R
import com.example.main_movies.databinding.FragmentMainBinding
import com.example.main_movies.presentation.adapters.genres.GenresAdapter
import com.example.main_movies.presentation.adapters.movies.GridSpacingItemDecoration
import com.example.main_movies.presentation.adapters.movies.MoviesAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = checkNotNull(_binding) { "FragmentMainBinding = null " }
    private val viewModel by viewModel<MoviesViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = binding.root.context.getString(R.string.films)
        val adapterGenres = GenresAdapter {
            viewModel.clickOnGenre(it)
        }
        val adapterMovies = MoviesAdapter {
            viewModel.saveMovie(it)
        }
        binding.genreItemsRecycler.adapter = adapterGenres
        binding.filmsItemsRecycler.adapter = adapterMovies
        binding.filmsItemsRecycler.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                8.toDp(binding.root.context),
                true,
                0
            )
        )
        viewModel.loadMovies()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { moviesUiState ->
                when (moviesUiState.loadingState) {
                    is LoadingState.Initial -> {
                        binding.groupMovies.isVisible = false
                        binding.progressBar.isVisible = false
                    }

                    is LoadingState.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.groupMovies.isVisible = false
                    }

                    is LoadingState.Error -> {
                        binding.groupMovies.isVisible = false
                        binding.progressBar.isVisible = false
                        Snackbar.make(
                            binding.root,
                            binding.root.context.getString(moviesUiState.loadingState.error),
                            Snackbar.LENGTH_LONG
                        )
                            .setActionTextColor(Color.YELLOW)
                            .setAction(binding.root.context.getString(R.string.retry)) {
                                viewModel.loadMovies()
                            }
                            .show()
                    }

                    is LoadingState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.groupMovies.isVisible = true
                        adapterMovies.submitList(moviesUiState.filmEntities)
                        adapterGenres.submitList(moviesUiState.genres)
                    }

                    is LoadingState.Navigation -> {
                        findNavController().navigate(com.example.navigation.R.id.action_main_fragment_to_detail_fragment)
                    }
                }
            }
        }
    }


}

fun Int.toDp(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}