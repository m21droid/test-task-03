package com.m21droid.booknet.presentation

import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m21droid.booknet.domain.models.BookModel
import com.m21droid.booknet.domain.models.ResponseState
import com.m21droid.booknet.domain.usecases.GetAllBooksUseCase
import com.m21droid.booknet.domain.usecases.GetBookUseCase
import com.m21droid.booknet.logD
import com.m21droid.booknet.presentation.book.states.BookState
import com.m21droid.booknet.presentation.main.states.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val getBookUseCase: GetBookUseCase,
) : ViewModel() {

    internal val liveDataRead = MutableLiveData<MainState>(MainState.Empty)
    internal val liveDataArchive = MutableLiveData<MainState>(MainState.Empty)
    internal val liveDataFavorite = MutableLiveData<MainState>(MainState.Empty)

    internal val liveDataBook = MutableLiveData<BookState>(BookState.Empty)

    private var source = ""
    private var width = 1000
    private var height = 1000
    private var lineHeight = 50
    private var textPaint = TextPaint()
    internal var indexSizeFont = 0

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

    internal fun getBook(bookId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getBookUseCase.execute(bookId).collect {
                when (it) {
                    ResponseState.Loading -> {
                        liveDataBook.postValue(BookState.Loading)
                    }

                    is ResponseState.Failure -> {
                        liveDataBook.postValue(BookState.Failure)
                    }

                    is ResponseState.Success -> {
                        if (it.data.isEmpty()) {
                            liveDataBook.postValue(BookState.Empty)
                        } else {
                            source = it.data.first().text
                            postPages()
                        }
                    }
                }
            }
        }
    }

    private fun postPages() {
        viewModelScope.launch {
            if (source.isEmpty()) {
                return@launch
            }
            liveDataBook.postValue(BookState.Loading)
            val list = mutableListOf<String>()

            val layout = staticLayout(source, textPaint, width)
            val lines = height / lineHeight
            var line = lines - 1
            var startIndex = 0
            var endIndex: Int
            while (line < layout.lineCount) {
                endIndex = layout.getLineEnd(line)
                list.add(source.substring(startIndex, endIndex))
                startIndex = endIndex
                line += lines
            }
            endIndex = layout.getLineEnd(layout.lineCount - 1)
            list.add(source.substring(startIndex, endIndex))
            if (list.isNotEmpty()) {
                liveDataBook.postValue(BookState.Display(list))
            }
        }
    }

    private fun staticLayout(text: String?, textPaint: TextPaint, width: Int): StaticLayout {
        val source = text ?: ""
        val w = if (width < 0) Int.MAX_VALUE else width
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder.obtain(source, 0, source.length, textPaint, w).build()
        } else {
            @Suppress("DEPRECATION")
            StaticLayout(source, textPaint, w, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, true)
        }
    }

    internal fun setSizesScreen(width: Int, height: Int) {
        if (width > 0 && height > 0) {
            if (this.width != width || this.height != height) {
                this.width = width
                this.height = height
                postPages()
            }
        }
    }

    internal fun setSizeFont(lineHeight: Int, textPaint: TextPaint) {
        if (lineHeight > 0 && this.lineHeight != lineHeight) {
            this.lineHeight = lineHeight
            this.textPaint = textPaint
            postPages()
        }
    }

}