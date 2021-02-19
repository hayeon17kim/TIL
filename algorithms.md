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



- 1분 자기소개

- 전에 하던 업무가 무엇인지?

- 왜 핸드스튜디오인가?

- 포트폴리오를 프레젠테이션

- 포트폴리오에 대한 의견을 받아 작업시 어떻게 협업해 나갈 것인지에 대한 태도를 본다.

- 사전과제

- 취미생활

  새로운 언어를 배우는 것을 좋아합니다. 요즘은 스페인어를 공부하고 있습니다. 

- 기업에 대해 궁금한 점'

- 나의 역량을 어떻게 평가하나요

- 요즘 무엇을 공부하나요?: 스프링 -> 프로젝트에 적용하는 중, 모던 자바스크립트, 코틀린

- 어떤 것에 관심이 있나요?

- 기술적인 것보다 그 외의 질문을 많이 받았다. 

- 해당 기술을 우리 회사에서 활용할 수 잇는가? : 해당 기술 이외의 것들도 소화 가능

- 어떤 사람인가? : 개선을 중요시 하는 사람. 성장 욕구가 큰 사람. 

- 코드리뷰를 진행: 

- 알레르기 여부: 없음

- 나중에 하고 싶은 것이 무엇인지?

- 기반지식이나 트렌드에 대한 질문. A와 B의 차이를 아느냐는 형식으로 한다.

- 야근을 할 수 있는 지

- 이력서와 포트폴리오 위주. 자신있는 언어

- 에이전시 업무에 대한 이해를 확실히 할 것

- 왜 지원했는지

- 어떤 일을 하고싶은지

- 요즘 관심있는 분야

- 자신의 실력을 퍼센트로 계산한다면?

- 분위기를 중시하는 듯함

- 웹 표준이란? DBMS란? 

- 영어 능력

- 회사 운영이 불안정하다면 어떻게 반응하는가: 



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
- `offset`: 요소의 위치를 조회하거나 지정하기 위해서 사용한다. 문서 기준 좌표로 위치를 측정하고, 이 x, y 좌표와 대응



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

