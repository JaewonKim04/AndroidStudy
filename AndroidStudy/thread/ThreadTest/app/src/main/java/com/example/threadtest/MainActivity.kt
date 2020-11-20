package com.example.threadtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var started = false
    var total=0
    val handler=object:Handler(){
        override fun handleMessage(msg: Message) {
            val total=msg.what
            textTimer.text=formatTime(total)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start_btn.setOnClickListener {
            start()
        }
        pause_btn.setOnClickListener {
            pause()
        }
        end_btn.setOnClickListener {
            stop()
        }
    }
    fun start(){
        started=true
        thread(true){
            while(started){
                Thread.sleep(1000)
                if(!started)break
                total += 1
                handler.sendEmptyMessage(total)
            }
        }
    }
    fun pause(){
        started=false
    }
    fun stop(){
        started=false
        total=0
        textTimer.text="00 : 00"
    }
    fun formatTime(size:Int):String{
        val minute=String.format("%02d",total/60)
        val second=String.format("%02d",total%60)
        return "$minute : $second"
    }
}