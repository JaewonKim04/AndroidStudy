## 파일입출력
* 내부 저장소: 특정 앱의 사용자가 접근할수있는 영역
1. 앱을 설치하면 생성됨
2. 특별한 권한 없이 읽고 쓸 수 있음
3. __주로 앱 내에서만 쓸 데이터를 저장__
* 외부저장소: 모든 앱이 공용으로 사용할 수 있는 영역
1. 안드로이드 q 부터 미디어스토어로만 접근가능(미디어 스토어: 외부저장소에 저장되는 파일을 관리하는 데이터베이스(파일들을 db에 추가해서 여러 앱에서 사용할수있게함))
2. 접근하려면 권한을 명세해야함
```xml
<!--외부 저장소 읽기 권한-->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<!--외부 저장소 쓰기 권한-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```
3. 앱을 __제거한 뒤에도 데이터가 유지__ 되야 하거나 __다른 앱도 접근할 수 있는 데이터__ 여야함
* 내부 저장소 파일 읽기
0. 파일 겍체를 만든다
```kotlin
val file=File("경로")
```
1. exists():파일의 존재여부를 확인한다
```kotlin
if(file.exists()){
    //존재하고있음
}
```
2. isFile:전달된 경로가 파일인지 확인함
```kotlin
if(file.isFile){
    //파일임을 확인함
}
```
3. isDirectory:전달된 경로가 디렉터리인지를 확인함
```kotlin
if(file.isDirectory){
    //디렉토리임을 확인함
}
```
4. name:파일 또는 디렉터리의 이름을 반환함
5. createNewFile():파일을 생성함(보통 exists()와 함께 사용됨)
```kotlin
if(!file.exist()){
    file.createNewFile()
}
```
6. mkdrs(): 디렉터리를 생성함
```kotlin
if(~file.exists()){
    file.mkdirs()
}
```
7. delete():파일이나 디렉터리를 삭제함(디렉터리는 안에 파일이 있으면 삭제하지 않음)
8. absolutePath:파일또는 디렉터리의 절대경로를 반환함
## 스트림
* 실제 데이터를 쓰고 읽을때 쓰는 복잡한 클래스
* 끝나면 파이프를 제거해서 __컴퓨터 자원을 효율적으로__ 사용할수있음
* 파일에 파이프를 하나 연결해 놓고 데이터를 꺼내는 방식
* 읽는용과 쓰는용이 구분되있음
### 텍스트 파일 읽기
1. 파일의 경로를 전달받는 메서드를 생성함
2. 전달받은 경로로 File로 생성하고 파일이 있는지 검사함
3. 없으면 공백값 리턴
4. FileReader으로 file을 읽고 BufferReader에 담아서 속도를 향상시킨다
5. buffer를 통해 임시로 저장할 temp변수를 선언하고 모든 내용을 저장할 StringBuffer를 result변수로 선언함
6. while문을 반복하면서 buffer에서 한 줄을 꺼내 temp 변수에 담고 그 값이 null이라면 더 이상 읽을 내용이 없으니 반복문을 빠져나감. 값이 있다면 result변수에 append()함
7. close()로 닫고 결괏값을 리턴함
```kotlin
fun readTextFile(fullPath:String):String{    //1
    val file=File(fullPath)
    if(!file.exists()) return ""             //2,3

    val reader=FileReader(file)
    val buffer=BufferedReader(reader)       //4
    var temp-""
    val result=StringBuffer()               //5
    while(true){
        temp=buffer.readLine()
        if(temp==null)break
        else result.append(buffer)
    }                                       //6
    buffer.close()
    return result.toString()                //7
}
```
### 내부 저장소 파일 쓰기
1. 파일을 생성할 디렉터리,파일명,작성할 내용을 전달하는 메서드를 생성함
2. directory가 존재하는지 검사하고 없으면 생성함.
3. directory에 파일명을 합해서 FileWriter로 생성함.
4. 생성한 FileWriter를 buffer에 담으면 속도가 향상됨
5. buffer로 내용을 쓰고 close()로 닫음
```kotlin
fun writeTextFile(directory:String,filename:String,content:String){//1
    val dir=File(directory)
    if(!dir.exists()){
        dir.mkrdirs()                                 //2
    }
    val writer=FileWriter(directory+"/"+filename)    //3
    val buffer=BufferedWriter(writer)               //4
    buffer.write(content)
    buffer.close                                   //5
}
```