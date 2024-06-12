package com.example.broadcast

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var receiver: BroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        configureReceiver()
        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            broadcastIntent()
        }
    }

        fun broadcastIntent() {
            val intent = Intent()
            intent.action = "com.example.broadcast"
            intent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
            sendBroadcast(intent)
    }
    private fun configureReceiver() {
        val filter = IntentFilter()
        filter.addAction("com.example.broadcast")
        receiver = MyReceiver()
        registerReceiver(receiver, filter)
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}