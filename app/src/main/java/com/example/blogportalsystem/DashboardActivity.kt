package com.example.blogportalsystem

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private val permissions= arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION

    )
    private lateinit var drawerlayout:DrawerLayout
    private lateinit var apptoolbar:androidx.appcompat.widget.Toolbar
    private lateinit var navmenu:NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        drawerlayout=findViewById(R.id.drawerlayout)
        apptoolbar=findViewById(R.id.apptoolbar)
        navmenu=findViewById(R.id.navmenu)

        if (!hasPermission()) {
            requestPermission()
        }
        setSupportActionBar(apptoolbar)

        val toogle=ActionBarDrawerToggle(this,drawerlayout,apptoolbar,0,0)
        drawerlayout.addDrawerListener(toogle)
        toogle.syncState()
        navmenu.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
            R.id.nav_home->{
                Toast.makeText(this@DashboardActivity, "Home navigation has been clicked", Toast.LENGTH_SHORT).show()
            }
           R.id.nav_profile->{
               Toast.makeText(this@DashboardActivity, "Home navigation has been clicked", Toast.LENGTH_SHORT).show()
           }
           R.id.nav_post->{
               Toast.makeText(this@DashboardActivity, "Post navigation has been clicked", Toast.LENGTH_SHORT).show()
           }
           R.id.nav_about->{
               Toast.makeText(this@DashboardActivity, "About navigation has been clicked", Toast.LENGTH_SHORT).show()
           }
           R.id.nav_notification->{
               Toast.makeText(this@DashboardActivity, "Notification navigation has been clicked", Toast.LENGTH_SHORT).show()
           }
           R.id.nav_logout->{
               Toast.makeText(this@DashboardActivity, "Logout navigation has been clicked", Toast.LENGTH_SHORT).show()
           }
       }
        drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@DashboardActivity,
            permissions, 1434
        )
    }
    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
            }
        }
        return hasPermission
    }

}