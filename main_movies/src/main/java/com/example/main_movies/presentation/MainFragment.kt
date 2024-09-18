package com.example.main_movies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.main_movies.MoviesViewModel

import com.example.main_movies.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: Fragment() {
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
        requireActivity().title = "Фильмы"
        binding.buttomNavigation.setOnClickListener {

            findNavController().navigate(com.example.navigation.R.id.action_main_fragment_to_detail_fragment)

        }
        binding.getMovie.setOnClickListener {
            viewModel.loadMovies()
        }
    }

}