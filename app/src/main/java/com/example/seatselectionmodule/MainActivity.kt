package com.example.seatselectionmodule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { supportFragmentManager.beginTransaction().addToBackStack(null).add(R.id.id_root, SeatSelectionFragment()).commit() }
    }
}