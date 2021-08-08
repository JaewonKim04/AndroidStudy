# Open Class

* Java 에서는 __final__ 이 붙지 않으면 다른 클래스에서 사용가능
* __kotlin에서는 final이 기본__ -> 어떤클래스의 상속을 허용하려면 open 을 붙어야함
  * 오버라이드를 허용하고싶은 __프로퍼티나 메소드__도 마찬가지

```kotlin
open class Car{
  fun cantOverride(){
    //이 메소드는 오버라이드 불가능
  }
  
  open fun canOverride(){
    //이 메소드는 오버라이드 가능
  }
  
  val cant = 0 // 이 프로퍼티는 오버라이드 불가능
  
  open val can = 0 // 이 프로퍼티는 오버라이드 가능
}
```

## abstract와의 차이점

* abstract는 필수로 override 해야하지만 open 은 __선택적으로 override__ 할수있다

```kotlin
abstract class Animal{
  abstract fun mustOverride(){
    // 필수적으로 override 해야함
  }
  
  open fun canOverride(){
    // 선택적으로 override 할수있음
  }
}
```

참고:[[Kotlin] 키워드 정리 (open, internal, data class)](https://velog.io/@conatuseus/Kotlin-%ED%82%A4%EC%9B%8C%EB%93%9C-%EC%A0%95%EB%A6%AC-open-internal-companion-data-class-%EC%9E%91%EC%84%B1%EC%A4%91)