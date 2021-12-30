package com.example.singin_screen.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.singin_screen.MainActivity
import com.example.singin_screen.R
import com.example.singin_screen.adapter.ProductsAdapter
import com.example.singin_screen.databinding.FragmentRvMedicinesBinding
import com.example.singin_screen.network.NetworkService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

import kotlinx.serialization.ExperimentalSerializationApi


class BiologicallyActiveAdditivesFragment : Fragment(R.layout.fragment_rv_medicines) {


    //ловим необработанные ошибки
    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, exception ->
        binding.progressBar.visibility = GONE
        binding.rvMedicines.adapter =
            ProductsAdapter(listOf()) {}
        binding.swipeRefreshLayout.isRefreshing = false
        Snackbar.make(//окно с ошибкой
            requireView(),
            getString(R.string.error),
            Snackbar.LENGTH_SHORT
        ).setBackgroundTint(Color.parseColor("#ED4337"))
            .setActionTextColor(Color.parseColor("#FFFFFF"))
            .show()
    }
        //всплывающее окно
    private val scope =
        CoroutineScope(Dispatchers.Main + SupervisorJob() + coroutineExceptionHandler)

    companion object{
        private const val KEY_NAME = "name"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_ICON_RES_ID = "iconUrl"

        fun newInstance() = BiologicallyActiveAdditivesFragment()
    }

    private lateinit var binding: FragmentRvMedicinesBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRvMedicinesBinding.bind(view)

        //загружаем данные
        loadBiologicalactadd()
        binding.swipeRefreshLayout.setOnRefreshListener {
            //загружаем данные при свайпе
            binding.swipeRefreshLayout.isRefreshing = true
            loadBiologicalactadd()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    //передаем данные на фрагмент
    @ExperimentalSerializationApi
    private fun loadBiologicalactadd() {
        scope.launch {
            val medicines = NetworkService.loadBiologicalactadd()
            binding.rvMedicines.layoutManager = LinearLayoutManager(context)
            binding.rvMedicines.adapter =
                ProductsAdapter(medicines) { (name,description,iconUrl) ->

                    (activity as MainActivity).navigateToFragment(
                        ProductDetailsFragment.newInstance(name, description, iconUrl)
                    )
                }
            binding.progressBar.visibility = View.GONE
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}


