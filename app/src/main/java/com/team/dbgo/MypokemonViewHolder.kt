package com.team.dbgo

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class MypokemonViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView!!) {
    var poke_name : TextView = itemView!!.findViewById(R.id.poke_name) as TextView
}