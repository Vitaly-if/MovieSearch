package com.example.movie_detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movie_detail.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment: Fragment() {
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
        requireActivity().title = "rgergf"
        return binding.root
    }
}