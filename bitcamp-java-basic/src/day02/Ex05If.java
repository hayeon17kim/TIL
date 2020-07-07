package day02;

// if 조건문
// if 조건문은 괄호가 붙고
// 그 괄호 안의 결과값이 true가 나오면
// 그 아래의 { } 코드를 모두 실행한다.
// 만약 false가 나오면 { } 내의 코드들은
// 실행되지 않는다.

public class Ex05If {
	public static void main(String[] args) {
		int age = 10;
		if(age >= 18) {
			System.out.println("나이: " + age);
			System.out.println("성인입니다.");
		} 
		System.out.println("프로그램 종료");
	}
}
