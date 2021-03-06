package com.heymaster.heymaster.role.master.fragment.profile

import android.os.Bundle
import android.view.View
import com.heymaster.heymaster.R
import com.heymaster.heymaster.databinding.FragmentMasterProfileHelpBinding
import com.heymaster.heymaster.global.BaseFragment
import com.heymaster.heymaster.utils.extensions.viewBinding

class MasterProfileHelpFragment : BaseFragment(R.layout.fragment_master_profile_help) {
    private val binding by viewBinding { FragmentMasterProfileHelpBinding.bind(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}