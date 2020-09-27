## 파일입출력
* 내부 저장소: 특정 앱의 사용자가 접근할수있는 영역
1. 앱을 설치하면 생성됨
2. 특별한 권한 없이 읽고 쓸 수 있음
3. __주로 앱 내에서만 쓸 데이터를 저장__
* 외부저장소: 모든 앱이 공용으로 사용할 수 있는 영역
1. 안드로이드 q 부터 미디어스토어로만 접근가능(미디어 스토어: 외부저장소에 저장되는 파일을 관리하는 데이터베이스(파일목록을 관리하는 앱))
2. 접근하려면 권한을 명세해야함
```xml
<!--외부 저장소 읽기 권한-->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<!--외부 저장소 쓰기 권한-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```
3. 앱을 __제거한 뒤에도 데이터가 유지__ 되야 하거나 __다른 앱도 접근할 수 있는 데이터__여야함
* 내부 저장소 파일 읽기
0. 파일 겍체를 만든다
```kotlin
val file=File("경로")
```
1. 파일의 존재여부를 확인한다
```kotlin
if(file.exists()){
    //존재하고있음
}
```
2. 