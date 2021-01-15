---
title: "이산수학 #7강: 집합의 대수법칙"
categories: discrete-mathematics
tags: [ discrete-mathematics ]
---

신흥철 교수님의 이산수학 7강을 듣고 정리하였습니다.

## 집합의 대수법칙

|                                                              |                                  |
| ------------------------------------------------------------ | -------------------------------- |
| A∪Ø=A<br />A∩U=A                                             | 항등법칙(Identity Law)           |
| A∪U=U<br />A∩Ø=Ø                                             | 지배법칙(Domination Law)         |
| A∪A=A<br />A∩A=A                                             | 멱등법칙(Idempotent Law)         |
| A∪B=B∪A<br />A∩B=B∩A                                         | 교환법칙(Commutative Law)        |
| A∪(B∪C)=(A∪B)∪C<br />A∩(B∩C)=(A∩B)∩C                         | 결합법칙(Associative Law)        |
| A∪(B∩C)=(A∪B)∩(A∪C)<br />A∩(B∪C)=(A∩B)∪(A∩C)<br />A×(B∩C)=(A×B)∩(A×C)<br />A×(B∪C)=(A×B)∪(A×C) | **분배법칙(Distribute Law)**     |
| (A')'=A                                                      | 이중 보법칙(Double Negation Law) |
| A∪A'=U<br />A∩A'=Ø<br />Ø'=U<br />U'=Ø                       | 보법칙(Complement Law)           |
| (A∪B)'=A'∩B'<br />(A∩B)'=A'∪B'                               | 드모르간의 법칙(De Morgan's Law) |
| A∪(A∩B)=A<br />A∩(A∪B)=A                                     | 흡수법칙(Absorption Law)         |

> 멱등법칙? 연산을 여러 번 적용하더라도 결과가 달라지지 않는 성질

> 상등이라는 것은 필요충분조건으로 나타내서 증명해도 된다.  

**예제 3-24.** 드모르간의 법칙을 증명하시오.

- (A∪B)'=A'∩B'

- (A∩B)'=A'∪B'



**예제 3-25.** 다음의의 흡수법칙을 증명하시오.

- A∩(A∪B)=A
- A∪(A∩B)=A



**예제**  (A∪B)-(A∩B)=A⊕B임을 증명하시오.



## 집합의 분할

### 분할 (Partition) {A1 , A2 , …, An}

- 공집합이 아닌 임의의 집합 A에 대하여, **서로소**면서 **공집합이 아닌** **부분집합**으로 **나누는 것**
- 분할의 성질: 집합 A가 있을 때, i=1, 2, …, k에 대하여
  - Ai≠Ø (공집합이 아닌)
  - Ai⊆A (A의 부분집합)
  - A=A1∪A2∪…∪Ak (나누는 것) 
  - i≠j면 Ai∩Aj=Ø (서로소)

- 집합류 Ai

  집합 A에 대하여 분할의 성질을 가지고 있는 집합 A의 부분집합



예제 3-30. 집합 A={a, b, c}의 모든 분할은?

- `{{a}, {b}, {c}}`
  - `{a}≠Ø, {b}≠Ø, {c}≠Ø`
  - `{a}⊂A, {b}⊂A, {c}⊂A`
  - `{a}∪{b}∪{c}={a, b, c}=A`
  - `{a}∩{b}=Ø, {b}∩{c}=Ø, {c}∩{a}=Ø`
- `{{a, b}, {c}}, {{a, c}, {b}}, {{a}, {b, c}}`
- `{{a, b, c}}`
  - `{a, b, c}≠Ø`
  - `{a, b, c}⊂A`
  - `{a, b, c}∪Ø=A`
  - `{a, b, c}∩Ø=Ø``

