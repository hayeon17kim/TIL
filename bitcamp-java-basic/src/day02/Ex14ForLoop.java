package day02;
//for 반복문에 대한 팁들
//1. i를 고집할 필요는 없지만
//	  전통적으로 i > j > k 순으로 들어간다.

// 2. 컨트롤하는 변수의 값을 다른 변수로 초기화하거나
//	    혹은 조건식에 다른 변수가 들어갈 수 있다.
public class Ex14ForLoop {
	public static void main(String[] args) {
		for (int number = 1; number <= 3; number ++) {
			System.out.println("number로 반복!!!");
		}
		
		int start = 30;
		int max = 33;
		for (int i = start; i <= max; i++) {
			System.out.println("변수로 초기화하고 변수로 조건식 만듦!");
		}
	}
}
