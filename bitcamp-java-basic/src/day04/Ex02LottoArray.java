package day04;

import java.util.Random;

// 배열을 이용한 로또 번호 생성기
// 부제: 배열을 안 쓰는 이유
public class Ex02LottoArray {
	private static final int SIZE = 6;
	private static final int MAX = 45;
	public static void main(String[] args) {
		// 로또 번호를 위한 int 배열을 만든다.
		int[] array = new int[SIZE];
		
		// 이클립스에서 자동완성은
		// ctrl 과 space를 동시에 누르면 된다.
		// 랜덤 + 자동완성 해서 java.util.Random을 import 하자
		Random random = new Random();
		// random은 난수를 만드는 데 쓰이는 Random 클래스의 객체이다.
		
		// for문을 이용해서
		// array에 숫자를 넣어보자
		for(int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(MAX) + 1;
			// random의 nextInt 메소드는
			// 파라미터로 숫자가 들어가면
			// 0~숫자-1 까지의 값이 
			// 랜덤하게 나오게 된다.
			// 따라서 그 결과값 + 1 을 해서
			// 1~숫자 까지 나오게 만들어준다.
		}
		
		System.out.println("======번호 생성 직후=======");
		for(int i = 0; i < array.length; i++) {
			System.out.printf("array[%d]: %d\n", i, array[i]);
		}
		System.out.println("=======================");
		
		// 중복 제거를 하는 코드
		// 중복이란?
		// 인덱스는 다르지만 배열의 값이 같은 경우
		// 중첩 for문을 사용해서
		// i와 j가 다르지만 array[i]와 array[j]가 같을 경우
		// i의 값을 다시 넣고
		// 그리고 처음으로 돌아가서 다시 검사하는 코드를 만들어보자
		
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length; j++) {
				if(i != j && array[i]==array[j]) {
					array[i] = random.nextInt(MAX) + 1;
					j = -1;
				}
			}
		}
		
		System.out.println("======중복 제거 직후=======");
		for(int i = 0; i < array.length; i++) {
			System.out.printf("array[%d]: %d\n", i, array[i]);
		}
		System.out.println("=======================");
		
		// 정렬을 해보자
		// 오름차순 정렬 시
		// 만약 i번째의 값이 i+1번째보다 크면
		// 2개의 순서를 바꿔주고 i를 맨 처음으로 보내면 된다.
		
		for(int i = 0; i< array.length - 1; i++) {
			if(array[i] > array[i+1]) {
				// 2개의 순서를 바꿔주면 된다.
				int temp = array[i];
				array[i] = array[i+1];
				
				array[i+1] = temp;
				i = -1;
			}
		}
		
		System.out.println("========정렬 직후========");
		for(int i = 0; i < array.length; i++) {
			System.out.printf("array[%d]: %d\n", i, array[i]);
		}
		System.out.println("=======================");
	}
}
