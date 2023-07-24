package com.example.chatting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var edtname: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var buttonSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        mAuth= FirebaseAuth.getInstance()
        edtname=findViewById(R.id.name)
        editEmail = findViewById(R.id.email)
        editPassword = findViewById(R.id.password)
        buttonSignUp = findViewById(R.id.btnSignUp)

        buttonSignUp.setOnClickListener{

            val name=edtname.text.toString()
            val email=editEmail.text.toString()
            val password=editPassword.text.toString()

            buttonSignUp(name,email,password)

        }
    }

    private fun buttonSignUp(name:String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //to jump on home screen i.e main activity
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    val intent= Intent(this@SignUpActivity,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                    

                } else {
                    Toast.makeText(this@SignUpActivity, "error has Occurred", Toast.LENGTH_SHORT).show()
                    // If sign in fails, display a message to the user.

                }
            }

    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDbRef=FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))



    }
}