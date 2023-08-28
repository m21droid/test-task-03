package com.m21droid.booknet.data.datasources.remote.mappers

import com.m21droid.booknet.data.datasources.remote.dto.PageDTO
import com.m21droid.booknet.data.datasources.remote.rest.Const.SECRET
import com.m21droid.booknet.data.datasources.remote.rest.decrypt
import com.m21droid.booknet.domain.models.PageModel

fun PageDTO.toPageModel(): PageModel {
    return PageModel(
        id = id,
        text = text?.decrypt(SECRET) ?: ""
    )
}