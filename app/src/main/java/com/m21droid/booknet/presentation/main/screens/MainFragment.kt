package com.m21droid.booknet.presentation.main.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.m21droid.booknet.R
import com.m21droid.booknet.databinding.FragmentMainBinding
import com.m21droid.booknet.domain.models.BookModel
import com.m21droid.booknet.gone
import com.m21droid.booknet.init
import com.m21droid.booknet.logD
import com.m21droid.booknet.presentation.MainViewModel
import com.m21droid.booknet.presentation.base.BaseHolder.OnItemClickListener
import com.m21droid.booknet.presentation.base.BaseSpace
import com.m21droid.booknet.presentation.main.adapters.BookAdapter
import com.m21droid.booknet.presentation.main.states.MainState
import com.m21droid.booknet.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class MainFragment : Fragment(), Observer<MainState>, OnItemClickListener<BookModel> {

    protected val viewModel by activityViewModels<MainViewModel>()

    private lateinit var binding: FragmentMainBinding

    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        val space = resources.getDimension(R.dimen.space).toInt()
        bookAdapter = BookAdapter(this)
        binding.recyclerViewMain.init(bookAdapter, BaseSpace(space))
        return binding.root
    }

    override fun onChanged(value: MainState) {
        logD("onChanged: value - $value")

        binding.apply {
            when (value) {
                MainState.Loading -> {
                    recyclerViewMain.gone()
                    textViewMainError.gone()
                    progressBarMain.visible()
                }

                MainState.Failure -> {
                    recyclerViewMain.gone()
                    textViewMainError.visible()
                    progressBarMain.gone()
                    textViewMainError.text = getString(R.string.error)
                }

                MainState.Empty -> {
                    recyclerViewMain.gone()
                    textViewMainError.visible()
                    progressBarMain.gone()
                    textViewMainError.text = getString(R.string.empty_book)
                }

                is MainState.Display -> {
                    recyclerViewMain.visible()
                    textViewMainError.gone()
                    progressBarMain.gone()
                    bookAdapter.clearItems()
                    bookAdapter.addItems(value.books.toMutableList())
                }
            }
        }
    }

    override fun onItemViewClick(view: View, model: BookModel) {
        logD("onItemViewClick: model - $model")

        viewModel.getBook(model.id)
        findNavController().navigate(getActionId())
    }

    abstract fun getActionId(): Int

}