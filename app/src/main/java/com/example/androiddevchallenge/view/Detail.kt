package com.example.androiddevchallenge.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.MyApp
import com.example.androiddevchallenge.data.Database
import com.example.androiddevchallenge.model.Cat
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.background
import com.example.androiddevchallenge.ui.theme.purple200
import com.example.androiddevchallenge.ui.theme.white

@Composable
fun Detail(navController: NavController, catId: Int = 0) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable {
                                navController.navigateUp()
                            },
                        tint = Color.White
                    )
                }
            )
        },
        modifier = Modifier.background(MaterialTheme.colors.background),
        content = {
            Column {
                val cat = Database.cats.find { it.id == catId } ?: return@Column
                val image = painterResource(cat.image)
                Image(
                    modifier = Modifier
                        .background(white)
                        .fillMaxWidth()
                        .height(300.dp),
                    painter = image,
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Kitty ${cat.name}"
                )

                Column(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    Text(
                        cat.name, style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            val style = MaterialTheme.typography.body2
                            val modifier = Modifier.padding(start = 10.dp)
                            Text("# ${cat.id}", style = style)
                            Text("Gen ${cat.gen}", modifier = modifier, style = style)
                            Text("Cooldown ${cat.coolDown} min", modifier = modifier, style = style)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Bio(cat = cat)
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text(text = "ADOPT")
                    }
                }
            }
        })
}

@Composable
fun Bio(cat: Cat) {
    Text(
        "Bio", modifier = Modifier.padding(top = 10.dp),
        fontWeight = FontWeight.Bold
    )
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text("  ${cat.bio}", style = MaterialTheme.typography.body2)
    }
}

@Preview("Detail")
@Composable
fun Preview() {
    MyTheme(darkTheme = false) {
        Detail(rememberNavController())
    }
}