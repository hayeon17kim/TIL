package day03;

import java.util.Scanner;

public class PrintStars10 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("=====별찍기 10번=====");
		System.out.print("출력할 줄 수를 입력하세요: ");
		int userNumber = scanner.nextInt();
		
		for(int i = 1; i <= 2 * userNumber - 1; i++){
			String stars = "";
			if(i == 1 || i == 2 * userNumber - 1) {
				// 가장 윗줄 or 가장 마지막 줄이므로
				// 별을 2 * userNumber - 1 개만큼 stars에 추가한다.
				for(int j = 1; j <= 2 * userNumber - 1; j++) {
					stars += "*";
				}
			} else if(i < userNumber) {
					// 윗부분
					// 9 7 5 3 1
					for(int j = i; j <= userNumber; j++) {
						//왼쪽 별
						stars += "*";
					}
					int upperI = i - 1;
					
					for(int j = 1; j <= 2 * upperI - 1; j++) {
						//가운데 공백
						stars += " ";
					}
					for(int j = i; j <= userNumber; j++) {
						//오른쪽 별
						stars += "*";
					}
					
			} else {
				// 아랫부분
				int lowerI = i - userNumber + 1;
				for(int j = 1; j <= lowerI; j++) {
					//왼쪽 별
					stars += "*";
				}
				// 공백은 무조건 총 가로 칸 수 - 별의 갯수가 된다.
				// 해당 줄의 별의 갯수는 왼쪽 별 + 오른쪽 별이고
				// 한쪽 별의 갯수는 lowerI개 이다.
				// 결국 한줄 별의 갯수는 2*lowerI가 된다.
				// 따라서 공백의 갯수는
				// 2*userNumber-1-2*lowerI개가 된다.
				// 위의 식을 좀 더 간단히 쓰면
				// 2*(userNumber - lowerI) - 1이 된다. 
				for(int j = 1; j <= 2*(userNumber - lowerI) - 1; j++) {
					//가운데 공백
					stars += " ";
				}
				for(int j = 1; j <= lowerI; j++) {
					//오른쪽 별
					stars += "*";
				}
			}

			System.out.println(stars);
		}
		scanner.close();
	}
}
