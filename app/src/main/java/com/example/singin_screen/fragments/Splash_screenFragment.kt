package com.example.singin_screen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.singin_screen.R

class Splash_screenFragment : Fragment(R.layout.fragment_splash_screen) {

    companion object{
        fun newInstance() = MedicinesFragment()
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}