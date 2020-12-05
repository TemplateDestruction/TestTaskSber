package com.test.sber.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class Entity {
    @Parcelize
    data class Drug(
        val id: Int,
        val title: String,
        val icon: String,
        val isReadyForKids: Boolean
    ) : Entity(), Parcelable
}