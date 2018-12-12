package com.team.dbgo

import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @GET("gym/3")
    fun getGymPokemon(
    ) : Call<GymPokemonResponse>

    @GET("users/?username=dbmongo&password=test")
    fun getUserInfo() : Call<UserInfoResponse>

    @GET("users/1/pokemons")
    fun getPokemonInfo() : Call<PokemonInfoResponse>

    @GET("users/1/bag")
    fun getItemInfo() : Call<ItemInfoResponse>

    @GET("pokemon/nearby")
    fun getSurrPoke() : Call<SurrPokeResponse>

    @GET("gym/nearby")
    fun getSurrGym() : Call<SurrGymResponse>

    @GET("pokemon/catch?user_id={user_id}&pokemon_id={pokemon_id}")
    fun getCatchPokemon() : Call<CatchPokemonResponse>

    @GET("pokemon/evolve?user_id={user_id}&pokemon_seq={pokemon_seq}")
    fun getEvolvePokemon() : Call<EvolvePokemonResponse>
}