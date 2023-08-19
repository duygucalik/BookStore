package com.example.bookstore.data.model

data class GetBooksResponse(

    val books: List<Book?>?,
    val message: String?,
    val success: Int?
)