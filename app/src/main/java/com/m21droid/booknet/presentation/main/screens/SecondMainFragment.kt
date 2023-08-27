package com.m21droid.booknet.presentation.main.screens

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondMainFragment : MainFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveDataArchive.observe(viewLifecycleOwner, this)
    }

}