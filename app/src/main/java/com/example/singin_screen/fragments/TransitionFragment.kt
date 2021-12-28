package com.example.singin_screen.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.singin_screen.MainActivity
import com.example.singin_screen.R
import com.example.singin_screen.databinding.FragmentLoginBinding
import com.example.singin_screen.databinding.FragmentTransitionPageBinding


class TransitionFragment : Fragment(R.layout.fragment_transition_page) {

    companion object{
        fun newInstance() = TransitionFragment()

}

    private lateinit var binding: FragmentTransitionPageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTransitionPageBinding.bind(view)
        //setContentView(binding.root)

        binding.BiologicallyActiveAdditives.setOnClickListener {
            (activity as MainActivity).navigateToFragment(
                BiologicallyActiveAdditivesFragment.newInstance()
            )
        }
        binding.Disinfectants.setOnClickListener {
            (activity as MainActivity).navigateToFragment(
                DisinfectantsFragment.newInstance()
            )
        }
        binding.Medicines.setOnClickListener {
            (activity as MainActivity).navigateToFragment(
                MedicinesFragment.newInstance()
            )
        }

        //back
        binding.toolbar.setNavigationOnClickListener(){
            parentFragmentManager.popBackStack()
        }
    }
}