package com.dicoding.jlegends.detail

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2

import com.bumptech.glide.Glide
import com.dicoding.jlegends.R
import com.dicoding.jlegends.databinding.ActivityDetailUserBinding
import com.dicoding.jlegends.section.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val tabTitles = intArrayOf(R.string.tab_1, R.string.tab_2)

        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL= "extra_url"

    }

    private lateinit var binding:ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID,0)
        val avatarUrl =intent.getStringExtra(EXTRA_URL)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)

        viewModel.setUserDetail(username.toString())
        viewModel.getUserDetail().observe(this, {
            if (it != null) {
                showLoading(true)
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = it.followers.toString()
                    tvFollowing.text = it.following.toString()
                    tvCompany.text = it.company
                    tvLocation.text = it.location
                    tvRepository.text = it.public_repos.toString()
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(ivProfile)
                }
            }
            showLoading(false)
        })
        var _isChecked=false
         CoroutineScope(Dispatchers.IO).launch{
             val count= viewModel.checkUser(id)
             withContext(Dispatchers.Main){
                 if (count != null){
                     if(count>0){
                         binding.toggleFavorite.isChecked=true
                         _isChecked=true
                     }else {
                         binding.toggleFavorite.isChecked=false
                         _isChecked=false
                     }
                 }
             }

         }
         binding.toggleFavorite.setOnClickListener{
             _isChecked = ! _isChecked
             if (_isChecked){
                 viewModel.addToFavorite(username.toString(),id,avatarUrl.toString())

             }else {
                 viewModel.removeFromFavorite(id)
             }
         binding.toggleFavorite.isChecked=_isChecked

         }

        val sectionsPagerAdapter = SectionPagerAdapter(bundle, this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(tabTitles[position])
        }.attach()
        supportActionBar?.elevation = 0f

        supportActionBar?.let {
            it.title = getString(R.string.detail_user)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    @Suppress("SameParameterValue")
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

