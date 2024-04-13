package com.example.myhotel.data.model

data class BookingRequest(
    val user: User,
    val hotel_id: String,
    val check_in_date: String,
    val check_out_date: String,
    val guests: List<Guest>
)
