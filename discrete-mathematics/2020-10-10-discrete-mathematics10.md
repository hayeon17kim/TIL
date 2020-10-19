---
title: "이산수학 #11강: 행려래의 개념, 연산, 종류, 행렬식"
categories: discrete-mathematics
tags: [ discrete-mathematics ]
---

## 행렬의 개념

### 행렬(Matrix) m×n

m, n이 자연수일 때 m행(row), n열(column)로 나열된 실수의 2차원 배열

- a~ij~: i번째 행(row 가로줄), j번째 열(column 세로칸) (i = 1 : m, j = 1 : n)

![image](https://user-images.githubusercontent.com/50407047/96337357-8de10900-10c1-11eb-8295-fdd947bf84c9.png)



### 행렬의 덧셈과 뺄셈 A±B

- 두 행렬 A, B에서 같은 자리에 있는 원소끼리 더하거나 뺌
- 행렬의 크기가 같아야 함



### 행렬의 스칼라 곱 연산 (Scalar Multiplication)

- 행렬 A에 실수 k를 곱하는 연산 : Ak=kA=[kaij]



### 행렬의 곱셈

- m×p 행렬 A와 p×n 행렬 B의 곱 (A×B: m×n)
- c~ij~=a~i1~ b~1j~+a~i2~ b~2j~+…+a~ip~ b~pj~=∑a~ik~ b~kj~

![image](https://user-images.githubusercontent.com/50407047/96337434-224b6b80-10c2-11eb-9712-850faaa9c595.png)



**예제:** 행렬 A, B, C의 곱셈이 가능한 경우와 그 곱은?

![image](https://user-images.githubusercontent.com/50407047/96337457-48710b80-10c2-11eb-90d0-325f8a81dead.png)



## 행렬 연산의 성질

- A+B=B+A 
- A+(B+C)=(A+B)+C 
- (AB)C=A(BC)
- A+O=O+A=A 
- AI=IA=A 
- A+(-A)=(-A)+A=O 
- (-1)A=-A 
- k(A+B)=kA+kB 
- (k+l)A=kA+lA 
- (kl)A=k(lA) 
- k(AB)=(kA)B=A(kB) 
- A(B+C)=AB+AC 
- (B+C)A=BA+CA



## 행렬의 종류

### 영행렬(Zero Matrix) O

m×n 행렬 O=[a~ij~]에서 모든 i, j에 대해 a~ij~=0인 행렬 (행렬의 모든 원소가 0인 행렬)



### n차 정사각행렬(n-Square Matrix)

m×n 행렬 A=[a~ij~]에서 m=n인 행렬



### 대각행렬(Diagonal Matrix)

정사각행렬에서 대각원소 a~11~, a~22~, …, a~nn~ 이외의 모든 원소가 0인 행렬



### 단위행렬(Unit/Identy Matrix) I

대각행렬에서 대각원소가 모두 1인 행렬



**예제.** 행렬 A와 단위행렬 I의 곱

![image](https://user-images.githubusercontent.com/50407047/96337528-df3dc800-10c2-11eb-8d56-66d898f5d23e.png)



### 전치행렬(Transpose Matrix) A^T^

m×n 행렬 A=[a~ij~]에서 행과 열을 바꾼 n×m 행렬

![image](https://user-images.githubusercontent.com/50407047/96337556-10b69380-10c3-11eb-9074-c849cd1d02f0.png)



### 대칭행렬(Symmetric Matrix)

m×n 행렬 A=[aij]에서 AT=A인 행렬

> 대칭행렬이 성립하려면 한 대각선으로 데칼코마니처럼 양 쪽이 서로 같아야 한다. 따라서 대각선 양쪽이 서로 같은 정사각행렬이어야 한다.



### 부울행렬(Boolean/Zero-One Matrix)

모든 원소가 부울값(0과 1)으로만 구성된 행렬



#### 부울행렬 연산자

크기가 같은 부울행렬 A=[aij]와 B=[bij]에 대해

- 합(join): A∨B=[a~ij~∨b~ij~]
- 교차(meet): A∧B=[a~ij~∧b~ij~]

부울행렬 (m×p) A=[aij]와 (p×n) B=[bij]에 대해

- 부울곱(boolean product): (m×n) A⊙B=[c~ij~]

  ![image](https://user-images.githubusercontent.com/50407047/96337671-b0742180-10c3-11eb-9142-3e10ef64313f.png)

  - c~ij~=(a~i1~∧b~1j~ )∨(a~i2~∧b~2j~ )∨…∨(a~ip~ ∧b~pj~ )



**예제:** 부울곱 A⊙B

![image](https://user-images.githubusercontent.com/50407047/96337740-106ac800-10c4-11eb-8f3f-8dca44142309.png)



#### 부울행렬 연산의 특징

- A∨A=A, A∧A=A 
- A∨B=B∨A, A∧B=B∧A 
- (A∨B)∨C=A∨(B∨C), (A∧B)∧C=A∧(B∧C) (A⊙B)⊙C=A⊙(B⊙C) 
- A∨(B∧C)=(A∨B)∧(A∨C), A∧(B∨C)=(A∧B)∨(A∧C)



## 행렬식

### 행렬식(Determinant) |A| 또는 det(A)

n차 **정사각행렬**에 대응하는 수를 구하는 식



### 소행렬과 행렬식





### 여인수와 여인수행렬



### 여인수를 이용한 행렬식







