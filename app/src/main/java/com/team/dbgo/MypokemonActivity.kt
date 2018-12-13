package com.team.dbgo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_mypokemon.*
import kotlinx.android.synthetic.main.my_pokenmons.*
import kotlinx.android.synthetic.main.myitems.*


class MypokemonActivity : AppCompatActivity() {
    private var adapter: MypokemonAdapter? = null
    private var mypokeDatas : ArrayList<MypokemonData>? = null

    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypokemon)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        poke_list.layoutManager = LinearLayoutManager(this)

        val getPokemonInfoResponse = networkService!!.getPokemonInfo()
        getPokemonInfoResponse.enqueue(object : retrofit2.Callback<PokemonInfoResponse>{
            override fun onResponse(call: Call<PokemonInfoResponse>?, response: Response<PokemonInfoResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().pokemons != null){
                        adapter = MypokemonAdapter(response!!.body().pokemons !!, requestManager!!) { position ->
                            val seq : Int? = mypokeDatas!!.get(position).seq
                            val name : String? = mypokeDatas!!.get(position).name
                            val cp : String? = mypokeDatas!!.get(position).cp.toString()
                            val hp : Int? = mypokeDatas!!.get(position).hp
                            val weight : String? = mypokeDatas!!.get(position).weight
                            val height : String? = mypokeDatas!!.get(position).height
                            val level : Int? = mypokeDatas!!.get(position).level
                            val catched_at : String? = mypokeDatas!!.get(position).catched_at
                            val normal_attack_name : String? = mypokeDatas!!.get(position).normal_attack_name
                            val specail_attack_name : String? = mypokeDatas!!.get(position).specail_attack_name

                            //Toast.makeText(this@MypokemonActivity, name, Toast.LENGTH_SHORT).show()
                            var intent = Intent(this@MypokemonActivity, PokemonDetailActivity::class.java)
                            intent.putExtra("name", name)
                            intent.putExtra("cp",cp)
                            intent.putExtra("weight", weight)
                            intent.putExtra("height", height)
                            intent.putExtra("catched_at", catched_at)

                            startActivity(intent)
//                            evolveButton.setOnClickListener { _ ->
//                                val getEvolvePokemonResponse = networkService!!.getEvolvePokemon()
//                                getEvolvePokemonResponse.enqueue(object : retrofit2.Callback<EvolvePokemonResponse> {
//                                    override fun onResponse(call: Call<EvolvePokemonResponse>?, response: Response<EvolvePokemonResponse>?) {
//                                        if (response!!.isSuccessful) {
//
//                                        }
//                                    }
//                                })
//                            }
                        }
                        mypokeDatas = response!!.body().pokemons
                        poke_list.adapter = adapter
                    }
                }
            }
            override fun onFailure(call: Call<PokemonInfoResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }
        })

    }
}