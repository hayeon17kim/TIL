package day03;
// 사용자로붵 점수를 입력받되
// 올바르지 않은 점수가 들어올 경우
// 올바른 점수를 입력해 달라고 반복하는 프로그램
import java.util.Scanner;
public class Ex04While {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("점수를 입력하세요: ");
		int score = scanner.nextInt();
		
		while(score < 0 || score > 100) {
			System.out.println("잘못 입력하셨습니다.");
			System.out.print("점수를 입력하세요: ");
			score = scanner.nextInt();
		}
		
		System.out.println("사용자가 입력한 점수: " + score);
		
		scanner.close();
	}
}
