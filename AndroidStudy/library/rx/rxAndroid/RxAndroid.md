# RxAndroid
* 기존 안드로이드 개발에서의 어려움
    1. 안드로이드의 비동기 처리 및 에러 핸들링
    2. 수많은 핸들러와 콜백 때문에 발생하는 디버깅 문제
    3. 2개의 비동기 처리 후 결과를 하나로 함성하는 작업
    4. 이벤트 중복 실행
* RxAndroid의 장점
    1. 간단한 코드로 복잡한 병행 프로그래밍 가능
    2. 비동기 구조에서 에러를 다루기 쉬움
    3. 함수형 프로그래밍 기법도 부분적으로 적용가능
## 리액티브 라이브러리와 API
* RxAndroid는 기본적으로 RxJava의 리액티브 라이브러리를 이용
## 안드로이드 스튜디오 환경 설정
* build.gradle 파일의 dependencies 부분에 RxAndroid 라이브러리를 추가해야함
* RxAndroid는 RxJava에 대한 의존성이 있어 RxJava를 추가하지 않아도 되지만, 최신 버전의 RxJava를 사용하려면 명시해주는게 좋음
## RxAndroid 기본
* RxJava와 기본개념은 동일
* __RxJava의 구조에 안드로이드의 각 컴포넌트를 사용할 수 있게 변경한것__
* RxAndroid의 스케줄러
    * __AndroidSchedulers.mainThread()__:안드로이드의 UI 스레드에서 동작하는 스케줄러
    * __HandlerScheduler.from(handler)__:특정 핸들러에 의존하여 동작하는 스케줄러

