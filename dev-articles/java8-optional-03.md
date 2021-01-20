# 자바8 Optional 3부: Optional을 Optional 답게

## Stream 처럼 사용하기

Optional을 제대로 사용하려면 Optional을 **최대 1개의 원소를 가지고 있는 특별한 Stream**이라고 생각하면 좋다. 두 클래스 간 직접적인 구현이나 상속 관계는 없지만 사용 방법이나 기본 사상이 유사하기 때문이다. Stream 클래스의 `map()`이나 `flatMap()`, `filter()`와 같은 메서드를 Optional도 가지고 있다.

### `map()`으로 변신하기

```java
public String getCityOfMemberFrom(Order order) {
  return Optional.ofNulluble(order)
    .map(Order::getMember)
    .map(Member::getAddress)
    .map(Address::getCity)
    .orElse("Seoul");
}
```

- `orNullable()` 정적 팩토리 메서드를 호출하여 `Order` 객체를 `Optional`로 감싸주었다. 혹시 `Order` 객체가 `null`인 경우를 대비하여 `of()` 대신에 `ofNullable()`을 사용한다.
- **`map()` 메서드의 연쇄 호출**을 통해 **`Optional` 객체를 3번 변환**했다. **매번 다른 메서드 레퍼런스를 인자로 넘**겨 **`Optional`에 담긴 객체의 타입을 바꿔준다.**
- 마무리 작업으로 **`orElse()` 메서드를 호출**하여 전 과정을 통해 얻은 Optional이 **비어있을 경우, 디폴트로 사용할 도시 이름을 셋팅**해주고 있다, 

> `public <U> Optional<U> map(Function<? super T, ? extends U) mapper`: 값이 존재한다면 파라미터의 매핑 함수를 적용한다. 결과값이 null이 아니라면 함수를 적용한 `Optional` 객체를 리턴한다. null이라면 빈 `Optional` 객체를 리턴한다.

### `filter()`로 레벨업

NPE 방지를 위해 null체크로 시작하는 if 조건문 패턴: 주어진 시간 내에 생성된 주문을 한 경우에만 해당 회원 정보를 구하는 메서드

```java
public Member getMemberIfOrderWithin(Order order, int min) {
  if (order != null && order.getDate.getTime() > System.currentTimeMillis() - min * 1000) {
	return order.getMember();
  }
}
```

`filter()`를 사용하면 메서드 연쇄 호출만으로도 가독성 있는 코드를 작성할 수 있따. 뿐만 아니라, 메서드 리턴 타입을 `Optional`로 사용함으로써 호출자에게 해당 메서드가 `null`을 담고 있는 `Optional`을 반환할 수도 있다는 것을 명시적으로 알려주고 있다.

```java
public Optional<Member> getMemberIfOrderWithin(Order order, int min) {
  return Optinal.ofNullable(order)
    .filter(o -> o.getDate().getTime() > System.currentTimeMillis - min * 1000)
    .map(Order::getMember);
}
```

`filter()` 메서드는 넘어온 함수형 인자의 리턴값이 `false`인 경우 `Optional`을 비워버리므로 그 이후 메서드 호출은 의미가 없다. 

## Java8 이후 개발된 코드를 Optional하게 바꾸기

`Optional` 클래스를 사용하여 기존 코드가 `null-safe`하게 바꿔준다.

**null 반환**

`Map` 인터페이스의 `get()` 메서드는 주어진 인덱스에 해당하는 값이 없으면 `null`을 반환한다. 따라서 해당 API를 사용하는 코드를 `null-safe`하게 만들기 위해서 `null` 체크를 해줘야 한다.

```java
String city = cities.get(4); // returns null
int length = city == null ? 0 : city.length(); // null check
System.out.println(length);
```

`get` 메서드의 반환 값을 `Optional`로 감싸면, 자연스럽게 `null-safe`한 코드가 된다.

```java
Optional<String> mabyeCity = Optional.ofNullable(cities.get(4)); // Optional
int length = maybeCity.map(String::length).orElse(0); // null-safe
System.out.println("length: " + length);
```

**예외 발생**

null을 반환하지 않고 예외를 던져버리는 경우. `List` 인터페이스의 `get()` 메서드는 주어진 인덱스에 해당하는 값이 없으면 `ArrayIndexOutOfBoundsException`을 던진다. 따라서 try-catch 구문을 사용하여 예외 처리를 해줘야 하며, 예외 처리 후에도 null check를 해줘야 한다.

```java
String city = null;
try {
	city = cities.get(3); // throws exception
} catch (ArrayIndexOutOfBoundsException e) {
	// ignore
}
int length = city == null ? 0 : city.length(); // null check
System.out.println(length);
```

이런 경우, **예외 처리부를 감싸서 정적 유틸리티 메서드로 분리해주면 좋다.** `Optional` 클래스의 정적 팩토리 메서드를 사용하여 정상 처리 시와 예외 처리 시에 반환할 `Optional` 객체를 각각 지정해주었다. 이 경우 `Optional`에 담을 객체가 `null`인지 아닌지 확실히 알 수 있기 때문에 `Optional.ofNullable()` 대신에 다른 2개의 정적 팩토리 메서드를 쓸 수 있다.

```java
public static <T> Optional<T> getAsOptional(List<T> list, int index) {
	try {
		return Optional.of(list.get(index));
	} catch (ArrayIndexOutOfBoundsException e) {
		return Optional.empty();
	}
}
```

이 정적 유틸리티 메서드를 통해 `Optional` 객체 확보 후에 `null-safe`하게 코딩할 수 있다.

```java
Optional<String> maybeCity = getAsOptional(cities, 3); // Optional
int length = maybeCity.map(String::length).orElse(0); // null-safe
System.out.println("length: " + length);
```

### `ifPresent()` 메서드

`ifPresent(Consumer<? super T> consumer)`: 특정 결과를 반환하는 대신 `Optional` 객체가 감싸고 있는 값이 존재할 경우에만 실행될 로직을 함수형 인자로 넘길 수 있다. 함수형 인자로 람다식이나 메서드 레퍼런스가 넘어올 수 있다. 마치 비동기 메서드의 콜백 함수처럼 작동한다.

```java
Optional<String> maybeCity = getAsOptional(cities, 3); // Optional
maybeCity.ifPresent(city -> {
	System.out.println("length: " + city.length());
});
```