# Java Tutorial

Udemy의 자바 기초 프로그래밍 강좌 강의노트입니다.

## 1. 자바 개발환경 설치

자바 개발환경인 이클립스(Eclipse) 설치

## 2. 변수

변수의 설정 및 사용하는 방법 알아보기

변수
[variable/constant]("https://3.bp.blogspot.com/-gdUFsBu0B6M/Wn-OIn1uopI/AAAAAAAADh4/FQj6nCiov901XGWGvxMVhANrDwy9dkXIACLcBGAs/s1600/c-variables-constants.jpg")

- 변수와 상수

  - 변수(variable)
    - 변하지 않는 수
    - 프로그램이 실행되는 동안에 언제든지 저장된 값이 변경될 수 있는 공간
    - ex) 두 점 간의 거리를 의미하는 Distance
  - 상수(constant)
    - 항상 같은 수
    - 한 번 정해지면 값을 변경할 필요가 없는 데이터
    - ex) 원주율 3.141592

- 변수의 쓰임
  - 1. 가장 많이 쓰이는 int, double, String 타입의 데이터 선언 및 출력

```
  public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int intType = 100;			//int: 정수
		double doubleType = 150.5;	//double: 실수
		String stringType = "김하연";

		System.out.println(intType);        //100
		System.out.println(doubleType);     //150.5
		System.out.println(stringType);     //김하연
	}

}
```

- final 키워드 활용해 상수 정의하고 사용

```
public class Main {

	final static double PI = 3.141592;

	//final: 한번 선언하면 바뀔 수 없다는 의미: 상수
	//상수: main함수 밖에 선언

	public static void main(String[] args) {

		int r = 30;
		System.out.println(r * r * PI); //2827.4328

	}

}

```

- **오버플로**의 개념 이해

  - 모든 변수는 정해진 범위 안에서만 값을 가질 수 있음
  - ex) int
    - int의 범위: -21억 ~ +21억
    - 21억을 넘어가면 다시 -21억으로 돌아가는 **순환구조**를 가지고 있음

```
public class Main {

	final static int INT_MAX = 2147483647;

	//final: 한번 선언하면 바뀔 수 없다는 의미: 상수
	//상수: main함수 밖에 선언

	public static void main(String[] args) {

		int a = INT_MAX;
        System.out.println(a); //2147483647
		System.out.println(a + 1); //-2147483648
	}

}
```

- 사칙연산 프로그램 작성

```
public class Main {

	public static void main(String[] args) {

		int a = 1;
		int b = 2;

		System.out.println("a + b = " + (a + b));   //a + b = 3
		System.out.println("a - b = " + (a - b));   //a - b = -1
		System.out.println("a * b = " + (a * b));   //a * b = 2
		System.out.println("a / b = " + (a / b));   //a / b = 0
	}

}
```

- 변수 관련 상식

  - 자바에서는 변수 초기화를 하지 않으면 사용할 수 없음
  - 정수를 나타내는 타입만 해도 short, int, long으로 다양
  - 정수 변수 안에 실수를 넣으면 정수 부분만 변수에 저장됨(소수점 자리 없어짐)

```
int a = 0.5
System.out.println(a)   //error
```

```
int a = (int) 0.5;      //형변환
System.out.print(a)     //0
```

    - 실수 값을 반올림할 때는 변수에 0.5를 더한 후에 정수형으로 형변환
    - **반올림**한 값 = (int) (실수 + 0.5);

```
double b = 0.4;
double d = 0.6;
int a = (int) (b + 0.5);
int c = (int) (d + 0.5);
System.out.println(a); // 0
System.out.println(c); // 1
```

## 4. 자료형

[datatype]("https://static.javatpoint.com/images/java-data-types.png");
