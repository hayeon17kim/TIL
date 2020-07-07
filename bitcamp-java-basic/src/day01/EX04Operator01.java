package day01;
// 연산자
// 연산자란 기호에 프로그램적 코드가 정의되어 있어서
// 해당 연산자를 적어줄 경우
// 그 코드가 내부적으로 실행되는 것을 연산자라고 한다.
// 연산자는
// 산술, 증감, 비교, 논리, 비트 5가지 연산자가 존재한다.

// 산술연산자란
// 더하기, 빼기, 곱하기, 나누기, 나머지 5가지 연산자가 있다.
// +, -, *, /, %
public class EX04Operator01 {
	public static void main (String[] args) {
		int number1 = 5;
		int number2 = 3;
		
		System.out.println("number1 + number2: ");
		System.out.println(number1 + number2);
		
		System.out.println("number1 - number2: ");
		System.out.println(number1 - number2);
		
		System.out.println("number1 * number2: ");
		System.out.println(number1 * number2);
		
		System.out.println("number1 / number2: ");
		System.out.println(number1 / number2);
		
		System.out.println("number1 % number2: ");
		System.out.println(number1 % number2);
		
		// 서로 다른 데이터타입을 산술연산하면
		// 자동으로 더 작은 데이터타입을 더 큰 데이터타입으로 암시적형변환한다.
		// 정수와 실수를 산술연산하면 실수가 된다.
		
		double myDouble = 2.0;
		
		System.out.println("number1 + myDouble: ");
		System.out.println(number1 + myDouble);
		
		System.out.println("number1 - myDouble: ");
		System.out.println(number1 - myDouble);
		
		System.out.println("number1 * myDouble: ");
		System.out.println(number1 * myDouble);
		
		System.out.println("number1 / myDouble: ");
		System.out.println(number1 / myDouble);
		
		System.out.println("number1 % myDouble: ");
		System.out.println(number1 % myDouble);
	}
}
