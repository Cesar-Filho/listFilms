package com.example.listfilms

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawerOpen,
            R.string.drawerClose
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.profile -> Toast.makeText(
                this@MainActivity,
                "Profile Selected",
                Toast.LENGTH_SHORT
            ).show()
            R.id.contact -> Toast.makeText(
                this@MainActivity,
                "Contact us Selected",
                Toast.LENGTH_SHORT
            ).show()
            R.id.about -> Toast.makeText(
                this@MainActivity,
                "About us Selected",
                Toast.LENGTH_SHORT
            ).show()
            R.id.logout -> Toast.makeText(
                this@MainActivity,
                "Logout Selected",
                Toast.LENGTH_SHORT
            ).show()
        }
        return false
    }
}
