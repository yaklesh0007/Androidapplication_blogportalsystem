package com.example.blogportalsystem

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.blogportalsystem.adapter.ViewPagerAdapter
import com.example.blogportalsystem.fragments.AboutFragment
import com.example.blogportalsystem.fragments.HomeFragment
import com.example.blogportalsystem.fragments.MoreFragment
import com.example.blogportalsystem.fragments.ProfileFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DashboardActivity : AppCompatActivity() {
    private val permissions= arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION

    )
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var lstTitle:ArrayList<String>
    private lateinit var lstFragments:ArrayList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        viewPager=findViewById(R.id.viewPager)
        tabLayout=findViewById(R.id.tabLayout)
        if (!hasPermission()) {
            requestPermission()
        }
        val adapter= ViewPagerAdapter(lstFragments,supportFragmentManager,lifecycle)
        viewPager.adapter=adapter
        TabLayoutMediator(tabLayout,viewPager){tab, position ->
            tab.text=lstTitle[position]
        }.attach()
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
    private fun loadfragment(){
        lstTitle=ArrayList<String>()
        lstTitle.add("Home")
        lstTitle.add("Profile")
        lstTitle.add("Contact")
        lstTitle.add("More")
        // set icons
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_home_24)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_person_24)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_local_phone_24)
        tabLayout.getTabAt(3)!!.setIcon(R.drawable.ic_baseline_menu_24)
        lstFragments= ArrayList<Fragment>()
        lstFragments.add(HomeFragment())
        lstFragments.add(ProfileFragment())
        lstFragments.add(AboutFragment())
        lstFragments.add(MoreFragment())

    }

}