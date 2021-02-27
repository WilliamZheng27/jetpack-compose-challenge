/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
