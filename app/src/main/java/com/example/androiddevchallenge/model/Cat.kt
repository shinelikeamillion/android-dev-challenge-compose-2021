package com.example.androiddevchallenge.model

import android.media.Image

data class Cat(
    val id: Int,
    val name: String,
    val birthday: String,
    val gen: Int,
    val coolDown: Int,
    val bio: String,
    val image: Int,
    val likes: Int = 0
)