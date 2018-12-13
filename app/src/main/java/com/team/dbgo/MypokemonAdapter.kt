package com.team.dbgo

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager

/**
 * 아이템 클릭 리스너
 */
fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}


class MypokemonAdapter(var dataList : ArrayList<MypokemonData>, var requestManager: RequestManager, val onClicked: (position: Int) -> Unit): RecyclerView.Adapter<MypokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MypokemonViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.my_pokenmons, parent, false)
        return MypokemonViewHolder(mainView).listen { position, _ ->
            Log.d("adsf", "$position")
            onClicked(position)
        }
    }

    override fun onBindViewHolder(holder: MypokemonViewHolder, position:  Int) {
        holder.name.text = dataList!!.get(position).name
    }

    override fun getItemCount() : Int = dataList!!.size
}