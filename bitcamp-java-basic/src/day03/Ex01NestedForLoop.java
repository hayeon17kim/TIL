package day03;

// 중첩 for문
// 중첩 for문 같은 경우에는
// 바깥쪽 for문이 한 번 돌 때
// 안쪽 for문은 처음부터 끝까지 다 돈다.

public class Ex01NestedForLoop {
	public static void main(String[] args) {
		// 다중 for문의 특징은
		// 굳이 안쪽 for문과 바깥쪽 for문의
		// 반복 횟수가 같을 필요가 없다는 것이다.
		for(int i = 1; i <= 4; i++) {
			for(int j = 10; j <= 15; j++) {
				System.out.printf("i현재값: %d, j의 현재값: %d\n", i, j);
			}
			System.out.println("j for문 종료");
		}
		System.out.println("i for문 종료");
	}
}
