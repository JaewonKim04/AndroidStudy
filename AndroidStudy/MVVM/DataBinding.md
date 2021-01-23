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
<layout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto">
        <data>
            <variable
                name="viewmodel"
                type="com.myapp.data.ViewModel" />
        </data>
        <ConstraintLayout... /> <!-- UI layout's root element -->
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