package com.team.dbgo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_surrounding.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var pokemonList : RecyclerView? = null
    private var pokemonDatas : ArrayList<SurrData>? = null
    private var adapter1 : SurrAdapter? = null

    private var surrgymList : RecyclerView? = null
    private var surrgymDatas : ArrayList<SurrGymData>?=null
    private var adapter2 : SurrGymAdapter?=null

    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    override fun onClick(v: View?) {

        val idx : Int = pokemonList!!.getChildAdapterPosition(v!!)
        val name : String? = pokemonDatas!!.get(idx).pokemon_name
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surrounding)

        pokemonList = findViewById(R.id.surr_pokemon_list) as RecyclerView
        pokemonList!!.layoutManager = LinearLayoutManager(this)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        val getSurrPokeResponse = networkService!!.getSurrPoke()
        getSurrPokeResponse.enqueue(object : retrofit2.Callback<SurrPokeResponse>{
            override fun onFailure(call: Call<SurrPokeResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<SurrPokeResponse>?, response: Response<SurrPokeResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().pokemons != null){
                        adapter1=SurrAdapter(response!!.body().pokemons!!, requestManager!!)
                        pokemonDatas = response!!.body().pokemons
                        pokemonList!!.adapter = adapter1
                    }
                }
            }

        })

        surrgymList = findViewById(R.id.surr_gym_list) as RecyclerView
        surrgymList!!.layoutManager = LinearLayoutManager(this)

        val getSurrGymResponse = networkService!!.getSurrGym()
        getSurrGymResponse.enqueue(object : retrofit2.Callback<SurrGymResponse>{
            override fun onFailure(call: Call<SurrGymResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<SurrGymResponse>?, response: Response<SurrGymResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().gyms != null){
                        adapter2=SurrGymAdapter(response!!.body().gyms!!, requestManager!!)
                        surrgymDatas = response!!.body().gyms
                        surrgymList!!.adapter = adapter2
                    }
                }
            }

        })
        go_to_mypage.setOnClickListener{
            var intent = Intent(this, MypageActivity::class.java)
            startActivity(intent)
        }

    }
}
