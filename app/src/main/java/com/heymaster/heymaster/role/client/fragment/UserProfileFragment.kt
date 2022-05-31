package com.heymaster.heymaster.role.client.fragment


import android.annotation.SuppressLint

import android.app.Activity.RESULT_OK
import android.app.Dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.heymaster.heymaster.R
import com.heymaster.heymaster.databinding.DialogChooseLanguageBinding
import com.heymaster.heymaster.databinding.FragmentUserProfileBinding
import com.heymaster.heymaster.ui.global.BaseFragment
import com.heymaster.heymaster.utils.extensions.viewBinding



class UserProfileFragment : BaseFragment(R.layout.fragment_user_profile) {

    private val binding by viewBinding { FragmentUserProfileBinding.bind(it) }


    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }



    private fun clickListeners() {
        binding.buttons.btnUserNotification.setOnClickListener {
            findNavController().navigate(R.id.action_userProfileFragment_to_userNotificationFragment)
        }
        binding.buttons.btnUserChangeLanguage.setOnClickListener {
            showDialog()
        }
        binding.ivProfile.setOnClickListener {
            pickImageProfile()
        }
    }





    private fun pickImageProfile() {
        ImagePicker.with(this)
            .compress(1024)
            .crop()
            .galleryMimeTypes(mimeTypes = arrayOf(
                "image/png",
                "image/jpg",
                "image/jpeg"
            ))//Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080,
                1080)  //Final image resolution will be less than 1080 x 1080(Optional)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            when (resultCode) {
                RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
                    val fileUri = data?.data!!
                    binding.ivProfile.setImageURI(fileUri)
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(requireContext(), "Task cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }


    private fun showDialog() {
        val dialog = Dialog(requireContext())
        val binding: DialogChooseLanguageBinding =
            DialogChooseLanguageBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.tvSave.setOnClickListener {
            Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        binding.tvCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }


}