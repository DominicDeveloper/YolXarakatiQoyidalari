package com.dominic.ypx

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
import androidx.navigation.fragment.findNavController
import com.dominic.ypx.Adapter.MyReciycle
import com.dominic.ypx.DataBase.MyDbHelper
import com.dominic.ypx.Target.Target
import kotlinx.android.synthetic.main.fragment_buyuruvchi.view.*

class BuyuruvchiFragment : Fragment() {
   lateinit var root:View
   lateinit var targets:ArrayList<Target>
   lateinit var myDbHelper: MyDbHelper
   lateinit var myReciycle: MyReciycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root =inflater.inflate(R.layout.fragment_buyuruvchi, container, false)
        targets = ArrayList()
        myDbHelper = MyDbHelper(requireContext())
        loadData()

        return root
    }

    private fun loadData() {
        if (targets.isNotEmpty()){
            targets.clear()
        }
        val list = ArrayList<Target>()
        list.addAll(myDbHelper.getAllTargets())
        list.forEach {
            if (it.target_type == "Buyuruvchi"){
                targets.add(it)
            }
        }
        sync()

    }
    private fun sync(){
        myReciycle = MyReciycle(requireContext(),targets,object : MyReciycle.RvClick{
            override fun onClickDelete(target: Target, cardView: CardView) {
                myDbHelper.deleteTarget(target)
                loadData()
                Toast.makeText(requireContext(), "O`chirildi", Toast.LENGTH_SHORT).show()
            }

            override fun onClickEdit(target: Target, cardView: CardView) {
                Objects.target = target
                findNavController().popBackStack(R.id.myHomeFragment,true)
                findNavController().navigate(R.id.editFragment)
            }

            override fun onCliclLike(target: Target, imageView: ImageView) {
                val saveTarget = target
               when(saveTarget.target_isliked){
                   "0" -> {
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
        root.buyuruvchi_recylce.adapter = myReciycle

    }
    private fun anim(imageView: ImageView)
    {
        val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.like_anim)
        imageView.startAnimation(animation)
    }

}