package com.example.chatting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LogInActivity : AppCompatActivity() {
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var actLogIn: Button
    private lateinit var actSignUp: Button
    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        supportActionBar?.hide()
        mAuth= FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.edtEmail)
        editPassword = findViewById(R.id.edtPassword)
        actLogIn = findViewById(R.id.logIn)
        actSignUp = findViewById(R.id.signUp)

        actSignUp.setOnClickListener {
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        actLogIn.setOnClickListener{
            val email=editEmail.text.toString()
            val password=editPassword.text.toString()
            login(email,password)
            

        }

    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent= Intent(this@LogInActivity,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@LogInActivity, "error has occurred", Toast.LENGTH_SHORT).show()

                }
            }


    }

}