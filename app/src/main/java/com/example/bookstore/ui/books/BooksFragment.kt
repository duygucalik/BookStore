package com.example.bookstore.ui.books

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.bookstore.MainApplication
import com.example.bookstore.R
import com.example.bookstore.common.viewBinding
import com.example.bookstore.data.model.Book
import com.example.bookstore.data.model.GetBooksResponse
import com.example.bookstore.databinding.FragmentBooksBinding
import com.example.bookstore.ui.books.adapter.BooksAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksFragment : Fragment(R.layout.fragment_books), BooksAdapter.BookListener {

    private val binding by viewBinding(FragmentBooksBinding::bind)

    private val booksAdapter by lazy { BooksAdapter(this) }

    var bestSellerBook : Book? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvBooks.adapter = booksAdapter
            ivBestSellerBook.setOnClickListener {
                val action = bestSellerBook?.id?.let { it ->
                    BooksFragmentDirections.HomeToDetail(it)
                }
                if (action != null) {
                    findNavController().navigate(action)
                }
            }
            getBooks()
        }
    }

    private fun getBooks() {
        MainApplication.productService?.getProducts()?.enqueue(object :
            Callback<GetBooksResponse> {
            override fun onResponse(call: Call<GetBooksResponse>, response: Response<GetBooksResponse>) {
                val result = response.body()?.books

                bestSellerBook = result?.firstOrNull { it!!.bestSeller }

                bestSellerBook?.id
                bestSellerBook?.let {
                    Glide.with(requireContext())
                        .load(it.imageUrl)
                        .into(binding.ivBestSellerBook)
                }

                if (result.isNullOrEmpty().not()) {
                    booksAdapter.submitList(result)
                }
            }

            override fun onFailure(call: Call<GetBooksResponse>, t: Throwable) {
                Log.e("GetProducts", t.message.orEmpty())
            }
        })
    }

    override fun onBookClick(id: Int) {
        val action = BooksFragmentDirections.HomeToDetail(id)
        findNavController().navigate(action)
    }
}