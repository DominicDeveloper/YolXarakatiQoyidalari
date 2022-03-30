package com.dominic.ypx.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dominic.ypx.BuyuruvchiFragment
import com.dominic.ypx.ImtiyozliFragment
import com.dominic.ypx.OgohlantiruvchiFragment
import com.dominic.ypx.TaqiqlovchiFragment

class MyAdapter(fragmentActivity: FragmentActivity, val title:ArrayList<String>): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return title.size
    }

    override fun createFragment(position: Int): Fragment {

        when (position) {
            0 -> return OgohlantiruvchiFragment()
            1 -> return ImtiyozliFragment()
            2 -> return TaqiqlovchiFragment()
            3 -> return BuyuruvchiFragment()

        }
        return OgohlantiruvchiFragment()
    }
}
