package com.borja.android.pokeapp

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.borja.android.pokeapp.databinding.ItemPokeBinding
import com.squareup.picasso.Picasso

//7º ViewHolder
class PokeViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemPokeBinding.bind(view)

    /* fun bind(image: String) {
         Log.d("PokePrueba", "Loading image: $image")
         Picasso.get().load(image).into(binding.ivPoke)
     }*/
    fun bind(pokeItemResponse: PokeItemResponse, onItemSelected: (String) -> Unit) {

        Picasso.get().load(pokeItemResponse.sprites.url).into(binding.ivPoke)

    }
}