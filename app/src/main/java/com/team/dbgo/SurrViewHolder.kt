package com.team.dbgo

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class SurrViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView!!) {
    var surr_pokemon_name : TextView = itemView!!.findViewById(R.id.surr_pokemon_name) as TextView
}