# DataBinding
* __MVVM 을 구현할때의 필수 기술__
* View와 ViewModel이 서로의 독립성을 더 높일 수 있음
* View와 ViewModel간의 데이터와 명령을 연결해주는 매개체
## 사용법
1. graddle에 databinding을 추가함(kotlin-kapt 필요)
```graddle
android{
    ...
    dataBinding{
        enabled true
    }
}
```
2. 사용할 Xml을 layout으로 묶고 data와 variable을 정의함
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>
        <variable
            name="viewmodel"
            type="com.example.mvvmclickergame.ViewModel" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{viewmodel.score}"
            android:textSize="30sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>

```
3. MainActivity에서 binding 해준다
```kotlin
package com.example.mvvmclickergame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mvvmclickergame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModel()
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
    }
}
```
## 2-WayBinding(양방향 binding)
* View에 지정한 정보를 ViewModel에도 상호 갱신되도록 해야할때 사용
* 1-WayBinding:__ViewModel to View__ 로 화면갱신
* 2-WayBinding:__View to ViewModel__ 로 갱신하는것
```xml
<EditText
android:text="@={viewmodel.inputText}"/>
```
