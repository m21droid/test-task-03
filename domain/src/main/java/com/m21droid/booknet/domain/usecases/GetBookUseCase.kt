package com.m21droid.booknet.domain.usecases

import com.m21droid.booknet.domain.models.PageModel
import com.m21droid.booknet.domain.models.ResponseState
import com.m21droid.booknet.domain.repositories.DataRepository
import kotlinx.coroutines.flow.Flow

class GetBookUseCase(
    private val dataRepository: DataRepository,
) : BaseUseCase<Int, Flow<ResponseState<List<PageModel>>>> {

    override fun execute(input: Int): Flow<ResponseState<List<PageModel>>> {
        return dataRepository.getBook(input)
    }

}