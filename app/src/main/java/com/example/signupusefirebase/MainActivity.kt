package com.example.signupusefirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {


    //For connect with firebase create a variable Database type. //  means this type of value that give value next time.// Or  is A

lateinit var database : DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val signButton = findViewById<Button>(R.id.signupBtn)
        val etName = findViewById<EditText>(R.id.userName)
        val etPass = findViewById<EditText>(R.id.password)
        val etId  =findViewById<EditText>(R.id.userId)
        val etEmail = findViewById<EditText>(R.id.userEmail)




        signButton.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPass.text.toString()
            val uniqueId = etId.text.toString()



            val user = User(name,email,password,uniqueId)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(user).addOnSuccessListener {
                Toast.makeText(this,"User Registered", Toast.LENGTH_SHORT).show()
                etName.setText(" ")
                etEmail.setText(" ")
                etPass.setText(" ")
                etId.setText(" ")
            }.addOnFailureListener {
                Toast.makeText(this, "Registered Failed", Toast.LENGTH_SHORT).show()
            }

        }


        val goSignInPage = findViewById<Button>(R.id.signInPage)

        goSignInPage.setOnClickListener {
            val signIntent = Intent(this, SignInActivity::class.java)
            startActivity(signIntent)
        }
    }
}