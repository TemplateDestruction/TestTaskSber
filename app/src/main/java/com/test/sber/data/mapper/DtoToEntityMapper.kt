package com.test.sber.data.mapper

import com.test.sber.data.model.Dto
import com.test.sber.domain.entity.Entity

fun Dto.Drug.toEntity() = Entity.Drug(
    id = id,
    title = title,
    icon = icon,
    isReadyForKids = isReadyForKids
)