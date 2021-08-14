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

## RxLifecycle 라이브러리
* 안드로이드의 액티비티와 프래그먼트의 라이프 사이클을 RxJava에서 사용할 수 있게 해줌
    * 구독할 때 발생할 수 있는 메모리 누수를 방지하기 위해 사용(완료하지 못한 구독을 자동해제)
* RxLifecycle 라이프 사이클 컴포넌트

    |컴포넌트|설명|
    |---|----|
    |RxActivity|액티비티에 대응|
    |RxDialogFragment|DialogFragment에 대응|
    |RxFragment|Fragment에 대응|
    |RxPreferenceFragment|PreferenceFragment에 대응|
    |RxAppCompatActivity|AppCompatActivity에 대응|
    |RxAppCompatDialogFragment|AppCompatDialogFragment에 대응|
    |RxFragmentActivity|FragmentActivity에 대응|
    * 예시
        ```java
        public class Activity extends RxAppCompatActivity {//<-AppCompatActivity 대신 RxAppCompat Activity 상속
        public static final String TAG = Activity.class.getsimpleName();

        @BindView(R.id.textView) TextView textView;

        private Unbinder mUnbinder;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mUnbinder = ButterKnife.bind(this);

            Observable.just("Hello, rx world")
                .compose(bindToLifecycle())
                .subscribe(textView::setText);
        }

        @Override
        protected void onDestroy(){
          super.onDestroy();
         if(mUbinder != null){
             mUnBinder.unbind();
            }
        }

        }
        ```
## UI 이벤트 처리
* 비동기적으로 UI의 이벤트를 처리할 수 있다
* 이벤트 리스너 인터페이스에 포함된 콜백 메서드

    |콜백 메서드 이름|설명|
    |---|---|
    |onClick()|enter키 혹은 트랙볼을 눌렀을 때 호출|
    |onLongClick()|enter키 혹은 트랙볼을 길게(1초이상) 눌렀을 때 호출|
    |onFocusChange()|아이템 위로 움직이게 하거나 포커스가 벗어날 때 호출|
    |onKey()|디바이스에 있는 키를 누르거나 놓았을 때 호출|
    |onTouch()|어떤 움직임을 포함하는 터치 이벤트 액션을 실행할 때 호출|
    |onCreateContextMenu()|길게 터치하거나 누른 결과로 컨텍스트 메뉴가 열렸을 때 호출|

    * onClick()의 Observable 예시
        ```java
        public class OnClickFragment extends Fragment {
            public static final String TAG = OnClickFragment.class.getSimpleName();

            @BindView(R.id.btn_click_observer)
            Button mButton;

            @Override
            public void onActivityCreated(@Nullable Bundle savedInstanceState){
                getClickEventObservable()
                    .map(s -> "clicked")
                    .subscribe(getObserver());
            }

            private Observable<View> getClickEventObservable() {
                return Observable.create(new ObservableOnSubscribe<View>(){
                    @Override
                    public void subscribe(ObservableEmitter<View> e) throws Exception {
                        mButton.setOnClickListener(e::onNext);
                    }
                }); 
            }

            private DisposableObserver<? super String> getObserver() {
                return new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s){log(s);}

                    @Override
                    public void onError(Throwable e){log(e.getMessage());}

                    @Override
                    public void onComplete(){log("complete");}
                }
            }
        }
      ```