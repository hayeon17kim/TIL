# 자바스크립트 함수

## 정의

- 데이터 타입이 없기 때문에 함수의 리턴 타입을 지정하지 않는다.
- `function` 키워드로 시작한다.
- 파라미터가 없을 때는 빈 괄호로 지정한다.
- 값을 리턴할 때는 return 키워드를 사용한다.
- 파라미터는 `var`를 붙이지 않는다. 붙이면 안 된다. 

## 함수의 파라미터와 아규먼트

자바스크립트 함수를 호출할 때 아규먼트의 개수는 **함수에 정의된 파라미터 개수와 일치하지 않아도 된다**.

- 아규먼트(argument): 함수를 호출할 때 전달하는 값
- 파라미터(parameter): 함수를 호출할 때 전달한 값을 보관하는 함수 로컬 변수

## 함수 오버로딩?

같은 이름의 변수를 여러 개 선언하는 것은 문법 오류는 아니다. 단지 기존 변수를 가리킨다. 마찬가지로 같은 이름의 함수를 여러 개 선언하는 것은 문법 오류가 아니다. 단지 기존 함수를 대체한다. 따라서 자바스크립트는 **메서드 오버로딩**을 지원하지 않는다.

## 함수 아규먼트와 내장 변수 argument

자바스크립트의 함수는 **함수를 호출할 때 전달한 값들을 보관할 배열 변수를 내장**하고 있다. 그 내장 변수의 이름은 `arguments`이다.

```js
function f1(a) { 
	console.log("a =", a);
	console.log("----------------");
	
  for (var value of arguments) {
    console.log(value);
  }
  console.log("----------------");
  for (var i in arguments) {
    console.log(arguments[i]);
  }
  console.log("----------------");
  for (var i = 0; i < arguments.length; i++) {
    console.log(arguments[i]);
  }
}

//f1();
//f1(100);
f1(100, 200, 300, 400, 500);
```

- 자바스크립트는 **파라미터의 개수와 상관없이 아규먼트를 넘길 수 있기 때문에 자바의 오버로딩 문법이 자바스크립트에서는 없다.**
- 자바는 **메서드를 찾을 때 값의 타입과 개수를 이용하여 찾는다.** 그러나 자바스크립트는 **값의 타입이 없고 아규먼트의 개수도 의미가 없다.** 그래서 자바스크립트는 **같은 이름을 가진 함수를 여러 개 만들 수 없다.**

`Array()`로 만든 오리지널 배열은 `forEach()` 메서드가 있다. 그러나 `arguments`는 `Array()`로 만든 오리지널 배열이 아니다. 따라서 `forEach()` 함수가 없다. `forEach()`를 사용하려면 진짜 배열로 바꾼 후 사용해야 한다. 

```js
function f1(a) { 
	console.log("a =", a);

	/*
  arr.forEach(function(value) { // 실행 오류!
    console.log(value);
  });
  */
  
  console.log("----------------");
  // forEach()를 사용하고 싶다면 진짜 배열로 바꾼후 사용해야 한다.
  var arr = Array.from(arguments);
  arr.forEach(function(value) {
    console.log(value);
  });
  
//f1();
//f1(100);
f1(100, 200, 300, 400, 500);
```

한편 `Array()`로만든 배열에는 `reduce()` 함수가 있다. 합계 등을 계산할 때 유용하다.

```js
function f1(a) { 
 	arr.reduce(function(sum, value) {
    console.log(sum, value)
    return sum + value;
  });

//f1();
//f1(100);
f1(100, 200, 300, 400, 500);
```

## 함수와 window 객체

함수도 변수와 같이 자동으로 `window` 객체에 소속된다. 함수나 변수나 객체 입장에서는 그냥 프로퍼티이다. 따라서 프로퍼티 값을 가리키는 다음 문법을 그대로 사용할 수 있다.

```
객체["프로퍼티명"]
객체['프로퍼티명']
```

```js
function f1(str) { 
	console.log(str);
}

f1("안녕1");
window.f1("안녕2");

window["f1"]("안녕3");
```

**함수의 주소를 값처럼** 다룰 수 있다. 즉 **다른 변수에 저장하여 사용**할 수 있다. 함수 주소를 갖고 있는 변수는 함수처럼 사용할 수 있다.

```js
var ohora = window.f1;
ohora("안녕"); 
```



## 함수와 레퍼런스

함수는 그 자체로 객체이다. 즉 프로퍼티와 코드를 포함하고 있는 객체이다. (`function = properties + code`)

- 객체는 주소가 있다. 따라서 함수도 주소가 있다.
- 함수의 주소는 함수 이름을 가진 변수에 저장된다.
- 복사한 함수 주소를 가지고 원래의 함수처럼 사용할 수 있다.

```js
function f1() { 
	console.log("안녕!");
}

var f2;
f2 = f1; // f1에 저장된 함수 주소를 f2에 복사한다.

// 복사한 함수 주소를 가지고 원래의 함수처럼 사용할 수 있다.
f2();
window.f2();
```



## 익명 함수

함수 이름 없이 함수를 정의할 수 있다. 이름 없는 함수를 정의한 후 사용하려면 그 함수의 주소를 어딘가에 저장해야 하냐다.

```js
var f1;
f1 = function(a) { 
	console.log(a + "님, 안녕!");
};

// 함수 주소가 저장되어 있는 변수는 함수처럼 사용하면 된다.
f1("홍길동");
window.f1("홍길동");
```



## 함수 호이스팅

함수도 변수처럼 함수 정의를 맨 먼저 실행한다. 즉 script 태그를 실행할 때 **함수 정의가 있으면 제일 먼저 실행한다**. 이것은 함수를 정의한 코드를 맨 위로 끌어올린다고 해서 **함수 호이스팅(hoisting)**이라고 한다.

다음 코드에서는 `f1()` 함수를 정의하기 전에 `f1()`를 호출하고 있다. 그럼에도 오류가 발생하지 않는 이유는 함수 정의가 먼저 실행되기 때문이다.

한편 **다음 script 태그에 정의된 함수는 호이스팅 대상이 아니다**. 그 script 태그를 실행할 때 호이스팅된다. 따라서 두번째 script 태그에 선언된 `f2()`는 아직 정의된 상태가 아니기 때문에 호출할 수 없다.

```html
<script>
f1();
f2();
function f1() {
	console.log("안녕!");
}
</script>

<script>
function f2() {
	console.log("안녕2!");
}
</script>
```

### 함수 호이스팅(hoisting)과 익명 함수

변수 선언과 그 변수의 값을 초기화시키는 할당문이 함께 있을 경우 호이스팅 대상은 변수 선언만이 그 대상이 된다.

다음 코드에서 아래에 선언된 v1 변수가 호이스팅 되기 때문에 먼저 사용할 수는 있다. 그러나 값을 할당하는 = 연산자는 현재 위치에 도달할 때 실행된다. 

```js
console.log(v1); // undefined

var v1 = "홍길동"; 
console.log(v1); // 홍길동
```

함수도 같은 규칙을 따른다. 현재 위치에 도달했을 때 익명 함수가 정의되어 그 주소가 f1에 저장된다.

```js
//f1(); // 실행 오류!

var f1 = function() { 
    console.log("안녕!");
};

f1(); //OK! 현재 f1 변수에는 익명 함수의 주소가 들어 있다.
```



## 값으로서의 함수

함수는 객체이기 때문에 주소를 주고 받을 수 있다. 

### 아규먼트로 함수를 전달하기

함수 객체를 아규먼트로 넘길 때 보통 파라미터의 이름을 **fn** 또는 **cb(callback)**로 한다.

```js
function play(cb) {
  console.log("계산 결과 =", cb(100, 200));
}

function plus(a, b) {return a + b;}
function minus(a, b) {return a - b;}

play(plus); // 계산 결과 =300
play(minus); // 계산 결과 =-100
```

### 함수 리턴하기

함수 안에서 함수를 만들어 리턴할 수 있다.

```js
function interestCalculator(type) {
  switch (type) {
    case "보통예금":
      return function(money, month) {return money + (money * 0.0011 * month);};
    case "정기예금":
      return function(money, month) {return money + (money * 0.0014 * month);};
    default:
      return function(money, month) {return money;};
  }
}
```

`interestCalculator()` 함수가 리턴하는 것은 **내부에서 정의한 함수의 주소**이다.

```js
var fn = interestCalculator("보통예금");
console.log("100억 7달 =", fn(10000000000, 7));
```



