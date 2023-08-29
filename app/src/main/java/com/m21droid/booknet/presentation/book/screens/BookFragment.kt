package com.m21droid.booknet.presentation.book.screens

import android.content.res.Configuration
import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.m21droid.booknet.R
import com.m21droid.booknet.databinding.FragmentBookBinding
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

    private val listSizeFont = arrayOf(14, 18, 22, 24, 32)
    private val listTextView = mutableListOf<TextView>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBookBinding.inflate(inflater).apply {
            listSizeFont.forEachIndexed { index, font ->
                val textView = TextView(context).apply {
                    layoutParams =
                        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            LayoutParams(LayoutParams.MATCH_PARENT, 0, 1.0f)
                        } else {
                            LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f)
                        }
                    text = font.toString()
                    setTextSize(COMPLEX_UNIT_SP, font.toFloat())
                    gravity = Gravity.CENTER
                    setOnClickListener {
                        viewModel.indexSizeFont = index
                        setSizeFont()
                    }
                }
                linearLayoutBook.addView(textView)
                listTextView.add(textView)
            }
            viewPagerBook.addOnLayoutChangeListener { v, _, _, _, _, _, _, _, _ ->
                viewModel.setSizesScreen(v.width, v.height)
            }
        }
        setSizeFont()
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
                    linearLayoutBook.gone()
                    textViewBookError.gone()
                    progressBarBook.visible()
                }

                BookState.Failure -> {
                    viewPagerBook.gone()
                    linearLayoutBook.gone()
                    textViewBookError.visible()
                    progressBarBook.gone()
                    textViewBookError.text = getString(R.string.error)
                }

                BookState.Empty -> {
                    viewPagerBook.gone()
                    linearLayoutBook.gone()
                    textViewBookError.visible()
                    progressBarBook.gone()
                    textViewBookError.text = getString(R.string.empty_page)
                }

                is BookState.Display -> {
                    val pages = value.pages.map {
                        TextView(context).apply {
                            val size = listSizeFont[viewModel.indexSizeFont].toFloat()
                            setTextSize(COMPLEX_UNIT_SP, size)
                            text = it
                        }
                    }
                    binding.viewPagerBook.adapter = ViewPagerAdapter(pages)
                    viewPagerBook.visible()
                    linearLayoutBook.visible()
                    textViewBookError.gone()
                    progressBarBook.gone()
                }
            }
        }
    }

    private fun setSizeFont() {
        TextView(context).apply {
            setTextSize(COMPLEX_UNIT_SP, listSizeFont[viewModel.indexSizeFont].toFloat())
            viewModel.setSizeFont(lineHeight, paint)
        }
        listTextView.forEachIndexed { index, textView ->
            val resId =
                if (index == viewModel.indexSizeFont) R.drawable.background_font else R.drawable.background_item
            textView.setBackgroundResource(resId)
        }
    }

}