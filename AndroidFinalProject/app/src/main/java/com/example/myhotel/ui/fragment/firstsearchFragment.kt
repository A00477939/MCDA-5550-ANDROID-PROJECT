import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myhotel.R
import com.example.myhotel.ui.activity.MainActivity
import java.util.*

class firstsearchFragment : Fragment() {

    private lateinit var startDateButton: Button
    private lateinit var endDateButton: Button
    private lateinit var userName: EditText
    private lateinit var guestNumber: EditText
    private lateinit var searchButton: Button
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var check_in_date: Button
    private lateinit var check_out_date: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startDateButton = view.findViewById(R.id.startdate)
        endDateButton = view.findViewById(R.id.enddate)
        searchButton = view.findViewById(R.id.btn_search)
        userName = view.findViewById(R.id.edit_name)
        guestNumber = view.findViewById(R.id.edit_guests)
        check_in_date = view.findViewById(R.id.startdate)
        check_out_date = view.findViewById(R.id.enddate)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("MyHotelPrefs", Context.MODE_PRIVATE)

        userName.setText(sharedPreferences.getString("userName", ""))
        guestNumber.setText(sharedPreferences.getString("guestNumber", ""))
        check_in_date.setText(sharedPreferences.getString("check in date", ""))
        check_out_date.setText(sharedPreferences.getString("check out date", ""))

        startDateButton.setOnClickListener {
            showDatePicker(startDateButton)
        }

        endDateButton.setOnClickListener {
            showDatePicker(endDateButton)
        }

        searchButton.setOnClickListener {

            val userNameText = userName.text.toString().trim()
            val guestNumberText = guestNumber.text.toString().trim()
            val checkInDateText = check_in_date.text.toString().trim()
            val checkOutDateText = check_out_date.text.toString().trim()

            if (userNameText.isEmpty() || guestNumberText.isEmpty() || checkInDateText.isEmpty() || checkOutDateText.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val guestNumberInt = guestNumberText.toInt()
            if (guestNumberInt > 5) {
                Toast.makeText(requireContext(), "Guest number cannot be more than 5", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val checkInDate = Calendar.getInstance()
            val checkOutDate = Calendar.getInstance()

            val checkInDateParts = checkInDateText.split("/")
            checkInDate.set(checkInDateParts[2].toInt(), checkInDateParts[1].toInt() - 1, checkInDateParts[0].toInt())

            val checkOutDateParts = checkOutDateText.split("/")
            checkOutDate.set(checkOutDateParts[2].toInt(), checkOutDateParts[1].toInt() - 1, checkOutDateParts[0].toInt())

            if (checkOutDate.before(checkInDate)) {
                Toast.makeText(requireContext(), "Check-out date cannot be before check-in date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save user inputs to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("userName", userNameText)
            editor.putString("guestNumber", guestNumberText)
            editor.putString("check in date", checkInDateText)
            editor.putString("check out date", checkOutDateText)
            editor.apply()

            Toast.makeText(requireContext(), "User name and guest number saved!", Toast.LENGTH_SHORT).show()

            (requireActivity() as? MainActivity)?.replaceFragment(HotelFragment())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDatePicker(button: Button) {
        // Get current date
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireActivity(),
            { _, year, monthOfYear, dayOfMonth ->
                button.text = "$dayOfMonth/${monthOfYear + 1}/$year"
            }, year, month, day)

        datePickerDialog.show()
    }
}
