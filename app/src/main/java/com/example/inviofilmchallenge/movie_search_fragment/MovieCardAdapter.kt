package com.example.inviofilmchallenge.movie_search_fragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.inviofilmchallenge.databinding.AdapterMovieCardBinding
import com.example.inviofilmchallenge.model.Movie
import com.example.inviofilmchallenge.until.dowloandImage


class MovieCardAdapter(private var movieList:List<Movie>,private var context:Context):RecyclerView.Adapter<MovieCardAdapter.ViewHolder>() {

    class ViewHolder(val binding:AdapterMovieCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AdapterMovieCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       var bind = holder.binding
        var movie = movieList.get(position)
        bind.movieName.text = movie.title
        bind.movieType.text = movie.type
        bind.movieType.text = movie.year
        bind.movieImage.dowloandImage(context,movie.poster.toString())
        Log.e("MovieCardAdapter","Position: "+position+"Imdb ID: "+ movie.imdbID )
        bind.movieCard.setOnClickListener {
            Log.e("MovieCardAdapter","Click: "+ movie.imdbID )
            if (movie.imdbID != null){
                var action = MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieDetailFragment(movie.imdbID!!)
                Navigation.findNavController(it).navigate(action)
            }else{
                Toast.makeText(context,"Imdb ID not found",Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun getItemCount(): Int {
        return movieList.size
    }


}