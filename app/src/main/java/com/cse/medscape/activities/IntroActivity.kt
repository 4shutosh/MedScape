package com.cse.medscape.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cse.medscape.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var introBinding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        introBinding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(introBinding.root)
    }
}