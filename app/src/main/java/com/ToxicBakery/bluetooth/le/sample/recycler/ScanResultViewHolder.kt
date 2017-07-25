package com.ToxicBakery.bluetooth.le.sample.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.ToxicBakery.library.btle.scanning.ScanResultCompat

class ScanResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textViewScanResult: TextView = itemView.findViewById(android.R.id.text1) as TextView

    fun bind(scanResultCompat: ScanResultCompat) {
        with(scanResultCompat) {
            textViewScanResult.text = "${device.name} : ${device.address} : $rssi"
        }
    }

}
