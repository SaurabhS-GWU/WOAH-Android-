package com.saurabh.woah

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.saurabh.woah.data.model.User

import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener({
            signUpUser()
        })
    }

    fun signUpUser(){
        if(tv_username.text.toString().isEmpty() ){
            tv_username.error = "Please enter an Email Address"
            tv_username.requestFocus()
            return
        }

        if(tv_nickName.text.toString().isEmpty() ){
            tv_nickName.error = "Please enter a Username for your account"
            tv_nickName.requestFocus()
            return
        }

        if(tv_password.text.toString().isEmpty() ){
            tv_password.error = "Please enter Password"
            tv_password.requestFocus()
            return
        }

        if(tv_repeatPassword.text.toString().isEmpty() ){
            tv_repeatPassword.error = "Please enter Password"
            tv_repeatPassword.requestFocus()
            return
        }

        if(tv_repeatPassword.text.toString() != tv_password.text.toString() ){
            tv_repeatPassword.error = "Passwords do not match"
            tv_repeatPassword.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(tv_username.text.toString()).matches()){
            tv_username.error = "Please enter valid email address"
            tv_username.requestFocus()
            return
        }


        mAuth.createUserWithEmailAndPassword(tv_username.text.toString(), tv_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = User(tv_username.text.toString(), tv_nickName.text.toString(), tv_password.text.toString())
                    val currentUser = mAuth.currentUser
//                    updateUI(user)
                    val url = "https://woah-7ad1b-default-rtdb.firebaseio.com/"
                    Toast.makeText(baseContext, "User Created",
                        Toast.LENGTH_SHORT).show()
                    FirebaseDatabase.getInstance().getReference("User")
                        .child(currentUser!!.getUid()).setValue(user).addOnCompleteListener(this){
                            if(it.isSuccessful){
                                Toast.makeText(baseContext, "User Information Updated",
                                    Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(baseContext, "Update failed.",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // ...
            }



    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }
//? after the FirebaseUser tells us that it is nullable
    fun updateUI(currentUser: FirebaseUser?){

    }
}