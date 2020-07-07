package day02;
// 중첩 if문을 사용해야
// 사용자의 편의성도 우리가 도와줄 수 있다.

// 이번 예제에서는
// 사용자로부터 값을 입력받아서
// 현역, 공익, 국방의무x 3가지를 출력하는 프로그램을 만들어볼 것이다.

import java.util.Scanner;
public class Ex09NestedIf {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("성별을 입력해주세요(1-> 남자   2-> 여자): ");
		int choice = scanner.nextInt();
		if (choice == 1) {
			// 남성이므로 나이를 입력받는다.
			System.out.print("나이를 입력해주세요: ");
			int age = scanner.nextInt();
			if (age > 18) {
				//남성+성인이므로 신체 등급을 입력 받는다.
				System.out.print("신체 등급을 입력해주세요: ");
				int category = scanner.nextInt();
				if (category <= 3 && category >=1) {
					System.out.println("현역 입니다.");
				} else if (category == 4){
					System.out.println("공익 입니다.");
				} else {
					System.out.println("면제입니다.");
				}
			} else {
				System.out.println("미성년자이므로 아직 국방의 의무가 없습니다.");
			}
		} else {
			if (choice == 2) {
				System.out.println("여성이므로 국방의 의무가 없습니다.");
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		System.out.println("프로그램이 종료됩니다.");
		
		scanner.close();
	}
}
