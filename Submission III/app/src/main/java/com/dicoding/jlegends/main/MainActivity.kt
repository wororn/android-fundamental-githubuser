package com.dicoding.jlegends.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.jlegends.R

import com.dicoding.jlegends.about.AboutActivity
import com.dicoding.jlegends.databinding.ActivityMainBinding
import com.dicoding.jlegends.detail.DetailUserActivity
import com.dicoding.jlegends.favorite.FavoriteActivity
import com.dicoding.jlegends.setmodes.SettingPreferences
import com.dicoding.jlegends.setmodes.ThemesActivity
import com.dicoding.jlegends.setmodes.ThemesViewModel
import com.dicoding.jlegends.setmodes.ViewModelFactory
import com.dicoding.jlegends.user.User
import com.dicoding.jlegends.user.UserAdapter

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(dataStore)
        val ThemesViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            ThemesViewModel::class.java
        )
        ThemesViewModel.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                }
            })

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
           override fun onItemClicked(data: User) {
               Intent(this@MainActivity, DetailUserActivity::class.java).also {
                   it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                   it.putExtra(DetailUserActivity.EXTRA_ID,data.id)
                   it.putExtra(DetailUserActivity.EXTRA_URL,data.avatar_url)
                   startActivity(it)
               }
           }

       })

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            val searchView = binding.SearchView

                searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
                searchView.queryHint = getString(R.string.Cari)
                showLoading(false)
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        viewModel.setSearchUsers(query ?: "")
                        showLoading(true)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }

                })

        }
        viewModel.getSearchUser().observe(this, {
            if (it != null) {
                if (it.size == 0) {
                    Toast.makeText(applicationContext, "Sorry, No Result Found", Toast.LENGTH_SHORT)
                            .show()
                }
                adapter.setList(it)
                showLoading(false)

            } else {
            showLoading(false)}
        })

    }

        @Suppress("SameParameterValue")
        private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                Intent(this@MainActivity, AboutActivity::class.java).also{
                    startActivity(it)
                }
                true
            }
            R.id.favorite_menu -> {
                Intent(this@MainActivity, FavoriteActivity::class.java).also{
                    startActivity(it)
                }
                true
            }

            R.id.theme_mode -> {
                Intent(this@MainActivity, ThemesActivity::class.java).also{
                    startActivity(it)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

