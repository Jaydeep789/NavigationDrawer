package com.example.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.navigationdrawer.Fragments.ChatFragment
import com.example.navigationdrawer.Fragments.MessageFragment
import com.example.navigationdrawer.Fragments.ProfileFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout
    private lateinit var nav_view: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer)
        nav_view = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, 0, 0)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, MessageFragment())
                .commit()
            nav_view.setCheckedItem(R.id.message)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.message -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, MessageFragment())
                    .commit()
            }
            R.id.chat -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, ChatFragment())
                    .commit()
            }
            R.id.profile -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, ProfileFragment())
                    .commit()
            }
            R.id.share -> {
                Toast.makeText(this, "Share Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.send -> {
                Toast.makeText(this, "Send Clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}