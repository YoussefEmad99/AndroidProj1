package com.example.androidproj1.fragments

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetails(val name:String, val pop:Double, val desc:String) : Parcelable {
    val movieName:String = name
    val popularity: Double = pop
    val description: String = desc

}