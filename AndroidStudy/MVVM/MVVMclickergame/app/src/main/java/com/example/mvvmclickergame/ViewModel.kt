package com.example.mvvmclickergame

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel:ViewModel() {
    var ct=0
    var score=ObservableField<String>("0")
    fun onPlusClick(){
        ct++
        score.set(ct.toString())
    }
    fun onMinusClick(){
        ct--
        score.set(ct.toString())
    }
    fun onResetClick(){
        ct=0
        score.set(ct.toString())
    }

}