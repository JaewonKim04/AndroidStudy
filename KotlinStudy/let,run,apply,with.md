# let()

* 함수를 호출하는 객체를 이어지는 블록으로 넘기고, 블록의 결과값을 반환

* 함수원형

  ```kotlin
  fun<T,R> T.let(block: (T) -> R): R
  ```

* 사용예

  ```kotlin
  val padding = TypedValue.applyDimension(
          TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics).toInt()
  // 왼쪽, 오른쪽 padding 설정
  setPadding(padding, 0, padding, 0)
  
  // let 사용->
  TypedValue.applyDimension(
  	TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics).toInt().let{ padding->
    setPadding(padding, 0, padding, 0)
  }
  ```




# apply()

* 함수를 호출하는 객체를 이어지는 블록의 __리시버__로 전달하고, 객체 자체를 반환함

  * __리시버__: 바로 이어지는 블록 내에서 메서드 및 속성에 바로 접근할 수 있도록 한 객체

* 함수원형

  ```kotlin
  fun<T> T.apply(block: T.() -> Unit): T
  ```

* 사용 예

  ```kotlin
  val param = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
  param.gravity = Gravity.CENTER_HORIZONTAL
  param.weight = 1f
  param.topMargin = 100
  param.bottomMargin = 100
  
  //apply 사용->
  val param = LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT).apply{
    	gravity = Gravity.CENTER_HORIZONTAL
      weight = 1f
      topMargin = 100
      bottomMargin = 100
  }
  ```



# run()

* __인자가 없는 익명 함수처럼 동작하는 형태__

  * 이어지는 블록에서 처리할 작업들을 넣을 수 있음

  * 값을 반환하지 않거나 특정값을 반환할 수 있음

  * 함수원형

    ```kotlin
    fun<R> run(block: () -> R): R
    ```

* __객체에서 호출하는 형태__

  * 호출하는 객체를 이어지는 블록의 리시버로 전달

  * 블록의 결과값을 반환

  * 함수원형

    ```kotlin
    fun<T,R> run(block: T.() -> R): R
    ```

* 사용 예

  * 특정 객체의 메서드나 필드를 연속적으로 호출하거나 값을 할당할 때 사용

  * 안전한 호출도 가능함

    ```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        ...
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_clear_white)
        }
        ...
      //spportActionBar이 null이 아닐때만 블록내 내용이 실행됨
    }
    ```

* apply와 차이점 : apply는 새로운 객체를 생성함과 동시에 사용하고, run은 이미 생성되어있는 객체에서 사용함

# with()

* 인자로 받는 객체를 블록의 리시버로 전달

* 블록의 결과값을 반환

* run()함수와 기능이 거의 동일(리시버로 전달할 객체가 어디에 위치해있는가만 다름)

* 함수원형

  ```kotlin
  fun <T, R> with(receiver: T, block: T.() -> R): R
  ```

* 사용 예

  ```kotlin
  override fun onCreate(savedInstanceState: Bundle?) {
      ...
      supportActionBar?.let {
        	with(it){
           	setDisplayHomeAsUpEnabled(true)
          	setHomeAsUpIndicator(R.drawable.ic_clear_white) 
          }
      }
      ...
  }
  ```

* With()는 run()과 다르게 안전한 호출을 지원하지 않음(run()을 사용할 일이 더 많을것이다)

참고자료: [커니의 안드로이드](https://www.androidhuman.com/2016-07-06-kotlin_let_apply_run_with)