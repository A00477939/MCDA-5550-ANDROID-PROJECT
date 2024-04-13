package com.example.myhotel.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhotel.data.model.Hotel
import com.example.myhotel.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HotelViewModel : ViewModel() {

    private val apiService: ApiService = ApiService.getInstance()
    val hotels: MutableLiveData<List<Hotel>> by lazy {
        MutableLiveData<List<Hotel>>()
    }

    fun getHotels() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getHotels()
                if (response.isSuccessful) {
                    hotels.postValue(response.body())
                }
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }
}



