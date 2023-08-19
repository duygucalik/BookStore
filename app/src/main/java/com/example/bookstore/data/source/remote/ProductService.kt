package com.example.bookstore.data.source.remote

import com.example.bookstore.common.Constants
import com.example.bookstore.data.model.GetBookDetailResponse
import com.example.bookstore.data.model.GetBooksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET(Constants.Endpoint.GET_BOOKS)
    fun getProducts(): Call<GetBooksResponse>

    @GET(Constants.Endpoint.GET_BOOK_DETAIL)
    fun getBookDetail(
        @Query("id") id: Int
    ): Call<GetBookDetailResponse>
}