# 함수 표현식

- 자바스크립트를 함수를 **특별한 종류의 값**으로 취급한다. 

  - 다른 언어에서처럼 '특별한 동작을 하는 구조'로 취급하지 않는다.
  - **호출할 수 있다**는 점 때문에 일반적인 값과 다른 **특별한 값**이다.

- 함수 선언방식 외에 **함수 표현식(Function Expressions)**을 사용해서 함수를 만들 수 있다.

- **함수는 값**이고, 따라서 **변수에 할당**될 수 있다.

  ```js
  // 함수를 만들고 그 함수를 변수에 할당하기
  let sayHi = function() {
    alert("Hello");
  };
  ```

- 함수 코드를 출력할 수 있다. **괄호가 없기 때문에 함수는 실행되지 않는다.**

  - 자바스크립트는 괄호가 있어야만 함수가 호출된다.
  - 함수 소스 코드가 문자형으로 바뀌어 출력된다.

  ```js
  function sayHi() {
    alert("Hello");
  }
  
  alert( sayHi ); // 함수 코드가  보임
  ```

- 함수를 새로운 변수에 저장(복사)할 수 있다.

  ```js
  function sayHi() { //함수 생성
    alert( "Hello" ); 
  }
  
  let func = sayHi; // 함수 복사
  //let func = sayHi(); // 함수 그 자체가 아니라 반환값을 저장
  
  func(); // Hello	//복사한 함수를 실행
  sayHi(); // Hello 
  ```

- 함수 표현식의 끝에 세미콜론이 붙는 이유

  - 중괄호로 만든 코드 블록 끝엔 `;` 이 없어도 된다.
  - 함수 표현식은 `let sayHi = ...`와 같은 구문 안에서 값의 역할을 한다. 코드블록이 아니라 값처럼 취급되어 변수에 할당된다. 함수 표현식 끝에 붙은 세미콜론은 함수 표현식 때문이 아니라 구문의 끝이기 때문이다.



## 콜백 함수

- 함수 `ask`의 인수 `showOk`와 `showCancel`은 *콜백 함수* 또는 *콜백*이라고 부른다.
- 함수를 함수의 인수로 전달하고, 필요하다면 인수로 전달한 그 함수를 **나중에 호출(called back**하는 것이 **콜백 함수**이다.

```js
function ask(question, yes, no) {
  if (confirm(question)) yes()
  else no();
}

function showOk() {
  alert("동의하셨습니다.");
}

function showCancel() {
  alert("취소 버튼을 누르셨습니다.");
}

ask("동의하십니까?", showOk, showCancel);
```

다음과 같이 함수 표현식을 사용하면 코드 길이가 짧아진다.

```js
function ask(question, yes, no) {
  if (confirm(question)) yes()
  else no();
}

ask(
  "동의하십니까?", 
  function() {alert("동의하셨습니다.")}, 
  function() {alert("취소 버튼을 누르셨습니다.")}
);
```

`ask(..)` 안에 이름이 없는 익명 함수(anonymous function)를 선언하였다. 익명 함수는 변수에 할당되지 않아 `ask` 바깥에서 접근할 수 없다.

> 함수는 **동작을 나타내는 값**이다. 문자열이나 숫자 등의 일반적인 값들은 *데이터*를 나타내는 반면 함수는 하나의 *동작(action)*을 나타낸다. **동작을 대변하는 값인 함수를 변수 간 전달하고, 동작이 필요할 때 이 값을 실행할 수 있다**.



## 함수 표현식 vs 함수 선언문

### 문법

- 함수 선언문: 함수는 주요 코드 흐름 중간에 **독자적인 구문 형태**로 존재한다.

- 함수 표현식: 함수는 **표현식이나 구문 구성(syntax construct) 내부**에 생성된다. 

  ```java
  // 함수는 할당 연산자 = 를 이용해 만든 할당 표현식 우측에 생성되었다.
  let sum = function(a, b) { return a + b; }
  ```

### 자바스크립트 엔진이 함수를 생성하는 시점

- **함수 표현식**: **실제 실행 흐름이 해당 함수에 도달**했을 때 함수를 생성하고, **이때부터 함수를 사용할 수 있다**.
  - 스크립트가 실행되고, 실행 흐름이 `let sum = function..`의 우측(함수 표현식)에 도달했을 때 함수가 생성된다. 이때 이후부터 함수를 사용(할당, 호출 등)을 할 수 있다.
- **함수 선언문**: 함수 **선언문이 정의되기 전에도 호출**할 수 있다.
  - 전역함수 선언문은 스크립트 어디에 있느냐에 상관 없이 어디서든 사용할 수 있다.
  - 왜? 자바스크립트는 **스크립트를 실행하기 전**, 준비단계에서 **전역에 선언된 함수 선언문을 찾고, 해당 함수를 생성한다**. 스크립트가 진짜 실행되기 전 *초기화 단계*에서 함수 선언 방식으로 정의한 함수가 생성되는 것이다. 스크립트는 함수 선언문이 모두 처리된 이후에서야 실행된다. 따라서 스크립트 어디서든 함수 선언문으로 선언한 함수에 접근할 수 있다.

```js
sayHi("John"); //Hi, John
sayHello("Monica"); // error: sayHello is not defined

function sayHi(name) {
  alert( `Hi, ${name}`);
}

let sayHello = function(name) { // 실행 흐름이 여기에 도달했을 때 함수 생성됨
  alert(`Hello, ${name}`);
}
```



### 스코프

**엄격 모드에서 함수 선언문이 코드 블록 내에 위치하면 해당 함수는 블록 내 어디서든 접근할 수 있다. 하지만 블록 밖에서는 함수에 접근하지 못한다.**

- **함수 선언문: 함수가 선언된 코드 블록 안에서만 유효하다.**

```js
let age = prompt("나이를 알려주세요", 18);

// 조건에 따라 함수를 선언함
if (age < 18) {
  welcome(); //안녕!
  function welcome() {
    alert("안녕!");
  }
} else {
  welcome(); //안녕하세요!
  function welcome() {
    alert("안녕하세요!");
  }
}

welcome(); // Error: welcome is not defined
```

`if`문 밖에서 `welcome` 함수를 호출하려면 어떻게 해야 할까? `if`문 밖에 선언한 변수 `welcome`에 함수 표현식으로 만든 함수를 할당하면 된다.

```js
let age = prompt("나이를 알려주세요.", 18);

let welcome;

if (age < 18) {
  welcome = function() {
    alert("안녕!");
  };
} else {
  welcome = function() {
    alert("안녕하세요!");
  };
}

welcome(); // 제대로 동작한다.
```

물음표 연산자를 사용하면 코드를 단순화시킬 수 있다.

```js
let age = prompt("나이를 알려주세요.", 18);

let welcome = (age < 18) ? 
    function() {alert("안녕!");} : 
function() {alert("안녕하세요!");};

welcome();
```



## 함수 선언문과 함수 표현식 중 무엇을 선택해야 할까?

함수 선어문을 먼저 고려하는 것이 좋다.

- 함수가 선언되기 전에 호출할 수 있어 코드 구성을 유연하게 할 수 있다.
- 가독성이 더 좋다.

함수 표현식은 함수 선언문을 사용하는 것이 부적절할 때 사용하는 것이 좋다.