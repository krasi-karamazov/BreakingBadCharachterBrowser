package kpk.dev.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot)

//ImageView Specific extension functions

fun ImageView.loadImage(imageUrl: String) = Glide.with(this).load(imageUrl).into(this)