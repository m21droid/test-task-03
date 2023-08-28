package com.m21droid.booknet.presentation.main.screens

import android.os.Bundle
import android.view.View
import com.m21droid.booknet.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstMainFragment : MainFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveDataRead.observe(viewLifecycleOwner, this)
    }

    override fun getActionId(): Int = R.id.action_firstMainFragment_to_bookFragment

}