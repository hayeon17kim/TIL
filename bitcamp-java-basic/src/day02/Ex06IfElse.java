package day02;

// if - else 구조는
// 만약 if문의 조건식이 false가 나오면
// 무조건 else {	}에 적혀 있는 코드들이 실행이 된다!

// 반대로 if문의 조건식이 true가 나오면
// else {	} 안에 있는 코드들은 무시가 된다.

public class Ex06IfElse {
	public static void main(String[] args) {
		int age = 10;
		if(age >= 18) {
			System.out.println("나이: " + age);
			System.out.println("성인입니다.");
		} else {
			System.out.println("나이: " + age);
			System.out.println("미성년자입니다.");
		}
		System.out.println("프로그램 종료");
	}
}
