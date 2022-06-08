package com.heymaster.heymaster.role.master.fragment.profile

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.tabs.TabLayout
import com.heymaster.heymaster.R
import com.heymaster.heymaster.data.network.ApiClient
import com.heymaster.heymaster.data.network.ApiService
import com.heymaster.heymaster.ui.adapter.MasterProfilePagerAdapter
import com.heymaster.heymaster.databinding.FragmentMasterProfileBinding
import com.heymaster.heymaster.role.master.repository.MasterPortfolioRepository
import com.heymaster.heymaster.role.master.viewmodel.MasterProfileViewModel
import com.heymaster.heymaster.role.master.viewmodel.factory.MasterPortfolioViewModelFactory
import com.heymaster.heymaster.utils.extensions.viewBinding

class MasterProfileFragment : Fragment(R.layout.fragment_master_profile) {

    private val binding by viewBinding { FragmentMasterProfileBinding.bind(it) }
    lateinit var adapter: MasterProfilePagerAdapter
    private lateinit var viewModel: MasterProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupViewPager()
        itemClickManager()
    }

    private fun itemClickManager() {
        binding.ivEditProfile.setOnClickListener {
            findNavController().navigate(R.id.masterEditProfileFragment)
        }

        binding.apply {

            ivProfile.setOnClickListener {
                pickImageProfile()
            }
            ivEditProfile.setOnClickListener {
                findNavController().navigate(R.id.masterEditProfileFragment)
            }

        }
    }


    private fun pickImageProfile() {
        ImagePicker.with(this)
            .compress(1024)
            .crop()
            .galleryMimeTypes(
                mimeTypes = arrayOf(
                    "image/png",
                    "image/jpg",
                    "image/jpeg"
                )
            )//Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            )  //Final image resolution will be less than 1080 x 1080(Optional)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            when (resultCode) {
                Activity.RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
                    val fileUri = data?.data!!
                    binding.ivProfile.setImageURI(fileUri)
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(requireContext(), "Task cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, MasterPortfolioViewModelFactory(
                MasterPortfolioRepository(
                    ApiClient.createService(
                        ApiService::class.java
                    )
                )
            )
        )[MasterProfileViewModel::class.java]
    }


    private fun setupViewPager() {
        adapter = MasterProfilePagerAdapter(childFragmentManager, lifecycle)
        binding.apply {
            tabMasterProfile.addTab(binding.tabMasterProfile.newTab().setText("Portfolio"))
            tabMasterProfile.addTab(binding.tabMasterProfile.newTab().setText("Profile"))
            vpMasterProfile.adapter = adapter
        }

        binding.tabMasterProfile.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.vpMasterProfile.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        binding.vpMasterProfile.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabMasterProfile.selectTab(binding.tabMasterProfile.getTabAt(position))

            }
        })
    }
}
