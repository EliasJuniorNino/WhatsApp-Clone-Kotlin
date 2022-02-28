package com.eliasjuniornino.zapzap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eliasjuniornino.zapzap.databinding.ActivityMainBinding
import com.eliasjuniornino.zapzap.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.mainContainer.id, HomeFragment.newInstance())
                .commitNow()
        }
    }
}