# 권한
* 권한명세와 기능명세 둘다 __AndroidManifest.xml__ 에서 설정해야한다
### 권한명세
* 데이터나 기능의 사용여부를 설정
* "uses-permission"태그로 설정한다
### 기능명세
* 헤당 기능이있는 안드로이드 폰에서만 받을수있게 하는것
* "uses-feature"으로 설정한다
* 기능명세를 설정하면 기능사용여부로 플레이 스토어 검색조건이 결정됨

### 권한의 보호수준

#### 일반권한
* 설치시 사용자에게 권한 승인을 묻는 팝업창을 보여줌
* 종류
1. 인터넷사용
2. 인터넷 상태확인
3. 알람설정
4. 블루투스 상태확인
5. 기기 간 근거리 통신 사용
6. 진동설장   
쌍따옴표("")안에 android.permission.권한이름 으로 적으면 됨

#### 위험권한
* 사용자의 개인정보와 관련된 데이터나 기능을 액세스함
* 다른 앱 및 기기의 작동에 영향을 줄 우려가 있는 권한
* build.gradle파일의 targetSdkVersion 이 23 이상이어야 정상으로 작동함
* 종류
1. 캘린더 읽기,쓰기
2. 카메라 
3. 주소록 읽기,쓰기
4. 계정정보 가져오기
5. 위치정보 사용
6. 마이크 녹음
7. 폰 상태 정보
8. 전화번호 가져오기
9. 발신하기
10. 응답하기
11. 전화 로그 쓰기
12. 음성메일 추가
13. sip사용
14. 통화관련 brodcase수신
15. 바디센서
16. sms보내기,받기,읽기
17. wap수신
18. mms받기
19. 안드로이드 공용 저장소 읽기,쓰기
* 처리하기
* 소스코드에서 처리한다   
1. 권한에 대한 사용자 승인 확인   
__-권한의 승인상태가져오기:__  
```kotlin
val cameraPermission=Context.Compat.checkSelfPermission(this,Manifest.permission.CAMERA)   
```
__-권한여부 확인하기:__   
```kotlin
if(cameraPermssion==PackageManager.PERMSSION_GRANTED){//상태가 승인일때 코드}   
```
__아니라면 2단계 실행__   
2. 사용자에게 승인 요청   
-requestPermissions()를 호출해서 요청   
```kotlin
fun requestPermission(){
    ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CAMERA),99)
    //2번째 매개변수:권한이 복수있때를 대비한 배열
    //3번째 매개변수:요청한 주체가 어떤 것인지 구분하기 위한것
}
```    
-앱을 실행시키면 권한 승인을 묻는 팝업이 나타남   
3. 사용자 승인 후 처리
- 사용자가 거절 혹은 수락을 클릭하면 onRequestPermissionResult()메서드가 호출됨
```kotlin
override fun onRequestPermissionsResult(
        requestCode: Int, //요청한 주체를 확인하는 코드
        permissions: Array<out String>, //요청한 권한 목록
        grantResults: IntArray  //권한 목록에 대한 승인 미승인값,권한 목록의 개수
    ) {
        when(requestCode){
            99->{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    startProcess()
                }else{
                    finish()
                }
            }
        }
    }
```
#### 서명권한
* 권한을 사용하려는 앱이 권한을 정의하는 앱과 동일한 인증서로 서명된 경우 시스템은 권한을 자동으로 부여함(예:구글의 플레이 스토어,크롬,유튜브 등)