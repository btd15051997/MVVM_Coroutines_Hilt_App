package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.TvShowAdapter
import com.example.movieapp.databinding.ActivityMovieAppBinding
import com.example.movieapp.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieApp : AppCompatActivity() {
    private lateinit var binding: ActivityMovieAppBinding
    private val viewModel: TvShowViewModel by viewModels()
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupRvc()
    }

    private fun setupRvc() {
        tvShowAdapter = TvShowAdapter()
        binding.recyclerViewMovie.apply {
            adapter = tvShowAdapter
            layoutManager =
                LinearLayoutManager(this@MovieApp, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        viewModel.reponseTvShow.observe(this) { listTvshows ->
            tvShowAdapter.tvShows = listTvshows
        }
    }
}