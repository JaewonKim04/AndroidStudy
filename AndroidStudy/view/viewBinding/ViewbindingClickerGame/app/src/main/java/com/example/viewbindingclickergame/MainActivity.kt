package com.example.viewbindingclickergame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewbindingclickergame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var score = 0
        binding.scoreTv.text = score.toString()
        binding.minusBtn.setOnClickListener { binding.scoreTv.text = (--score).toString() }
        binding.plusBtn.setOnClickListener { binding.scoreTv.text = (++score).toString() }
        binding.resetBtn.setOnClickListener {
            score = 0
            binding.scoreTv.text = score.toString()

        }
    }
}