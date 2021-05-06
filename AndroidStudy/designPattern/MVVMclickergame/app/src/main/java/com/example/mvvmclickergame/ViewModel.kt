package com.example.mvvmclickergame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel:ViewModel() {
    var score=MutableLiveData<Int>(0)
    fun onPlusClick(){
        score.value = score.value?.plus(1)
    }
    fun onMinusClick(){
        score.value=score.value?.minus(1)
    }
    fun onResetClick(){
        score.value=0
    }

}