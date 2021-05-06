# ViewBinding
* XML의 view component에 접근하는 __object를 반환받아__ view에 접근하는 방식
* object는 Android studio에서 자동으로 만들어줌
## 사용법
1. build.gradle(module)에서 viewBinding을 활성화 시킨다
```gradle
android{
    ...
    viewBinding{
        enabled=true
    }
}
```
레이아웃 파일을 무시하려면 다음코드를 레이아웃 파일의 루트 뷰에 추가
```xml
<LinearLayout
...
tools:viewBindingIgnore="true">
</LinearLayout>
```
2. 자동으로 생성된 object를 호출한다(xml파일 이름을 카멜표기법으로 변환후 binding 추가)
```kotlin
private lateinit var binding:ActivityMainBinding
// xml파일이름:activity_main
```
3. 생성된 클래스에서 inflate()메서드를 호출
```kotlin
binding=ActivityMainBinding.inflate(layoutInflater)
```
4. getRoot()메서드로 루트 뷰 참조를 가져옴
5. onCreateView()메서드에서 루트뷰를 반환하여 화면상의 활성 뷰로 만듦
```kotlin
val view=binding.root
setContentView(view)
```
## 장점
* __Null 안전__:유효하지 않은 뷰ID로 인한 null포인터 예외가 발생하지 발생하지 않음. 레이아웃의 일부 구성에만 뷰가 있는 경우 결합 클래스에서 참조를 포함하는 필드가  @Nullable로 표시됨.
* __유형안전__:각 바인딩 클래스에 있는 필드의 유형이 XML파일에서 참조하는 뷰와 일치함.즉, 클래스 변환 예외가 발생할 위험이 없음.