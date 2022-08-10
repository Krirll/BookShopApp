package ru.krirll.testtask.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.krirll.testtask.data.network.models.BookItemDto
import ru.krirll.testtask.data.network.models.ImageItemDto

interface ApiService {

    @GET("carousel")
    suspend fun getCarouselImages(): List<ImageItemDto>

    @GET("best")
    suspend fun getBestSellerBooks(): List<BookItemDto>

    @GET("similar")
    suspend fun getSimilarBooks(): List<ImageItemDto>

    companion object {

        private var service: ApiService? = null
        private val INSTANCE = Any()

        //TODO что нужно делать здесь?
        fun getApiServiceInstance(): ApiService =
            synchronized(INSTANCE) {
                if (service == null)
                    service = Retrofit.Builder()
                        .baseUrl("https://my-json-server.typicode.com/stellardiver/ebookdata/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(ApiService::class.java)
                return service!!
            }
    }
}