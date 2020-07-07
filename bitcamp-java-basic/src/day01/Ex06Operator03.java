package day01;

// 비교연산자
// > >= < <= == !=
// 비교연산자는
// 두 공간의 내부에 저장된 값을 비교해서
// 맞으면 true, 틀리면 false가 나오게 된다.

public class Ex06Operator03 {
	public static void main(String[] args) {
		int number1 = 20;
		int number2 = 15;
		System.out.print("number1 > number2: ");
		System.out.println(number1 > number2);
		
		System.out.print("number1 >= number2: ");
		System.out.println(number1 >= number2);
		
		System.out.print("number1 < number2: ");
		System.out.println(number1 < number2);
		
		System.out.print("number1 <= number2: ");
		System.out.println(number1 <= number2);
		
		System.out.print("number1 == number2: ");
		System.out.println(number1 == number2);
		
		System.out.print("number1 != number2: ");
		System.out.println(number1 != number2);
		
		// 하지만 비교 연산자를 사용할 때는 한 가지 주의할 것이 있다.
		// 기본형 변수에서만 정확한 값이 나올 수 있다.
		// 참조형 변수에서는 틀린 값이 나올 수 있다.
		
		// String은 여러 문자를 한번에 다루는 클래스이다.
		// String 변수를 만들어서 그 안의 값을 비교해보자.
		String string1 = "abc";
		String string2 = new String("abc");
		String string3 = string1;
		
		System.out.println("string1의 현재 값: " + string1);
		System.out.println("string2의 현재 값: " + string2);
		System.out.println("string3의 현재 값: " + string3);
		
		System.out.print("string1 == string2: ");
		System.out.println(string1 == string2);
		
		System.out.print("string1 == string3: ");
		System.out.println(string1 == string3);
		
		System.out.print("string2 == string3: ");
		System.out.println(string2 == string3);
		
		// 위의 코드에서는 우리가 기대한 것과 다르게
		// false, true, false가 나오게 된다.
		// 왜 이런 일이 벌어질까?
		
		// String은 클래스이다.
		// 클래스 변수는 참조형 변수이다.
		
		// 참조형 변수가 왜 참조형이라는 이름이 붙을까?
		// 기본형 변수의 경우 해당 공간에 값이 직접 들어가 있지만 
		// 참조형 변수는 해당 공간에 값이 아닌 주소값만 들어가 있고
		// 그 주소값을 "참조"해서 다른 공간을 확인해야
		// 실제 값을 확인할 수 있다.
		
		// 하지만 비교연산자는 주소값을 타고 들어가서 실제 값을 확인하는 게 아니라
		// 주소값 자체를 비교해버린다.
		
		// 따라서 우리는 참조형 변수를 비교할 때에는 비교연산자 대신 다른 방법을 사용해야 한다.
		// 바로 해당 참조형 변수에 정의되어 있는 메소드를 호출해서 변경해야 한다.
		
		// 참조형 변수에 정의되어 있는 메소드를 호출할 때에는
		// 변수이름.메소드()로 호출하면 된다.
		
		// 비교를 위해서는 우리가 equals()라는 메소드를 호출해주면 된다.
		System.out.print("string1.equals(string2): ");
		System.out.println(string1.equals(string2));
	}
}
