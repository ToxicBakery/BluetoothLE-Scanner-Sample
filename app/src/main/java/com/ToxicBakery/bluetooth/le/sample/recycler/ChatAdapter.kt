package com.ToxicBakery.bluetooth.le.sample.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ToxicBakery.library.btle.scanning.ScanResultCompat

class ChatAdapter : RecyclerView.Adapter<ScanResultViewHolder>() {

    val scanResults: MutableList<ScanResultCompat> = ArrayList()

    override fun getItemCount(): Int = scanResults.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScanResultViewHolder
            = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
            .let { ScanResultViewHolder(it) }

    override fun onBindViewHolder(holder: ScanResultViewHolder, position: Int) {
        holder.bind(scanResults[position])
    }

    fun addScanResult(scanResultCompat: ScanResultCompat) {
        val indexOf = scanResults.indexOf(scanResultCompat)
        if (indexOf >= 0) {
            scanResults[indexOf] = scanResultCompat
            notifyItemChanged(indexOf)
        } else {
            if (scanResults.add(scanResultCompat)) {
                notifyItemInserted(scanResults.size - 1)
            }
        }
    }

    fun removeScanResult(scanResultCompat: ScanResultCompat) {
        val indexOf = scanResults.indexOf(scanResultCompat)
        if (indexOf >= 0) {
            scanResults.removeAt(indexOf)
            notifyItemRemoved(indexOf)
        }
    }

}
