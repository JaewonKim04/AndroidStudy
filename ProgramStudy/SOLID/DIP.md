# DIP: 의존성 역전의 원칙

(Dependency Inversion Priciple)

### 상위모듈은 하위모듈을 의존하면 안된다
* 상위모듈: 상위 정첵을 의미
* 하위모듈: 상위 정체에 따른 하위정책
### 추상화는 세부 사항에 의존해서는 안된다
### __변화하지 않는것에 의존하라__


## 예시
```java
class Pay{ //상위 모듈
    public void pay(){
        PayByMoney money = new PayByMoney();
        println(money.pay());
    }
}

class PayByMoney{ //하위 모듈
    public String pay(){
        ...
        return "Money";
    }
}
// 상위모듈이 하위모듈을 의존하고있음
// 공통부분을 추상화하여 interface 를 만듦

public interface Pay{
    String pay();
}
class Payment{
    public void pay(){
        Pay pay = new PayByMoney();
        println(pay.pay());
    }
}

class PayByMoney implements Pay { //하위 모듈
    @Override
    public String pay(){
        ...
        return "Money";
    }
}

//interface를 통해 의존성을 역적시킴