package com.example.autoclicker

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.provider.Settings.Global
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FloatingBarService: Service() {

    private var mWindownManager: WindowManager? = null
    private var windowManagerParams: WindowManager.LayoutParams? = null

    private lateinit var mFloatingWidget: View
    private lateinit var bt_star: Button
    private lateinit var bt_stop: Button

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        mWindownManager = getSystemService(WINDOW_SERVICE) as WindowManager

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        var inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflaterView(layoutInflater = inflater)

        return START_STICKY
    }

    private fun inflaterView(layoutInflater: LayoutInflater) {
        mFloatingWidget = layoutInflater.inflate(R.layout.floating_bar_widget, null)

        windowManagerParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        windowManagerParams!!.gravity = Gravity.TOP or Gravity.START
        windowManagerParams!!.x = 0
        windowManagerParams!!.y = 500

        mWindownManager?.addView(mFloatingWidget, windowManagerParams)

        bt_star = mFloatingWidget.findViewById(R.id.bt_star)
        bt_stop = mFloatingWidget.findViewById(R.id.bt_stop)

        var myAccessibilitySevice = MyAccessibilitySevice()

        bt_star.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                myAccessibilitySevice.gotoInbox()
            }
        }

        bt_stop.setOnClickListener {
            onDestroy()
        }
    }
}