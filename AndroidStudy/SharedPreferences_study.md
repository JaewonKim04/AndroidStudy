# SharedPreferences
* 내부 저장소를 이용함(권한 설정 필요x)
* 주로 로그인 정보나 앱의 상태 정보를 저장하는 용도로 사용됨
* 키,값쌍으로 저장할수 있음
* 값을 추가할때는 apply()를 사용해야함
### 값 저장하기
1. SharedPreference생성하기
2. Editor 꺼내기
3. putInt().putString()메서드로 저장하기
4. apply()로 파일에 반영하기
### 값 가져오기
1. SharedPreference생성하기
2. getInt(),getString()메서드로 값 읽어오기
## getSharedPreferences()
* Context를 가지고 있는 모든 컴포넌트에서 접근과 호출이 가능
* getSharedPreferences(저장될 파일명,모드)를 호출하면 SharedPreferences가 반환됨
```kotlin
val shared=getSharedPreferences("이름",Context.MODE_PRIVATE)
```
보안상의 이유로 모드는 __MODE_PRIVATE__ 만 사용
## getPreferences()
* 액티비티가 하나밖에 없을때 호출할수있음
* 호출하는 액티비티의 이름으로 저장 파일이 생성됨
```kotlin
var preference=getPreferences(Context.MODE_PRIVATE)
```
## Editor로 데이터를 저장하기
* SharedPreferences로 데이터를 저장하려면 Editor 인터페이스를 사용해야함
* edit() 함수로 호출가능
* 입력될 값의 타입에 맞는 Editor메서드를 사용해야함
* 마지막에 apply() 해야함
```kotlin
val shared=getSharedPreferences("이름",Context.MODE_PRIVATE)
val editor=shared.edit()
editor.putString("key이름","값")
editor.apply()
```
* Editor메서드들:putString,putFloat,putInt등등
## 데이터 불러오기
* editor을 사용하지 않음
* SharedPreferences의 메서드를 직접 호출해서 데이터를 불러옴
* defalutValue를 지정하면 데이터가 없을때 기본값을 반환함
```kotlin
val shared=getSharedPreferences("이름",Context.MODE_PRIVATE)
shared.getString("키","키값이비었을때기본값")?:"키가없을때기본값"
```
## Editor으로 삭제처리
* remove(Stirng key):해당 키의 데이터를 삭제함
* clear():모든 데이터를 삭제함
* apply():변경한 업데이트를 파일에 __비동기적으로__ 저장함
* commit():변경한 업데이트를 파일에 __동기적으로__ 저장함.   
UI스레드에서 호출하는 것을 피해야함
