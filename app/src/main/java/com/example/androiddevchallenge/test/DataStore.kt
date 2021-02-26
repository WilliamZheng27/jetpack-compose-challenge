package com.example.androiddevchallenge.test

import com.example.androiddevchallenge.model.Cat

object DataStore {
    val catNames = listOf(
        "Bella",
        "Kitty",
        "Lily",
        "Lucy",
        "Charlie",
        "Leo",
        "Milo",
        "Jack",
        "Rose",
        "Tulip",
        "Iris",
        "Daisy",
        "Maple",
        "Willow",
        "Blueberry",
        "Pumpkin",
        "Cookie",
        "Oreo"
    )

    fun generateCatList(): List<Cat> {
        val result = mutableListOf<Cat>()
        for ((index, name) in catNames.withIndex()) {
            result.add(Cat(index.toLong(), name, (1..20).random(), (1 + Math.random() * (4 - 1)).toFloat()))
        }
        return result
    }
}