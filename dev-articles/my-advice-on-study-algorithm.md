출처: https://shlegeris.com/2016/08/14/algorithms

알고리즘 학습에 대한 조언

## 공부 방법

- 모든 대표적 알고리즘과 자료 구조 문제를 알아야 한다.
- 부담되는 상황에서 알고리즘 논리를 화이트보드에 빠르게 풀어나갈 수 있어야 한다.



### 표준 알고리즘 자료

#### 자료구조

- list 구조: 배열, 동적 배열, linked list
- set과 map 구조: 해시맵, 이진 검색 트리, 힙

이에 대한 지식

- 필수 메서드가 어떻게 구현되어 있는지, 런타임은 어떻게 동작하는지, 자료구조의 구현을 어떻게 사용하는지

이를 알기 위해서는 

- 이진 검색 트리 구현에 균현을 맞추는 코드가 필요하다
- 큐를 스택 두 개로 구현할 수 있다.



### 알고리즘

- 그래프 알고리즘: 너비 우선 탐색(breadth first seath), 깊이 우선 탐색(depth first search), 다익스트라 알고리즘(dikstra's algorithm)
- 빠른 정렬 알고리즘 하나: 병합 정렬(mergesort) 또는 퀵정렬(quicksort)
- 배열에서 수행하는 이진 검색. 
- Big O 표기법을 편하게 사용

- 공부하기 좋은 자료: Skiena의 Algorithm Design Manuel (C언어)



### 표준 알고리즘 기술

- 공부하기 좋은 자료: Cracking the Coding Interview
- 동적 프로그래밍
- 재귀
- 유명 자료구조를 반복(iterating)하는 문제
- 문제 해결을 위해 빠른 자료 구조를 조합하기
- 문제 푸는 일을 내던지고 포기하는 것보다는 답안을 훔쳐보는 일이 그나마 낫다. (그러나 풀려고 노력할 것)



### 더 배우기

- 취업 목적 외에 본인을 위해 즐겁게 배우고 싶을 때
- 핵심 자료구조에 포함되지 않지만 상대적으로 간단한 자료구조 학습: 트리, 스킵 리스트, 증강 이진검색트리, 서로소 집합 자료구조
- 공부하기 좋은 자료
  - Skiena의 챕터 12과 이후 챕터



### 스택 2개로 큐 구현

2번째 스택에 아무것도 없는 상태에서 pop을 수행하려고 할 때 첫 번째 스택에 쌓여 있는 값들을 전부 2번째 스택으로 이관시킨다.

![queue-with-two-stacks](https://i.stack.imgur.com/1YfMo.png)

```java
public class Queue<E> {
  private Stack<E> inbox = new Stack<E>();
  private Stack<E> outbox = new Stack<E>();
  
  public void queue(E item) {
    inbox.push(item);
  }
  
  public E dequeue() {
    if (outbox.isEmpty()) { // 중요!
      while (!inbox.isEmpty()) {
        outbox.push(inbox.pop());
      }
    }
    return outbx.pop();
  }
}
```