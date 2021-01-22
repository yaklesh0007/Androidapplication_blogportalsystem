package com.example.blogportalsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerlayout:DrawerLayout
    private lateinit var apptoolbar:androidx.appcompat.widget.Toolbar
    private lateinit var navmenu:NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        drawerlayout=findViewById(R.id.drawerlayout)
        apptoolbar=findViewById(R.id.apptoolbar)
        navmenu=findViewById(R.id.navmenu)

        setSupportActionBar(apptoolbar)

        val toogle=ActionBarDrawerToggle(this,drawerlayout,apptoolbar,0,0)
        drawerlayout.addDrawerListener(toogle)
        toogle.syncState()
        navmenu.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
            R.id.nav_home->{
                Toast.makeText(this, "Home navigation has been clicked", Toast.LENGTH_SHORT).show()
            }
           R.id.nav_profile->{
               Toast.makeText(this, "Home navigation has been clicked", Toast.LENGTH_SHORT).show()
           }
           R.id.nav_post->{
               Toast.makeText(this, "Post navigation has been clicked", Toast.LENGTH_SHORT).show()
           }
           R.id.nav_about->{
               Toast.makeText(this, "About navigation has been clicked", Toast.LENGTH_SHORT).show()
           }
           R.id.nav_notification->{
               Toast.makeText(this, "Notification navigation has been clicked", Toast.LENGTH_SHORT).show()
           }
           R.id.nav_logout->{
               Toast.makeText(this, "Logout navigation has been clicked", Toast.LENGTH_SHORT).show()
           }
       }
        drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }
}