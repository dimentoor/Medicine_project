package com.example.singin_screen.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.singin_screen.R
import com.example.singin_screen.databinding.FragmentRvMedicinesBinding

class Splash_screenFragment : Fragment(R.layout.fragment_splash_screen) {

    private lateinit var binding: FragmentRvMedicinesBinding

    companion object{
        fun newInstance() = Splash_screenFragment()
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRvMedicinesBinding.bind(view)


    }

}