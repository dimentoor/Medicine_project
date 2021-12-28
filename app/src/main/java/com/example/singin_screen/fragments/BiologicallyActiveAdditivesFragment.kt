package com.example.singin_screen.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.singin_screen.MainActivity
import com.example.singin_screen.R
import com.example.singin_screen.adapter.ProductsAdapter
import com.example.singin_screen.data.DataSourse
import com.example.singin_screen.databinding.FragmentRvMedicinesBinding

class BiologicallyActiveAdditivesFragment : Fragment(R.layout.fragment_rv_medicines) {

    companion object{
        private const val KEY_NAME = "name"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_ICON_RES_ID = "iconResID"

        fun newInstance() = BiologicallyActiveAdditivesFragment()
    }

    private lateinit var binding: FragmentRvMedicinesBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRvMedicinesBinding.bind(view)

        binding.rvMedicines.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMedicines.adapter = ProductsAdapter(DataSourse.biologicalactadd){
                (name,description,iconResId) ->

            (activity as MainActivity).navigateToFragment(
                ProductDetailsFragment.newInstance(name, description, iconResId)
            )
        }
    }
}


