package com.test.sber.domain.entity

sealed class Entity {
    data class Drug(
        val id: Int,
        val title: String,
        val icon: String,
        val isReadyForKids: Boolean
    ) : Entity()
}