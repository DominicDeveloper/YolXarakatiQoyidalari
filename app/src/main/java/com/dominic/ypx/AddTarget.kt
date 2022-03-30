package com.dominic.ypx

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dominic.ypx.DataBase.MyDbHelper
import com.dominic.ypx.Target.Target
import kotlinx.android.synthetic.main.fragment_add_target.view.*


class AddTarget : Fragment() {
    lateinit var root:View
    lateinit var TargetList:ArrayList<Target>
    lateinit var myDbHelper: MyDbHelper
    var myuri: Uri? = null
    var types = arrayListOf("Ogohlantiruvchi","Imtiyozli","Taqiqlovchi","Buyuruvchi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       root = inflater.inflate(R.layout.fragment_add_target,container,false)
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,types)
        TargetList = ArrayList()
        myDbHelper = MyDbHelper(requireContext())
        val targetname:EditText = root.findViewById(R.id.add_target_name)
        val targetinfo:EditText = root.findViewById(R.id.add_target_name_info)
        val iconadd:ImageView = root.findViewById(R.id.icon_add)
        val iconback:ImageView = root.findViewById(R.id.icon_back)

        iconadd.isVisible = false

        iconback.setOnClickListener {
            findNavController().popBackStack(R.id.addTarget,true)
            findNavController().navigate(R.id.myHomeFragment)
            iconadd.isVisible = true
        }
       root.add_target_type.setAdapter(adapter)

        root.add_image.setOnClickListener {
            startActivityForResult(Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            },1)
        }
        root.add_save_target.setOnClickListener {
            if (targetname.text.isNotEmpty() && targetinfo.text.isNotEmpty() && root.add_target_type.text.isNotEmpty()){
                myDbHelper.addTarget(Target(targetname.text.toString(),targetinfo.text.toString().trim(),myuri,root.add_target_type.text.toString(),"0"))
                findNavController().popBackStack(R.id.addTarget,true)
                findNavController().navigate(R.id.myHomeFragment)
                Toast.makeText(requireContext(), "Saqlandi!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Malumot to`liq emas!", Toast.LENGTH_SHORT).show()
            }
        }



        return root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            val uri =  data?.data ?: return
            root.add_image.setImageURI(uri)
            myuri = uri
            Objects.uri = uri
        Toast.makeText(requireContext(), "${Objects.uri}", Toast.LENGTH_SHORT).show()
        // uri string tipiga otkazib yoziladi sqlite ga


    }


}