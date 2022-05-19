package com.example.inviofilmchallenge.movie_detail_fragment

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.inviofilmchallenge.until.MovieStatus
import com.example.inviofilmchallenge.model.Movie
import com.example.inviofilmchallenge.service.MovieAPIService
import com.example.inviofilmchallenge.until.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel(application: Application):BaseViewModel(application) {
    val movieMutable = MutableLiveData<Movie>()
    val movieMutableStatus = MutableLiveData<MovieStatus>()
    val movieMutableErrorMessage = MutableLiveData<String>()
    val disposible = CompositeDisposable()

    fun getMoivieApi(imdbID:String) {
        movieMutableStatus.value = MovieStatus.Loading
        disposible.add(

            MovieAPIService().getMovieApi().getFindImdbMovie(imdbID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Movie>() {
                    override fun onSuccess(t: Movie) {
                        if (t != null) {
                            if (t.response.equals("True")) {
                                movieMutableStatus.value = MovieStatus.Success
                                movieMutable.value = t
                            } else {
                                movieMutableStatus.value = MovieStatus.Error
                                movieMutableErrorMessage.value = "Movie not found"
                            }
                        } else {
                            movieMutableStatus.value = MovieStatus.Error
                            movieMutableErrorMessage.value = "Error"
                        }
                    }

                    override fun onError(e: Throwable) {
                        movieMutableStatus.value = MovieStatus.Error
                        movieMutableErrorMessage.value = e.localizedMessage
                    }

                })
        )
    }
}