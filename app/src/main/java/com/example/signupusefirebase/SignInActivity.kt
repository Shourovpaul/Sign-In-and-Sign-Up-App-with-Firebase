package com.example.signupusefirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

//create KEY.
    companion object{
        const val KEY1 ="com.example.signupusefirebase.SignInActivity.mail"
        const val KEY2 ="com.example.signupusefirebase.SignInActivity.name"
        const val KEY3 ="com.example.signupusefirebase.SignInActivity.id"
    }


    private  lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val iSignIn = findViewById<Button>(R.id.signInBtn)
        val userName = findViewById<TextInputEditText>(R.id.userId)

        iSignIn.setOnClickListener {
            // i Want to take tha Reference note users in firebase.
            val uniqueId = userName.text.toString()
            if (uniqueId.isNotEmpty()) {
                readData(uniqueId)
            } else {
                Toast.makeText(this, "Please Enter User Name", Toast.LENGTH_SHORT).show()
            }
        }
    }//on create method over.


    private fun readData(uniqueId: String) {

        database= FirebaseDatabase.getInstance().getReference("Users")
        database.child(uniqueId).get().addOnSuccessListener {
            if (it.exists()){
                //Welcome user in your App, With intent and also Pass
                val email = it.child("email").value
                val name = it.child("name").value
                val uniqueId = it.child("uniqueId").value

                val signInIntent = Intent(this, HomeActivity::class.java)
                signInIntent.putExtra(KEY1, email.toString())
                signInIntent.putExtra(KEY2, name.toString())
                signInIntent.putExtra(KEY3, uniqueId .toString())
                startActivity(signInIntent)

            }else{
                Toast.makeText(this, "User does Not Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Fail Sign In error DB , please set info again", Toast.LENGTH_SHORT).show()
        }
    }

}