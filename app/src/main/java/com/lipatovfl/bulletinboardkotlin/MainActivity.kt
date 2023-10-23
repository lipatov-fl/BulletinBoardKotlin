package com.lipatovfl.bulletinboardkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.lipatovfl.bulletinboardkotlin.databinding.ActivityMainBinding
import com.lipatovfl.bulletinboardkotlin.dialogHelper.DialogConst
import com.lipatovfl.bulletinboardkotlin.dialogHelper.DialogHelper

class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {
    private lateinit var tvAccount: TextView
    private lateinit var bindingClass: ActivityMainBinding
    private val dialogHelper = DialogHelper(this)
    val mainAuth = FirebaseAuth.getInstance() // Иницилизируем наш обьект mainAuth
    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        setSupportActionBar(bindingClass.mainContentLayout.toolbar)
        init()
    }

    override fun onStart() {
        super.onStart()
        uiUpdate(mainAuth.currentUser)
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
        bindingClass.navView.setNavigationItemSelectedListener(this)
        tvAccount = bindingClass.navView.getHeaderView(0).findViewById(R.id.tvAccountEmail)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_my_ads -> {
                Toast.makeText(this, "Presed id_my_ads", Toast.LENGTH_LONG).show()
            }

            R.id.id_car -> {
                Toast.makeText(this, "Presed id_car", Toast.LENGTH_LONG).show()
            }

            R.id.id_ps -> {
                Toast.makeText(this, "Presed id_ps", Toast.LENGTH_LONG).show()
            }

            R.id.id_smartphone -> {
                Toast.makeText(this, "Presed id_smartphone", Toast.LENGTH_LONG).show()
            }

            R.id.id_applianes -> {
                Toast.makeText(this, "Presed id_applianes", Toast.LENGTH_LONG).show()
            }

            R.id.id_sign_up -> {
                dialogHelper.createSignDialog(DialogConst.SIGN_UP_STATE)
            }

            R.id.id_sign_in -> {
                dialogHelper.createSignDialog(DialogConst.SIGN_IN_STATE)
            }

            R.id.id_sign_out -> {
                uiUpdate(null)
                mainAuth.signOut()
            }
        }
        bindingClass.drawerLayout.closeDrawer(GravityCompat.START) // Закрытие меню
        return true
    }

    fun uiUpdate(user: FirebaseUser?) {
        tvAccount.text = if(user == null) {
            resources.getString(R.string.not_reg)
        } else{
            user.email
        }
    }
}