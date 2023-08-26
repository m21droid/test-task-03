package com.m21droid.booknet.domain.usecases

import com.m21droid.booknet.domain.models.BookModel
import com.m21droid.booknet.domain.models.ResponseState
import com.m21droid.booknet.domain.repositories.DataRepository
import kotlinx.coroutines.flow.Flow

class GetAllBooksUseCase(
    private val dataRepository: DataRepository,
) : BaseUseCase<Unit, Flow<ResponseState<List<BookModel>>>> {

    override fun execute(input: Unit): Flow<ResponseState<List<BookModel>>> {
        return dataRepository.getAllBooks()
    }

}