package day02;
// if-else if 구조
// if else if 구조는
// if 조건을 체크해서 false이면
// 그 다음에 있는 else if 조건을 체크한다.
// 만약 해당 else if 조건도 false가 나오면
// 그 다음에 있는 else if의 조건을 체크한다.
// 그러다가 전체가 끝날 때까지 false이면
// else를 실행한다. 

public class Ex07IfElseIf {
	public static void main(String[] args) {
		int age = 3;
		// 이번에는 단순히 성인과 미성년자로 나누지 말고
		// 나이에 따라서 유아-어린이-청소년-성인 4단계로 나눠 보자.
		
		if(age >= 18) {
			System.out.println("성인입니다.");
		} else if(age >= 14) {
			System.out.println("청소년입니다.");
		} else if(age >= 6) {
			System.out.println("어린이입니다.");
		} else {
			System.out.println("유아입니다.");
		}
		
		System.out.println("프로그램 종료");
	}
}
