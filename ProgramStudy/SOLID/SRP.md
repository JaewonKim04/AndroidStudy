# SRP: 단일 책임 원칙

(Single Responsiblility Principle)

###  하나의 모듈은 오직 하나의 엑터에 대해서만 책임져야 한다
### 클래스가 제공하는 모든서비스는 그 하나의 책임을 수행하는 데 집중되어야 한다

* 클래스를 변경해야하는 이유는 오직 하나여야함

## 이점
* 책임영역이 확실해, 다른책임의 변경으로부터 자유로움
* 코드의 가독성 향상
* 유지보수 용이
* __다른 원리들을 적용하는데에 기초가 됨__

## 적용방법
1. 여러 원인에 의한 변경: __Extract class를 통해__ 혼재된 책임을 개별 클래스로 분할하여 __클래스 하나당 하나의 책임__ 을 맡도록 하는것. 이때, 두 클래스간의 복잡도를 줄이도록 설계 하는게 중요함. 만일 Extract class된 각각의 클래스가 동일한 책임을 지고있으면 __Extract Superclass__ 를 사용할 수 있음.
    * __Extract Superclass__:유사한 책임은 부모에게 위임하는 것
2. 산탄총 수술: Move field, Move Method를 통해 책임을 기존의 어떤 클래스로 모으거나, 새로운 클래스를 만듦.

## 예시
```java
class Car{
    Integer serialNumber;
    Integer price;
    String type;
    String backWood;
}
// serialNumber은 고유정보이지만, 나머지는 특성정보군이므로 변경 할 수 있는 부분
// 이부분이 SRP 적용 대상

class Car{
    Integer serialNumber;
    CarSpec carSpec;
}

class CarSpec{
    Integer price;
    String type;
    String backWood;
}

```
