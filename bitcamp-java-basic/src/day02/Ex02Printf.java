package day02;
// printf 사용법
public class Ex02Printf {
	public static void main(String[] args) {
		int number = 47;
		// 1. 10진법 정수(decimal)
		// A. 10진법 정수를 그대로 출력하라.
		System.out.printf("1-A. %d\n", number);
		// B. 10진법 정수를 5자리로 출력하되 오른쪽 정렬해서 출력해라.
		System.out.printf("1-B. %5d\n", number);
		// C. 10진법 정수를 5자리로 출력하되 왼쪽 정렬해서 출력해라.
		System.out.printf("1-C. %-5d(끝)\n", number);
		// D. 10진법 정수를 5자리로 출력하고 오른쪽 정렬하되 왼 쪽 빈 공간은 0으로 채워라
		System.out.printf("1-D. %05d\n", number);
		
		// 2. 16진법 정수(he'x'adecimal)
		// 16진법은 오른쪽부터 16의 0승, 1승, 2승 순으로 자릿수가 정해지고
		// 한자리당 숫자가 16개(0~15까지) 들어갈 수 있는 숫자체계이다.
		// 10부터 15는 A, B, C, D, E, F로 치환된다.
		
		// A. 정수를 16진법으로 표시하고 알파벳이 있으면 소문자로 출력해라.
		System.out.printf("2-A. %x\n", number);
		// B. 정수를 16진법으로 표시하고 알파벳이 있으면 대문자로 출력해라.
		System.out.printf("2-B. %X\n", number);
		// C. 정수를 16진법으로 표시하고 알파벳이 있으면 대문자로 출력하되 
		//	    총 8자리로 맞추고 오른쪽 정렬하고 왼쪽 빈 공간은 0으로 채워라.
		System.out.printf("2-C. %08X\n", number);
		
		// 자릿수 지정과 정렬 방법 (아무 것도 안 쓰면 오른쪽, -는 왼쪽 정렬)
		// 그리고 오른쪽 정렬 시 왼쪽 빈 공간에 0을 채우는 건 모든 것이 다 같다.
		// 자릿수 지정의 경우 만약 출력해야 할 내용이 자릿수보다 많으면
		// 자릿수 지정은 무시된다!
		
		double myDouble = 3.141592;
		// 3. 실수 출력하기
		// A. 실수를 총 5자리로 출력해라. -> 무시됨
		System.out.printf("3-A. %5f\n", myDouble);
		// B. 실수를 소숫점 3번째 자리까지 출력해라.
		System.out.printf("3-B. %.3f\n", myDouble);
		// C. 실수를 총 10자리로 출력하되 소숫점은 2자리까지만 출력해라.
		System.out.printf("3-C. %10.2f\n", myDouble);
		// D. 과학표기법으로 표시해라.
		System.out.printf("3-D. %e\n", myDouble);
		
		// 4. String 출력하기
		String string1 = "abcDEF";
		// A. 스트링을 그대로 출력해라.
		System.out.printf("4-A. %s\n", string1);
		// B. 스트링을 출력하되 소문자가 있으면 대문자로 바꿔서 출력해라.
		System.out.printf("4-B. %S\n", string1);
		
		// printf의 가장 큰 장점은 여러가지 %문자를 섞어서 쓸 수 있다는 점이다.
		// 단! %문자의 갯수와 데이터타입이 일치해야 한다!
		System.out.printf("학생의 이름: %s\n" , "조재영");
		System.out.printf("국어: %03d점, 영어: %03d점, 수학: %03d점", 80, 92, 82);
	}
}
