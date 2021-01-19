package com.example.mvvmclickergame

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel:ViewModel() {
    val score=MutableLiveData<String>("0")
    fun onPlusClick(){
        score.value=score.value?.toInt()?.plus(1).toString()
        Log.d("클릭",score.value.toString())
    }
    fun onMinusClick(){
        score.value=score.value?.toInt()?.minus(1).toString()
    }
    fun onResetClick(){
        score.value="0"
    }

}