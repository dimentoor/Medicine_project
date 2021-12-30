package com.example.singin_screen.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.singin_screen.*
import com.example.singin_screen.adapter.ProductsAdapter
import com.example.singin_screen.databinding.FragmentRvMedicinesBinding
import com.example.singin_screen.model.Products
import com.example.singin_screen.network.NetworkService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi


class DisinfectantsFragment : Fragment(R.layout.fragment_rv_medicines) {


    //ловим необработанные ошибки
    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, exception ->
        binding.progressBar.visibility = View.GONE
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
        private const val KEY_ICON_RES_ID = "iconResID"

        fun newInstance() = DisinfectantsFragment()
    }

    private lateinit var binding: FragmentRvMedicinesBinding

    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRvMedicinesBinding.bind(view)

        merge(
            flowOf(Unit),
            binding.swipeRefreshLayout.onRefreshFlow(),
            binding.buttonRefresh.onClickFlow()
        ).flatMapLatest { loadDisinfectants() }
            .distinctUntilChanged()
            .onEach {
                when (it) {
                    is ScreenState.DataLoaded -> {
                        setLoading(false)
                        setError(null)
                        setData(it.products)
                    }
                    is ScreenState.Error -> {
                        setLoading(false)
                        setError(it.error)
                        setData(null)
                    }
                    is ScreenState.Loading -> {
                        setLoading(true)
                        setError(null)
                    }
                }
            }.launchIn(lifecycleScope)
    }

    @ExperimentalSerializationApi
    private fun loadDisinfectants() = flow {
        emit(ScreenState.Loading)
        val disinfectants = NetworkService.loadDisinfectants()
        emit(ScreenState.DataLoaded(disinfectants))
    }.catch {
        emit(ScreenState.Error(getString(R.string.error)))
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading && !rvMedicines.isVisible
        swipeRefreshLayout.isRefreshing = isLoading && rvMedicines.isVisible
    }

    private fun setData(disinfectants: List<Products>?) = with(binding) {
        swipeRefreshLayout.isVisible = disinfectants != null
        binding.rvMedicines.layoutManager = LinearLayoutManager(context)
        rvMedicines.adapter = ProductsAdapter(
            disinfectants ?: emptyList()
        ) { (name,description,iconUrl) ->
            (activity as MainActivity).navigateToFragment(
                ProductDetailsFragment.newInstance(
                    name,description,iconUrl
                )
            )
        }
    }

    private fun setError(message: String?) = with(binding) {
        errorLayout.isVisible = message != null
        tvError.text = message
    }


}