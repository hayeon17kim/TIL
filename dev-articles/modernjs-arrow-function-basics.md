# 화살표 함수 기본

화살표 함수(arrow function)를 사용하면 함수 표현식보다 단순하고 간결한 문법으로 함수를 만들 수 있다.

- 중괄호 없이 작성: `(...args) => expression`
- 중괄호와 함께 작성: `(...args) => { body }`

## 중괄호 없이 작성

```js
let func = (arg1, arg2, arg3, ...argN) => expression

// 다음 함수와 같다.
let func = function(arg1, arg2, arg3, ... argN) {
  return expression;
}
```

- 함수 `func`는 실행되는 순간 **화살표 우측의 표현식(expression)을 평가**하고, **평가 결과를 반환**한다.
- 인수가 없어도 괄호는 생략할 수 없다.
- 다음과 같이 함수를 동적으로 만들 수 있다.

```js
let age = prompt("나이를 알려주세요.", 18);

let welcome = (age < 18) ?
    () => alert('안녕') :
() => alert("안녕하세요!");

welcome();
```

- 본문이 한 줄인 간단한 함수는 화살표 함수를 사용하는 것이 편리하다.

## 중괄호와 함께 작성

- 평가해야 할 표현식이나 구문이 여러개면, 중괄호 안에 평가해야 할 코드를 넣어주고, `return` 지시자를 사용해 명시적으로 결괏값을 반환해주어야 한다.

```js
let sum = (a, b) => { // 중괄호는 본문이 여러 줄로 구성되어 있음을 알려준다.
  let result = a + b;
  return result; // 중괄호를 사용했다면, return으로 결괏값을 반환한다.
};
```



## 과제

**Q1. 화살표 함수로 변경하기**

```js
function ask(question, yes, no) {
  if (confirm(question)) yes()
  else no();
}

ask (
  "동의하십니까?",
  function() { alert("동의하셨습니다." );},
  function() { alert("취소 버튼을 누르셨습니다.");}
);
```

  

- static 중첩 클래스는 바깥클래스의 제네릭을 사용하지 못한다. 적용하는 범위가 아니다.

