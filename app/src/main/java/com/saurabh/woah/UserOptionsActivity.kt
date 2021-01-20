package com.saurabh.woah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user_options.*

class UserOptionsActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_user_options)
        var user = mAuth.currentUser
        supportActionBar?.hide()
        if (user != null) {
            welcomeText.text = "Welcome ${user.displayName}, The Pain you feel today is the Strength you will need tomorrow."
        }

        btn_logout.setOnClickListener({
            mAuth.signOut()
            val intent = Intent(this@UserOptionsActivity, HomeActivity::class.java);
            startActivity(intent)
        })
    }
}