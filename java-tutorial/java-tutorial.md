# Java Tutorial

Udemy의 자바 기초 프로그래밍 강좌 강의노트입니다.

## 1. 자바 개발환경 설치

이클립스는 JDK로서 대표적인 개발 환경. 이클립스를 이용하여 JSP, Java Swing부터 시작해 다양한 자바 개발을 시작할 수 있음 

## 2. 변수

변수의 설정 및 사용하는 방법 알아보기

변수
[variable/constant]("https://3.bp.blogspot.com/-gdUFsBu0B6M/Wn-OIn1uopI/AAAAAAAADh4/FQj6nCiov901XGWGvxMVhANrDwy9dkXIACLcBGAs/s1600/c-variables-constants.jpg")

- 변수와 상수

  - 변수(variable)
    - 변하지 않는 수
    - **프로그램이 실행되는 동안에 언제든지 저장된 값이 변경될 수 있는 공간**
    - ex) 두 점 간의 거리를 의미하는 Distance
  - 상수(constant)
    - 항상 같은 수
    - **한 번 정해지면 프로그램이 종료될 때까지 변경되지 않는 데이터**
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

## 3. 자료형

[datatype]("https://static.javatpoint.com/images/java-data-types.png");

- string: char(racter)를 엮어서 만든 개념 => 비 원시자료
- 일반적인 프로그래밍 언어에는 정수 자료형 `int`, 실수 자료형 `float`과 `double`, 문자 자료형 `char`, 문자열 자료형 `String`이 사용됨. 자바에서는 특히 `String`을 많이 활용하게 됨
- 자바에서는 다른 프로그래밍 언어에서 사용하는 것과 거의 동일한 자료형이 존재. - C언어와의 차이점: boolean형, String형 존재하는 정도
- 가장 많이 사용되는 자료형: String, Array, boolean, char, int, double
- 자료형 자체는 굳이 암기할 필요 없으며 프로그래밍을 통해 많은 훈련을 거치게 되면 알아서 잘 활용할 수 있게 됨
- String 형이 편리하게 사용되며, String 내부적으로 `substring` 등의 함수 제공하여 활용도 높음
- 정수를 나타내는 자료형이 많은 이유: 각 **자료형이 차지하는 메모리 공간의 크기가 다르기 때문**
- double 형이라고 하더라도 과도하게 큰 수를 저장하고자 하면 잘못된 계산 결과가 나올 수 있음
- 소수점 표기 형식을 **지수** 형식으로 출력하고 싶으면 `%e` 이용
- 자바에서 String은 내부적으로 char의 배열로 되어 있음 
  - String의 최대 크기는?: 4GB => 거의 무한에 가까움
- 자바의 String은 `클래스 기반의 비원시적인 자료형`: 
  - 함수 등 다양한 기능 제공

### 실습

- `double` 이용하여 평균 구하기

```
		double a = 10.3;
		double b = 9.6;
		double c = 10.1;

		System.out.println((a + b + c) / 3);
```

- 아스키코드 기반의 char형을 사용해 a부터 z까지 출력하는 프로그램 작성
  - char 형은 내부적으로 a부터 z까지 아스키코드를 사용해서 1씩 증가하는 방식으로 알파벳이 이루어짐

```
		for(char i = 'a'; i <= 'z'; i++) {
			System.out.print(i + " ");
			// a b c d e f g h i j k l m n o p q r s t u v w x y z
		}
```

- 10진수를 8진수 또는 16진수로 바꾸는 프로그램 작성

```
		int a = 200;

		System.out.println("10진수: " + a);		//200
		System.out.format("8진수: %o\n", a);	//310
		System.out.format("16진수: %x", a); 	//c8
```

  - `\n`: 한 줄 띄우기 (`println`이 아니라 `format` 메소드 사용하여 문단 바꾸기가 안 되었음)

- String의 `substring` 함수를 활용

```
		String name = "John Doe";
		System.out.println(name);					//John Doe
		System.out.println(name.substring(2, 6));	//hn D
		System.out.println(name.substring(5, 8));	//Doe
```


## 4. 연산자(Operator)

| Operator | Use                                       | Example | Result |
| :------- | :---------------------------------------- | :------ | :----- |
| +        | To add two numbers                        | i=3+2   | 5      |
| -        | For subtraction                           | i=3+2   | 1      |
| *        | For multiplication                        | i=3\*2  | 6      |
| /        | For division                              | i=3/2   | 1      |
| %        | Modular division(Reminder after division) | i=10%3  | 1      |

- 하나의 기호 체계.
- `1+2`에서 1은 피연산자(operand), +는 연산자(operator)
- `++`나 `!` 등 다른 연산자도 있음
- 특히 `&&`와 `||`는 AND, OR 연산으로서 `조건문`과 `반복문`에서도 많이 사용됨

### 실습
- 초를 입력 받아 몇 분 몇 초인지 계산하는 프로그램 작성
```
	final static int SECOND = 1000;
	
	public static void main(String[] args) {
		
		int minute = SECOND / 60;
		int second = SECOND % 60;
		
		System.out.println(minute + "분 " + second + "초");
	}
```
- `++`와 `--` 연산의 개념을 이해하고 프로그램 작성
```
		int a = 10;
		System.out.println("현재의 a는 " + a + "입니다.");		//10
		a++;
		System.out.println("현재의 a는 " + a + "입니다.");		//11
		System.out.println("현재의 a는 " + ++a + "입니다.");	//12
		System.out.println("현재의 a는 " + a++ + "입니다.");	//12
		System.out.println("현재의 a는 " + a + "입니다.");		//13
```
  - `++`가 앞에 올 때: 결과 출력 전 바뀜 
  - `++`가 뒤에 올 때: 결과 출력 후 바뀜

- `%` 연산자의 사용법을 숙지하고 프로그램 작성
```
		System.out.println(1 % 3);		//1
		System.out.println(2 % 3);		//2
		System.out.println(3 % 3);		//0
		System.out.println(4 % 3);		//1
		System.out.println(5 % 3);		//2
		System.out.println(6 % 3);		//0
```
- `==`, `>`, `<`, `&&`, `||`, `!` 연산의 개념을 바르게 이해하고 프로그램 작성
```
		int a = 50;
		int b = 50;
		
		System.out.println("a와 b가 같은가요? " + (a == b));		//true
		System.out.println("a가 b보다 큰가요? " + (a > b));			//false
		System.out.println("a가 b보다 작은가요? " + (a < b));		//false
		System.out.println("a가 b와 같으면서 a가 30보다 큰가요? " + ((a == b) && (a > 30)));	//true
		System.out.println("a가 50이 아닌가요? " + !(a == 50));		//false
```
- 삼항연산자: `조건 ? 참 : 거짓` 연산의 형태를 숙지하고 프로그램 작성
```
	public static void main(String[] args) {
		
		int x = 50;
		int y = 60;
		
		System.out.println("최댓값은 " + max(x, y) +"입니다.");
		// 최댓값은 60입니다.
	}
	
	// 반환형, 함수 이름, 매개 변수
	static int max(int a, int b) {
		int result = (a > b) ? a : b;
		return result;
	}
```
- `pox()`를 이용한 거듭제곱 연산 프로그램을 작성
```
		double a = Math.pow(3.0, 20.0);
		System.out.println("3의 20제곱은 " + (int) a + "입니다.");
		// 3의 20제곱은 2147483647입니다.
```
	- (Q) 왜 double로 선언하고 후에 int로 형변환할까?
    	- int로 선언할 경우, pow 메소드를 사용할 수 없음
    	- double에서 int로 형변환 안 할 때, 3의 20제곱은 3.486784401E9입니다.


[datatypes](http://2.bp.blogspot.com/-IrM2CbJ071s/VP_QXfRzxlI/AAAAAAAAAFI/y8S0-W7fmEo/s1600/Datatypes.JPG)
출처: http://ocjplearners.blogspot.com/

- 헷갈리는 연산자
  - i++와 ++i는 단순히 값을 증가시키려는 목적이라면 그 기능이 동일
    - but, 출력하는 문자 안에 들어가 있다면 다름 (i++: 출력이후 증가, i--: 출력 이전 증가)
  - 100 < x < 200은 잘못된 표현: (100 < x) && (x <200)로 표현해야 함
  - 1만큼 증가시킨다: 동일한 표현들
    - `i++`
    - `i += 1`
    - `i = i + 1`
      - 우항을 좌항에 넣는다
```
		int i = 20;
		i = i + 1;					//(i + 1)을 i에 대입
		System.out.println(i);		//21
```

## 5. 조건문과 반복문
(conditional statement)[https://beginnersbook.com/wp-content/uploads/2017/08/if_statement_flow_diagram.jpg]

- 조건문과 반복문은 프로그래밍에 있어서 **논리의 흐름**을 정하는 가장 기본적인 기술
- 여러 개의 데이터를 서로 비교하거나 1부터 100까지 반복하는 등 다양한 논리적 흐름에 따라 프로그램을 전개할 수 있게 해줌
- 알고리즘 그 자체. 프로그래밍에서 중요한 부분
- 조건문: 조건에 따라 결정을 내리는 것
  - ex) 학점을 결정할 때 90점 이상이면 A+
- 반복문: 반복적으로 같은 처리를 되풀이하는 것
  - 100명의 학생이 있을 때, 학생 각각의 학점 출력


### 실습
- if문을 이용하여 문자열이 특정 문자열을 포함하는지 확인하는 프로그램 작성
```
		String a = "I Love You";
		if(a.contains("Love")) {
			System.out.println("Me too.");		//Me too.
		} else {
			System.out.println("I hate you.");
		}
```

- 점수에 따라서 다른 메시지를 출력하는 프로그램 작성
```
		int score = 85;
		
		if(score >= 90) {
			System.out.println("A+입니다.");
		}
		else if(score >= 80) {
			System.out.println("B+입니다.");
		}
		else if(score >= 70) {
			System.out.println("C+입니다.");
		}
		else {
			System.out.println("F입니다.");
		}

// B+입니다.
```
- 문자열과 정수형 각각 조건문 이용해 활용하고 차이점 이해
```
		String a = "Man";
		int b = 0;
		
		// 자바는 String을 비교할 때 equal()을 이용
		// 그 이유는 String은 다른 자료형과 다른 문자열 자료형이기 때문입니다.(비원시적)
		
		if(a.equals("Man")) {
			System.out.println("남자입니다.");
		} else {
			System.out.println("남자가 아닙니다.");
		}
		
		if(b == 3){
			System.out.println("b는 3입니다.");
		} else {
			System.out.println("b는 3이 아닙니다");
		}
		
		if(a.equalsIgnoreCase("man") && b == 0) {
			System.out.println("참입니다.");
		} else {
			System.out.println("거짓입니다.");
		}
/*
남자입니다.
b는 3이 아닙니다
참입니다.
*/
```
  - String 비교
    -  `equals()`
    -  `equalsIgnoreCase()`: 대소문자 무시
- `while`을 이용하여 1부터 1000까지의 합을 출력하는 프로그램 작성
```
		int i = 1, sum = 0;
		while(i <= 1000) {
			sum += i++;
		}
		System.out.println("1부터 1000까지의 합은 " + sum + "입니다.");
// 1부터 1000까지의 합은 500500입니다.
```
- for문을 이용하여 삼각형을 출력하는 프로그램 작성 (이중for문, 중첩for문)
```
	final static int N = 30;
	
	public static void main(String[] args) {
		
		// for문: 초기화 부분, 조건 부분, 연산 부분
		for(int i = N; i > 0; i--)
		{
			for(int j = i; j > 0; j--)
			{
				System.out.print("*");
			}
			System.out.println();
		}
		
	}

*/

******************************
*****************************
****************************
(...)
***
**
*

*/
```
- for문을 이용하여 원을 출력하는 프로그램 작성

```
public class Main {
	
	final static int N = 15;
	
	public static void main(String[] args) {
		
		// x^2 + y^2 = r^2: 원점을 중심으로 하는 원의 방정식
		for(int i = -N; i <= N; i++)
		{
			for(int j = -N; j<=N; j++)
			{
				if(i * i + j * j <= N * N)
				{
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
	}
}

```

결과
```
               *               
          ***********          
        ***************        
      *******************      
     *********************     
    ***********************    
   *************************   
   *************************   
  ***************************  
  ***************************  
 ***************************** 
 ***************************** 
 ***************************** 
 ***************************** 
 ***************************** 
*******************************
 ***************************** 
 ***************************** 
 ***************************** 
 ***************************** 
 ***************************** 
  ***************************  
  ***************************  
   *************************   
   *************************   
    ***********************    
     *********************     
      *******************      
        ***************        
          ***********          
               * 
```

- 무한루프 for(;;)와 break
```
		int count = 0;
		
		for(;;)
		{
			System.out.println("출력");
			count++;
			if(count == 10)
			{
				break;		//없다면 무한정 출력
			}
		}
```
결과: "출력" 10번

### 주의
- 하나의 비교 연산자는 true 혹은 false를 반환
- 모든 조건문, 반복문에서 웬만하면 무조건 괄호 적용
- for문 혹은 while문은 얼마든지 중첩될 수 있음
- for(;;)는 while(true)와 똑같이 무한 루프라는 의미로 동작
- break;를 사용하여 반복문을 즉시 빠져나올 수 있음


```
		int count = 0;
		
		for(;;)
		{
			System.out.println("출력");
			count++;
			if(count == 10)
			{
				break;
			}
		}
```

## 6. 기본 입출력(input/output)
사용자와의 상호작용
- Java에서는 기본적으로 `Scanner` 클래스 이용하여 사용자와 **상호작용**할 수 있음
- `Scanner sc = new Scarnner(System.in);`으로 클래스 객체를 생성한 뒤에 `sc.nextint()`와 같은 방법으로 int형을 입력 받을 수 있음. 
- 입력 받은 자료는 내부적으로 어떠한 처리를 한 뒤에 다시 사용자에게 그 값을 반환할 수 있음
- 프로그램이 입출력을 잘 지원한다는 것은 사용자 인터페이스가 뛰어나다는 의미
- 나중에 `GUI`를 배우게 되면 이러한 입출력을 훨씬 용이하게 할 수 있음

콘솔창을 통해 입력 받음
```
		Scanner sc = new Scanner(System.in); // System.in: 콘솔창을 통해 입력한 데이터값
		System.out.print("정수를 입력하세요 : ");
		int i = sc.nextInt(); 
		System.out.println("입력된 점수는 : " + i + "입니다.");
		sc.close();
```

파일을 통해 입력 받음
```
File file = new File("input.txt");
		try {
			Scanner sc = new Scanner(file); // file을 매개변수
			while(sc.hasNextInt()) {
				System.out.println(sc.nextInt() * 100);
			}
			sc.close();
		} catch (FileNotFoundException e) { // 예외처리 구문
			System.out.println("파일을 읽어오는 도중에 오류가 발생했습니다.");
		}
```

- 자바에서는 `Scanner` 클래스만 잘 활용해도 다양한 입출력 형태를 자유자재로 구사할 수 있음
- `Scanner` 클래스는 정수를 입력받는 `nextInt()`, 문자열을 입력 받는 `next()` 등 다양한 함수 지원
- 주석은 일단 최대한 많이 작성하는 습관을 들여야 함. **주석은 컴파일 단계에서 제거**되기에 프로그램의 크기와는 상관이 없음 
- `Scanner`로 문자열을 입력 받고 싶을 때는 `next()` 함수와 `nextLine()`을 적절히 활용할 수 있음


## 7. 사용자 정의 함수

자바 객체지향 프로그래밍
- 객체 지향 프로그래밍에 익숙할 때 손쉽게 다양한 프로그램을 개발할 수 있고, 나아가 다양한 동적 웹 애플리케이션을 구현할 때에도 JSP와 같은 자바 기반의 프로그래밍을 할 수 있음. 
- 객체 지향 프로그래밍의 장점을 살린 `MVC 모델`을 이용한 구축 
- **현실 세계**의 다양한 사물을 컴퓨터 내부의 언어로 표현하고 그것을 활용할 수 있음
- 객체지향: 객체는 일반적으로 말하는 물건(사물)을 의미하며 여기서 물건은 단순한 데이터가 아니고 그 데이터의 조작 방법에 대한 정보 또한 포함하고 있어 그것을 대상으로 다루는 수법 자체를 객체지향이라고 할 수 있음
- 사물이 있으면 그 사물의 특징을 살려서 모델링을 할 수 있게 해주고
- 객체지향 프로그래밍을 하고 있으면 현실세계의 사물을 다루고 있는 것처럼 느끼게 됨

사용자 정의 함수
- 사용자 정의 함수는 정해진 특정한 기능을 수행하는 모듈을 의미
- 함수를 적절히 활용하면 하나의 문제를 잘게 분해할 수 있음
  - ex) 이진 탐색 틀는 삽입, 삭제, 순회 등 다양한 함수의 집합으로 구성됨. 만약 사용자 정의 함수가 없다면 오직 메인 함수에서 모든 알고리즘을 처리해야 하는데 이는 작업의 효율성을 저하시킬 수 있음. 또한 함수는 각각의 모듈로서 쉽게 수정되고 보완될 수 있다는 장점 


### 실습
- 3개 수의 최대공약수 구하기
```
public class Main {
	
	// 반환형, 함수명, 매개변수
	public static int function(int a, int b, int c) {
		int min;
		if(a > b) {
			if (b > c) {
				min = c;
			} else {
				min = b;
			}
		} else {
			if(a > c) {
				min = c;
			} else {
				min = a;
			}
		}
		
		for(int i = min; i > 0; i--) {
			if(a % i == 0 && b % i == 0 && c % i == 0) {
				return i;
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("(400, 300, 750)의 최대공약수: " + function(400, 300, 750));		// (400, 300, 750)의 최대공약수: 50
		
	}

}
```

- 약수 중 K번째로 작은 수를 찾는 프로그램
```
public class Main {
	
	public static int function(int number, int k) {
		for(int i = 1; i <= number; i++) {
			if(number % i == 0) {
				k--;
				if(k == 0) {
					return i;
				}
			}
		}
		return -1;
	}

	
// main 메소드도 일종의 함수
// void: main 메소드가 종료되면 프로그램이 종료되는 것과 같기 때문에 반환형이 없다는 뜻
// String[] args: 처음에 콘솔창에서 프로그램을 실행할 때 inject 값으로 넣는 건데, 거의 사용 안하기 때문에 신경쓰지 말기
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result = function(3050, 10);
		if(result == -1) {
			System.out.println("3050의 10번째 약수는 없습니다.");
		} else {
			System.out.println("3050의 10번째 약수는 " + result + "입니다.");
			//3050의 10번째 약수는 610입니다.
		}
	}

}
```

- 문자열에서 마지막 단어를 반환하는 함수

```
public class Main {
	
	public static char function(String input) {
		// charAt(): ~번째 문자열을 뽑는 함수
		return input.charAt(input.length() - 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World의 마지막 단어는 " + function("Hello World") + "입니다.");
		// Hello World의 마지막 단어는 d입니다.
	}
}
```

- `max()`를 이용하여 최대값 저장하는 프로그램
  - 모듈화
```
public class Main {
	
	public static int max(int a, int b) {
		return (a > b) ? a : b;
	}
	
	public static int function(int a, int b, int c) {
		int result = max(a, b);
		result = max(result, c);
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("(345, 450, 602) 중에서 가장 큰 값은 " + function(345, 450, 602) + "입니다.");
		//(345, 450, 602) 중에서 가장 큰 값은 602입니다.
	}
}

```


## 8. 반복 함수와 재귀 함수
- 반복 함수: 단순히 `while` 혹은 `for`문법을 이용하여 특정한 처리를 반복하는 방식으로 문제를 해결하는 함수
- 재귀 함수: 자신의 함수 내부에서 자기 자신을 스스로 호출함으로써 재귀적으로 문제를 해결하는 함수
  - 경우에 따라서는 아주 **간결하고 직관**적인 코드로 문제를 해결할 수 있게 해 주지만 때에 따라서는 심각한 **비효율성**을 낳을 수 있기 때문에 알고리즘을 작성할 때에 유의
  - 동적 프로그램, 다양한 정렬, 그래프 탐색 알고리즘 작성할 때 유용

### 실습

1. 팩토리얼을 반복함수로 구현
```
	public static int factorial (int number) {
		int sum = 1;
		for (int i = 2; i <= number; i++) {
			sum *= i;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("10 팩토리얼은 " + factorial(10) + "입니다.");
		// 10 팩토리얼은 3628800입니다.
	}
```

2. 팩토리얼을 재귀함수로 구현
```
	public static int factorial (int number) {
		if(number == 1)
			return 1;
		else
			return number * factorial(number - 1);
		// 5! = 5 * 4!
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("10 팩토리얼은 " + factorial(10) + "입니다.");
		// 10 팩토리얼은 3628800입니다.
	}
```
![재귀함수_팩토리얼](https://user-images.githubusercontent.com/50407047/86528453-c5e18900-bee2-11ea-9b8e-74538748cf36.png)

재귀함수의 특징: 함수를 타고 들어가서 가장 작은 단위의 함수로 들어간 후, 작은 단위가 해결이 되면 다시 값을 거슬러 올라가면서 반환하여 답 도출


3. 피보나치수열을 반복함수로 구현 
```
	public static int fibonacci (int number) {
		int one = 1;
		int two = 2;
		int result = -1;
		
		if (number == 1) {
			result = one;
		} else if (number == 2) {
			result = two;
		} else {
			for(int i = 3; i < number; i++) {
				result = one + two;
				one = two;
				two = result;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("10번째 피보나치 숫자는 " + fibonacci(10) + "입니다.");
		// 10번째 피보나치 숫자는 55입니다.
	}
```


4. 피보나치수열을 재귀함수로 구현 

![재귀함수_피보나치1](https://user-images.githubusercontent.com/50407047/86528997-01cb1d00-bee8-11ea-9814-b2f348e50608.png)

```
	public static int fibonacci (int number) {
		if(number == 1) {
			return 1;
		} else if(number == 2) {
			return 1;
		} else {
			return fibonacci(number -1) + fibonacci(number - 2);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("10번째 피보나치 숫자는 " + fibonacci(10) + "입니다.");
	}
```
`fibonacci(50)`을 구한다면?
![재귀함수_피보나치2](https://user-images.githubusercontent.com/50407047/86528999-042d7700-bee8-11ea-8b07-f7355e4629e4.png)
- 특정한 연산이 계속 반복됨: 숫자가 클 수록 많음
- 기하급수적으로 증가 => 한 층 증가할 때마다 2배씩 증가 => 2^50 정도 연산



## 9. 배열
- 배열은 데이터가 많을 때 사용
- 가장 간단한 프로그램 예제에서는 단순히 한두 개의 변수만으로 프로그램을 작동시킬 수 있었지만 현실에서의 다양한 프로그램에는 아주 많은 양의 데이터가 사용되는 것이 일반적임. 따라서 데이터가 많을 때 주로 배열을 이용
- 이 때 배열은 한없이 많을 수 있으면서도 그 데이터 개수가 변경될 수 있는 데이터들의 집합을 지정해줄 수 있기에 효과적으로 대부분의 프로그램에 사용됨