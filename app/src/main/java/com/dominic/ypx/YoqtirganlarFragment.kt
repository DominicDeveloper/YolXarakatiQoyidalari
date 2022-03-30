package com.dominic.ypx

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
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
import kotlinx.android.synthetic.main.fragment_yoqtirganlar.view.*

class YoqtirganlarFragment : Fragment() {
   lateinit var root:View
   lateinit var targetLikes:ArrayList<Target>
   lateinit var myDbHelper: MyDbHelper
   lateinit var myReciycle: MyReciycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_yoqtirganlar, container, false)
        targetLikes = ArrayList()
        myDbHelper = MyDbHelper(requireContext())

        loadData()



        return root
    }

    private fun loadData() {
        if (targetLikes.isNotEmpty()){
            targetLikes.clear()
        }
        val list = ArrayList<Target>()
        list.addAll(myDbHelper.getAllTargets())
                list.forEach {
                    if (it.target_isliked == "1"){
                        targetLikes.add(it)
                    }
                }
        sync()
    }
    private fun sync(){
        if (targetLikes.isEmpty()){
            root.yogtirilganlar_recylce.visibility = View.GONE
            root.hereismepty_image.visibility = View.VISIBLE
            root.hereisempty_text.visibility = View.VISIBLE
            empty_anim()

        }else{
            root.yogtirilganlar_recylce.visibility = View.VISIBLE
            root.hereismepty_image.visibility = View.GONE
            root.hereisempty_text.visibility = View.GONE
        }

        myReciycle = MyReciycle(requireContext(),targetLikes,object : MyReciycle.RvClick{
            override fun onClickDelete(target: Target, cardView: CardView) {
                myDbHelper.deleteTarget(target)
                loadData()
                Toast.makeText(requireContext(), "O`chirildi!", Toast.LENGTH_SHORT).show()
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
                    "1" ->{
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
        root.yogtirilganlar_recylce.adapter = myReciycle

    }
    private fun anim(imageView: ImageView)
    {
        val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.like_anim)
        imageView.startAnimation(animation)
    }
    private fun empty_anim(){
        val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.empty_anim)
        root.hereismepty_image.startAnimation(anim)
        anim.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

                absent()
            }

            override fun onAnimationEnd(p0: Animation?) {


            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }
    private fun absent(){
        val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.absent)
        root.hereisempty_text.startAnimation(anim)
        anim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {


            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }
}