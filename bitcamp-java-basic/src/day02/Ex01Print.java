package day02;
// 자바 콘솔에서 글자나 문구를 출력하는 방법은 크게 3가지가 있다.
// 1. print()
//	  print()는 괄호 안의 내용을 출력하고 다음 출력 위치를 바꾸지 않는다.
// 2. println()
// 	  println() print a line의 줄임말로써, 괄호 안의 내용을 출력하고
//	   다음 출력 위치를 다음 줄로 바꾼다.
// 3. printf()
//	  printf()는 print in format의 줄임말로써, 괄호 안의 내용을 출력하되
//	  % 문자를 이용해서 형식을 지정해줄 수 있다. 단 출력 위치는 바뀌지 않는다.

// 4. 공백문자
// \n, \t 등의 특수한 문자를 이용해서
// 공백을 강제로 넣어줄 수 있다.
// \n은 강제로 줄을 바꿔준다.
// \t는 탭 공백(스페이스 4개 분량)을 넣어준다.
public class Ex01Print {
	public static void main(String[] args) {
		
		// print
		System.out.print("A 안킬로사우르스\t");
		System.out.print("B 브라키오사우르스\t");
		System.out.print("C 콤프소그루나투스");
		
		// println
		System.out.println("D 데이노니쿠스");
		System.out.println("E 엘라스모사우르스");
		System.out.println("F 파르보사우르스");
		
		// printf
		// 얼핏 보면 print와 비슷하지만 내부의 내용을 형식을 지정해줄 수 있다
		System.out.printf("G %s\n", "갈리미무스");
		System.out.printf("H %20s\n", "하드로사우르스");
		System.out.printf("%S 이구아노돈", "i");
	}
}
