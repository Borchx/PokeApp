package com.borja.android.pokeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


//6º Adapter
class PokeAdapter(
    private var pokeList:MutableList<PokeItemResponse> = mutableListOf(),
    private val onItemSelected: (String) -> Unit
):
    RecyclerView.Adapter<PokeViewHolder>() {

    fun updateList(pokeList: List<PokeItemResponse>) {
        this.pokeList.clear()
        this.pokeList.addAll(pokeList)
        notifyDataSetChanged()
    }
    fun add(pokemon: PokeItemResponse) {
        this.pokeList.add(pokemon)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokeViewHolder(layoutInflater.inflate(R.layout.item_poke,parent,false))
    }

    override fun onBindViewHolder(viewholder: PokeViewHolder, position: Int) {
        viewholder.bind(pokeList[position],onItemSelected)
        //val item = pokeList[position]
        //holder.bind(item)
    }

    override fun getItemCount(): Int = pokeList.size
}