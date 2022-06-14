package com.dicoding.jlegends

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.jlegends.legends.Legends
import com.dicoding.jlegends.legends.LegendsAdapter
import com.dicoding.jlegends.legends.LegendsData

class MainActivity : AppCompatActivity() {

    private lateinit var  rvLegends: RecyclerView
    private var list: ArrayList<Legends> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionbar = supportActionBar
        actionbar!!.title = "Home"

        rvLegends = findViewById(R.id.rv_legends)
        rvLegends.setHasFixedSize(true)

        list.addAll(LegendsData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvLegends.layoutManager = LinearLayoutManager(this)
        val legendsAdapter = LegendsAdapter(list)
        rvLegends.adapter = legendsAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.miCompose -> {
                val iAbout = Intent(this@MainActivity,
                    About::class.java)
                startActivity(iAbout)
            }
        }
    }
}