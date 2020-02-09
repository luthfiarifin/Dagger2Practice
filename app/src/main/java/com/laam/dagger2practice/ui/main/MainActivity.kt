package com.laam.dagger2practice.ui.main

import android.os.Bundle
import android.widget.Toast
import com.laam.dagger2practice.BaseActivity
import com.laam.dagger2practice.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show()
    }
}