package com.dicoding.jlegends.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.jlegends.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_about)

         supportActionBar?.title = "About"
         supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onClick()
        return super.onSupportNavigateUp()
    }

    private fun onClick(): Boolean {
        return true
    }

}