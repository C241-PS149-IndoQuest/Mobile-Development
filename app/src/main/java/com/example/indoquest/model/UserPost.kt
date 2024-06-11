package com.example.indoquest.model

data class UserPost(
    val username : String = "",
    val location : String = "",
    val profile_img : Int = 0,
    val post_img : Int = 0,
    val title : String = "",
    val description : String = ""
)
