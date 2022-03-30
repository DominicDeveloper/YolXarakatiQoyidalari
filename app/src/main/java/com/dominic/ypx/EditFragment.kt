package com.dominic.ypx

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dominic.ypx.DataBase.MyDbHelper
import com.dominic.ypx.Target.Target
import kotlinx.android.synthetic.main.fragment_edit.view.*
import java.io.InputStream

class EditFragment : Fragment() {
    lateinit var root:View
    var myuri: Uri? = null
    lateinit var myDbHelper: MyDbHelper
    var types = arrayListOf("Ogohlantiruvchi","Imtiyozli","Taqiqlovchi","Buyuruvchi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_edit, container, false)
        myDbHelper = MyDbHelper(requireContext())
        val targetme  = Objects.target

        sync(targetme!!)

        root.edit_image.setOnClickListener {
                startActivityForResult(Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "image/*"
                },2)
        }
        root.edit_save_target.setOnClickListener {
               if (myuri != null){
                   myDbHelper.editTarget(Target(targetme.id,root.edit_target_name.text.toString(),root.edit_target_name_info.text.toString(),myuri,root.edit_target_type.text.toString(),targetme.target_isliked))
                   Toast.makeText(requireContext(), "O`zgartirildi!", Toast.LENGTH_SHORT).show()
                   findNavController().popBackStack(R.id.editFragment,true)
                   findNavController().navigate(R.id.myHomeFragment)
               }else{
                   myDbHelper.editTarget(Target(targetme.id,root.edit_target_name.text.toString(),root.edit_target_name_info.text.toString(),targetme.target_image,root.edit_target_type.text.toString(),targetme.target_isliked))
                   Toast.makeText(requireContext(), "O`zgartirildi!", Toast.LENGTH_SHORT).show()
                   findNavController().popBackStack(R.id.editFragment,true)
                   findNavController().navigate(R.id.myHomeFragment)
               }

        }

        return root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val uri =  data?.data ?: return
        root.edit_image.setImageURI(uri)
        myuri = uri
    }

    private fun sync(target: Target){
        /*
        imgView.setImageDrawable(Drawable.createFromStream(
                    getContentResolver().openInputStream(imgUri),
                    null));
         */

            root.edit_image.setImageURI(target.target_image)
            root.edit_image.setImageURI(target.target_image)
            root.edit_target_name.setText(target.target_name)
            root.edit_target_name_info.setText(target.target_info)
            root.edit_target_type.setText(target.target_type)
            val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,types)
            root.edit_target_type.setAdapter(adapter)
    }
}