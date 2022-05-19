package com.example.inviofilmchallenge.movie_detail_fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.inviofilmchallenge.R
import com.example.inviofilmchallenge.databinding.FragmentMovieDetailBinding
import com.example.inviofilmchallenge.until.dowloandImage


class MovieDetailFragment : Fragment() {

    lateinit var binding:FragmentMovieDetailBinding
    lateinit var viewModel: MovieDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       arguments?.let {
           var imdbID = MovieDetailFragmentArgs.fromBundle(it).imdbID
           viewModel.getMoivieApi(imdbID)
       }
        binding.leftImage.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        observerMovie()
        observerMovieErrorMessage()
    }

    private fun observerMovie(){
        viewModel.movieMutable.observe(viewLifecycleOwner, Observer {
            binding.loadingAnimasyon.visibility = View.GONE
            binding.coustemToolbar.title = it.title
            binding.shapeableImageView.dowloandImage(requireContext(),it.poster.toString())
            binding.titleText.text = it.title
            binding.genreText.text = it.genre
            binding.runTimeText.text = it.runtime
            binding.directorText.text = it.director
            binding.actorsText.text = it.actors
            binding.relasedText.text = it.released
            binding.plotText.text = it.plot
        })
    }

    private fun observerMovieErrorMessage(){
        viewModel.movieMutableErrorMessage.observe(viewLifecycleOwner, Observer {
            var dialog = AlertDialog.Builder(requireContext())
            dialog.setTitle(getString(R.string.error))
            dialog.setMessage(it.toString())
            dialog.setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
                Navigation.findNavController(requireView()).popBackStack()
            })
            dialog.show()
        })
    }


}


