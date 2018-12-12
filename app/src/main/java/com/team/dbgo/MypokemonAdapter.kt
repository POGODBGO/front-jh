package com.team.dbgo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.team.dbgo.R.id.parent
import java.text.FieldPosition

class MypokemonAdapter(var dataList : ArrayList<MypokemonData>, var requestManager: RequestManager): RecyclerView.Adapter<MypokemonViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MypokemonViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.my_pokenmons, parent, false)
        return MypokemonViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: MypokemonViewHolder, position:  Int) {
        holder.poke_name.text = dataList!!.get(position).name
    }

    override fun getItemCount() : Int = dataList!!.size

    fun setOnItemClickListener(l:View.OnClickListener){
        onItemClick = l
    }
}
