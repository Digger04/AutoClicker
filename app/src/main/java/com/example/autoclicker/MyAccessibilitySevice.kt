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
    }

    override fun onInterrupt() {
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

            Thread.sleep(1000)
            var homefblite = root.findAccessibilityNodeInfosByViewId("com.facebook.lite:id/main_layout")

            if (homefblite.isEmpty())
                return

            var chat = homefblite[0].getChild(0).getChild(4);
            chat.performAction(AccessibilityNodeInfo.ACTION_CLICK)

            Thread.sleep(1000)

            var listpeople = homefblite[0].getChild(0).getChild(8)
                .getChild(1).getChild(0)

            listpeople.performAction(AccessibilityNodeInfo.ACTION_CLICK)

            Thread.sleep(1000)

            var  homechat = accessibilityService?.rootInActiveWindow!!.findAccessibilityNodeInfosByText("com.facebook.lite:id/main_layout")
            if (homechat.isEmpty())
                return
            
       //     var listmess = root.findAccessibilityNodeInfosByViewId("android:id/content")
       //     var listM = root.findAccessibilityNodeInfosByText("Set Status")

        //    if (listM.isEmpty())
       //         return
      //      var searchmess = listmess[0].getChild(0).getChild(1).getChild(0).getChild(1).
     //                    getChild(0).getChild(0).getChild(0).getChild(0)
      //               .getChild(1).getChild(0)

            
      //      var btnProfileTabNodeInfo = root.findAccessibilityNodeInfosByViewId("com.instagram.android:id/profile_tab")
      //      if (btnProfileTabNodeInfo.isEmpty())
      //          return

    //        btnProfileTabNodeInfo[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)

   //         if (btnProfileTabNodeInfo.isEmpty()){
   //             return
   //         }

   //         var searchmess = btnProfileTabNodeInfo[3].getChild(0).getChild(1).
   //             getChild(0).getChild(0).getChild(0).getChild(0)
                //         .getChild(1).getChild(0)


     //       searchmess.performAction(AccessibilityNodeInfo.ACTION_CLICK)

         //   listmess.forEach { node ->
         //       if (node.contentDescription == "Chung active now")
        //            node.parent.parent.parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
         //           return@forEach
       //     }
        }
    }
}