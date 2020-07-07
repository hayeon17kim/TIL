package day01;

// 상수(Constant)
// 상수란 해당 공간에 값이 한번 셋팅 되면
// 다시 못 바꾸는 공간을 상수라고 한다.
// 상수의 선언은 변수와 똑같지만
// 단지 제일 앞에 final이라는 예약어가 붙는다는 게 차이점이다.

public class Ex03Constant {
	public static void main(String[] args) {
		final int SIZE = 5;
		System.out.println("SIZE의 현재 크기: " + SIZE);
		// SIZE = 10;
		// 위의 코드는 에러가 난다.
		// 이미 SIZE에 5라는 값이 고정되었기 때문에
		// 새로운 값은 넣어줄 수 없게 된다.
	}
}
