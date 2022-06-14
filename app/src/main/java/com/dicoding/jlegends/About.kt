package com.dicoding.jlegends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_about)

        if (supportActionBar != null) {
            supportActionBar!!.title = "About";
            supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        OnBackPressedDispatcher()
        return super.onSupportNavigateUp()
        }

    private fun onClick(): Boolean {
        return true
    }

}