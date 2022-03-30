package com.dominic.ypx

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.dominic.ypx.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var myHomeFragment: MyHomeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        bottomNavigationView = findViewById(R.id.my_nav_bottom)

        checkReadPermission()
        sy()
        myHomeFragment = MyHomeFragment()
       NavigationUI.setupWithNavController(bottomNavigationView,findNavController(R.id.my_static_fragment))



        my_static_fragment.findNavController().addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                when(destination.id){
                    R.id.sartFragment -> bottomNavigationView.visibility = View.GONE
                    R.id.myHomeFragment -> bottomNavigationView.visibility = View.VISIBLE
                    R.id.addTarget -> bottomNavigationView.visibility = View.GONE
                    R.id.aboutFragment -> bottomNavigationView.visibility = View.GONE

                }
            }
        })



    }
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_navigation).navigateUp()
    }
    private fun sy(){

        when(bottomNavigationView.id){
            R.id.yoqtirganlarFragment -> {

            }
            R.id.myHomeFragment ->{

            }
            R.id.appinfoFragment ->{

            }
        }


    }
    private fun checkReadPermission(){
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            // well
        }else{
           // don't give up next time try, you can!
            requestToPermission()
        }

    }

    private fun requestToPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1)
        if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
           // well
        }else{
            // well
        }

    }

}