package com.test.rxandroidpractice

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ReactiveTool {
    fun setTextView(textView: TextView,editText: TextInputEditText){
        val observableTextQuery = Observable
            .create(ObservableOnSubscribe { emitter: ObservableEmitter<String>? ->
                editText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                        emitter?.onNext(s.toString())
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    }

                })
            })
            .map{changeText(it)}
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
        observableTextQuery.subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                TODO("Not yet implemented")
            }

            override fun onNext(t: String?) {
                textView.text=t
            }

            override fun onError(e: Throwable?) {
                TODO("Not yet implemented")
            }

            override fun onComplete() {
                TODO("Not yet implemented")
            }
        })
    }

    private fun changeText(text:String):String{
        return text + "가 입력되었습니다"
    }
}