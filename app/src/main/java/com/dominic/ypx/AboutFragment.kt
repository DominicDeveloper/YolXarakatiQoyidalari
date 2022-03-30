package com.dominic.ypx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.dominic.ypx.Target.Target
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutFragment : Fragment() {
    lateinit var root:View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_about, container, false)
        val iconBack:ImageView = root.findViewById(R.id.icon_back)
        val iconAdd:ImageView = root.findViewById(R.id.icon_add)
        iconAdd.isVisible = false
        iconBack.setOnClickListener {
            findNavController().popBackStack(R.id.aboutFragment,true)

        }
        val getTarget = arguments?.getSerializable("target") as Target

        isHas(getTarget)


        return root
    }
    private fun isHas(target: Target?){
        if (target != null){
            root.aboutTarget_image.setImageURI(target.target_image)
            root.aboutTarget_name.setText(target.target_name)
            root.aboutTarget_about.setText(target.target_info)
        }
    }

}