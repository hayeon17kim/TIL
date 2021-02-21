## 자연수의 각 자리수의 합 구하기

```java
public class Solution {
  public int solution(int n) {
    int answer = 0;
    while (n != 0) {
      answer += n % 10;
      n /= 10;
    } 
    return answer;
  }
}
```

n을 10으로 나눈 나머지: 

```java
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            answer[i] = Integer
                .toBinaryString(arr1[i] | arr2[i])
                .replace("0", " ")
                .replace("1", "#");
        }
        
        return answer;
    }
}
```

다음과 같이 했을 때는 두번째 테스트를 통과하지 못했다. 값이 작을때 실행한 자리수만큼 나오지 않았기 때문.

그래서 다음과 같이 자리수가 n보다 작으면 자리수가 n과 같아질 때까지 앞에 "0"을 추가해주는 코드를 작성했다. 

```java
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            answer[i] = Integer
                .toBinaryString(arr1[i] | arr2[i]);
            while (answer[i].length() != n) {
                answer[i] = "0" + answer[i];
            }
            answer[i] = answer[i].replace("0", " ").replace("1", "#");
        }
        
        return answer;
    }
}
```





비트코인 거래를 포함하는 CSV가 주어지면, 프레이드를 CandleStick 차트로 표현하고자 한다. 그러기 위해서는 과거 데이터를 변환해야 한다.

CSV 파일과 원하는 기간(창 크기)을 사용하고 결과를 JSON 형식으로 출력하는 함수를 만들어야 한다.

**입력 형식**

CSV 파일 trade.csv(http://api.bitcoincharts.com/v1/csv/korbitKRW.csv.gz)에는 다음과 같은 형식의 Ntrade가 포함되어 있다.

- timestamp: UNIX 시간에 트랜잭션이 채워진 시간
- price: 원화로 거래되는 가격
- size: 비트코인(BTC)의 거래 규모

**샘플 CSV 파일:**

```csv
timestamp, price, size
1383037954,227000,0.30000000
1383038122,245000,1.19300000
1383038122,250000,0.30020000
1383038122,250000,2.00000000
1383038169,259000,0.09700000
1383038169,259000,1.90300000
1383059294,230000,0.69000000
1383059458,230000,0.31000000
1383059491,259000,0.09700000
1383059658,259500,3.00000000
1383059737,260000,5.00000000
1383067046,220000,0.15000000
1383089545,269500,1.00000000
1383089648,269500,1.00000000
1383091563,269500,1.00000000
1383091580,269500,0.76000000
1383091581,270000,4.24000000
1383091594,270000,0.76000000
1383091621,279000,1.80000000
1383092762,279000,0.20000000
1383092780,280000,0.20000000
1383092780,280000,0.80000000
1383094787,279000,6.60000000
1383102079,245000,1.02000000
1383102211,245000,0.98000000
1383102509,250000,2.00000000
1383104598,230000,0.10000000
```

- 원하는 기간은 초 단위로 한다. 이 값은 항상 30초보다 크거나 같고, 86400초 (1일)보다 작다.
- JSPN 어레이 출력은 다음을 포함하는 목록이어야 한다.
  - `open`: 기간 시작 시 문자열 가격(원화)
  - `close`: 기간 종료 시 문자열 가격(원화)
  - `high`: 해당 기간 중 최고가(원화) 문자열
  - `low`: 해당 기간 중 최저가(원화) 문자열
  - `Start`: 기간의 정수 시작 시간(유닉스 타임스탬프)
  - `End`: 기간의 정수 종료 시간(유닉스 타임스탬프)
  - `average`: 해당 기간 중 거래당 평균가격(원)
  - `weighted_average`: 해당 기간 동안의 문자열 가중 평균 가격(원 단위)이다.
  - `volumn`: 문자열(BTC 단위) 동안 거래된 총 볼륨
- 설계 고려사항
  - CSV 구문 분석 라이브러리가 이미 있다고 가정한다. 파서를 쓸 필요가 없다.
  - 원 단위에서 가장 가까운 한 자리수의 경우, 가장 가까운 Satoshi까지의 BTC 단위
  - 가정사항을 모두 적어서 당사와 논의할 준비가 되어 있다.
  - 동작하는 코드가 주요 목표이다. 시간이 남아 있을 때만 최적화한다.

Sample Input

```csv
1383038122,250000,2.00000000
1383038169,254000,0.09700000
1383038169,259000,1.90300000
1383038233,251000,1.39100000
```

30초 동안의 샘플 출력

```json
[{ 
"start": 1383038122, 
"end": 1383038151, 
"open": "250000", 
"close": "250000", 
"high": "250000", 
"low": "250000", 
"average": "250000", 
"weighted_average": "250000",
"volume": "2.00000000" 
}, { 
"start": 1383038152, 
"end": 1383038181, 
"open": "254000", 
"close": "259000", 
"high": "259000", 
"low": "254000", 
"average": "256500", 
"weighted_average": "258,758", 
"volume": "2.00000000" 
}, { 
"start": 1383038182, 
"end": 1383038211, 
"open": null, 
"close": null, 
"high": null, 
"low": null, 
"average": null, 
"weighted_average": null, 
"volume": "0.00000000" 
}, { 
"start": 1383038212, 
"end": 1383038241, 
"open": "251000", 
"close": "251000", 
"high": "251000", 
"low": "251000", 
"average": "251000", 
"weighted_average": "251000", 
"volume": "1.39100000" 
}]
```



8bwvR0K1P4



## 1강 기지국 설치

```java
class Solution {
    // n 아파트의 개수  stations 기지국이 설치된 아파트 번호  W 전파의 도달 거리
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int[] checker = new int[n];
        for (int i = 0; i < stations.length; i++) {
            for (int j = -w; j <= w; j++) {
                if (stations[i] - j < 0 || stations[i] - j >= n) {
                    continue;
                }
                checker[stations[i] - j] = 1;
            }
        }
        int count = 0;
        for (int i = 0; i < n; i ++) {
            
            if (checker[i] == 0) {
                count++;
            }
            
            if (checker[i] == 1 && i != 0 && checker[i - 1] == 0 || i == n-1 ) {
                int temp = count / (2*w + 1);
                if (count % (2*w + 1) > 0) {
                    answer += temp + 1;
                } else {
                    if (temp == 0) {
                        answer += 1;
                    } else {
                        answer += temp;
                    }
                }
                //System.out.println("count:" + count);
                //System.out.println("answer: " + answer);
                count = 0;
            }
            //System.out.print(checker[i] + ", ");
        }
        return answer;
    }
}

// 11, [4, 11], 1 => 3
// 16, [9], 2 => 3
```



- 아파트들을 순회하면서 일단 전파범위에 속하지 않으면 일단 기지국을 세우고 본다. 
- 그런데, 기지국을 세울 때, 전파범위의 효과가 최대가 되도록, **전파 유효범위 만큼 이동해서 기지국을 세운다.** 
- 전파범위가 끝나는 지점 이후부터 계속 살펴본다. 
- 만약 중간 이미 설치된 기지국이 있다면, **이미 설치된 전파범위 밖으로 벗어나서** 같은 작업을 하면 된다.
- 현재 전파가 없기 때문에 현재 조건에 의해서 일단 설치하고 본다. 전체적인 조건을 따지는 것이 아니라, 현재의 상태만 보고 탐욕적으로 당장의 해결방법을 적용하는 방식. **욕심쟁이 알고리즘**, 또는 **그리디 알고리즘**이라고 한다.



```java
class Solution {
  public int solution(int n, int[] stations, int w) {
    int answer = 0;
    
    Queue<Integer> sq = new LinkedList<>();
    for (int s : stations) sq.offer(s);
    
    int position = 1;
    while (position <= n) {
      // sq.peek() - w: 기존에 설치되어 있던 기지국 전파범위의 왼쪽 끝보다 오른쪽이라면 기지국 전파범위 안에 있다.
      if (sq.isEmpty() && sq.peek() - w <= position) {
        position = sq.poll() + w + 1;
      } else {
        // 기지국을 세운다!
        answer += 1;
      	position += w * 2 + 1;
      }
    }
    
    return answer;
  }
}
```

- `Queue`: 그래프의 넓이 우선 탐색(BFS)에서 사용
- `peek()`: 첫번째로 저장된 값 참조

- 기존에 설치되어 있던 기지국을 하나씩 꺼내서 전파의 범위를 계산할 것이기 때문에 데이터구조의 queue를 사용한다.
- `sq.peek() - w <= position`: 기존에 설치되어 있던 기지국 전파범위의 왼쪽 끝보다 오른쪽이라면 기지국 전파범위 안에 있다. 
- `position = sq.poll() + w + 1`:이렇게 되면 다음 이동해야 할 곳은 기지국의 오른쪽 끝 전파범위 바깥이 된다. 
- `sq.isEmpty()`: 큐가 다 소진되어서 끝날 수도 있으니까 비어있는지 여부 확인



효율성 높이기

- **Loop 개선**: 전체를 다 돌지 않고서도 처리할 수 있는지
- 적절한 데이터 구조 사용
- **불필요한 Object 제거**: 원시값을 사용하는 것이 훨씬 더 빠르다. 

```java
class Solution {
  public int solution(int n, int[] stations, int w) {
    int answer = 0;
    int si = 0;
    
    int position = 1;
    while (position <= n) {
      // sq.peek() - w: 기존에 설치되어 있던 기지국 전파범위의 왼쪽 끝보다 오른쪽이라면 기지국 전파범위 안에 있다.
      if (si < stations.length && stations[si] - w <= position) {
        position = stations[si] + w + 1;
        si += 1;
      } else {
        // 기지국을 세운다!
        answer += 1;
      	position += w * 2 + 1;
      }
    }
    
    return answer;
  }
}
```



```java
class Solution {
  public int solution(int n, int[] sations, int w) {
    int answer = 0;
    int si = 0;
    
    int position = 1;
    while (position <= n) {
      if (si < stations.length && stations[si] - w <= position) {
        position = stations[si] + w + 1;
        si += 1;
      } else {
        answer += 1;
        position += 2 * w + 1;
      }
    }
    return answer;
  }
}
```



- `System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length)`
- `System.arraycopy(commands[i], commands[i][0] - 1, temp, 0, commands[i][1] - commands[i][0] + 1)`



## 예산

탐색(Search)이란 데이터들 중에서 특정 값을 찾아내는 것을 말한다.

### 문제

- 탐색(Search): 데이터들 중에서 특정 값을 찾아내는 것
- 가장 단순한 방법: 0부터 끝까지 모든 값을 순회한다. 
- 찾는 값은 금액, 최소값과 최소값이 주어져 있다. 그리고 가장 낮은 금액과 가장 높은 금액이기 때문에 sort 데이터나 마찬가지다. 이미 정렬이 되어 있기 때문에 이분탐색(Binary Search)를 사용할 수 있다.
- 이분탐색: 전체 데이터를 다 확인하지 않고 범위를 줄여가면서 값을 찾아나가는 방식을 말한다. 최대값과 최소값 사이 어딘가쯤에 원하는 값이 있을테니 이 중간값을 취해 원하는 값인지 확인한다. 이 값을 상한선으로 했을 때 예산을 초과하는지 아닌지 확인한다. 예산을 초과하지 않는다면 m과 최댓값사이일 것임. 새로운 범위가 생기고, 여기에 중간값을 다시 예산을 초과하는지 안하는지 확인. 이렇게 범위가 하향조정되고 다시 중간점을 상한선으로 하는 총합을 구해본다.
- 이 작업은 최소값과 최대값이 한 시점으로 수렴할 것이고, 수렴하는 값이 나올 때까지 반복하면 된다. 범위를 1/2 씩 줄여나가기 때문에 이분탐색이다. 이 조건은 데이터가 정렬되어 있어야 한다는 것이다. 

```java
class Solution {
  public int solution(int[] budgets, int M) {
    int min = 0;
    int max = 0;
    for (int b : bugets) {
      if (b > max) max = b;
    }
    
    if answer = 0;
    while (min <= max) {
      int mid = (min + max) / 2;
      
      int sum = 0;
      for (int b : bugets) {
        if (b > mid) sum += mid;
        else sum += b;
      }
      
      //총 합이 국가 전체 예산보다 작은 경우
      if (sum <= M) {
        min = mid + 1;
        // 이때 mid가 answer이 될 수 있다.
        answer = mid;
      } else {
        max = mid - 1;
      }
    }
    return answer;
  }
}
```

리팩토링 하기

```java
import java.util.stream.*;

class Solution {
  public int solution(int[] budgets, int M) {
    int min = 0;
    int max = IntStream.of(budgets).max().orElse(0); // Optional(Int)
    
    if answer = 0;
    while (min <= max) {
      // stream을 사용할 때 ~ function 안에서 사용되는 값들은
      // 가변 변수를 사용하면 안 된다. final로 바꿔주자.
      final mid = (min + max) / 2;
      
      int sum = 0;
      IntStream.of(budgets)
        // 예상 값이 들어왔을 때
        // mid 값과 비교해서 더 큰 경우 mid 값을 취하도록 만든다.
        .map(b -> Math.min(b, mid))
        .sum();
      
      //총 합이 국가 전체 예산보다 작은 경우
      if (sum <= M) {
        min = mid + 1;
        // 이때 mid가 answer이 될 수 있다.
        answer = mid;
      } else {
        max = mid - 1;
      }
    }
    return answer;
  }
}
```

- `max()` 메서드를 사용하면 `Optional(Int)` 값을 리턴한다. 이 값은 값이 있을 수도, 없을 수도 있다는 걸 말한다. 이때 값이 없으면 0이 되도록 `orElse(0)` 메서드를 사용해서 `int`값이 정상적으로 리턴되도록 만들어주자.

- `final`로 변수를 선언해주는 경우:

  stream 중간에서 stream 외부에 있는 변수값을 참조하고 있는 경우이다. stream이 처리되는 도중에 참조하는 변수의 값이 변경되면 side-effect가 된다. stream의 흐름은 비동기 처리처럼 기존 프로그램 흐름과 다르다. 그래서 stream 외부에 있는 변수의 값을 참조할 때 immutable한 값을 참조한다. 자바 컴파일러에서도 가변 변수를 참조하고 있다면 경고를 띄우며 final 변수를 참조하라고한다. final이 아니여도 문제 풀이 코드를 작성하고 실행하는 데는 큰 문제는 없다. 

- 좋은 프로그램 만드는 방법

  - 당면한 문제를 해결한다.
  - 좋은 코드가 되도록 계속 리팩토링한다.



## 숫자게임

- A팀은 숫자와 숫서가 정해져 있다.
- B팀의 순서를 조합했을 때 



- B팀이 배열될 수 있는 모든 경우의 수를 비교해 보고, 그 상황에서의 승점을 계산해서 최댓값.
- 만약 전체 모든 경우의 수를 다 확인하고, 그 안에서 원하는 값을 찾는다면 이것은 탐색법으로 풀게 된다. 하지만 B가 최대 승점으로 이긴다는 조건이 있고, 이 조건에 맞는 조합을 재현해낼 수 있다.
- 특정 상황을 재현해내는 것이 시뮬레이션한다는 의미이다.
- B의 숫자가 근소한 숫자로 이기는 경우를 봐야 한다. 
- 그래서 B의 작은 값부터 시작해서 A를 이길수 있는 값을 하나씩 제거해가면서 계산해보자.





시뮬레이션

- 일련의 명령에 따라서 개체를 차례대로 이동시키는 것
- 일반적으로 2차원 공간을 다루는 문제가 많이 나온다.

이건 내가 작성했던 코드인데, 이렇게 할 경우 샘플 테스트케이스는 통과하지만 대부분의 테스트 케이스는 통과하지 못하고, 효율성 테스트에서도 모두 시간초과가 났다. 

```java
public class Simulation {
  public static void main(String[] args) {
    System.out.println(solution(new int[] {5, 1, 3, 7}, new int[] {2, 2, 6, 8}));
  }

  public static int solution(int[] A, int[] B) {
    int score = 0;
    Arrays.sort(B);
    List<Integer> blist = new LinkedList<>();
    for (int b : B) blist.add(b);

    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < blist.size(); j++) {
        if (A[i] < blist.get(j)) {
          blist.remove(j);
          score++;
          continue;
        } else {
          blist.remove(0);
        }
      }
    }
    return score;
  }
}
```





강사님.

B팀이 근소한 차이로 배치하는 것이 중요하다. B팀이 근소한 차이로 이기도록 하도록 B팀의 작은 숫자부터 비교를 해야 한다. 따라서 우선 B팀을 정렬한다.

```java
class Solution {
  public int solution(int[] A, int[] B) {
    Arrays.sort(B);
    
    int answer = 0;
    for (int i = 0; i < A.length; i++) {
      for (int j = 0 ; i < B.length; j++) {
        if (A[i] < B[j]) {
          answer++;
          B[j] = 0;
          break;
        }
      }
    }
  }
}
```

- `B[j]`:  이미 사용한 숫자가 되었으니 다시 사용하지 못하도록 최솟값을 준다. 이 문제에서 1보다 큰값을 조건으로 주고 있기 때문에, 0은 어떤 값과 비교해도 가장 작은 값이 될 테니 사용되지 않을 것이다. 
- 그리고 가장 작은 값을 찾아냈기 때문에 **B의 루프는 멈춰도 될 것이다.** 
  - 이부분에서 나는 continue를 썼는데 break를 썼어야 했다.
- 이러면 A의 하나의 숫자를 이길 수 있는 B의 가장 작은 수를 찾아서 사용하게 된다. 

우선 루프를 의심해보자. 이중 루프를 루프 하나로 처리해보자. 

A도 B처럼 sort를 해놓고 A에 있는 값의 큰 순서대로 B에 있는 큰 수들을 하나씩 제거해나간다.

```java
class Solution {
  public int solution(int[] A, int[] B) {
    Arrays.sort(A);
    Arrays.sort(B);
    int index = B.length - 1;
    
    int answer = 0;
    for (int i = A.length - 1; i >= 0; i--) {
      if (A[i] < B[index]) {
        index--;
        answer++;
      }
    }
  }
}
```

- A의 가장 큰 값과 B의 가장 큰 값을 비교했는데 A가 더 크다면 그 A는 이길 수 없는 숫자가 된다. 그럼 B에서 가장 큰 수를 A의 다음번 큰 숫자와 비교해야 한다.
- B가 이길 수 있는 숫자라면 B의 숫자가 사용된다. index는 하나 감소되고, 승점이 하나 올라간다.
- **A와 B 모두 정렬을 해놓고 서로 큰 값을 비교해가면서 계산을 해줬다. 이중루프에서 단일루프로 변경되었고, 따라서 효율성테스트에 통과되었다.**



## 위장

### 내가 푼 방법

```java
public static int solution(String[][] clothes) {
  int answer = 1;
  int count = 0;
  Map<String, Integer> map = new HashMap<>();
  for (int i = 0; i < clothes.length; i++) {
    map.put(clothes[i][1], count++);
  }

  for (String key : map.keySet()) {
    answer *= (map.get(key) + 1);
  }

  return answer - 1;
}
```

이러면 샘플 테스트 1번만 맞고 다른 것은 다 틀리다. 여기서 내가 실수한 것이 있는데, count라는 하나의 변수를 재사용한다는 것이다. 이 경우 한 키값에서 사용한 count가 다른 키값에서도 재사용되기 때문에 0부터 시작하지 않는다. 이를 위해서는 각 key에 해당하는 value가 null이면 0을 넣고 1을 증가시키고, null이 아니면1을 증가시키기만 하도록 만들어 줘야 한다. 

### 수업

- 각 종류들을 조합해서 위장을 한다. 모든 경우의 수가 몇 가지가 되는지 물어보는 문제이다. 
- 위장용품은 착용할 수도 있고 안 할 수도 있다. 
- 착용 안할 수도 있으니까 경우의 수에 1을 더해서 모두 곱해주면 된다. 
- 위장 용품을 모두 착용하지 않는 경우도 있으니 이 경우 나중에 1을 빼준다.
- 문제의 핵심은 경우의 수를 물어보는 것이 아니라, 아이템의 종류별로 개수를 카운트하는 것이다.
- 문제에서는 headgear 종류가 2개가 있고, eyewear가 1개가 있다. 이름은 중요하지 않고, 종류별로 총 몇 개가 나오는 지 카운트하는 것이 이 문제의 핵심이다.
- 쉽게 생각하면, 배열 몇 개를 만들어서, 얼굴용은 1번 인덱스에, 상의용은 2번 인덱스에 저장하는 식으로 카운팅할 수도 있지만, 종류가 미리 주어지지 않았다면 할 수 없다.
- 이 경우 인덱스가 아니라 키 값으로 정보를 저장할 수 있는 해시를 사용하는 것이 좋다.
- 해시? 배열에 특정 값을 담을 때 어떤 인덱스에 해당 값이 있는 지 찾기 위해서 처음부터 끝까지 모두 탐색을 해야 한다. 매번 인덱스에 접근할 때마다 탐색을 하는 것은 굉장히 비효율적이다. 그래서 해시에서는 key값을 사용한다. key로부터 Hash값을 얻어 Hash값을 인덱스로 사용하는 것이다. 언제든지 해시값만 얻을 수 있다면 탐색 없이 인덱스로 접근 가능하다.
- 여기서 Hash값은 Hash 함수를 통해서 얻은 값을 말한다. 
- Hash함수란 최대한 겹치는 값이 발생하지 않도록 유니크 값을 생성해내는 함수이다. 특정 Key에 Hash값을 얻어서 배열의 index로 사용하는 자료구조를 Hash라고 한다.
- Hash 자료구조는 배열, 리스트, 탐색, 해시 요소를 복합적으로 사용해야 한다. 그래서 자료구조를 질문할 때 Hash 하나만 물어봐도 잘 이해하고 있는 지 알 수 있다. 기술 면접에도 단골 문제이다. 
- 자바에서는 Hash를 사용한 자료구조를 Map이라고 부른다. 

```java
public int solution(String[][] clothes) {
  Map<String, Integer> counts = new HashMap<>();
  
  for (String[] c : clothes) {
    String type = c[1];
    //counts.put(type, counts.get(type) == null ? 0 : counts.get(type) + 1);
    counts.put(type, counts.getOrDefault(type, 0) + 1);
  }
  
  int answer = 0;
  
  for (Integer c : counts.values()) {
    // 위장용품을 사용하지 않는 경우도 있으니까 + 1
    answer *= c + 1;
  }
  // 위장용품 모두 사용하지 않는 경우는 없으니 - 1
  answer -= 1; 
  return answer;
}
```

- `counts.getOrDefault(type, 0)`: `type`에 해당하는 값이 null 이라면 default 값으로 0을 가지고, 그리고 그것에 1을 더해서 `put`한다. `counts.get(type) == null ? 0 : counts.get(type) + 1`과 같다.
- `counts.values()`: Map 자료구조에는 `values()`라는 메서드가 있다. 이 메서드는 해당 Map의 value들만 모아 Collection으로 리턴한다.
- `answer *= c + 1`: 위장 용품을 사용하지 않는 경우도 카운트하기 위해 1을 더해주었다.
- `answer -= 1`:  위에서 계산한 answer 값은 위장 용품을 모두 사용하지 않는 경우도 포함한다. 문제에 따르면 위장 용품을 하나라도 사용해야 하니 1을 빼주었다. 

stream을 사용하여 리팩토링해보자.

```java
public int solution(String[][] clothes) {
  Map<String, Integer> counts = new HashMap<>();
  
  String type;
  // 옷만 추려서 본다.
  Arrays.stream(clothes).filter(c -> c[1].equals(type)).count();
  
  for (String[] c : clothes) {
    String type = c[1];
    //counts.put(type, counts.get(type) == null ? 0 : counts.get(type) + 1);
    counts.put(type, counts.getOrDefault(type, 0) + 1);
  }
  
  int answer = 0;
  
  for (Integer c : counts.values()) {
    // 위장용품을 사용하지 않는 경우도 있으니까 + 1
    answer *= c + 1;
  }
  // 위장용품 모두 사용하지 않는 경우는 없으니 - 1
  answer -= 1; 
}
```

Stream을 이용하여 리팩토링해보자.

```java
public int solution(String[][] clothes) {
  return Arrays.stream(clothes)
    .map(c -> c[1])
    .distinct()
    .map(type -> (int) Arrays.stream(clothes).filter(c -> c[1].equals(type)).count())
    .map(c -> c + 1)
    .reduce(1, (c, n) -> c * n) - 1;
}
```

- 전체 옷을 stream을 사용하여 filter로 첫번째 요소가 타입과 같은지 보고 그것의 카운트를 구하면 type의 개수를 구할 수 있다. 
- 각 타입은 전체 위장용품 중에서 1번 인덱스에 있는 값이고, 이것이 여러개가 나올 수 있으니 중복을 제거해야 한다. 그리고 여기에 type값을 
- `Arrays.stream(clothes)`: 모든 옷의 종류가 있는데 
- `map(c -> c[1])`: 그 중에 1번 인덱스에 있는 type만 꺼내서 
- `distict()`: 중복 없이 가져온다.
- `map(type -> (int) Arrays.stream(clothes).filter(c -> c[1].equals(type)).count())`:  이 type에 해당하는 것만 필터링해서 count를 얻어오면 종류별로 몇 개인지 알 수 있고,
- `map(c -> c + 1)`: 그것에 1을 더해서
- `reduce(1, (c, n) -> c * n)`: 모두 누적해서 곱한 값을 구한다.

**Tip 코딩 스킬을 향상시키는 방법**

- 문제를 풀이한다.
- 다른 방식으로 풀어본다.
- 다른 표현으로 바꿔본다. 

## 게임 맵 최단거리

- 미로의 시작점에서 목적지까지 찾아가는 탐색
- 너비 우선 탐색의 전형적인 문제
- 전체적으로 모든 정보를 다 확인하는 방식으로 해결한다.
- 맵에서 플레이어가 특정 위치에 있을 때 이동할 수 있는 경우의 수는 4가지가 있다. 이 4가지 경우를 모두 다 본다. 한 칸씩 움직인 후 다음 경우의 수를 생각.
- 현재 시작점 위치를 0으로 두고, 1씩 더해서 이동하게 된다. 이렇게 이동한 이후에 여기서 갈 수 있는 경우의 수를 본다. 다시 돌아갈 수 는 없고 판을 넘어갈 수는 없다. 이런 식으로 범위를 확장한다. 
- 너비우선탐색이나 깊이우선탐색: 한꺼번에 여러 값이 움직이기 때문에 복잡하다. 하지만 데이터 값을 추적하지 말고 규칙만 생각하면 된다. 모든 경우에 똑같이 적용할 수 있는 조건만 생각하고 종료조건을 잘 세워두기만 하면 된다. 
- 규칙
  - 네 방향으로 한 칸씩 이동한다.
  - 이동한 후에는 현재값보다 1 큰 값을 채운다.
  - 벽, 미로 밖, 왔던 길은 못간다.
  - 종료조건: 이동한 위치가 목적지이다. 
- 복잡한 데이터를 계산하는 것은 사람에게는 어렵지만 컴퓨터에게 쉽다. 규칙을 세우는 것은 사람에게는 쉽지만 컴퓨터에게는 어렵다. 따라서 규칙을 만들고 계산은 컴퓨터에게 시키자.
- 너비우선탐색은 Queue를 사용한다. 자바에서는 Queue를 인터페이스로만 제공해준다. Queue 인터페이스를 구현하고 있는 구조중에 LinkedList를 사용해보자.
- 플레이어가 움직이고 있는 현재 위치를 큐에 저장해보자.

```java
class Solution {
  class Position {
    int x, y;
    boolean isValid(int width, int height) {
      if (x < 0 || y >= width) return false;
      if (y < 0 || y >= height) return false;
      return true;
    }
  }
  
  public int solution(int[][] maps) {
    // BFS : Queue
    
    int mapHeight = maps.length;
    int mapWidth = map[0].length;
    
    Queue<Position> queue = new LinkedList<>();
    int[][] count = new int[mapHeight][mapWidth];
    boolean[][] visited = new boolean[mapHeight][mapWidth];
    
    queue.offer(new Position(0, 0));
    count[0][0] = 1;
    visited[0][0] = true;
    
    while(!queue.isEmpty()) {
      Position current = queue.poll();
      
      int currentCount = count[current.y][current.x];
      
      // 4가지 경우
      final int[][] moving = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}
      for (int i = 0; i < moving.length; i++) {
        Position moved = new Position(current + moving[i][0], current + moving[i][1]);
        if (!moved.isValid(mapWidth, mapHeight)) continue;
        if (visited[moved.y][moved.x]) continue;
        if (maps[moved.y][moved.x] == 1) continue;
      }
      
      count[moved.y][moved.x] = currentCount + 1;

      if (!visited[moved.y][moved.x] && maps[moved.y][moved.y] != 1 && moved.isValid(mapWidth, mapHeight)) {
        count[moved.y][moved.x] = currentCount + 1;
        queue.offer(moved);
      }
      
      queue.offer(new Position(current - 1, current));
      queue.offer(new Position(current, current + 1));
      queue.offer(new Position(current, current - 1));
    }
  } 
}
```

- BFS 문제를 푸는 가장 기본적인 형태. 
- 이렇게 되면 다음번 루프가 될 때 여기 있는 Queue 값을 꺼내서 똑같이 작업을 한다. 
- 큐를 이용해서 큐에 초기값을 넣고 큐가 빌 때까지 루프를 돌면서 처리할 수 있는 값을 계속 큐에 집어넣는 방식
- 최단거리.를 구할 것이기 때문에 count 값을 만든다.

