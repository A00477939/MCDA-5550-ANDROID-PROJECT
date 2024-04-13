import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhotel.R
import com.example.myhotel.ViewModel.HotelViewModel
import com.example.myhotel.ui.activity.MainActivity
import com.example.myhotel.ui.adapter.HotelAdapter
import com.example.myhotel.ui.fragment.GuestDetailFragment
import com.example.myhotel.ui.fragment.ReservationFragment

class HotelFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var hotelAdapter: HotelAdapter
    private lateinit var hotelViewModel: HotelViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_secound, container, false)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("MyHotelPrefs", Context.MODE_PRIVATE)

        // Retrieve data from SharedPreferences
        val userName = sharedPreferences.getString("userName", "")
        val guestNumber = sharedPreferences.getString("guestNumber", "")
        val checkInDate = sharedPreferences.getString("check in date", "")
        val checkOutDate = sharedPreferences.getString("check out date", "")

        view.findViewById<TextView>(R.id.text_title).text = "Hey $userName, We have selected the hotel for accommodating $guestNumber guests from $checkInDate to $checkOutDate"

        // RecyclerView setup
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        hotelAdapter = HotelAdapter { clickedHotel ->
            // Handle item click here
            val bundle = Bundle().apply {
                putParcelable("clickedHotel", clickedHotel) // Pass the clicked hotel data to the next fragment
            }

            (requireActivity() as? MainActivity)?.replaceFragment(GuestDetailFragment(), bundle)
        }
        recyclerView.adapter = hotelAdapter

        // ViewModel setup
        hotelViewModel = ViewModelProvider(this).get(HotelViewModel::class.java)
        hotelViewModel.getHotels()

        hotelViewModel.hotels.observe(viewLifecycleOwner, { hotels ->
            hotelAdapter.setHotels(hotels)
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
