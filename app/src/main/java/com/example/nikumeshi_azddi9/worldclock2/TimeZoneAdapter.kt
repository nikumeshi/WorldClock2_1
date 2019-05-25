package com.example.nikumeshi_azddi9.worldclock2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class TimeZoneAdapter (context: Context,
                       fromWhere: Int,
                       timeSet: Array<String> = TimeZone.getAvailableIDs(),
                       private val onItemClicked: (TimeZone)->Unit) : RecyclerView.Adapter<TimeZoneAdapter.ViewHolder>(){

    private val inflater = LayoutInflater.from(context)
    private val timeZones = when(fromWhere){
        1 -> {
            timeSet.map {
                TimeZone.getTimeZone(it)
            }
        }
        else -> {
            TimeZone.getAvailableIDs().map{
                    id -> TimeZone.getTimeZone(id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.time_zone_list_row, parent, false)
        val viewHolder = ViewHolder(view)

        view.setOnClickListener {
            val position = viewHolder.adapterPosition
            val timeZone = timeZones[position]
            onItemClicked(timeZone)
        }
        return viewHolder
    }

    override fun getItemCount() = timeZones.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val timeZone: TimeZone = timeZones[position]
        holder.name.text = timeZone.displayName
        holder.time.timeZone = timeZone.id
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.timeZoneName)
        val time: TextClock = view.findViewById(R.id.timeZoneTime)
    }
}