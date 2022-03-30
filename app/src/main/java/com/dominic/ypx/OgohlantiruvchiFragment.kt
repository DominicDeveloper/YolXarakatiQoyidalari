package com.dominic.ypx

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.dominic.ypx.Adapter.MyReciycle
import com.dominic.ypx.DataBase.MyDbHelper
import com.dominic.ypx.Target.Target
import kotlinx.android.synthetic.main.do_you_want_delete.view.*
import kotlinx.android.synthetic.main.fragment_ogohlantiruvchi.view.*

class OgohlantiruvchiFragment : Fragment() {
    lateinit var root:View
    lateinit var targets:ArrayList<Target>
    lateinit var myReciycle: MyReciycle
    lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       root = inflater.inflate(R.layout.fragment_ogohlantiruvchi, container, false)
        targets = ArrayList()

        myDbHelper = MyDbHelper(requireContext())
        loadData()


        
        return root
    }
    private fun loadData(){
        if(targets.isNotEmpty()){
            targets.clear()
        }
        val list = ArrayList<Target>()
        list.addAll(myDbHelper.getAllTargets())
        list.forEach {
            if (it.target_type == "Ogohlantiruvchi"){
                targets.add(it)
            }
        }
        sync()

    }
    private fun sync(){
        myReciycle = MyReciycle(requireContext(),targets,object : MyReciycle.RvClick{
            override fun onClickDelete(target: Target, cardView: CardView) {
               do_you_want_delete(true,target)
            }

            override fun onClickEdit(target: Target, cardView: CardView) {
                Objects.target = target
                findNavController().popBackStack(R.id.myHomeFragment,true)
                findNavController().navigate(R.id.editFragment)

            }

            override fun onCliclLike(target: Target, imageView: ImageView) {
                val saveTarget = target
                when(saveTarget.target_isliked){
                    "0" ->{
                        anim(imageView)
                        saveTarget.target_isliked = "1"}
                    "1" -> {
                        anim(imageView)
                        saveTarget.target_isliked = "0"}
                }
                myDbHelper.editTarget(saveTarget)
                loadData()

            }

            override fun onClickView(target: Target, cardView: CardView) {
                findNavController().popBackStack(R.id.myHomeFragment,false)
                findNavController().navigate(R.id.aboutFragment, bundleOf("target" to target))
            }
        })

        root.recycle_ogohlantiruvchi.adapter = myReciycle

    }
    private fun anim(imageView: ImageView)
    {
        val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.like_anim)
        imageView.startAnimation(animation)
    }
    private fun do_you_want_delete(answer:Boolean,target: Target){
        if (answer){
            val dialog = AlertDialog.Builder(requireContext()).create()
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.do_you_want_delete,null,false)
            dialog.setView(view)
            dialog.show()
            view.item_no.setOnClickListener {
                dialog.dismiss()
            }
            view.item_yes.setOnClickListener {
                myDbHelper.deleteTarget(target)
                Toast.makeText(requireContext(), "O`chirildi!", Toast.LENGTH_SHORT).show()
                loadData()
                dialog.cancel()
            }

        }else{
            Toast.makeText(requireContext(), "Dismissed error! :(", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }



}