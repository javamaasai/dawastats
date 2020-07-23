package com.dawastatistics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dawastatistics.R
import com.dawastatistics.ui.RetroFragment

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.network_layout)
        replaceFragment()
    }

    fun replaceFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_retro_room, RetroFragment())
            .commit()
    }
}