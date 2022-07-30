package com.dicoding.jlegends

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.jlegends.R.id.rv_member
import com.dicoding.jlegends.members.Legends
import com.dicoding.jlegends.members.LegendsAdapter
import com.dicoding.jlegends.members.LegendsData
import java.util.*

@Suppress("TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
class MainActivity : AppCompatActivity() {

    private lateinit var rvMember: RecyclerView
    private lateinit var searchView: SearchView
    private var list: ArrayList<Legends> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMember = findViewById(rv_member)
        rvMember.setHasFixedSize(true)
        list.clear()
        list.addAll(LegendsData.listData)
        showRecyclerList()

        supportActionBar?.title = "Home"

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView = findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = ""
        searchView.maxWidth= Int.MAX_VALUE
        searchView.clearFocus()

        @Suppress("UNCHECKED_CAST")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            val legendsAdapter = LegendsAdapter(list)
            override fun onQueryTextSubmit(query: String?): Boolean {
                val searchText = query.toString().toLowerCase(Locale.ROOT).trim()
                if (searchText.isNotEmpty() || searchText.isNotBlank()) {
                    legendsAdapter.filter.filter(query ?: "")
                    showRecyclerListItem(legendsAdapter)
                    return false
                }
                searchText.contains("")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText.toString().toLowerCase(Locale.ROOT).trim()

                if  (searchText.isNotEmpty()|| searchText.isNotBlank()){
                    legendsAdapter.filter.filter(newText ?: "")
                    showRecyclerListItem(legendsAdapter)
                    return false

               }else {
                        list.clear()
                        list.addAll(LegendsData.listData)
                        showRecyclerList()
               }
                searchText.contains("")
                return false
            }

        });


    }


    private fun showRecyclerList() {
        rvMember.layoutManager = LinearLayoutManager(this)
        val legendsAdapter = LegendsAdapter(list)
        rvMember.adapter = legendsAdapter
    }

    private fun showRecyclerListItem(legendsAdapter: LegendsAdapter) {
        rvMember.layoutManager = LinearLayoutManager(this)
        rvMember.adapter = legendsAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.about -> {
                Intent(this@MainActivity, AboutActivity::class.java).also {
                    startActivity(it)
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}
