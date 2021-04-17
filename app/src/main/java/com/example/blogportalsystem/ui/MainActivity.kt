package com.example.blogportalsystem.ui

import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.blogportalsystem.Notification.NotificationChannel
import com.example.blogportalsystem.R
import com.example.blogportalsystem.api.ServiceBuilder
//import com.example.blogportalsystem.db.UserDB
import com.example.blogportalsystem.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var tvRegister: TextView
    private lateinit var linearLayout: LinearLayout
    private lateinit var chkremember:CheckBox

    private var sensorManager: SensorManager? = null
    private var sensor: Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtemail = findViewById(R.id.edtemail)
        edtpassword = findViewById(R.id.edtpassword)
        btnlogin = findViewById(R.id.btnlogin)
        chkremember=findViewById(R.id.chkremember)

        tvRegister = findViewById(R.id.tvRegister)
        linearLayout = findViewById(R.id.linearLayout)
        getSharedPref()
        tvRegister.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
        }

        btnlogin.setOnClickListener {
            if(chkremember.isChecked){
                saveSharedPref()
            }
            login();

        }
        if (!checkSensor())
            return
        else {
            sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)
            sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

    }

    private fun login() {
        val email = edtemail.text.toString()
        val password = edtpassword.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.checkUser(email, password)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer " + response.token
                    startActivity(
                        Intent(
                            this@MainActivity,
                            DashboardActivity::class.java
                        )
                    )
                    finish()
                    showHighPriorityNotification()
                } else {
                    withContext(Dispatchers.Main) {
                        val snack =
                            Snackbar.make(
                                linearLayout,
                                "Invalid credentials",
                                Snackbar.LENGTH_LONG
                            )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "$ex", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    private fun saveSharedPref() {
         val email = edtemail.text.toString()
        val password = edtpassword.text.toString()
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
        Toast.makeText(
            this@MainActivity,
            "email and password saved",
            Toast.LENGTH_SHORT
        ).show()
    }
    private fun getSharedPref() {
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val email = sharedPref.getString("email", "")
        val password = sharedPref.getString("password", "")

        edtemail.setText("$email")
        edtpassword.setText("$password")
    }
    private fun showHighPriorityNotification() {

        val notificationManager = NotificationManagerCompat.from(this)

        val notificationChannels = NotificationChannel(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setContentTitle("Login Success")
            .setContentText("${edtemail.text} login successfully")
            .setColor(Color.BLUE)
            .build()

        notificationManager.notify(1, notification)

    }
    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT) == null) {
            flag = false
        }
        return flag
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        val values = p0!!.values[0]
        if(values>=6){
            linearLayout.setBackgroundColor(Color.BLACK);

        }
        else if(values<=5){
            linearLayout.setBackgroundColor(Color.WHITE);
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }
}