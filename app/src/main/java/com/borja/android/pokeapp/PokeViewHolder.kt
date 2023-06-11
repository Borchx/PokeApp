package com.borja.android.pokeapp

import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.borja.android.pokeapp.databinding.ItemPokeBinding
import com.squareup.picasso.Picasso
import java.util.Locale

//7º ViewHolder
class PokeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPokeBinding.bind(view)

    /* fun bind(image: String) {
         Log.d("PokePrueba", "Loading image: $image")
         Picasso.get().load(image).into(binding.ivPoke)
     }*/
    fun bind(pokeItemResponse: PokeItemResponse, onItemSelected: (String) -> Unit) {

        //pintar nombre capitalizado
        binding.tvPokeName.text = pokeItemResponse.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }

        //pintar id


        //GUARDAR POKEMONS

        //pintar types
        //val types = pokeItemResponse.types[0].type.nameType
        //Log.i("Nombre", types)

        Log.i("Typo", pokeItemResponse.types.size.toString())

        val firstType = pokeItemResponse.types[0].type.nameType

        //Log.i("Typo", pokeItemResponse.types.size.toString())
        binding.tvPokeType1.text = firstType.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        } ?: "-"

        if (pokeItemResponse.types.size >= 2) {
            val secondType = pokeItemResponse.types[1].type.nameType
            binding.tvPokeType2.text = secondType?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            } ?: "-"
            Log.i("Typo", secondType)
            paintBackgroundTypesTwo(secondType)
        } else {
            //binding.tvPokeType2.text = "-"
            binding.tvPokeType2.text = ""
            //esto deja en 1 linea si es solo 1 tipo pero falla
            //binding.tvPokeType1.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        }

        Log.i("Typo", firstType)
        paintBackgroundTypesOne(firstType)

        //pintar imagen
        Picasso.get().load(pokeItemResponse.sprites.url).into(binding.ivPoke)

    }

    private fun paintBackgroundTypesOne(typeOne: String) {
        val typeOneView = binding.tvPokeType1
        val pokeType = when (typeOne) {
            "bug" -> PokeTypes.Bug
            "dark" -> PokeTypes.Dark
            "dragon" -> PokeTypes.Dragon
            "electric" -> PokeTypes.Electric
            "fairy" -> PokeTypes.Fairy
            "fighting" -> PokeTypes.Fighting
            "fire" -> PokeTypes.Fire
            "flying" -> PokeTypes.Flying
            "ghost" -> PokeTypes.Ghost
            "grass" -> PokeTypes.Grass
            "ground" -> PokeTypes.Ground
            "ice" -> PokeTypes.Ice
            "normal" -> PokeTypes.Normal
            "poison" -> PokeTypes.Poison
            "psychic" -> PokeTypes.Psychic
            "rock" -> PokeTypes.Rock
            "shadow" -> PokeTypes.Shadow
            "steel" -> PokeTypes.Steel
            "unknown" -> PokeTypes.Unknown
            "water" -> PokeTypes.Water
            else -> PokeTypes.Unknown
        }
        val colorResId = getColorResourceForType(pokeType)
        typeOneView.setBackgroundColor(ContextCompat.getColor(typeOneView.context, colorResId))
    }

    private fun paintBackgroundTypesTwo(typeTwo: String?) {
        val typeTwoView = binding.tvPokeType2
        val pokeType = typeTwo?.let {
            when (it) {
                "bug" -> PokeTypes.Bug
                "dark" -> PokeTypes.Dark
                "dragon" -> PokeTypes.Dragon
                "electric" -> PokeTypes.Electric
                "fairy" -> PokeTypes.Fairy
                "fighting" -> PokeTypes.Fighting
                "fire" -> PokeTypes.Fire
                "flying" -> PokeTypes.Flying
                "ghost" -> PokeTypes.Ghost
                "grass" -> PokeTypes.Grass
                "ground" -> PokeTypes.Ground
                "ice" -> PokeTypes.Ice
                "normal" -> PokeTypes.Normal
                "poison" -> PokeTypes.Poison
                "psychic" -> PokeTypes.Psychic
                "rock" -> PokeTypes.Rock
                "shadow" -> PokeTypes.Shadow
                "steel" -> PokeTypes.Steel
                "unknown" -> PokeTypes.Unknown
                "water" -> PokeTypes.Water
                else -> PokeTypes.Unknown
            }
        } ?: PokeTypes.Unknown
        val colorResId = getColorResourceForType(pokeType)
        typeTwoView.setBackgroundColor(ContextCompat.getColor(typeTwoView.context, colorResId))
    }

    private fun getColorResourceForType(pokeType: PokeTypes): Int {
        return when (pokeType) {
            PokeTypes.Bug -> R.color.poke_bug
            PokeTypes.Dark -> R.color.poke_dark
            PokeTypes.Dragon -> R.color.poke_dragon
            PokeTypes.Electric -> R.color.poke_electric
            PokeTypes.Fairy -> R.color.poke_fairy
            PokeTypes.Fighting -> R.color.poke_fighting
            PokeTypes.Fire -> R.color.poke_fire
            PokeTypes.Flying -> R.color.poke_flying
            PokeTypes.Ghost -> R.color.poke_ghost
            PokeTypes.Grass -> R.color.poke_grass
            PokeTypes.Ground -> R.color.poke_ground
            PokeTypes.Ice -> R.color.poke_ice
            PokeTypes.Normal -> R.color.poke_normal
            PokeTypes.Poison -> R.color.poke_poison
            PokeTypes.Psychic -> R.color.poke_psychic
            PokeTypes.Rock -> R.color.poke_rock
            PokeTypes.Shadow -> R.color.poke_shadow
            PokeTypes.Steel -> R.color.poke_steel
            PokeTypes.Unknown -> R.color.poke_unknown
            PokeTypes.Water -> R.color.poke_water

        }
    }

}
