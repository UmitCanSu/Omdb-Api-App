package com.example.inviofilmchallenge.until

import android.content.Context
import com.bumptech.glide.Glide
import com.example.inviofilmchallenge.R
import com.google.android.material.imageview.ShapeableImageView



    fun ShapeableImageView.dowloandImage(context: Context, imageUrl:String){
        Glide
            .with(context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(this);
    }
