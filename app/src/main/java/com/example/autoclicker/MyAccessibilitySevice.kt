package com.example.autoclicker

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast

class MyAccessibilitySevice: AccessibilityService() {

    companion object {
        private var accessibilityService: MyAccessibilitySevice ? = null

        private fun setService(s: MyAccessibilitySevice) {
            this.accessibilityService = s
        }
    }


    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {
        TODO("Not yet implemented")
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()

        var info = serviceInfo

        this.serviceInfo = info
        setService(this)
        Log.e("ewewfwef", "Connected")

    }

    override fun onUnbind(intent: Intent?): Boolean {
        stopSelf()
        return super.onUnbind(intent)
    }

    fun gotoInbox() {
        if (accessibilityService == null){
            Toast.makeText(this, "accessbility service null", Toast.LENGTH_LONG).show()
            return
        }

        var root: AccessibilityNodeInfo? = accessibilityService?.rootInActiveWindow?: return

        root?.let {
            var btnProfileTabNodeInfo = root.findAccessibilityNodeInfosByViewId("com.facebook.katana:id/(name removed)")
            if (btnProfileTabNodeInfo.isEmpty())
                return

    //        btnProfileTabNodeInfo[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)

            var listmess = root.findAccessibilityNodeInfosByViewId("com.facebook.orca:id/(name removed)")

            listmess.forEach { node ->
                if (node.contentDescription == "Chung active now")
                    node.parent.parent.parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    return@forEach
            }
        }
    }
}