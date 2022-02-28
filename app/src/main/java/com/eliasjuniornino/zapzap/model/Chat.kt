package com.eliasjuniornino.zapzap.model

import android.graphics.drawable.Drawable

data class Chat(
    val name: String,
    val dateStr: String,
    val lastMessage: String,
    val image: Drawable? = null
)
