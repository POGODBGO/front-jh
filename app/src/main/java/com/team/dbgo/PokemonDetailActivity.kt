package com.team.dbgo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetailActivity : AppCompatActivity() {


    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)


        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        val getPokemonInfoResponse =networkService!!.getPokemonInfo()
        getPokemonInfoResponse.enqueue(object :Callback<PokemonInfoResponse>{
            override fun onFailure(call: Call<PokemonInfoResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<PokemonInfoResponse>?, response: Response<PokemonInfoResponse>?) {
                if(response!!.isSuccessful){


                }
            }

        })

    }
}
