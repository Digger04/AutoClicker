package com.example.autoclicker

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

private lateinit var bt_connect : Button

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_DRAW_OVER_OTHER_APPS = 99
        const val REQUEST_ACCESSIBILITY_SERVICE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initView()
        Connect()

    }

    private fun Connect() {
        bt_connect.setOnClickListener {
            checkDrawOverOtherApps()
        }
    }

    private fun initView() {
        bt_connect = findViewById(R.id.bt_connect)
    }

    private fun gotoHome() {
        val intent = Intent(this, SelectFun::class.java)
        startActivity(intent)
    }

    private fun checkDrawOverOtherApps() {
        if (Settings.canDrawOverlays(this)) {
            checkAccessibilitySetting()
            return
        }

        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
        startActivityForResult(intent, REQUEST_DRAW_OVER_OTHER_APPS)
    }

    private fun checkAccessibilitySetting() {

        if (isAccessibilitySetting()) {
            gotoHome()
        }else {
            openAccessibilityService()
        }
    }

    private fun openAccessibilityService() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivityForResult(intent, REQUEST_ACCESSIBILITY_SERVICE)
    }

    private fun isAccessibilitySetting(): Boolean {
        val s = Settings.Secure.getString(
            applicationContext.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false

        return s.contains(getString(R.string.app_name))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            REQUEST_DRAW_OVER_OTHER_APPS -> {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this,"Vui lòng cấp quyền", Toast.LENGTH_LONG).show()
                    return
                }

                checkAccessibilitySetting()
            }

            else -> {
                if (!isAccessibilitySetting()) {
                    Toast.makeText(this,"Vui lòng cấp quyền", Toast.LENGTH_LONG).show()
                    return
                }
                gotoHome()
            }
        }
    }
}