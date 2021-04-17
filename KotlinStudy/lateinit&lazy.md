# 늦은 초기화
* class의 전역에 아래와 같이 값을 생성하면, 클래스가 만들어지는 시점에 변수를 합꼐 초기화 합니다
* class 생성과 동시에 변수가 초기화되면, 재 접근시 빠르게 접근이 가능하여 이득을 볼 수 있습니다.
    ```kotlin
    class SampleActivity{
      private val sampleAdapter : SampleAdapter = SampleAdapter(ImageLoadeerAdapterViewModel(this@SampleActivity,3))
    }
    ```

* 하지만 위 변수를 사용하지 않으면 메모리 손해를 볼 수 있습니다.
* __필수 요건아 아닌 경우__ 늦은 초기화가 필요합니다.

    ```kotlin
    class SampleActivity{
        private var sampleAdapter : SampleAdapter? = null

        override fun onCreate(savedInstanceState: Bundle?){
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            sampleAdapter = SampleAdapter()
        }
    }
    ```
* Java라면 무조건 null에 접근이 가능해, 언제든 null로 명시할 수 있지만 kotlin에서는 null 은 필요한 경우 명시해야 합니다.

## Late-Initialized properties
* 늦은 초기화를 위한 Properties
    ```kotlin
    class SampleActivity{
        //lateinit
        private lateinit var sampleAdapter : SampleAdapter

        override fun onCreate(savedInstanceState: Bundle?){
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_sample_main)
            sampleAdapter = SampleAdapter()
        }
    }
### lateinit 조건
* __var__(mutable)에서만 사용 가능
* var이기 때문에 언제든 초기화를 변경할 수 있음
* null을 통한 초기화를 할 수 없음
* 초기화를 하기 전에는 변수에 접근할 수 없음
    * lateinit property subject has not been initialized
* 변수에 대한 setter&getter properties 정의가 불가능함
* lateinit은 모든 변수가 가능한 건 아니고, primitive type 에서는 활용이 불가능함(Int,Double등)

### lateinit 초기화 확인하기
* ::을 통해서만 접근이 가능한 .isinitialized을 사용하여 체크할 수 있다.
    ```kotlin
    if(::sampleAdapter.isinitialized){
        sampleAdapter.addItem()
        sampleAdapter.notifyDataSetChanged()
    }
    ```

## Lazy properties
* lateinit은 필요할 경우 언제든 초기화가 가능한 Properties였다(var).
* lazy properties는 생성한 후 값을 변경할 수 없다(val)
* lazy 초기화는 기존 val 변수 선언에 by lazy를 추가함으로 lazy{}에 생성과 동시에 값을 초기화하는 방법을 사용한다
    ```kotlin
    val sampleAdapter : SampleAdapter by lazy{
        SampleAdapter()
    }
    ```
### lazy 조건
* 호출 시점에 by lazy 정의에 의해서 초기화를 진행
* val(immutable)에서만 사용이 가능
* __val__ 이므로 값을 교체하는 건 불가능하다
* 초기화를 위해서는 함수명이라도 한번 적어줘야 한다
* lazy를 사용하는 경우 기본 Synchronized로 동작한다
