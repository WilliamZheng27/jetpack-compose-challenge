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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.model.Cat
import com.example.androiddevchallenge.test.DataStore
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        NavHost(navController = navController, startDestination = "listPet") {
            composable("listPet") { PetList(listPet = DataStore.generateCatList(), navController = navController) }
            composable("pet_profile/{pet}", arguments = listOf(navArgument("pet") { NavType.StringType })) { backStackEntry ->
                PetProfile(
                    pet = Json.decodeFromString(backStackEntry.arguments?.get("pet") as String)
                )
            }
        }
    }
}

@Composable
fun PetList(navController: NavController, listPet: List<Cat>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        itemsIndexed(listPet) { index, pet ->
            PetItem(index = index, pet = pet, navController = navController)
        }
    }
}

@Composable
fun PetItem(navController: NavController, index: Int, pet: Cat) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            val petSerialized = Json.encodeToString(pet)
            navController.navigate("pet_profile/$petSerialized")
        }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(40.dp, 0.dp)) {
            Text(index.toString(), fontSize = 14.sp)
            Spacer(Modifier.size(10.dp))
            Text(pet.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PetProfile(pet: Cat) {
    Column {
        Row {
            Text("Name:")
            Spacer(Modifier.padding(10.dp))
            Text(pet.name)
        }
        Row {
            Text("Age:")
            Spacer(Modifier.padding(10.dp))
            Text("${pet.age} months")
        }
        Row {
            Text("Weight:")
            Spacer(Modifier.padding(10.dp))
            Text("${pet.weight} kg")
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
