package com.ToxicBakery.bluetooth.le.sample

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.ToxicBakery.bluetooth.le.sample.bluetooth.btleEnabled
import com.ToxicBakery.bluetooth.le.sample.bluetooth.btleSupported
import com.ToxicBakery.bluetooth.le.sample.recycler.ChatAdapter
import com.ToxicBakery.library.btle.scanning.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), ILeScanCallback {

    private val chatAdapter: ChatAdapter = ChatAdapter()

    private lateinit var textViewError: TextView
    private lateinit var recyclerViewChat: RecyclerView
    private lateinit var leScanBinder: ILeScanBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewError = findViewById(R.id.activity_main_text_view_error) as TextView
        recyclerViewChat = findViewById(R.id.activity_main_recycler_view) as RecyclerView

        recyclerViewChat.layoutManager = LinearLayoutManager(this)
        recyclerViewChat.adapter = chatAdapter
    }

    override fun onResume() {
        super.onResume()

        updateView()

        if (hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            val scanConfiguration: ILeScanConfiguration = SimpleLeScanConfiguration(this)
            leScanBinder = LeDiscovery.startScanning(this, scanConfiguration)
        } else {
            requestPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    override fun onPause() {
        super.onPause()
        leScanBinder.stop()
    }

    fun updateView() {
        val hasLocationPermission = hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        val btleSupported = btleSupported()
        val btleEnabled = btleEnabled()
        val btleError = !hasLocationPermission || !btleEnabled || !btleSupported
        textViewError.visibility = if (btleError) View.VISIBLE else View.GONE
        recyclerViewChat.visibility = if (btleError) View.GONE else View.VISIBLE

        if (!hasLocationPermission) {
            textViewError.setText(R.string.location_permission_required)
        } else if (!btleSupported) {
            textViewError.setText(R.string.unsupported_device)
        } else if (!btleEnabled) {
            textViewError.setText(R.string.bluetooth_not_enabled)
        }
    }

    override fun onDeviceLost(scanResult: ScanResultCompat) {
        chatAdapter.removeScanResult(scanResult)
    }

    override fun onDeviceFound(scanResult: ScanResultCompat) {
        Timber.d("Found device %s", scanResult.device.address)
        chatAdapter.addScanResult(scanResult)
    }

}
