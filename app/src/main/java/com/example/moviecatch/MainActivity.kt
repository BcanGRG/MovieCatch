package com.example.moviecatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatch.adapters.MovieAdapter
import com.example.moviecatch.models.Movie
import com.example.moviecatch.viewmodels.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter

    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory)[HomePageViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lmHorizontal = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val lmVertical = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = lmHorizontal
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter

        viewModel.getObserverLiveData().observe(this, object : Observer<Movie> {
            override fun onChanged(t: Movie?) {
                if (t != null) {
                    movieAdapter.setList(t.results)
                }
            }
        })

        viewModel.loadPopularData("1")
    }
}