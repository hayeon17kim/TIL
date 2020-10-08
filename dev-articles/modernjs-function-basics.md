# 함수

- 유사한 동작을 하는 코드가 여러 곳에서 필요할 때 함수로 만든다. 
- 함수는 프로그램을 구성하는 주요 구성 요소(building block)이다.



## 함수 선언

- `function name(parameters) {/*본문*/}`
- 새롭게 정의한 함수는 함수 이름 옆에 괄호를 붙여 호출
  - `showMessage()`



## 지역 변수 (local variable)

- 함수 내에서 선언한 변수
- 함수 안에서만 접근할 수 있다.



## 외부 변수 (outer variable)

- **함수 내부에서 함수 외부 변수에 접근하고, 수정할 수 있다.**
- 외부 변수는 동일한 이름의 지역 변수가 없는 경우에만 사용할 수 있다.
  - 동일한 이름의 지역 변수는 외부 변수를 가려 접근할 수 없게 만든다.



## 전역 변수(global variable)

- 같은 이름의 지역변수로 가려지지 않는다면 모든 함수에서 접근할 수 있다.
- **변수는 연관되는 함수 내에 선언하고, 전역 변수는 되도록 사용하지 않는 것이 좋다.** => 깔끔하고 이해하기 쉬운 코드
- 프로젝트 전반에서  사용되는 데이터는 전역 변수에 저장하는 것이 유용할 때도 있다.



## 매개변수 (parameter)

- 매개변수를 통해 임의의 데이터를 함수 안에 전달할 수 있다.
- **함수에 전달된 매개변수는 복사된 후 함수의 지역변수가 된다.**
- 함수는 언제나 복사된 값을 사용하기 때문에, 인자 값에 대한 함수 내부에서의 변경 사항은 외부 변수 값에 반영되지 않는다.
- **개발자는 매개변수를 받아서 그 변수를 가지고 반환 값을 만들어 내는 함수를 좀 더 쉽게 이해할 수 있다. 매개변수 없이 함수 내부에서 외부 변수를 수정해 반환 값을 만들어내는 함수는 쉽게 이해하기 힘들다.**

```js
function showMessage(from, text) {
  from = "*" + from + "*"; 
  alert( from + ": " + text);
}

let from = "Ann";

showMessage(from, "Hello"); // *Ann*: Hello

// 함수는 복사된 값을 사용하기 때문에 바깥의 from은 값이 변경되지 않는다.
alert(from);
```



## 기본값 (default value)

- **매개변수에 값을 전달하지 않으면 그 값은 `undefined`가 된다.**
- 이를 막기 위해 기본값을 설정한다.

### 매개변수 기본값 설정 방법 (1) 함수 선언부

- 매개변수 오른쪽에 `=`를 붙이고 기본값을 써준다.

```js
function showMessage(from, text = "no text given") {
  alert( from + ": " + text );
}

showMessage("Ann"); //Ann: no text given
```

- 복잡한 표현식도 기본값으로 설정할 수 있다.

```java
function showMessage(from, text = anotherFunction()) {
  // anotherFunction()은 text값이 없을 때만 호출됨
  // anotherFunction()의 반환 값이 text의 값이 됨
}
```

- 매개변수 기본값 평가 시점: **특정 매개 변수 없이 함수를 호출할 때마다 해당 매개변수 기본값을 평가**한다.



### 매개변수 기본값 설정 방법 (2) 함수 실행 도중

- 함수 선언부가 아니라 함수가 실행되는 도중 기본값을 설정하는 것이 논리가 맞을 때 사용하는 방법이다.
- 매개변수를 `undefined`와 비교하여 함수 호출 시 매개변수가 생략되었는지 확인

**if문**

```js
function showMessage(text) {
  if (text == undefined) {
    text = '빈 문자열';
  }
  alert(text);
}

showMessage();
```

**논리연산자 ||**

```js
function showMessage(text) {
  text = text || '빈 문자열';
}
```

**null 병합 연산자 ??**

```java
function showCount(count) {
  alert(count ?? "unknown");
}

showCount(0); //0
showCount(null); // unknown
showCount(); //unknown
```



## 반환 값 (return value)

- 함수를 호출한 곳에 특정 값 반환 
- 지시자 `return`은 함수 내 어디서든 사용 가능
- `return`만 명시할 수 있다
  - `return;`: 함수 즉시 종료

- **return문이 없거나 return 지시자만 있는 함수는 `undefined` 반환**
  - `return undefined`와 동일하게 동작

- return과 값 사이에 줄을 삽입하면 안 된다.

  - 자바스크립트는 return 문 끝에 세미콜론을 자동으로 삽입한다.

  - 표현식과 return 사이에 줄바꿈이 있다면 아무것도 반환하지 못한다.

  - 여러 줄을 사용하고 싶다면 괄호를 사용하자

    ```js
    return (
      some + long + expression
      + or + 
      whatever * f(a) + f(b)
    )
    ```

    

## 함수 이름 짓기

- 함수는 어떤 동작을 수행하기 위한 코드를 모아놓은 것이다.
- 함수가 어떤 동작을 하는 지 축약해서 설명해주는 동사를 접두어로 붙인다.

- 예시
  - `show..()`: 무언가를 보여줌
  - `get..()`: 값을 반환함
  - `calc..()`: 무언가를 계산함
  - `create..()`: 무언가를 생성함
  - `check..()`: 무언가를 확인하고 불린값을 반환함
- 함수는 동작 하나만 담당해야 한다.
  - `get..()`: 값을 얻어 오는 동작만 수행한다. 출력해주는 동작은 들어가지 않는 것이 좋다.
  - `create..()`: 무언가를 생성하고 반환하는 동작만 수행한다. 그것을 문서에 추가하는 동작은 들어가지 않는 것이 좋다.
  - `check..()`: 확인하고 결과를 리턴하는 동작만 한다. 승인 여부를 보여주는 메시지를 띄우는 동작은 하지 않는 것이 좋다.

- 짧은 이름의 함수: ex) jQuery의 함수 `$`



## 함수 == 주석

- 함수는 간결하고, 한 가지 기능만 수행할 수 있게 만들어야 한다.
- 함수가 길어지면 함수를 잘게 쪼갤 때가 되었다는 신호로 받아들이자.
- 간결한 함수의 장점
  - **디버깅이 쉽다.**
  - **함수 그 자체로 주석의 역할**을 한다
- 자기 설명적(self-describing) 코드: 이름만 보고도 어떤 동작을 하는지 알 수 있는 코드

**소수를 출력하는 함수**

```js
function showPrimes(n) {
  nextPrime: for (let i = 2; i < n; i++) {
    for (let j = 2; j < i; j++) {
      if (i % j == 0) continue nextPrime;
    }
    alert(i); // 소수
  }
}
```

**소수인지 아닌지 여부 검증하는 코드 분리**

```js
fucntion showPrimes(n) {
  for (let i = 2; i < n; i++) {
    if (!isPrime(i)) continue;
    alert(i); // a prime
  }
}

function isPrime(n) {
  for (let i = 2; i < n) {
    if (n % i == 0) return false;
  }
}
```

두번째의 가독성이 더 높다. isPrime() 함수 이름을 보고 해당 함수가 소수 여부를 검증하는 동작을 한다는 것을 쉽게 알 수 있기 때문이다.



## 과제

**Q1. '?'나 '||'를 사용하여 함수 다시 작성하기**

```js
function checkAge(age) {
  if (age > 18) {
    return true;
  } else {
    return confirm('보호자의 동의를 받으셨나요?');
  }
}
```

(1) 물음표 연산자 `?`를 사용하여 본문을 작성

```js
function checkAge(age) {
  return (age > 18) ? true : confrim("보호자의 동의를 받으셨나요?");
}
```

(2) OR 연산자 `||`를 사용하여 본문을 작성

```js
function checkAge(age) {
  return (age > 18) || confirm("보호자의 동의를 받으셨나요?");
}
```

**Q2. min(a, b) 함수 만들기**

```js
function min(a, b) {
  return (a > b) ? a : b;
}
```

**Q3. pow(x, n) 함수 만들기**

```js
function pow (x, n) {
  let result = x;
  for (int i = 0; i < n; i++) {
    result *= x;
  }
  return result;
}

let x = prompt("x? ");
let n = prompt("n? ");

if (n < 1) {
  alert(`${n}은 양의 정수이어야 합니다.`);
} else {
  alert(pow(x, n));
}

```