package com.ToxicBakery.bluetooth.le.sample.bluetooth

import android.app.Activity
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager

fun Activity.getBluetoothManager(): BluetoothManager
        = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

fun Activity.btleSupported(): Boolean = btleSupported(this)

fun Activity.btleEnabled(): Boolean = getBluetoothManager().btleEnabled()

fun btleSupported(context: Context): Boolean
        = context.packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)

fun BluetoothManager.btleEnabled(): Boolean
        = adapter != null && adapter.isEnabled
