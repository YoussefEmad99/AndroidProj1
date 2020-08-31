package com.example.androidproj1.network

import com.google.gson.annotations.SerializedName

data class APIImageResponse (@SerializedName("backdrops")
                             val images: List<Image>)

data class Image(@SerializedName("file_path")
                 val filePath: String)