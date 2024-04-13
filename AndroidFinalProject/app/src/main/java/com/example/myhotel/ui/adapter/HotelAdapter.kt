package com.example.myhotel.ui.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myhotel.R
import com.example.myhotel.data.model.Hotel

class HotelAdapter(private val onItemClick: (Hotel) -> Unit) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>()  {

    private var hotels: List<Hotel> = ArrayList()

    fun setHotels(hotels: List<Hotel>) {
        this.hotels = hotels
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hotellistview, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hotels[position]
        holder.bind(hotel)
        holder.itemView.setOnClickListener {
            onItemClick(hotel)
        }

    }
    override fun getItemCount(): Int {
        return hotels.size
    }

    inner class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val availabilityTextView: TextView = itemView.findViewById(R.id.Availability)
        private val priceTextView: TextView = itemView.findViewById(R.id.price)

        fun bind(hotel: Hotel) {
            nameTextView.text = hotel.name
            availabilityTextView.text = hotel.avability
            priceTextView.text = hotel.price
        }
    }
}
