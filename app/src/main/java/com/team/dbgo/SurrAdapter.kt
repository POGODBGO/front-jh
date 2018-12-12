package com.team.dbgo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.team.dbgo.R.id.parent
import java.text.FieldPosition

class SurrAdapter(var dataList : ArrayList<SurrData>, var requestManager: RequestManager) : RecyclerView.Adapter<SurrViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SurrViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.surr_pokemons, parent, false)
        return SurrViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: SurrViewHolder, position:  Int) {
        holder.surr_pokemon_name.text = dataList!!.get(position).pokemon_name
    }

    override fun getItemCount() : Int = dataList!!.size

    fun setOnItemClickListener(l:View.OnClickListener){
        onItemClick = l
    }
}
