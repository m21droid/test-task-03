package com.m21droid.booknet

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.m21droid.booknet.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD("onCreate: ")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            textViewMainRead.setOnClickListener(this@MainActivity)
            textViewMainArchive.setOnClickListener(this@MainActivity)
            textViewMainFavorite.setOnClickListener(this@MainActivity)
        }
    }

    override fun onStart() {
        super.onStart()

        navController = findNavController(binding.fragmentContainerViewMain)
    }

    override fun onClick(v: View?) {
        logD("onClick: view - $v")

        binding.apply {
            when (v) {
                textViewMainRead -> {
                    navController.navigateUp()
                    navController.navigate(R.id.firstMainFragment)
                }

                textViewMainArchive -> {
                    navController.navigateUp()
                    navController.navigate(R.id.secondMainFragment)
                }

                textViewMainFavorite -> {
                    navController.navigateUp()
                    navController.navigate(R.id.thirdMainFragment)
                }
            }
        }
    }

}