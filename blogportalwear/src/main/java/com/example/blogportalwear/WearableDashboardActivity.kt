package com.example.blogportalwear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class WearableDashboardActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wearable_dashboard)

        // Enables Always-on
        setAmbientEnabled()
    }
}