package com.example.moviecatch.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatch.di.retrofit.RetrofitRepository
import com.example.moviecatch.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val repository: RetrofitRepository) :
    ViewModel() {
    var popularMovieList: MutableLiveData<Movie>

    init {
        popularMovieList = MutableLiveData()
    }

    fun getObserverLiveData(): MutableLiveData<Movie> = popularMovieList

    fun loadPopularData(page: String) {
        repository.getPopularMovies(page, popularMovieList)
    }
}