package day02;
// 의도적으로 break를 생략해서
// 코드를 간단하게 만드는 예제

// 사용자로부터 월을 입력받아서
// 해당 월이 몇 일까지 있는지 보여주는 프로그램

import java.util.Scanner;
public class Ex11Switch {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("월을 입력해주세요: ");
		int month = scanner.nextInt();
		if(month >= 1 && month <= 12) {
			switch(month) {
			// 의도적으로 break를 생략해서
			// 해당 월의 마지막 날이 같은 달끼리 모아놓고
			// 제일 마지막 케이스에만 몇 일까지인지를 출력하고
			// 다른 달들은 아무 것도 적지 않는다.
			case 2:
				System.out.println("28일까지 입니다.");
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				System.out.println("30일까지입니다.");
				break;
			default:
				System.out.println("31일까지입니다.");
				break;
			}
		} else {
			System.out.println("잘못 입력하셨습니다.");
		}

		scanner.close();
	}
}
// swtich는 케이스가 많을 때 비효율적