package day02;
// for 반복문
// for 반복문은 int 변수 하나를 임시로 만들어서
// 그 변수가 조건식을 만족하는 동안
// 코드들을 실행하고
// 해당 변수를 1씩 증가하거나 변화시켜서
// 조건식이 만족하지 않는 경우까지
// 특정 횟수를 반복한다. 
public class Ex12ForLoop {
	public static void main(String[] args) {
		for(int i = 1; i <= 5; i++) {
			System.out.println("for 반복문");
			System.out.println("i의 현재값: " + i );
		}
		
		// 1번: int i = 1;
		// 2번: i <= 5;
		// 2-A: 2번이 true가 나오면 
		//		System.out.print("for 반복문");
		//		System.out.print("i의 현재값: " + i);
		// 2-A가 끝나면 다시 2번을 실행해서
		// 조건식이 true가 나오는 지 체크한다.
		// 2-B: 2번이 false가 나오면
		//		for loop이 종료된다.
		
		
	
	}
}
