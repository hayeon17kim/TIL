---
title: "[JS] 생활코딩 #18: arguments"
categories: JavaScript
tags: [ JavaScript ]
---

# arguments

- arguments라는 객체는 **변수에 담긴 숨겨진 유사배열**이다. 이 배열에는 **함수를 호출할 때 입력한 인자가 담겨있다**. 
- arguments 안에는 사용자가 전달한 인자가 안에 들어가 있다. 이 객체를 통해서 **사용자가 전달한 인자들에 접근**할 수 있게 된다.

```javascript
function sum() {
    var i, _sum = 0;
    for (i = 0; i < arguments.length; i++) {
        document.write(i+' : '+arguments[i]+'<br />');
        _sum += arguments[i];
    }
    return _sum;
}
document.write('result : ' + sum(1,2,3,4));
```



### 매개변수의 수

- 매개변수와 관련된 두 가지 수가 있다.
  - `arguments.length`: 함수로 전달된 실제 인자의 수
  - `함수이름.length`: 함수에 정의된 인자의 수
  - 이 두 수를 **비교**하여 다르다면 **오류나 경고**를 띄울 수 있다.

> JS는 Java와 달리 함수에 정의된 인자의 수와 함수로 전달된 실제 인자의 수가 달라도 오류를 띄우지 않는다. 이러한 JS의 유연성은 프로그램에 문제를 발생시키는 원인이 될 수 있다. 따라서 이 둘을 비교하여 다르다면 오류나 경고를 띄우는 방법으로 문제 발생을 방지할 수 있다.

```javascript
function zero() {
    console.log(
    	'zero.length', zero.length,
        'arguments', arguments.length
    );
}

function one(arg1) {
    console.log(
    	'one.length', one.length,
        'arguments', arguments.length
    );
}

function two(arg1, arg2) {
    console.log(
    	'two.length', two.length,
        'arguments', arguments.length
    );
}

zero(); // zero.length 0 arguments 0
one('val1', 'val2'); // one.length 1 arguments 2
two('val1') // two.length 2 arguments 1
```


