package com.example.singin_screen.presentation.fragments


import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.singin_screen.*
import com.example.singin_screen.presentation.adapter.ProductsAdapter
import com.example.singin_screen.databinding.FragmentRvMedicinesBinding
import com.example.singin_screen.data.model.Products
import com.example.singin_screen.domain.network.NetworkService
import com.example.singin_screen.presentation.MainActivity
import com.example.singin_screen.presentation.viewmodel.ScreenState
import com.example.singin_screen.presentation.viewmodel.ViewModelDisinfectants
import com.example.singin_screen.presentation.viewmodel.ViewModelMedicines
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi


class MedicinesFragment : Fragment(R.layout.fragment_rv_medicines) {

    private lateinit var binding: FragmentRvMedicinesBinding
    private val viewModel by lazy { ViewModelMedicines(requireContext(), lifecycleScope) }


    companion object{

        fun newInstance() = MedicinesFragment()
    }


    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRvMedicinesBinding.bind(view)

        /*merge(
            flowOf(Unit),
            binding.swipeRefreshLayout.onRefreshFlow(),
            binding.buttonRefresh.onClickFlow()
        ).flatMapLatest { loadMedicines() }
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
            }.launchIn(lifecycleScope)*/
        if (savedInstanceState == null ){
            viewModel.loadData()
        }
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.loadData() }
        binding.buttonRefresh.setOnClickListener{ viewModel.loadData()}
        viewModel.screenState.onEach {
            when (it) {
                is ScreenState.DataLoaded ->
                {
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

   /* @ExperimentalSerializationApi
    private fun loadMedicines() = flow {
        emit(ScreenState.Loading)
        val medicines = NetworkService.loadMedicines()
        emit(ScreenState.DataLoaded(medicines))
    }.catch {
        emit(ScreenState.Error(getString(R.string.error)))
    }*/

    private fun setLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading && !rvMedicines.isVisible
        swipeRefreshLayout.isRefreshing = isLoading && rvMedicines.isVisible
    }

    private fun setData(medicines: List<Products>?) = with(binding) {
        swipeRefreshLayout.isVisible = medicines != null
        binding.rvMedicines.layoutManager = LinearLayoutManager(context)
        rvMedicines.adapter = ProductsAdapter(
            medicines ?: emptyList()
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

