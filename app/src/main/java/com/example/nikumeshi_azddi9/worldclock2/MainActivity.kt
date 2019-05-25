package com.example.nikumeshi_azddi9.worldclock2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

const val PREF_NAME = "pref"
const val TIME_KEY = "key"

class MainActivity : AppCompatActivity() {

    private val pref by lazy { getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) }

    private val clickListener = View.OnClickListener {
        val intent = Intent(this, TimeZoneSelectActivity::class.java)
        startActivityForResult(intent, 1)
    }

    private fun showClocks(){
        val timeSet = pref.getStringSet(TIME_KEY, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        val adapter = TimeZoneAdapter(this,1, timeSet.toTypedArray()){
                TimeZone -> Toast.makeText(this, TimeZone.displayName, Toast.LENGTH_SHORT).show()
        }

        timeZoneList.run {
            setHasFixedSize(true)
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton.setOnClickListener(clickListener)
        showClocks()
    }
}
