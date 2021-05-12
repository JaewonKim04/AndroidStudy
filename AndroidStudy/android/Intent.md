# Intent
## 역할
* __액티비티 시작__   
    * Activity의 새 인스턴스를 시작하려면 Intent를 startActivity()로 전달하면 됨 
    * __Intent는 시작할 액티비티를 설명하고 모든 필수 데이터를 담음__
* __서비스 시작__   
    * Service는 사용자 인터페이스 없이 백그라운드에서 작업을 수행하는 구성요소   
    * JobScheduler로 서비스를 시작할 수 있음
    * 서비스를 시작하여 일회성 작업을 수행하도록 하려면 Intent를 startService()에 전달하면 됨
    * __Intent는 시작할 서비스를 설명하고 모든 필수 데이터를 담고 있음__
* __브로드캐스트 전달__
    * 브로드 캐스트:모든 앱이 수신할 수 있는 메시지
    * 시스템은 시스템이 부팅될 때 또는 기기가 충전을 시작할 때 등 시스템 이벤트에 대한 다양한 브로드캐스트를 전달
    * Intent를 sendBroadcast() 또는 sendOrderedBroadcast()에 전달하면 다른 앱에 브로드캐스트를 전달할 수 있음

## 유형
* __명시적 인텐트__
    * 인텐트를 충족하는 애플리케이션이 무엇인지 지정
    * 주로 애플리케이션 내부에서 사용
    * __특정 컴포넌트나 액티비틸가 명확하게 실행되어야할 경우__ 사용
    * 예시
        ```java
        Intent intnet = new Intent(getApplication(),Activity.class);
        startActivity(intent);
        ```
* __암시적 인텐트__
    * 수행할 일반적인 작업을 선언하여 다른 앱의 구성요소가 이를 처리할 수 있도록 함
    * __인텐트의 액션과 데이터를 지정하긴 했지만, 호출할 대상이 달라질 수 있는 경우__ 사용
    * 예시
        ```java
        Intent intent = new Intent(Intent.ACTION_VIEW,Url.parse("http://www.google.com/"));
        startActivity(intent);
        ```