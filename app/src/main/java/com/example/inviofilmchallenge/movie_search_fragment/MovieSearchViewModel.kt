package com.example.inviofilmchallenge.movie_search_fragment

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.inviofilmchallenge.until.BaseViewModel
import com.example.inviofilmchallenge.until.MovieStatus
import com.example.inviofilmchallenge.model.Movie
import com.example.inviofilmchallenge.model.Search
import com.example.inviofilmchallenge.service.MovieAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers




class MovieSearchViewModel(application:Application): BaseViewModel(application) {

    val movieMutableList = MutableLiveData<List<Movie>>()
    val movieMutableStatus = MutableLiveData<MovieStatus>()
    val movieMutableErrorMessage = MutableLiveData<String>()
    val disposible = CompositeDisposable()

    fun getMoivieListApi(movieName:String){
        movieMutableStatus.value = MovieStatus.Loading
        disposible.add(
            MovieAPIService().getMovieApi().getSearchMovie(movieName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<Search>(){
                    override fun onSuccess(t: Search?) {
                        if(t!= null){
                           if(t.response.equals("True")){
                               movieMutableStatus.value = MovieStatus.Success
                               movieMutableList.value = t.search
                           }else{
                               movieMutableStatus.value = MovieStatus.Error
                               movieMutableErrorMessage.value = t.error.toString()
                           }
                        }else{
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