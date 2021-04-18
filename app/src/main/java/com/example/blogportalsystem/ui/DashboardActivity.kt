package com.example.blogportalsystem.ui

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.PowerManager
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.blogportalsystem.R
import com.example.blogportalsystem.adapter.ViewPagerAdapter
import com.example.blogportalsystem.fragments.AboutFragment
import com.example.blogportalsystem.fragments.HomeFragment
import com.example.blogportalsystem.fragments.MoreFragment
import com.example.blogportalsystem.fragments.ProfileFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DashboardActivity : AppCompatActivity(), SensorEventListener {
    private val permissions= arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var lstTitle:ArrayList<String>
    private lateinit var lstFragments:ArrayList<Fragment>
    private lateinit var Searchbox:SearchView
    private lateinit var DasrecyclerView:RecyclerView
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        viewPager=findViewById(R.id.viewPager)
        tabLayout=findViewById(R.id.tabLayout)
        DasrecyclerView=findViewById(R.id.DasrecyclerView)
        Searchbox=findViewById(R.id.Searchbox)

        if (!hasPermission()) {
            requestPermission()
        }
        loadfragment()

        val adapter= ViewPagerAdapter(lstFragments, supportFragmentManager, lifecycle)
        viewPager.adapter=adapter
        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text=lstTitle[position]
        }.attach()

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_home_24)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_person_24)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_local_phone_24)
        tabLayout.getTabAt(3)!!.setIcon(R.drawable.ic_baseline_menu_24)

//        if (!checkSensor())
//            return
//        else {
//            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
//            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
//        }
    }
//    o8p][

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

        lstFragments= ArrayList<Fragment>()
        lstFragments.add(HomeFragment())
        lstFragments.add(ProfileFragment())
        lstFragments.add(AboutFragment())
        lstFragments.add(MoreFragment())

    }

    override fun onSensorChanged(event: SensorEvent?) {
//        val values = event!!.values[0];
//        if (values <= 1) {
//            val builder = AlertDialog.Builder(this);
//            builder.setTitle("Object seems near")
//            builder.setMessage("Please donot cover the Camera or microphone");
//            builder.setIcon(android.R.drawable.ic_dialog_alert);
//            var alert = builder.create();
//            alert.setCancelable(true);
//            alert.show();
//        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

}