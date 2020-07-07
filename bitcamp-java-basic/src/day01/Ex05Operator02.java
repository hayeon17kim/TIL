package day01;
// 증감연산자
// 증감연산자는 변수에 저장된 값을 정확하게 1씩만 바꾼다.
// ++는 1 증가
// --는 1 감소
// 단 증감연산자는 변수의 앞에 붙냐 뒤에 붙냐에 따라서
// 의미가 달라지게 된다!

public class Ex05Operator02 {
	public static void main(String[] args) {
		int number = 10;
		System.out.println("number의 현재값: " + number);
		// 증감연산자가 변수의 앞에 붙게 되면
		// 해당 줄에서 우선순위가 가장 높게 된다
		// 즉 제일 먼저 실행이 된다
		System.out.println("number의 현재값: " + ++number);
		// 위의 코드는 크게 3가지 단계로 나뉜다.
		// 1. ++number -> number의 현재값을 1 늘려라 -> 11이 된다.
		// 2. "number의 현재값: " + ++number
		//	-> number의 현재값: 뒤에 number의 현재값을 1 늘린 값을 붙여라
		//	-> number의 현재값: 11 
		// 3. System.out.println("number의 현재값: " + ++number);
		//	-> 괄호 안의 내용을 화면에 출력해라

		System.out.println("number의 현재값: " + number);
		
		// 하지만 ++가 변수의 뒤에 붙으면
		// 우선순위가 가장 낮아진다.
		// 즉 가장 마지막에 실행된다.
		System.out.println("number의 현재값: " + number++);
		
		// 위의 코드는
		// 1. "number의 현재값: " 뒤에 number를 붙여준다.
		// 2. 화면에 괄호 안의 내용을 출력한다.
		// 3. number의 현재값을 1 증가시킨다.
		System.out.println("number의 현재값: " + number);
		
		// ++는 1 증가
		// --는 1 감소
		// **, //, %%는 존재하지 않는다.
	}
}
