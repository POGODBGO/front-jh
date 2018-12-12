package com.team.dbgo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    override fun onClick(v: View?) {
        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        val getUserInfoResponse = networkService!!.getUserInfo()
        getUserInfoResponse.enqueue(object : retrofit2.Callback<UserInfoResponse>{
            override fun onFailure(call: Call<UserInfoResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 확인")
            }

            override fun onResponse(call: Call<UserInfoResponse>?, response: Response<UserInfoResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().user_id != null){
                        val handler = Handler()
                        handler.postDelayed({
                            //startActivity(Intent(applicationContext, MainActivity::class.java))
                            finish()
                        }, 3000)
                    }
                }
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    }



}
