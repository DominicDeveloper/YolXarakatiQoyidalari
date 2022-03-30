package com.dominic.ypx

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_sart.view.*

class SartFragment : Fragment() {
    lateinit var root:View
    lateinit var handler: android.os.Handler

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_sart, container, false)



        root.linear.setOnClickListener {
            findNavController().navigate(R.id.myHomeFragment)
            findNavController().popBackStack(R.id.sartFragment,true)
        }

        handler = android.os.Handler(Looper.getMainLooper())
        handler.postDelayed({
            findNavController().popBackStack(R.id.sartFragment,true)
                            findNavController().navigate(R.id.myHomeFragment)
        },1500)

    return  root
    }


}
