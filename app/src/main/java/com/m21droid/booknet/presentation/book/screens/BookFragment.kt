package com.m21droid.booknet.presentation.book.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.m21droid.booknet.R
import com.m21droid.booknet.databinding.FragmentBookBinding
import com.m21droid.booknet.databinding.ViewPageBinding
import com.m21droid.booknet.gone
import com.m21droid.booknet.logD
import com.m21droid.booknet.presentation.MainViewModel
import com.m21droid.booknet.presentation.book.adapters.ViewPagerAdapter
import com.m21droid.booknet.presentation.book.states.BookState
import com.m21droid.booknet.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookFragment : Fragment(), Observer<BookState> {

    private val viewModel by activityViewModels<MainViewModel>()

    private lateinit var binding: FragmentBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBookBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveDataBook.observe(viewLifecycleOwner, this)
    }

    override fun onChanged(value: BookState) {
        logD("onChanged: value - $value")

        binding.apply {
            when (value) {
                BookState.Loading -> {
                    viewPagerBook.gone()
                    textViewBookError.gone()
                    progressBarBook.visible()
                }

                BookState.Failure -> {
                    viewPagerBook.gone()
                    textViewBookError.visible()
                    progressBarBook.gone()
                    textViewBookError.text = getString(R.string.error)
                }

                BookState.Empty -> {
                    viewPagerBook.gone()
                    textViewBookError.visible()
                    progressBarBook.gone()
                    textViewBookError.text = getString(R.string.empty_page)
                }

                is BookState.Display -> {
                    val pages = value.pages.map {
                        ViewPageBinding.inflate(LayoutInflater.from(context)).apply {
                            textViewBookText.text = it.text
                            textViewBookPage.text = it.id.toString()
                        }.root
                    }
                    binding.viewPagerBook.adapter = ViewPagerAdapter(pages)
                    viewPagerBook.visible()
                    textViewBookError.gone()
                    progressBarBook.gone()
                }
            }
        }
    }

}