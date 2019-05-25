package com.example.nikumeshi_azddi9.worldclock2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_time_zone_select.*

class TimeZoneSelectActivity : AppCompatActivity() {

    private val pref by lazy { getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_zone_select)

        val adapter = TimeZoneAdapter(this,2){
            val timeSet = pref.getStringSet(TIME_KEY, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
            timeSet.add(it.id.toString())
            pref.edit().putStringSet(TIME_KEY,timeSet).apply()
            finish()
        }

        selectTimeList.run {
            setHasFixedSize(true)
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(this@TimeZoneSelectActivity, RecyclerView.VERTICAL, false)
        }
    }
}
