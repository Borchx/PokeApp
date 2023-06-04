package com.borja.android.pokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.borja.android.pokeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //1 binding
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }

    //4 Instancia de retrofit

}