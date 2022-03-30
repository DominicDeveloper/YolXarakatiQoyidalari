package com.dominic.ypx.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.dominic.ypx.MainActivity
import com.dominic.ypx.Objects
import com.dominic.ypx.R
import com.dominic.ypx.Target.Target
import kotlinx.android.synthetic.main.item_target.view.*

class MyReciycle(val context: Context, val list:ArrayList<Target>, val rvClick: RvClick) : RecyclerView.Adapter<MyReciycle.VH>() {

        inner class VH(var itemRv: View): RecyclerView.ViewHolder(itemRv){
            @SuppressLint("SetTextI18n")
            fun onBind(target: Target){

                if (target.target_isliked == "1"){
                    itemRv.item_target_like.setImageResource(R.drawable.ic_baseline_favorite_24)
                }else{
                    itemRv.item_target_like.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
                itemRv.item_target_name.text = target.target_name


                itemRv.item_image_target.setImageURI(target.target_image)
                itemRv.item_target_delete.setOnClickListener {
                    rvClick.onClickDelete(target,itemRv.findViewById(R.id.item_target_delete))
                }
                itemRv.item_target_edit.setOnClickListener {
                    rvClick.onClickEdit(target,itemRv.findViewById(R.id.item_target_edit))
                }
                itemRv.item_target_like.setOnClickListener {
                    rvClick.onCliclLike(target,itemRv.findViewById(R.id.item_image_target))
                    val anim = AnimationUtils.loadAnimation(context,R.anim.like_anim)
                    itemRv.item_target_like.startAnimation(anim)


                }
                itemRv.item_card.setOnClickListener {
                    rvClick.onClickView(target,itemRv.findViewById(R.id.item_card))
                }


            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return  VH(LayoutInflater.from(parent.context).inflate(R.layout.item_target,parent,false))
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.onBind(list[position])
        }

        override fun getItemCount(): Int {
            return  list.size
        }
        interface RvClick{
            fun onClickDelete(target: Target, cardView: CardView)
            fun onClickEdit(target: Target, cardView: CardView)
            fun onCliclLike(target: Target,imageView: ImageView)
            fun onClickView(target: Target,cardView: CardView)

        }




}