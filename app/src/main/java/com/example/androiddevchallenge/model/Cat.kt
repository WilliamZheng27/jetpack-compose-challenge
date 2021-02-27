package com.example.androiddevchallenge.model

import kotlinx.serialization.Serializable

@Serializable
data class Cat(
    val id: Long = 0L,
    val name: String = "",
    val age: Int = 0,
    val weight: Float = 0F
)
