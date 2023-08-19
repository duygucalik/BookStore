package com.example.bookstore.data.model


import com.google.gson.annotations.SerializedName

data class Book(
    val author: String?,
    val id: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    val name: String?,
    val price: Double?,
    val publisher: String?,
    @SerializedName("is_best_seller") val bestSeller: Boolean
)