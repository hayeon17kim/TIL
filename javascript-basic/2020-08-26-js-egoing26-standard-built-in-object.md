---
title: "[JS] 생활코딩 #26: 표준 내장 객체의 확장"
categories: JavaScript
tags: [ JavaScript ]
toc: true
---

# 표준 내장 객체의 확장

## 표준 내장 객체(Standard Built-in Object)란?

- 자바스크립트가 기본적으로 가지고 있는 객체들을 말한다.
- 프로그래밍을 하는 데 기본적으로 필요한 도구이다.
- 프로그래밍: 언어와 호스트 환경이 제고애하는 기능들을 통해서 새로운 소프트웨어를 만들어내는 것
- 자바스크립트의 내장객체
  - Object
  - Function
  - Array
  - String
  - Boolean
  - Number
  - Math
  - Date
  - RegExp
- 다른 언어에 비해 내장객체가 적은 편이다. 
- 이 외에도 호스트 환경(브라우저)가 제공하는 API가 따로 있다. 



## 배열 확장

방법1: 독립적인 함수를 만든다.

``` javascript
var arr = new Array('seoul', 'asuncion', 'vilnius', 'osaka', 'morelia');

function getRandomValueFromArray(haystack) {
    var index = Math.floor(haystack.length*Math.random());
    return haystack[index];
}

console.log(getRandomValueFromArray(arr));
```



방법2: prototype을 이용해서 확장

- 함수를 배열 객체에 포함시킨다.

- 배열에 내장된 메서드인 것처럼 사용할 수 있다.

- 장점
  - Array에 소속됨으로서 코드의 가독성이 높아진다.
  - 인자를 받지 않아 사용자가 신경써야 할 것이 적어진다.

```javascript
// 배열의 원형에 random 메서드 추가
Array.prototype.random = function() {
    // this: 배열 객체
    var index = Math.floor(this.length*Math.random());
    return this[index];
}

var arr = new Array('seoul', 'asuncion', 'vilnius', 'osaka', 'morelia');
console.log(arr.random())
```

> 함수 네이밍 => 이제 배열 객체의 메서드를 정의할 때와 독립적인 함수로 정의할 때의 함수 이름이 다르다. 독립적인 함수(물론 이 함수도 전역객체의 메서드이다.)일 때는 함수의 기능을 누가 봐도 알 수 있도록(배열에서 랜덤하게 요소를 꺼낸다는 기능) 명확히 해야 한다. 그러나 배열의 메서드가 되면 어차피 배열 객체 안에 소속되어 있으니까 굳이 배열 관련 내용을 함수 이름에 적지 않아도 괜찮아진다.