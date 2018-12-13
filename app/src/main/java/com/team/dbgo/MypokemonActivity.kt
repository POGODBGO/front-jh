package com.team.dbgo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Response
import android.widget.Button


class MypokemonActivity : AppCompatActivity(), View.OnClickListener {
    private var mypokeList : RecyclerView? = null
    private var mypokeDatas : ArrayList<MypokemonData>? = null
    private var adapter : MypokemonAdapter? = null

    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypokemon)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        mypokeList = findViewById(R.id.poke_list) as RecyclerView
        mypokeList!!.layoutManager = LinearLayoutManager(this)

        val getPokemonInfoResponse = networkService!!.getPokemonInfo()
        getPokemonInfoResponse.enqueue(object : retrofit2.Callback<PokemonInfoResponse>{
            override fun onResponse(call: Call<PokemonInfoResponse>?, response: Response<PokemonInfoResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().pokemons != null){
                        adapter = MypokemonAdapter(response!!.body().pokemons !!, requestManager!!)
                        mypokeDatas = response!!.body().pokemons
                        adapter!!.setOnItemClickListener(this@MypokemonActivity)
                        mypokeList!!.adapter = adapter
                    }
                }
            }
            override fun onFailure(call: Call<PokemonInfoResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }
        })

//        val getEvolvePokemonResponse = networkService!!.getEvolvePokemon()
////        getEvolvePokemonResponse.enqueue(object : retrofit2.Callback<EvolvePokemonResponse>{
////            override fun onResponse(call: Call<EvolvePokemonResponse>?, response: Response<EvolvePokemonResponse>?) {
////                if(response!!.isSuccessful){
////
////                }
////            }
////            override fun onFailure(call: Call<EvolvePokemonResponse>?, t: Throwable?) {
////                ApplicationController.instance!!.makeToast("통신 확인")
////            }
////        })
    }
    override fun onClick(v: View?) {

        Log.v("yjjh", "yjjh")
        val idk : Int = mypokeList!!.getChildAdapterPosition(v!!)
        Log.v("jh", "jh")
        val name : String? = mypokeDatas!!.get(idk).name
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()


    }
}
