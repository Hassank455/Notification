package com.example.notifiaction_lap_ass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.notifiaction_lap_ass.SignUp.Companion.token

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var email = intent.getStringExtra("email")
        var password = intent.getStringExtra("password")
        addRegisterToken(email!!,password!!,token)

    }

    private fun addRegisterToken(email:String, password:String, regToken:String){
        val url="https://mcc-users-api.herokuapp.com/add_reg_token"
        val queue= Volley.newRequestQueue(this)
        val request = object : StringRequest(Method.PUT, url, Response.Listener { response ->
            Log.e("add_reg_token",response)

        }, Response.ErrorListener { error ->
            Log.e("error","error")
        }
        ){
            override fun getParams(): MutableMap<String, String> {
                val hashMap = HashMap<String,String>()
                hashMap["email"] = email
                hashMap["password"] = password
                hashMap["reg_token"] = regToken
                return hashMap
            }
        }
        queue.add(request)
    }
}