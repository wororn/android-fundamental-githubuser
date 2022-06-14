package com.dicoding.jlegends.detail

import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher
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


class DetailUserActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val tabTitles = intArrayOf(R.string.tab_1, R.string.tab_2)

        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding:ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailUserViewModel::class.java)

        viewModel.setUserDetail(username.toString())
        viewModel.getUserDetail().observe(this, {
            if (it != null) {
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
        })

        val sectionsPagerAdapter = SectionPagerAdapter(bundle, AppCompatActivity())
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

    override fun onSupportNavigateUp(): Boolean {
        OnBackPressedDispatcher()
        return super.onSupportNavigateUp()
    }
}

