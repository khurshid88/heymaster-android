package com.heymaster.heymaster.role.client.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.heymaster.heymaster.R
import com.heymaster.heymaster.role.client.adapter.ClientHomePopularMastersAdapter
import com.heymaster.heymaster.role.client.repository.UserHomeRepository
import com.heymaster.heymaster.role.client.viewmodel.UserHomeViewModel
import com.heymaster.heymaster.role.client.viewmodel.factory.UserHomeViewModelFactory
import com.heymaster.heymaster.data.network.ApiClient
import com.heymaster.heymaster.data.network.ApiService
import com.heymaster.heymaster.databinding.FragmentUserAllPopularMastersBinding
import com.heymaster.heymaster.ui.global.BaseFragment
import com.heymaster.heymaster.utils.UiStateList
import com.heymaster.heymaster.utils.extensions.viewBinding
import kotlinx.coroutines.flow.collect


class UserAllPopularMastersFragment : BaseFragment(R.layout.fragment_user_all_popular_masters) {

    private val binding by viewBinding { FragmentUserAllPopularMastersBinding.bind(it) }
    private lateinit var viewModel: UserHomeViewModel
    private val popularMastersAdapter by lazy { ClientHomePopularMastersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRv()
        viewModel.getServices()
        observeViewModel()


    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.services.collect { it ->
                when (it) {
                    is UiStateList.LOADING -> {
                        binding.progressUserHomeAllMasters.customProgress.visibility = View.VISIBLE
                    }
                    is UiStateList.SUCCESS -> {
                        binding.progressUserHomeAllMasters.customProgress.visibility = View.GONE
                        popularMastersAdapter.submitList(it.data)


                    }
                    is UiStateList.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setupRv() {
        binding.rvUserAllMaster.adapter = popularMastersAdapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            UserHomeViewModelFactory(UserHomeRepository(ApiClient.createService(ApiService::class.java)))
        )[UserHomeViewModel::class.java]
    }


}