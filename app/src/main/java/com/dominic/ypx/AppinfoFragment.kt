package com.dominic.ypx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_appinfo.view.*

class AppinfoFragment : Fragment() {
    lateinit var root:View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_appinfo, container, false)

        root.appinfo_image.setImageResource(R.drawable.codial)


        return root
    }


}