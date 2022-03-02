package com.androiddev.internetharrypotter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androiddev.internetharrypotter.network.CharacterModel
import com.androiddev.internetharrypotter.overview.CharacterAdapter
import com.androiddev.internetharrypotter.overview.HarryPotterApiStatus
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions





@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CharacterModel>?){
    var adapter = recyclerView.adapter as CharacterAdapter
    adapter.submitList(data)
    //bu kod yapısı normalde fragment da yazılıyordu
}


@BindingAdapter("harryPotterApiStatus")
fun bindStatus(statusImageView: ImageView, status: HarryPotterApiStatus?) {

    when(status){
        HarryPotterApiStatus.LOADING ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        HarryPotterApiStatus.DONE ->{
            statusImageView.visibility = View.GONE
        }

        HarryPotterApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?){
    imageUrl?.let {
        Glide.with(imageView.context).load(imageUrl).apply (
            RequestOptions().placeholder(R.drawable.loading_animation) //bekleme aşamasında
                .error(R.drawable.ic_broken_image)
        ).into(imageView)
    }
}

/*
*List data ve harryPotterApiStatus fragment_overview a bağlanıyor. viewModel
* image_url ise chracter_item_design a bağlanıyor. CharacterModel
* adapter class ında ki veriler character_item_design ile bağlantılı ve veriler characker model le bağlantılı
 */