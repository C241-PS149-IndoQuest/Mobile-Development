package com.example.indoquest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    val name : String = "",
    val description : String = "",
    val image : Int = 0,
    val rating : Double = 0.0,
    val location : String = ""
) : Parcelable
