package com.dicoding.jlegends.legends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jlegends.R

class LegendsDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_legends_detail)

        val actionbar = supportActionBar
        actionbar!!.title = "Detail"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val tvSetName: TextView = findViewById(R.id.tv_set_name)
        val tvSetPosition: TextView = findViewById(R.id.tv_item_position)
        val imgSetPhoto: ImageView = findViewById(R.id.img_item_photo)
        val tvSetInfo: TextView = findViewById(R.id.tv_set_detail)
        val tvOverview: TextView = findViewById(R.id.tv_set_overview)

        val data = intent.getParcelableExtra<Legends>(EXTRA_LEGEND) as Legends

        tvSetName.text = data.name
        tvSetPosition.text= data.area
        Glide.with(this)
            .load(data.photo)
            .apply(RequestOptions())
            .into(imgSetPhoto)
        tvOverview.text = data.overview
        tvSetInfo.text = data.detail
    }

    companion object {
        const val EXTRA_LEGEND="extra_legend"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}