# 메서드 정리

- 메서드 추출(Extract Method)
  - 지역 변수를 처리하는 것이 가장 힘들다. 주로 임시변수 때문에 힘들다. 
  - 임시변수를 메서드 호출로 전환(Replace Temp with Query): 없어도 되는 임시 변수를 제거
    - 임시변수 분리(Split Temporary Variable): 임시변수가 여러 개 사용될 때 실시
- 메서드를 메서드 객체로 전환(Replace Method with Method Object): 임시변수가 너무 얽혀 있어서 메서드 호출로 전환할 수 없을 때
- 매개변수로의 값 대입 제거(Remove Assignments to Parameters): 매개변수로ㅓ 대입하는 값이 있을 때
- 메서드 내용 직접 삽입(Inline Method): 호출되는 메서드의 내용을 호출하는 메서드에 직접 넣는 기법
  - 여러 묶음의 코드를 개별 메서드로 빼내고 보니 그렇게 만들어진 일부 메서드가 제 역할을 못하거나 그 메서드들을 쪼갠 방식을 바꿔야 할 때는 이 방법을 사용한다.

- 알고리즘 전환(Substitue Algorithm): 메서드를 잘게 쪼개면 동작 원리를 이해하기 훨씬 쉽고, **알고리즘을 더 명료하게 개선할 수 있다.**



## 메서드 추출 Extract Method

어떤 코드를 그룹으로 묶어도 되겠다고 판단될 댄 그 코드를 빼내어 목적을 잘 나타내는 직관적 이름의 메서드로 만들자. 

### 동기

- 직관적인 이름의 간결한 메서드를 만드는 이유
  - 메서드가 적절하게 잘게 쪼개져 있으면 다른 메서드에서 **쉽게 사용**할 수 있다.
  - **상위 계층의 메서드에서 주석 같은 더 많은 정보를** 읽어들일 수 있다.
  - **재정의**하기 훨씬 수월하다.
- 중요한 것은 길이가 아니라 **메서드명과 메서드 내용**의 의미적 차이이다.



### 방법

- **원리가 아니라 기능(목적)을 나타내는** 이름의 새 메서드 작성
  - 새 메서드 명을 통해 그 코드의 기능(목적)을 더 잘 드러낼 수 있을 때만 추출을 실시한다.
- 코드 복사
- 기존 메서드의 모든 **지역변수 참조**를 찾는다. 이는 새로 생성한 메서드의 **지역변수나 매개변수**로 사용할 것이다.
- 빼낸 코드 안에서만 사용되는 **임시변수**가 있다면 이것을 새로 생성한 메서드 안에 임시변수로 선언한다.
- 추출 코드에 의해 **변경되는 지역변수**가 있는지 파악한다.
  - 하나의 지역변수만 변경될 때: 리턴값을 변수에 대입할 수 있는지 알아낸다
  - 이것이 까다롭거나 둘 이상의 지역변수가 변경될 때
    - 메서드를 호출하기 위해 임시변수 분리 기법 사용
    - 임시변수를 제거하려면 임시변수를 메서드 호출로 전환 기법을 사용한다.
- 빼낸 코드에서 읽어들인 지역변수를 대상 메서드에 매개변수로 전달
- 모든 지역변수 처리를 완료했으면 컴파일 실시
- 원본 메서드 안에 있는 빼낸 코드 부분을 새로 생성한 메서드 호출로 수정
  -  임시 변수가 원본 코드 외부에 선언되어 있는지 검사해서 그렇다면 대상 코드에서는 선언 부분을 삭제



### 예제: 지역변수 사용 안 함

```java
void printOwing() {
  Enumeration e = _orders.elements();
  double outstanding = 0.0;
  
  printBanner();
  
  while (e.hasMoreElements()) {
    Order each = (Order) e.nextElement();
    outstanding += each.getAmount();
  }
  
  System.out.println("고객명: " + _name);
  System.out.println("외상액: " + outstanding);
}

void printBanner() {
  // 배너 출력 코드
  System.out.println("*********************");
  System.out.println("*******고객외상*******");
  System.out.println("*********************");
}
```



### 예제: 지역 변수 사용

지역변수는 해당 메서드 안에서만 효력이 있으므로 메서드 추출을 적용하면 지역변수와 관련된 작업을 추가로 처리해야 한다. 간혹 지역변수 때문에 리팩토링이 아예 불가능한 경우도 있다.

지역변수가 읽히기만 하고 변경되지 않을 때는 가장 가벼운 경우로, 필요한 만큼의 지역변수를 그냥 매개변수로 전달한다. 지역 변수가 객체이고 그 변수에서 쓰기 메서드를 호출할 때에도 마찬가지로 매개변수로 전달한다.

```java
void printOwing() {
  Enumeration e = _orders.elements();
  double outstanding = 0.0;
  
  printBanner();
  
  while (e.hasMoreElements()) {
    Order each = (Order) e.nextElement();
    outstanding += each.getAmount();
  }

  printDetails(outstanding);
}

void printDetails (double outstanding) {
  System.out.println("고객명: " + _name);
  System.out.println("외상액: " + outstanding);
}
```



### 예제: 지역변수를 다시 대입

지역변수로의 값 대입은 복잡한데, 이때는 임시변수만 생각하면 된다. 매개변수로의 값 대입이 있을 경우 즉시 매개변수로의 값 대입 제거(Remove Assginments to Parameters)를 실시한다.

임시변수로의 값 대입은 두 가지 경우가 있다.

- 임시변수가 추출한 코드 안에서만 사용되는 경우: 임시변수를 추출한 코드로 옮긴다.
- 임시변수가 추출한 코드 밖에서 사용되는 경우
  - 코드가 추출된 후 사용되지 않는다면 추출한 코드에서 **그 임시변수의 변경된 값을 반환하게 수정**해야 한다.

```java
void printOwing() {
  // outstanding은 기존 메서드에서도 쓰이고 새 메서드에서도 쓰인다.
  // 따라서 빼낸 메서드에서 반환하게 만든다.
  double outstanding = getOutstanding();
  printBanner();
  printDetails(outstanding);
}

double getOutstanding() {
  // 빼낸 코드에만 사용되므로 변수를 아예 새 메서드로 옮긴다.
  Enumeration e = _orders.elements();
  double outstanding = 0.0;
  while (e.hasMoreElements()) {
    Order each = (Order) e.nextElement();
    outstanding += each.getAmount();
  }
  return outstanding;
}
```

빼낸 메서드를 테스트했으면 **반환값의 이름을 변경**하자.

```java
double getOutstanding() {
  Enumeration e = _orders.elements();
  // 분명한 초깃값으로만 초기화되므로 빼낸 메서드 안에서만 초기화할 수 있다.
  // 그 변수에 더 복잡한 작업이 일어날 땐 이전 값을 매개변수로 전달한다.
  double result = 0.0;
  while (e.hasMoreElements()) {
    Order each = (Order) e.nextElement();
    result += each.getAmount();
  }
  return result;
}
```

outstanding 값에 더 복잡한 작업이 일어날 땐 이전 값을 매개변수로 전달해야 한다.

```java
void printOwing(double previousAmount) {
  Enumeration e = _orders.elements();
  double outstanding = previousAmount * 1.2;
  printBanner();
  
  while (e.hasMoreElements()) {
    Order each = (Order) e.nextElement();
    outstanding += each.getAmount();
  }
  
  printDetails(outstanding);
}
```

외상액 계산 코드를 메서드로 빼내면 다음과 같다.

```java
void printOwing(double previousAmount) {
  double outstanding = previousAmount * 1.2;
  printBanner();
  outstanding = getOutstanding(outstanding);
  printDetails(outstanding);
}

double getOutstanding(double initialValue) {
  double result = initialValue;
  Enumeration e = _orders.elements(); 
  while (e.hasMoreElements()) {
  	Order each = (Order) e.nextElement();
   	result += each.getAmount();
  }
  return result;
}
```

컴파일과 테스트를 실시하고 outstanding 변수의 초기화 방식을 수정한다.

```java
void printOwing(double previousAmount) {
  printBanner();
  double outstanding = getOutstanding(previousAmount * 1.2);
  printDetails(outstanding);
}
```

변수를 두 개 이상 반환할 때는 어떻게 해야 할까? 

- 각기 다른 값을 하나씩 반환하는 여러 개의 메서드를 만든다.
-  **임시변수를 메서드 호출로 전환** 기법: 임시변수의 수를 줄인다.
- **메서드를 메서드 객체로 전환** 기법 사용: 임시변수의 수나 용도에 상관 없이 적용 가능



## Inline Method: 메서드 내용 직접 삽입

메서드 기능이 너무 단순해서 메서드명만 봐도 너무 뻔할 땐 그 메서드의 기능을 호출하는 메서드에 넣어버리고 그 메서드는 삭제한다.

### 동기

- 메서드명에 모든 기능이 반영될 정도로 메서드 내용이 지나치게 단순할 때 메서드 삭제

- 메서드를 잘못 쪼개었을 때

  - 잘못 쪼개진 메서드의 내용을 하나의 큰 메서드에 직접 삽입한 후, 합친 메서드에서 다시 각각의 작은 메서드로 추출한다.

- **Replace Method with Method Object** 기법을 적용하기 전에 실시
  - 필요한 기능이 든 메서드의 각종 호출을 메서드 객체에 넣는다.
- **과다한 인다이렉션**과 동시에 모든 메서드가 **다른 메서드에 단순히 위임을 하고 있어서 코드가 지나치게 복잡할 때 사용**

### 방법

- 메서드가 정의되어 있지 않은지 확인한다.
- 그 메서드를 호출하는 부분을 모두 찾는다.
- 각 호출 부분을 메서드 내용으로 교체한다.
- 테스트를 실시한다.
- 메서드 정의를 삭제한다.

재귀 처리 방법, 여러 개의 반환 지점 처리 방법, 접근 메서드가 없을 때 다른 객체 안에 직접 삽입하는 방법이 필요할 수도 있는 복잡한 기법이다. 그러나 이렇게 복잡한 상황일 때는 이 기법을 사용하지 않는 것이 좋다.



## Inline temp: 임시변수 내용 직접 삽입

**간단한 수식을 대입받는 임시변수**로 인해 **다른 리팩토링 기법 적용이 힘들 땐**(Extract Method 등) 그 임시변수를 참조하는 부분을 전부 **수식으로 치환**한다.

```java
double basePrice = anOrder.basePrice();
return (basePrice > 1000)
```

```java
return (anOrder.basePrice() > 1000)
```

### 방법

- 대입문의 우변에 문제가 없는지 확인한다.
- 문제가 없다면 임시변수를 final로 선언하고 컴파일한다.
- 그 임시변수를 참조하는 모든 부분을 찾아 대입문 우변의 수식으로 바꾼다.
- 하나씩 수정을 마칠 때마다 컴파일과 테스트를 실시한다.
- 임시변수 선언과 대입문을 삭제한다.
- 컴파일과 테스트를 실시한다.



## Replace Temp with Query: 임시변수를 메서드 호출로 전환

**수식의 결과를 저장하는 임시변수**가 있을 땐 그 수식을 빼내어 **메서드로 만든 후**, 임시변수 참조 부분을 전부 수식으로 교체한다. 새로 만든 메서드는 다른 메서드에서도 호출 가능하다.

```java
double basePrice = _quantity = _itemPrice;
if (basePrice > 1000)
  return basePrice * 0.95;
else
  return basePrice * 0.98;
```

```java
if (basePrice() > 1000)
  return basePrice() * 0.95;
else
  return basePrice() * 0.98;

double basePrice() {
  return _quantity * _itemPrice;
}
```

### 동기

임시변수는 **일시적**이고 **적용이 국소적 범위로 제한**된다는 단점이 있다. 임시변수는 **자신이 속한 메서드의 안에서만 인식**되므로, 그 임시변수에 **접근하려다보면 코드는 길어지게 마련**이다. 임시변수를 메서드 호출로 수정하면 클래스 안 **모든 메서드가 그 정보에 접근할 수 있다**. 이렇게 하면 클래스의 코드가 훨씬 **깔끔**해진다.

Etract Method를 적용하기 전에 반드시 적용해야 한다. 지역변수가 많을수록 메서드 추출히 힘들어지기 때문에 **최대한 많은 변수를 메서드 호출로 고쳐야 한다.**

임시변수에 값이 한 번만 대입되고 대입문을 이루는 수식에 문제가 없을 때 이 기법을 사용한다. 다른 경우에는 Split Temporary Variable이나 Seperate Query(값 반환 메서드) from Modifier(상태 변경 메서드)를 먼저 적용해야 할 때도 있다. 

### 방법

- 값이 한 번만 대입되는 임시변수를 찾는다.
  - 값이 여러번 대입된다면 Split Temporary Variable 기법을 고려해본다.
- 임시변수를 final로 선언한다 => 이제 임시변수에는 값을 한 번만 대입할 수 있다.
- 컴파일을 실시한다.
- 대입문 우변을 빼내어 메서드로 만든다.
  - 처음에는 메서드를 private 로 선언한다.
  - 추출 메서드에 문제가 없는지(즉, 객체를 변경하지 않는지) 확인한다. 
  - 객체 변경 등 문제가 있다면 Seperate Query from Modifier 기법을 실시한다.
- 컴파일과 테스트를 실시한다.
- 임시변수를 대상으로 Inline Temp 기법을 실시한다.



### 예제

```java
double getPrice() {
  int basePrice = _quantity * _itemPrice;
  double discountFactor;
  if (basePrice > 1000) discountFacotr = 0.95;
  else discountFactor = 0.98;
  return basePrice * discountFactor;
}
```

**임시변수를 final로 선언**하여 **그 임시변수들이 값을 한 번만 대입받는지 시험**해본다.

```java
double getPrice() {
  final int basePrice = _quantity * _itemPrice;
  final double discountFactor;
  if (basePrice > 1000) discountFactor = 0.95;
  else discountFactor = 0.98;
  return basePrice * discountFactor;
}
```

컴파일을 시도해서 문제가 있는 지 확인한다. 이제 임시변수를 하나씩 메서드 호출로 바꾼다.

```java
double getPrice() {
  final int basePrice = basePrice();
  final double discountFactor;
  if (basePrice > 1000) discountFactor = 0.95;
  else discountFactor = 0.98;
  return basePrice * discountFactor;
}

private int basePrice() {
  return _quantity * _itemPrice;
}
```

Inline Temp(임시변수 내용 직접 삽입)을 실시하고, 더 이상 임시 변수 참조가 없으면 임시변수 선언을 삭제한다.

```java
double getPrice() {
  final double discountFactor;
  if (basePrice() > 1000) discountFactor = 0.95;
  else discountFactor = 0.98;
  return basePrice() * discountFactor;
}
```

나머지 임시변수 discountFactor에 대해서도 똑같은 과정을 수행한다.

```java
private double discountFactor() {
  return (basePrice() > 1000) ? 0.95 : 0.98;
}
```

최종 getPrice 메서드의 모습은 다음과 같다.

```java
private getPrice() {
  return basePrice() * discountFactor();
}
```



## Introduce Explaining Variable: 직관적 임시변수 사용

> 사용하는 수식이 복잡할 땐 수식의 결과나 수식의 일부분을 용도에 부합하는 직관적 이름의 임시변수에 대입한다.

```java
if ((platform.toUpperCase().indexOf("MAC") > -1 ) && (browser.toUppserCase().indexOf("IE") > -1) && wsInitialized() && resize > 0) {
  // 기능 코드
}
```

```java
final boolean isMacOs = platform.toUppserCase().indexOf("MAC") > 0;
final boolean isIEBrowser = browser.toUpperCase().indexOf("IE") > 0;
final boolean wasResized = resize > 0;

if (isMacOS && isIEBrowser && wasInitialized() && wasResized) {
  // 기능코드
}
```



### 동기

조건문에서 각 조건 절을 가져와서 직관적 이름의 임시변수를 사용해 그 조건의 의미를 설명하려 할 때 많이 사용한다. 그 외에 긴 알고리즘에서 임시변수를 사용해 계산의 각 단계를 설명할 수 있을 때도 사용한다.

하지만 임시변수를 사용하고 싶을 때는 Extract Method 등 더 좋은 방법이 있는지 고민해 보는 것이 좋다. 임시변수는 하나의 메서드 안에서만 사용할 수 있지만, 메서드는 해당 객체의 전역뿐 아니라 다른 객체에도 사용할 수 이씩 때문이다. 단 지역변수로 인해 Extract Method가 힘들 때는 Introduce Explaining Variable 기법을 고려한다.



### 방법

- 임시변수를 final로 선언하고, 복잡한 수식에서 한 부분의 결과를 그 임시변수에 대입한다.
- 그 수식에서 한 부분의 결과를 그 임시변수의 값으로 교체한다.
- 컴파일과 테스트를 실시한다.
- 수식의 다른 부분을 대상으로 과정을 반복 실행한다.



### 예제: 직관적 임시변수 사용

```java
double price() {
  // price = base price - quantity discount + shipping
  return _quantity * _itemPrice - 
    Math.max(0, _quantity - 500) * _itemPrice * 0.05 +
    Math.min(_quantity + _itemPrice * 0.1, 100.0);
}
```

계산의 일부분을 임시변수로 바꾼다.

```java
double price() {
    // price = base price - quantity discount + shipping
  final double basePrice = _quantity * _itemPrice;
  return basePrice -
    Math.max(0, _quantity - 500) * _itemPrice * 0.05 +
    Math.min(basePrice * 0.1, 100.0);
}
```

대량 구매 할인액을 나타내는 임시변수를 사용한다.

```java
double price() {
    // price = base price - quantity discount + shipping
  final double basePrice = _quantity * _itemPrice;
  final double quantityDiscount = Math.max(0, _quantity - 500) * _itemPrice * 0.05;
  return basePrice - quantityDiscount +
    Math.min(basePrice * 0.1, 100.0);
}
```

배송료에 해당하는 임시변수를 사용한다. 이제 코드 자체가 주석의 설명과 같아지기 때문에 주석도 제거할 수 있다. 

```java
double price() {
  final double basePrice = _quantity * _itemPrice;
  final double quantityDiscount = Math.max(0, _quantity - 500) * _itemPrice * 0.05;
  final double shipping = Math.min(basePrice * 0.1, 100.0);
  return basePrice - quantityDiscount + shipping;
}
```



### 예제: 메서드 추출

Introduce Explaining Variables (직관적 임시변수 사용) 기법보다는 이 예제에는 Extract Method를 적용하는 것이 좋다.

```java
double price() {
  return basePrice() - quantityDiscount() + shipping();
}

private double quantityDiscount() {
  return Math.max(0, _quantity - 500) * _itemPrice * 0.05;
}

private double shipping() {
  return Math.min(basePrice() * 0.1, 100.0);
}

private double basePrice() {
  return _quantity * _itemPrice;
}
```

그 이유는 메서드들을 객체의 다른 부분드렝서도 사용할 수 있기 때문이다. 처음에는 전부 private으로 만들어 놓고 다른 객체에서 사용해야 할 때 public으로 수정하면 된다. 게다가 Introduce Explaining Variable 기법을 실시할 때처럼 많은 작업도 필요 없다.

따라서, Introduce Explaining Variable은 Extract Method 기법 적용이 더 어렵거나 복잡할 때 사용한다. 예를 들어 알고리즘에 **수많은 지역변수가 있을 때**는 Extract Method를 쉽게 적용할 수 없고, Introduce Explaining Variable을 사용하면 코드가 돌아가는 원리를 이해하기 쉽게 만든다. 로직의 복잡함이 덜해지면 언제든 Replace Temp with Query 기법을 적용하면 된다. 



## Split Temporary Variable

> **루프 변수나 누적용 임시 변수(collecting temporary variable)가 아닌 임시변수**에 여러 번 값이 대입될 땐 각 대입마다 다른 임시변수를 사용한다.

```java
double temp = 2 * (_height + _width);
System.out.println(temp);
temp = _height + _width;
System.out.println(temp);
```

```java
final double perimeter = 2 * (_height + _width);
System.out.println(perimeter);
final double area = _height + _width;
System.out.println(area);
```

### 동기

**여러 용도로 사용되는 변수**는 **각 용도별로 다른 변수를 사용하게 분리**해야 한다. 임시변수 하나를 두 가지 용도로 사용하면 코드를 분석하는 사람에게 **혼동**을 줄 수 있기 때문이다. 

### 방법

- 선언문과 첫 번째 대입문에 있는 임시변수 이름을 변경한다.
- 이름을 바꾼 새 임시변수를 final로 선언한다.
- 그 임시변수의 모든 참조 부분을 두 번째 대입문으로 수정한다. 
- 두 번째 대입문에 있는 임시변수를 선언한다.
- 컴파일과 테스트를 실시한다.
- 각 대입문마다 차례로 선언문에서 임시변수 이름을 변경하고, 그 다음 대입문까지 참조를 수정하여 위의 과정을 반복하자.



### 예제

```java
double getDistanceTravelled (int time) {
  double result;
  double acc = _primaryForce / _mass;
  int primaryTime = Math.min(time, _delay);
  result = 0.5 * acc
}
```



## Remove Assignment to Parameters: 매개변수로의 값 대입 제거

> 매개변수로 값을 대입하는 코드가 있을 땐 매개변수 대신 임시변수를 사용하게 수정하자.

```java
int discount (int inputVal, int quantity, int yearToDate) {
  if (inputVal > 50) inputVal -= 2;
```

```java
int discount (int inputVal, int quantity, int yearToDate) {
 	int result = inputVal;
  if (inputVal > 50) result -= 2;
```



```java
void aMethod(Object foo) {
  foo.modifyInSomeWay(); // 괜찮다.
  foo = anotherObject; // 고통과 절망을 안겨줄 것이다.
}
```

