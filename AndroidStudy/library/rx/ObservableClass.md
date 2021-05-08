# Observable 클래스
* 옵서버 패턴은 객체의 상태 변화를 관찰하는 관찰(옵서버)목록을 객체에 등록->상태 변화가 있을 때마다 메서드를 호출하여 객체가 직접 목록의 각 옵서버에게 변화를 알려줌
* 라이프사이클은 존재X
* 단일 함수를 통해 변화만 알림
## 알림
* __onNext__:데이터의 발행을 알림
* __onComplete__:모든 데이터의 발행이 완료됨을 알림(단 한 번만 발생,발생 후에는 onNext이벤트가 발생하면 안됨)
* __onError__:어떤 이유로 에러가 발생했음을 알림(__Observable의 실행을 종료함__)
## 함수
* __종류__
    * Observable을 생성하는 팩토리 함수
    * 중간 결과를 처리하는 함수
    * 디버그 및 예외 처리 함수
    * 등등 __많은 함수가 존재함__
    ## 팩토리함수(생성)
    * 정적 팩토리 함수를 호출하여 인스턴스를 생성
        * RxJava 1.x 기본팩토리 함수:__create(),just(),from()__
        * RxJava 2.x 추가팩토리 함수:__fromArray(),fromIterable(),fromCallable,fromFuture(),fromPublisher()__
        * 기타팩토리 함수:__interval(),range(),timer(),defer()등__

        ### 1.just()
        * 인자로 넣은 데이터를 __차례로 발행하는__ Observable을 생성
        * 한개의 값을 넣을 수도 있고 인자로 여러개의 값(최대 10개,모두 같은 타입)을 넣을 수도 있음
        * Observable에서 발행하는 데이터를 그대로 발행하는 함수

        ### 2.create()
        * 알림이벤트(onNext,onComplete,onError)같은 알림을 개발자가 직접 호출해야함
        * 개발자가 무언가를 직접하는 느낌이 강한 함수
        * create()함수를 활용해 데이터를 발행하는 방법
            ```java
            Observable<Integer> source = Observable.create(
                (ObservableEmiiter<Integer> emitter) -> {
                    emitter.onNext(100);
                    emitter.onNext(200);
                    emitter.onNext(300);
                    emiiter.onComplete();
                }
            );
            source.subscribe(System.out::println);
            /*
            * Observable<Integer>타입의 변수를 분리했다(차가운 observable)
            * subscribe를 호출하지 않으면 아무것도 출력되지 않음
            */
            ```
        * RxJava에 익숙한 사용자만 활용하도록 권고함
        * 그래도 사용해야한다면 아래 사항을 확인해야함
            1. Observable이 구독 해지 되었을 때 등록된 콜백을 모두 해제해야함. 그렇지 않으면 잠재적으로 메모리 누수가 발생함
            2. 구독자가 구독하는 동안에만 onNext와onComplete 이벤트를 호출해야 함
            3. 에러가 발생했을 때는 오직 onError 이벤트로만 에러를 전달해야 함
            4. 배압(back pressure)을 직접 처리해야함

        ### 3.fromArray()
        * just()나 create()는 단일 데이터를 다룸
        * fromXXX():단일 데이터가 아닐때 사용
        * 배열에 들어있는 데이터를 처리할때:__fromArray()__
            ```java
            Integer[] arr = {100,200,300}; //Integer로 사용해야함
            Observable<Integer> source = Observable.fromArray(arr);
            source.subscribe(System.out::println);
            ```
        ### 4.fromIterable()
        * ArrayList,SetBlockQueue등의 데이터일때 사용
        ### 5. fromCallable()
        * 기존 자바에서 제공하는 비동기 클래스나 인터페이스와의 연동을 위한 함수
        * 예시코드
            ```java
            Callable<String> callable = () -> {
                Tread.sleep(1000);
                return "Hello Callable";
            };

            Observable<String> source = Observable.fromCallable(callable);
            source.subscribe(System.out::println);
            ```
        ### 6.fromFuture()
        * Future 객체와의 연동을 위한 함수
        * 예시코드
            ```java
            Future<String> future = Executors.newSingleThreadExecutor().submit(() -> {
                Thread.sleep(1000);
                return "Hello Future";
            })
            Observable<String> source = Observable.fromFuture(future);
            source.subscribe(System.out::println);
            ```
        ### 7. fromPublisher()
        * Publisher 객체와의 연동을 위한 함수
        * 예시코드
            ```java
            import org.reactivestreams.Publisher;
            import org.reactivestreams.Subscriber;
            import io.reactivex.Observable;

            Publisher<String> publisher = (Subscriber<? super String> s) ->{
                s.onNext("Heelo Observable.fromPublisher()");
                s.onComplete();
            };
            Observable<String> source = Observable.fromPublicsher(publisher);
            source.subscribe(System.out::println);
    ## subscribe()함수&Disposable객체
    * subscribe():실제로 실행되는 시점을 조절
        * Observable은 데이터 흐름을 정의한후 __subscribe()함수를 호출해야 실제로 발행__
        * __인자값에 따른 subscribe()__
            * __subscribe()__: onError 이벤트가 발생했을때만 OnErrorNotImplementedExceoption을 던짐(__테스트 하거나 디버깅할 때 활용)
            * __subscribe(1개)__: onNext 이벤트를 처리,onError 이번트가 발생하면 OnErrorNotImplementedExceoption을 던짐
            * __subscribe(2개)__:onNext,onError,onComplete 이벤트 처리
            * __subscribe(3개)__:onNext,onError,onComplete 이벤트 모두 처리
        * __Disaposable 객체__
            * subscribe() 함수들은 모두 Disaposable 인터페이스의 객체를 리턴
            * Observable에게 더이상 데이터를 발행하지 않도록 __구독을 해지하는 함수__
            * 따로 호출할 필요는 없음
    ## 뜨거운&차가운 Observable
    * 차가운 Observable
        * just(),fromIterable()함수를 호출해도 subscribe()함수를 호출하여 구독하지 않으면 데이터를 발행하지 않음 (__게으른 접근법__)
        * 웹 요청, 데이터베이스 쿼리와 파일 읽기등
    * 뜨거운 Observable
        * 구독자의 존재여부와 관계없이 데이터를 발행하는 Observable
            * 여러 구독자를 고려할 수 있음
            * 마우스 이벤트, 키보드 이벤트, 시스템 이벤트, 센서 데이터,주식 가격
    * Subject 클래스
        * 차가운 Observable을 뜨거운 Observable로 바꿔주는 클래스
        * Observable처럼 데이터를 발행할 수도 있고, 구독자처럼 발행된 데이터를 바로 처리할 수도 있음
        * __AsyncSubject__ 클래스
            * Observable에서 발행한 마지막 데이터를 얻어올 수 있는 Subject 클래스
            * 예시
                ```java
                AsyncSubject<String> subject = AsyncSubject.create();
                subject.subscribe(data -> System.out.println("Subscriber #1 =>"+data));
                subject.onNext("1");
                subject.onNext("3");
                subject.subscribe(data -> Sustem.out.println("Subscriber #2 =>"+data));
                subject.onNest("5");
                subject.onComplete();
                ```
                실행결과
                ```
                Subscriber #1 =>5
                Subscriber #2 =>5
                ```
        * __BehaviorSubject__ 클래스
            * 구독을 하면 가장 최근 값 또는 기본값을 넘겨주는 클래스
            * 예시
                ```java
                BegaviorSubject<String> subject = BehaviorSubject.createDefault("6");
                subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
                subject.onNext("1");
                subject.onNext("3");
                subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
                subject.onNext("5");
                subject.onComplete();
                ```
                실행결과
                ```
                Subscriber #1 => 6
                Subscriber #1 => 1
                Subscriber #1 => 3
                Subscriber #2 => 3
                Subscriber #1 => 5
                Subscriber #2 => 5
                ```
        * __PublishSubject__ 클래스
            * 가장 평범한 Subject 클래스
            * subscribe() 함수를 호출하면 값을 발행하기 시작
            * 해당시간에 발생한 데이터를 그대로 구독자에게 전달받음
            * 예시
                ```java
                PublishSubject<String> subject = PublishSubject.create();
                subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
                subject.onNext("1");
                subject.onNext("3");
                subject.subscribe(data -> System.out.println("Subscriber #2 =>" + data));
                subject.onNext("5");
                subject.onComplete();
                ```
                실행결과
                ```
                Subscriber #1 => 1
                Subscriber #1 => 3
                Subscriber #1 => 5
                Subscriber #2 => 5
                ```
        * __ReplaySubject__ 클래스
            * 뜨거운 Observable을 활용하는 목적인데, 차가운 Observable처럼 동작함
            * 구독자가 새로 생기면 항상 데이터의 처음부터 끝까지 발행함
            * 모든 데이터 내용을 저장해두는 과정중 메모리 누수가 발생할 가능성을 염두에 두고 사용해야함
            * 예시
                ```java
                ReplaySubject<String> subject = ReplaySubject.create();
                subject.subscribe(data -> System.out.println("Subscriber #1 =>" + data));
                subject.onNext("1");
                subject.onNext("3");
                subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
                subject.onNext("5");
                subject.onComplete();
                ```
                실행결과
                ```
                Subscriber #1 => 1
                Subscriber #1 => 3
                Subscriber #2 => 1
                Subscriber #2 => 3
                Subscriber #1 => 5
                Subscriber #2 => 5
                ```
    ## ConnectableObservable 클래스
    * 차가운 Observable을 뜨거운 Observable으로 변환
    * 원 데이터 하나를 여러 구독자에게 동시에 전달할 떄 사용함
    * Observable에 publish() 함수를 호출 해야함
    * connect() 함수를 호출한 시점부터 subscribe() 함수를 호출한 구독자에게 데이터를 발행함
    * 예시
        ```java
        String[] dt ={"1","3","5"};
        Observable<String> balls = Observable.invertal(100L,TimeUnit.MILLISECONDS)  //100ms 간격으로 발행
            .map(Long::intValue)
            .map(i -> dt[i])
            .take(dt.length);
        ConnectableObservable<String> source = balls.publish();
        source.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        source.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        source.connect(); //발행시작

        CommonUtils.sleep(250);
        source.subscribe(data -> System.out.println("Subscriber #3 => " + data));
        CommonUtils.sleep(100);
        ```
        실행결과
        ```
        Subscriber #1 => 1
        Subscriber #2 => 1
        Subscriber #1 => 3
        Subscriber #2 => 3
        Subscriber #1 => 5
        Subscriber #2 => 5
        Subscriber #3 => 5
        ```
        

참고도서: [RxJava프로그래밍-유동환,박정준 지음](https://book.naver.com/bookdb/book_detail.nhn?bid=12495967)