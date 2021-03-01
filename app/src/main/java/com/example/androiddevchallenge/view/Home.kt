package com.example.androiddevchallenge.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
                    .fillMaxWidth())

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = cat.name, fontWeight = FontWeight.Bold)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    val style = MaterialTheme.typography.body2
                    val modifier = Modifier.padding(top = 2.dp)
                    Text("born in ${cat.birthday}",modifier = modifier, style = style)
                    Text("${cat.likes} likes",modifier = modifier, style = style)
                }
            }
            IconToggleButton(checked = false, onCheckedChange = { /*TODO*/ }) {

            }
        }
    }
}