package com.test.sber.data.model

sealed class Dto {
    data class Drug(
        val id: Int,
        val title: String,
        val icon: String,
        val isReadyForKids: Boolean
    ) : Dto()
}