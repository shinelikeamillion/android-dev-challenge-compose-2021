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
package com.example.androiddevchallenge.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconToggleButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.data.Database
import com.example.androiddevchallenge.model.Cat

@Composable
fun Home(navController: NavController) {
    Column {
        Database.cats.map { cat ->
            CatCard(cat, navController)
        }
    }
}

@Composable
fun CatCard(cat: Cat, navController: NavController) {
    val image = painterResource(cat.image)
    Card(
        modifier = Modifier
            .padding(10.dp, 10.dp, 10.dp, 0.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .clickable(onClick = { println(cat.id); navController.navigate("Detail/${cat.id}") })
        ) {
            Image(
                image,
                "Kitty ${cat.name}",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = cat.name, fontWeight = FontWeight.Bold)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    val style = MaterialTheme.typography.body2
                    val modifier = Modifier.padding(top = 2.dp)
                    Text("born in ${cat.birthday}", modifier = modifier, style = style)
                    Text("${cat.likes} likes", modifier = modifier, style = style)
                }
            }
            IconToggleButton(checked = false, onCheckedChange = { /*TODO*/ }) {
            }
        }
    }
}
