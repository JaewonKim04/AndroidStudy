package com.test.rxandroidpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.rxandroidpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainActivityBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
    }
}