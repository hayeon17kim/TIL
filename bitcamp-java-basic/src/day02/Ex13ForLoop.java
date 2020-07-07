package day02;
//1부터 100까지의 홀수를 출력하는 프로그램
public class Ex13ForLoop {
	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			if(i % 2 == 1) {
				System.out.println(i + "는 홀수입니다.");
			} 
		}
	}
}
