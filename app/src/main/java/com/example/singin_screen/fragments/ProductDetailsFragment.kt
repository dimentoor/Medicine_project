package com.example.singin_screen.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import com.example.singin_screen.R
import com.example.singin_screen.databinding.FragmentProductDetailsBinding

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {

    private lateinit var binding : FragmentProductDetailsBinding

    companion object{
        private const val KEY_NAME = "name"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_ICON_RES_ID = "iconUrl"

        fun newInstance(name : String, description : String, iconUrl: String): ProductDetailsFragment {

            val args = bundleOf(
                KEY_NAME to name,
                KEY_DESCRIPTION to description,
                KEY_ICON_RES_ID to iconUrl,
            )
            val fragment = ProductDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailsBinding.bind(view)

        val name = arguments?.getString(KEY_NAME)
        val description = arguments?.getString(KEY_DESCRIPTION)
        val imageView = arguments?.getString(KEY_ICON_RES_ID)

        binding.name.text = name
        binding.description.text = description

        if (imageView != null) {
            binding.imageView2.load(imageView)
        }
        //back
        binding.toolbar.setNavigationOnClickListener(){
            parentFragmentManager.popBackStack()
        }
    }
}