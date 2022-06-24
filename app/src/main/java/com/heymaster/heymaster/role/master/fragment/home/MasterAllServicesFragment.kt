package com.heymaster.heymaster.role.master.fragment.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.heymaster.heymaster.R
import com.heymaster.heymaster.SharedPref
import com.heymaster.heymaster.data.network.ApiClient
import com.heymaster.heymaster.data.network.ApiService
import com.heymaster.heymaster.databinding.FragmentMasterAllServicesBinding
import com.heymaster.heymaster.global.BaseFragment
import com.heymaster.heymaster.global.adapter.home.AllCategoryAdapter
import com.heymaster.heymaster.role.master.adapter.MasterHomeAllServicesAdapter
import com.heymaster.heymaster.role.master.repository.MasterHomeRepository
import com.heymaster.heymaster.role.master.viewmodel.MasterHomeViewModel
import com.heymaster.heymaster.role.master.viewmodel.factory.MasterHomeViewModelFactory
import com.heymaster.heymaster.utils.Constants
import com.heymaster.heymaster.utils.UiStateObject
import com.heymaster.heymaster.utils.extensions.viewBinding
import kotlinx.coroutines.flow.collect


class MasterAllServicesFragment : BaseFragment(R.layout.fragment_master_all_services) {

    private val binding by viewBinding { FragmentMasterAllServicesBinding.bind(it) }
    private lateinit var viewModel: MasterHomeViewModel
    private val categoryAdapter by lazy { AllCategoryAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        setupViewModel()
        viewModel.getAllCategory()
        obServeViewModel()
        categoryAdapter.itemClickListener = {
            launchCategoryDetailFragment(it.id)
        }

    }

    private fun launchCategoryDetailFragment(id: Int) {
        findNavController().navigate(R.id.action_masterAllServicesFragment_to_masterServiceDetailFragment , bundleOf("category_id" to id))
    }

    private fun obServeViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.categories.collect { it ->
                when (it) {
                    is UiStateObject.LOADING -> {
                        binding.progressMasterHomeAllService.customProgress.visibility = View.VISIBLE
                    }
                    is UiStateObject.SUCCESS -> {
                        binding.progressMasterHomeAllService.customProgress.visibility = View.GONE
                        categoryAdapter.submitList(it.data.`object`)


                    }
                    is UiStateObject.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setupRv() {
        binding.rvMasterAllService.adapter = categoryAdapter
    }

    private fun setupViewModel() {
        val token = SharedPref(requireContext()).getString(Constants.KEY_ACCESS_TOKEN)
        viewModel = ViewModelProvider(
            this,
            MasterHomeViewModelFactory(MasterHomeRepository(ApiClient.createServiceWithAuth(ApiService::class.java, token!!)))
        )[MasterHomeViewModel::class.java]
    }

}