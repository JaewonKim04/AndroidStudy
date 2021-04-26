# Single클래스
* 보통 결과가 유일한 서버 API를 호출할때 유용하게 사용
* Observable 클래스는 데이터를 무한하게 발행할 수 있지만, Single 클래스는 오직 1개의 데이터만 발행
    * 데이터 하나의 발행과 동시에 종료(onSuccess)됨 -> onNext()와onComplete()가 onSuccess()로 통합됨
    * Single 클래스의 라이프 사이클 함수: onSuccess(T value), onError()