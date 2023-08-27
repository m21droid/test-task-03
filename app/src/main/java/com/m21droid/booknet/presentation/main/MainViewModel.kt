package com.m21droid.booknet.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m21droid.booknet.domain.models.BookModel
import com.m21droid.booknet.domain.models.ResponseState
import com.m21droid.booknet.domain.usecases.GetAllBooksUseCase
import com.m21droid.booknet.logD
import com.m21droid.booknet.presentation.main.states.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase,
) : ViewModel() {

    val liveDataRead = MutableLiveData<MainState>(MainState.Empty)
    val liveDataArchive = MutableLiveData<MainState>(MainState.Empty)
    val liveDataFavorite = MutableLiveData<MainState>(MainState.Empty)

    init {
        logD("init: ")

        getAllBooks()
    }

    private fun getAllBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllBooksUseCase.execute(Unit).collect {
                when (it) {
                    ResponseState.Loading -> {
                        liveDataRead.postValue(MainState.Loading)
                        liveDataArchive.postValue(MainState.Loading)
                        liveDataFavorite.postValue(MainState.Loading)
                    }

                    is ResponseState.Failure -> {
                        liveDataRead.postValue(MainState.Failure)
                        liveDataArchive.postValue(MainState.Failure)
                        liveDataFavorite.postValue(MainState.Failure)
                    }

                    is ResponseState.Success -> {
                        val listBookRead = mutableListOf<BookModel>()
                        val listBookArchive = mutableListOf<BookModel>()
                        val listBookFavorite = mutableListOf<BookModel>()
                        it.data.forEach { book ->
                            when (book.type) {
                                0 -> listBookRead.add(book)
                                1 -> listBookArchive.add(book)
                                2 -> listBookFavorite.add(book)
                            }
                        }
                        liveDataRead.postState(listBookRead)
                        liveDataArchive.postState(listBookArchive)
                        liveDataFavorite.postState(listBookFavorite)
                    }
                }
            }
        }
    }

    private fun MutableLiveData<MainState>.postState(data: MutableList<BookModel>) {
        if (data.isEmpty()) {
            postValue(MainState.Empty)
        } else {
            postValue(MainState.Display(data))
        }
    }

}