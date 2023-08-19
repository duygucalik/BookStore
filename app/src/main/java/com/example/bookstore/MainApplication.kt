package com.example.bookstore

import android.app.Application
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.bookstore.common.Constants.BASE_URL
import com.example.bookstore.data.source.remote.ProductService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainApplication:Application() {
    companion object {

        var productService: ProductService? = null

        private const val TIMEOUT = 60L

        fun provideRetrofit(context: Context) {

            val chuckerInterceptor = ChuckerInterceptor.Builder(context).build()

            val okHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(chuckerInterceptor)
                readTimeout(TIMEOUT, TimeUnit.SECONDS)
                connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            }.build()

            productService = Retrofit.Builder().apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(BASE_URL)
                client(okHttpClient)
            }.build().create(ProductService::class.java)
        }
    }
}