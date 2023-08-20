package com.lipatovfl.bulletinboardkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.lipatovfl.bulletinboardkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        setSupportActionBar(bindingClass.mainContentLayout.toolbar)
        init()
    }

    private fun init() {
        val toggle = ActionBarDrawerToggle(
            this,
            bindingClass.drawerLayout,
            bindingClass.mainContentLayout.toolbar,
            R.string.open,
            R.string.close
        )
        bindingClass.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }
}