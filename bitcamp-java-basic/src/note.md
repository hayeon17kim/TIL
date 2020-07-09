1. 리턴 타입을 무조건 지정해야 하는가? 어떤 값이 나올 지 모르니까 처음에 지정하지 않고서도 호출한 곳으로 돌려보낼 수 있는 방법은 없는가? (void 는 돌려보내지 않을 때)

- 경험을 쌓아야지 리턴 타입을 쉽게 정할 수 있음

- 엄밀히 말하면 자바스크립트에서 객체를 지정해주지 않고 파라미터를 넣는 것이 아니라 그 파라미터 또한 자바스크립트 객체임 

- car 객체: car 데이터타입

1. 이때까지 우리가 void를 쓴 이유는 프로그램을 다른 곳에서 호출한 적이 없기 때문인가?
2. 2세대 언어에서는 정보를 담는 틀과 기능을 별개로 보았기 때문에 우리가 실질적으로 재사용할 수 있는 부분은 기능 부분만 되었다고 말했는데, java에서도 학생의 정보와 기능을 여기서도 field와 method로 나누어서 보고 있는거 아닌가?
3. 예전에는 파라미터를 "매개 변수"라고 불렀다고 하셨는데, 지금은 매개변수라는 말을 사용하지 않는가? 사용하지 않는다면 왜 사용하지 않는가?



## ArrayList

자바에서는 배열을 클래스화시킨 `ArrayList`가 있어 이를 사용하면 편함

`ArrayList<Integer> list = new ArrayList<>();`

- 내부적으로 ArrayList는 클래스 객체를 다루기 때문에 해당 ArrayList가 다룰 데이터타입을 `< >` 안에 넣어야 함
- 기본형 데이터 타입을 다룰 수 없음
- 그래서 Java는 

1. `contains()` : 리스트가 해당 객체를 가지고 있는 지 확인할 때

2. `indexOf()`: 리스트에서 해당 객체의 index 번호를 찾을 때

   만약 해당 객체가 리스트에 존재하지 않을 때에는 -1이 나오게 된다.

3. `lastIndexOf()`: 가장 마지막에 있는 인덱스를 찾을 때

   `indexOf()`와 마찬가지로 존재하지 않을 시에는 -1이 나오게 된다.



매직넘버를 쓰지 마라

하드코딩을 하지 마 ! (경직된, 딱딱한) => 유지보수가 어렵다.

소프트코드를 해라 (유연한 코드)

```java
	private static final int SIZE = 6;
	private static final int MAX = 45;
	
	public static void main(String[] args) {
		Random random = new Random();
		ArrayList<Integer> list = new ArrayList<>();
		// 리스트에 값을 추가해보자
		// 단 contains가 false가 나올 때만 값 추가
		// list의 사이즈가 6보다 작을 동안 값 추가를 하면 된다.
		
		while(list.size() < SIZE) {
			int number = random.nextInt(MAX) + 1;
			if(!list.contains(number)) {
				list.add(number);
			}
		}
```

여기서 `SIZE`나 `MAX`처럼