package com.example.singin_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.singin_screen.databinding.ActivityLoginBinding
import com.example.singin_screen.databinding.TransitionPageBinding

class TransitionActivity : AppCompatActivity() {
    private lateinit var binding: TransitionPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TransitionPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BiologicallyActiveAdditives.setOnClickListener {
            startActivity(Intent(this, BiologicallyActiveAdditivesActivity::class.java))
        }
        binding.Disinfectants.setOnClickListener {
            startActivity(Intent(this, DisinfectantsActivity::class.java))
        }
        binding.Medicines.setOnClickListener {
            startActivity(Intent(this, MedicinesActivity::class.java))
        }
    }
}