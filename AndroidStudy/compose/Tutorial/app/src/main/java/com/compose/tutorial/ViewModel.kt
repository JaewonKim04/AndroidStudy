package com.compose.tutorial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class ViewModel:ViewModel() {
    private val _persons = MutableLiveData(ArrayList<Person>())
    val persons :LiveData<ArrayList<Person>> get() = _persons

    private val setPersons = listOf(Person("김재원",18),Person("김재투",19),Person("김재쓰리",20),Person("김재투",19),Person("김재투",19),Person("김재투",19),Person("김재투",19),Person("김재투",19),Person("김재투",19),Person("김재투",19))

    fun setPerson(){
        Observable.fromIterable(setPersons).subscribe{
            _persons.value!!.add(it)
        }
    }

}