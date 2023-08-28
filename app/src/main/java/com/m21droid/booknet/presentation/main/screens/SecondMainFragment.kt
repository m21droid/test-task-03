package com.m21droid.booknet.presentation.main.screens

import android.os.Bundle
import android.view.View
import com.m21droid.booknet.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondMainFragment : MainFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveDataArchive.observe(viewLifecycleOwner, this)
    }

    override fun getActionId(): Int = R.id.action_secondMainFragment_to_bookFragment

}