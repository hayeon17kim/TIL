package day02;
// 사용자로부터 정수를 입력받아서
// 90~100: A
// 80~89: B
// 70~79: C
// 60~69: D
// ~59: F
// 가 출력되는 프로그램 작성

import java.util.Scanner;

public class Ex08IfElseIf {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("점수를 입력해주세요: ");
		int score = scanner.nextInt();
		
		System.out.println("데이터 검증이 되지 않은 버전");
		
		if(score >= 90) {
			System.out.println("A");
		} else if (score >= 80) {
			System.out.println("B");
		} else if (score >= 70) {
			System.out.println("C");
		} else if (score >= 60) {
			System.out.println("D");
		} else {
			System.out.println("E");
		}
		
		System.out.println("======================");
		// 위 코드는 한 가지 문제가 있다.
		// 사용자가 입력한 점수가 100점을 초과하거나 0점 미만이더라도
		// A 혹은 F를 출력한다.
		
		// 이럴 때는 2가지 방법을 통해서 해결이 가능하다.
		
		// 1. 조건식을 빡빡하게 잡는다. 
		
		System.out.println("조건식을 빡빡하게 잡은 버전");
		
		if(score >= 90 && score <= 100) {
			System.out.println("A");
		} else if (score >= 80 && score <= 89) {
			System.out.println("B");
		} else if (score >= 70 && score <= 79) {
			System.out.println("C");
		} else if (score >= 60 && score <= 69) {
			System.out.println("D");
		} else if (score >= 50 && score <= 59) {
			System.out.println("E");
		} else {
			System.out.println("잘못 입력하셨습니다.");
		}
		
		System.out.println("======================");
		
		System.out.println("입력값의 선 검증 후 올바를 때에만 코드 실행");
		// 이 코드는 중첩 if문(Nested If)으로써
		// if 조건을 만족할 때에만
		// 다시 그 안으로 들어가서
		// 다른 if 조건문을 실행하게 된다.
		// 2. 
		if (score >= 0 && score <= 100) {
			// 위의 조건식이 true가 나왔다는 것은 
			// 정수가 올바른 형태이다는 것을 보증
			// 따라서 조건식이 간단해진다.
			if(score >= 90) {
				System.out.println("A");
			} else if (score >= 80) {
				System.out.println("B");
			} else if (score >= 70) {
				System.out.println("C");
			} else if (score >= 60) {
				System.out.println("D");
			} else {
				System.out.println("E");
			}
		} else {
			System.out.println("잘못 입력하셨습니다.");
		}
		
		scanner.close();
	}
	



}
