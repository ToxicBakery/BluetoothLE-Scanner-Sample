package com.ToxicBakery.bluetooth.le.sample

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

fun Activity.hasPermission(permission: String): Boolean
        = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Activity.requestPermission(vararg permission: String, requestCode: Int = 1)
        = ActivityCompat.requestPermissions(this, permission, requestCode)
