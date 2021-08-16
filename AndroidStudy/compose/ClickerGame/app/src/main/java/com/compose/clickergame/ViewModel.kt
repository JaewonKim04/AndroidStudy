package com.compose.clickergame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel:ViewModel() {

    private val _score = MutableLiveData(1)
    val score : LiveData<Int> get() = _score

    fun plusScore(){
        _score.value = score.value!!+1
    }

    fun resetScore(){
        _score.value = 1
    }

    fun minusScore(){
        _score.value = score.value!!-1
    }
}