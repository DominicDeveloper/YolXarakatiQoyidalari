package com.dominic.ypx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.dominic.ypx.Adapter.MyAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.view.*

class MyHomeFragment : Fragment() {
    lateinit var root:View
    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2
    lateinit var typeList:ArrayList<String>
    lateinit var myAdapter: MyAdapter
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_my_home, container, false)
        typeList = ArrayList()
        tabLayout = root.findViewById(R.id.my_tablayout)
        viewPager2 = root.findViewById(R.id.my_viewpager2)
        myAdapter = MyAdapter(requireActivity(),typeList)
        loadTypes()
        val iconBack:ImageView = root.findViewById(R.id.icon_back)
        iconBack.isVisible = false

        viewPager2.adapter = myAdapter

        TabLayoutMediator(tabLayout,viewPager2,(TabLayoutMediator.TabConfigurationStrategy { tab, position ->  tab.setText(typeList[position])})).attach()

        val iconAdd:ImageView = root.findViewById(R.id.icon_add)
        iconAdd.setOnClickListener {
            findNavController().popBackStack(R.id.myHomeFragment,true)
            findNavController().navigate(R.id.addTarget)
        }


        return root
    }

    private fun loadTypes() {
        typeList.add("Ogohlantiruvchi")
        typeList.add("Imtizoyli")
        typeList.add("Taqiqlovchi")
        typeList.add("Buyuruvchi")

    }






}