# OCP: 개방 폐쇄의 원칙
(Open Close Principle)
### 소프트웨어의 구성요소(컴포넌트,클래스,모듈,함수)는 확장에는 열려있고, 변경에는 닫혀있어야한다
* 관리가능하고 재사용 가능한 코드를 만드는 기반
* 객체지향의 장점을 극대화하는 중요한 원리
* 중요 메커니즘: __다형성,추상화__

## 적용방법
1. 변경(확장)될것과 아닌것을 확실히 구분함
2. 이 두 모듈이 만나는 지점에 인터페이스를 정의
3. 구현에 의존하기보다 인터페이스에 의존하도록 코드를 작성

## 에시
```java
class Car{
    Integer serialNumber;
    CarSpec carSpec;
}
class CarSpec{
...
}

class Train{
    Integer serialNumber;
    TrainSpec trainSpec;
}
class TrainSpec{
...
}

// 자동차 외에 기차, 비행기등 다른 교통수단도 다뤄야한다면 일일이 새로운 만들어야함
// 이부분이 OCP적용 대상

// interface를 통해 해결

interface Tranceportation{
    Integer serialNumber;
    TranceportationSpec spec;

}

interface TranceportationSpec{
...
}

class Car extends Tranceportation{
    public Car(Integer serialNumber,CarSpec carspec){
        this.serialNumber = serialNumber;
        this.spec = carspec;
    }
}

class CarSpec extends TranceportationSpec{
    ...
}

class Train extends Tranceportation{
    public Train(Integer serialNumber,TrainSpec trainspec){
        this.serialNumber = serialNumber;
        this.spec = trainspec;
    }
}

class TrainSpec extends TranceportationSpec{
    ...
}