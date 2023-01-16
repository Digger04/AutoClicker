package com.example.autoclicker.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.autoclicker.PackageName.Constants
import com.example.autoclicker.R
import com.example.autoclicker.Service.FloatingBarService

private lateinit var bt_openmess : Button

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
        setStatusbar()
    }

    private fun setStatusbar() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
    }

    private fun initView() {
        bt_openmess = findViewById(R.id.bt_openmess)
        bt_openmess.setOnClickListener {
          //  packageManager.clearInstantAppCookie();
            var OpenMessenger = packageManager.getLaunchIntentForPackage(Constants.MESSENGER_BUNDLE_ID)

            if (OpenMessenger != null) {
                OpenMessenger.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            try {
                startActivity(OpenMessenger)
            }catch (e: java.lang.Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show();
            }

            var floatingBarServiceIntent = Intent(this, FloatingBarService::class.java)
            startService(floatingBarServiceIntent)
        }
    }
}