package com.example.notifiaction_lap_ass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignIn : AppCompatActivity() {

    lateinit var email:String
    lateinit var password:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnSignIn.setOnClickListener {
            if(txtEmail.text.isNotEmpty() && txtEmail.text.toString() != ""){
                email = txtEmail.text.toString()
            }
            if(txtPassword.text.isNotEmpty() && txtPassword.text.toString() != ""){
                password = txtPassword.text.toString()
            }

            checkEmailAndPassword(email,password)
            val i = Intent(this,MainActivity::class.java)
            i.putExtra("email",email)
            i.putExtra("password",password)
            startActivity(i)
        }

        txtSignUp.setOnClickListener {
            val i = Intent(this,SignUp::class.java)
            startActivity(i)
        }

    }

    private fun checkEmailAndPassword(email:String, password:String){
        val url="https://mcc-users-api.herokuapp.com/login"
        val queue= Volley.newRequestQueue(this)
        val request = object : StringRequest(Method.POST, url, Response.Listener { response ->
            Log.e("login",response)

        }, Response.ErrorListener { error ->
            Log.e("error",error.toString())
        }
        ){
            override fun getParams(): MutableMap<String, String> {
                val hashMap = HashMap<String,String>()
                hashMap["email"] = email
                hashMap["password"] = password

                return hashMap
            }
        }
        queue.add(request)
    }

}