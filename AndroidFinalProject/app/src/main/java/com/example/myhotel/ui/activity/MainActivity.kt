package com.example.myhotel.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.myhotel.R
import firstsearchFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            replace(R.id.firstscreen, firstsearchFragment())
        }

    }
    fun replaceFragment(fragment: Fragment, bundle: Bundle? = null) {
        fragment.arguments = bundle // Set bundle to fragment
        supportFragmentManager.commit {
            replace(R.id.firstscreen, fragment)
            addToBackStack(null)
        }
    }
}