package com.team.dbgo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Response

class MypokemonActivity : AppCompatActivity(), View.OnClickListener {
    private var mypokeList : RecyclerView? = null
    private var mypokeDatas : ArrayList<MypokemonData>? = null
    private var adapter : MypokemonAdapter? = null

    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    override fun onClick(v: View?) {

        val idx : Int = mypokeList!!.getChildAdapterPosition(v!!)
        val name : String? = mypokeDatas!!.get(idx).name
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()

    }

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
                        mypokeList!!.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<PokemonInfoResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }



        })

    }
}
