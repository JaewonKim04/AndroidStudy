# LSP: 리스코프 치완의 원칙
### 서브 타입은 언제나 기반 타입으로 교체할 수 있어야 함
* 서브타입은 기반타입이 약속한 규약을 지켜야함
    * 서브클래스가 확장에대한 인터페이스를 준수해야함

* 다형성을 통한 확장의 원리인 OCP를 제공하게됨

## 구현방법
* 두 개체가 똑같은 일을 한다면 __하나의 클래스로 표현__ 하고 이들을 구분할 수 있는 필드를 만듦
* 두개체가 똑같은 연산을 제공하지만, 이들이 약간씩 다르다면 공통의 __인터페이스__ 를 만들고 둘이 이를 구현
* 공통된 연산이 없다면 완전히 별개인 클래스 2개로 만듦
* 두 개체가 하는일에 추가적으로 무언가를 더한다면, 구현상속을 사용

## 예시
```java
void f(){
    LinkedList list = new LinkedList();
    ...
    modify(list)
}

void modify(LinkedList list){
    list.add(...);
    doSomethingWith(list);
}

// LinkedList만 사용한다면 상관없는 코드

// LinkedList 가 아닌 HashSet을 사용한다면 변경하기 불편

// LinkedList와 HashSet은 모두 Collection 인터페이스를 상속하고있으므로 다음과 같이 작성하는게 바람직


void f(){
    Collection collection = new LinkedList();
    ...
    modify(collection)
}

void modify(Collection collection){
    collection.add(...);
    doSomethingWith(collection);
}
```