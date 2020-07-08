package day03;

import java.util.Scanner;

// 무한루프를 이용해서 메뉴를 만들어보자. 
public class Ex06ShowMenu {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("비트 고등학교 성적 관리 프로그램");
			System.out.println("1. 입력  2. 수정  3. 출력  4. 삭제  5. 종료");
			System.out.print("> ");
			int choice = scanner.nextInt();
			if (choice == 1) {
				// 학생 정보 입력하는 코드 구현
			} else if (choice == 2) {
				// 학생 정보 수정하는 코드 구현
			} else if (choice == 3) {
				// 학생 정보 출력하는 코드 구현
			} else if (choice == 4) {
				// 학생 정보 삭제하는 코드 구현
			} else if (choice == 5) {
				System.out.println("사용해주셔서 감사합니다.");
				break;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		
		scanner.close();
	}
}
