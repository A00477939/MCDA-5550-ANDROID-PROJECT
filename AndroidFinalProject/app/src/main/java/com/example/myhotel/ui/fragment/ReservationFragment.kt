package com.example.myhotel.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myhotel.R


class ReservationFragment : Fragment() {

    private var reservationNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            reservationNumber = bundle.getString("reservationNumber")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.reservation_id_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load GIF using Glide
        val gif: ImageView = view.findViewById(R.id.gify)
        Glide.with(this)
            .asGif()
            .load(R.drawable.refund) // Specify the GIF resource
            .into(gif) // Specify the target view

        // Display the reservation number
        reservationNumber?.let { number ->
            val reservationNumberTextView: TextView = view.findViewById(R.id.messageTextView)
            reservationNumberTextView.text = "Reservation number: $number"
        }
    }
}
