package com.borja.android.pokeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.borja.android.pokeapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    //1 binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PokeAdapter
    private lateinit var retrofit: Retrofit
    private var pokeImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.svPoke.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    searchByName(query.lowercase())
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        //ERROR
        adapter = PokeAdapter(emptyList()) { selectedItem ->
            // Código para manejar la selección del elemento
        }
        //ERROR
        binding.rvPokes.setHasFixedSize(true)
        binding.rvPokes.layoutManager = LinearLayoutManager(this)
        binding.rvPokes.adapter = adapter
    }

    //4 Instancia de retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Lo que son vistas en el Hilo principal,
    // lo que son llamadas, logico en hilos distintos(corrutinas)
    //5º Corrutinas

    /*  private fun searchByName(query: String) {
            CoroutineScope(Dispatchers.IO).launch {
                val response = getRetrofit().create(APIService::class.java).getPokeByName("pokemon/$query")
                val pokemones = response.body()
                Log.d("Nombre", "response $response --- pokemones $pokemones")
                // Volver al hilo principal con runOnUiThread{}
                runOnUiThread{
                    if (response.isSuccessful) {
                        // Mostrar recyclerview
                        //val images = pokemones?.pokemon?.map { it.sprites.url } ?: emptyList()
                        //val images = pokemones?.sprites?.map { it.url } ?: emptyList()
                        //val images = pokemones?.sprites?.map { it.imageFront } ?: emptyList()
                        val images2 = pokeImages
                        pokeImages.clear()
                        pokeImages.addAll(images2)
                        Log.d("Poke", "IMAGEN Poke $images2")
                        adapter.notifyDataSetChanged()
                    } else {
                        // Mostrar error
                        showError()
                    }
                    hideKeyboard()
                }
            }
        }*/


    // Hacer pruebas con abra o pikachu
    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(APIService::class.java).getPokemon("pokemon/$query")
            Log.d("Nombre", "response $myResponse")
            if (myResponse.isSuccessful) {
                //Log.i("Borchx", "funciona :)")
                val response: PokeResponse? = myResponse.body()
                if (response != null) {
                    Log.i("Nombre", response.toString())
                    runOnUiThread { //corre fuera de la corretuna, en el hilo principal
                        adapter.updateList(response.pokemon)
                    }
                }
            }else {
                runOnUiThread {
                    showError()
                }
            }
            runOnUiThread {
                hideKeyboard()
            }
        }
    }


    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error en la llamada", Toast.LENGTH_SHORT).show()
    }


}