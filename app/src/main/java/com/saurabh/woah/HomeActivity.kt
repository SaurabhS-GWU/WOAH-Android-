package com.saurabh.woah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_register.*

class HomeActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener {
            val intent = Intent(this@HomeActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        login_button.setOnClickListener{
          login()
        }

    }

     override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if(currentUser != null){
            val intent = Intent(this@HomeActivity, UserOptionsActivity::class.java)
            startActivity(intent)
        }
    }

    fun login(){
        if(editTextEmailAddress.text.toString().isEmpty() ){
            editTextEmailAddress.error = "Please enter an Email Address"
            editTextEmailAddress.requestFocus()
            return
        }

        if(editTextPassword.text.toString().isEmpty() ){
            editTextPassword.error = "Please enter an Email Address"
            editTextPassword.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(editTextEmailAddress.text.toString()).matches()){
            editTextEmailAddress.error = "Please enter valid email address"
            editTextEmailAddress.requestFocus()
            return
        }

        mAuth.signInWithEmailAndPassword(editTextEmailAddress.text.toString(), editTextPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = mAuth.currentUser
                    val intentOptions = Intent(this@HomeActivity, UserOptionsActivity::class.java)
                    startActivity(intentOptions)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                    // ...
                }

                // ...
            }



    }
}