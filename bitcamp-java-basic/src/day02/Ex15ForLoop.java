package day02;
// 사용자로부터 숫자를 하나 입력받아서
// 1부터 그 숫자까지의 합을 구해주는 프로그램

import java.util.Scanner;

public class Ex15ForLoop {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int sum = 0;
		System.out.print("숫자를 입력해주세요: ");
		int userNumber = scanner.nextInt();
		for (int i= 1; i <= userNumber; i++) {
			// sum = sum + i;
			// 위의 코드는 아래 코드로 짧게 줄여서 쓸 수 있다.
			sum += i;
			// 물론 -, *, / 모두 가능하다.
		}
		System.out.println("1부터 " + userNumber + "까지의 합: " + sum);
		scanner.close();
	}
}
