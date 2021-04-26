# Single클래스
* 보통 결과가 유일한 서버 API를 호출할때 유용하게 사용
* Observable 클래스는 데이터를 무한하게 발행할 수 있지만, Single 클래스는 오직 1개의 데이터만 발행
    * 데이터 하나의 발행과 동시에 종료(onSuccess)됨 -> onNext()와onComplete()가 onSuccess()로 통합됨
    * Single 클래스의 라이프 사이클 함수: onSuccess(T value), onError()
## 함수
* Observable과 거의 같은 방법으로 활용할 수 있음
    ### just()
    * 정적 팩토리 함수 just()를 호출해서 Single객체를 생성할 수 있음
    * 예시코드
        ```java
        Single<String> source = Single.just("Hello Single");
        source.subscribe(System.out::println);
        ```
        