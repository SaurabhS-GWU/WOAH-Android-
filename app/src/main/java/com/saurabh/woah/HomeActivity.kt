package com.saurabh.woah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        register.setOnClickListener {
            val intent = Intent(this@HomeActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}