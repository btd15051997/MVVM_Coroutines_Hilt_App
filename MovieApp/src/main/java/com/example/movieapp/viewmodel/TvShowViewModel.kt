package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.TvShowItem
import com.example.movieapp.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val repository: TvShowRepository) : ViewModel() {
    private val _response = MutableLiveData<List<TvShowItem>>()
    val reponseTvShow: LiveData<List<TvShowItem>> = _response

    init {
        getAllTvShow()
    }

    private fun getAllTvShow() = viewModelScope.launch {
        Log.d("Thread", "thread is running -- ${Thread.currentThread().name}")
        repository.getTvShows().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("tag", "error reponse ${response.code()}")
            }
        }
    }
}