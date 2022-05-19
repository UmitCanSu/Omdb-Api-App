package com.example.inviofilmchallenge.movie_search_fragment

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inviofilmchallenge.until.MovieStatus
import com.example.inviofilmchallenge.R
import com.example.inviofilmchallenge.databinding.FragmentMovieSearchBinding


class MovieSearchFragment : Fragment() {


    lateinit  var binding:FragmentMovieSearchBinding
    lateinit var viewModel:MovieSearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieSearchViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerMovieList()
        observerMovieStatus()

        binding.movieRv.hasFixedSize()
        binding.movieRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        binding.serarchButton.setOnClickListener {
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            var movieNameInput=binding.movieNameText.text.toString()
            movieNameInput = movieNameInput.trim()
           viewModel.getMoivieListApi(movieNameInput)
        }


    }

    private fun observerMovieList(){
        viewModel.movieMutableList.observe(viewLifecycleOwner, Observer {
           it.forEach(){movie->
               binding.movieRv.adapter  = MovieCardAdapter(it,requireContext())
           }
        })
    }
    private fun observerMovieStatus(){
        viewModel.movieMutableStatus.observe(viewLifecycleOwner, Observer {
            when(it){
                MovieStatus.Loading ->{
                    binding.movieRv.visibility = View.GONE
                    binding.loadingView.visibility = View.VISIBLE
                }
                MovieStatus.Success ->{
                    binding.movieRv.visibility = View.VISIBLE
                    binding.loadingView.visibility = View.GONE
                }
                MovieStatus.Error ->{
                    binding.movieRv.visibility = View.VISIBLE
                    binding.loadingView.visibility = View.GONE
                    observerMovieErrorMessage()
                    binding.movieRv.adapter = MovieCardAdapter(arrayListOf(),requireContext())
                }
            }
        })
    }
    private fun observerMovieErrorMessage(){
        viewModel.movieMutableErrorMessage.observe(viewLifecycleOwner, Observer {
            var dialog = AlertDialog.Builder(requireContext())
            dialog.setTitle(getString(R.string.error))
            dialog.setMessage(it.toString())
            dialog.setPositiveButton("Ok",null)
            dialog.show()
        })
    }


}