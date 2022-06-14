package com.dicoding.jlegends.section

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.jlegends.followers.FollowersFragment
import com.dicoding.jlegends.following.FollowingFragment

class SectionPagerAdapter(data: Bundle, activity:AppCompatActivity) :
    FragmentStateAdapter(activity) {

    private var fragmentBundle: Bundle = data

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

//    override fun getPageTitle(position: Int): CharSequence {
//       return mCtx.resources.getString(tabTitles[position])
//    }
}
