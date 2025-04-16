package com.example.signupusefirebase


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val mail = intent.getStringExtra(SignInActivity.KEY1)
        val name = intent.getStringExtra(SignInActivity.KEY2)
        val id = intent.getStringExtra(SignInActivity.KEY3)


        val welcomeText = findViewById<TextView>(R.id.nameInfo)
        val mailInfo = findViewById<TextView>(R.id.emailInfo)
        val uniqueIdInfo = findViewById<TextView>(R.id.userId)


        welcomeText.text = "Welcome : $name"
        mailInfo.text = "mail : $mail"
        uniqueIdInfo.text = "uniqueId : $id"

    }
}