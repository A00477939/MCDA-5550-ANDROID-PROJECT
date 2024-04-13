package com.example.myhotel.data.network

import com.example.myhotel.data.model.BookingRequest
import com.example.myhotel.data.model.Hotel
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @GET("hotel")
    suspend fun getHotels() : Response<List<Hotel>>
    @POST("hotel")
    suspend fun createReservation(@Body bookingRequest: BookingRequest): Response<String>


    companion object {
        private var apiService: ApiService? = null

        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000/")
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}
