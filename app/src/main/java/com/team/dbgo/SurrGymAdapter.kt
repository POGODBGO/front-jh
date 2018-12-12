package com.team.dbgo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.team.dbgo.R.id.parent
import java.text.FieldPosition

class SurrGymAdapter(var dataList : ArrayList<SurrGymData>, var requestManager: RequestManager) : RecyclerView.Adapter<SurrGymViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SurrGymViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.surr_gyms, parent, false)
        return SurrGymViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: SurrGymViewHolder, position:  Int) {
        holder.title.text = dataList!!.get(position).title
    }

    override fun getItemCount() : Int = dataList!!.size

    fun setOnItemClickListener(l:View.OnClickListener){
        onItemClick = l
    }
}
