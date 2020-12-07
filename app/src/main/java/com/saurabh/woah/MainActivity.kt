package com.saurabh.woah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        /*
        There are two main uses for a Handler: (1) to schedule messages and runnables to be executed at some point in the future; and
        (2) to enqueue an action to be performed on a different thread than your own
         */
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}