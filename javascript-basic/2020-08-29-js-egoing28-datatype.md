---
title: "[JS] 생활코딩 #28: 데이터 타입"
categories: JavaScript
tags: [ JavaScript ]
---

# 데이터 타입

## 데이터 타입의 종류

- 객체
- 객체 아닌 것: 원시 데이터 타입(primitive type)
  - 숫자
  - 불리언(true/false)
  - null
  - undefined



## 래퍼 객체

- 자바스크립트는 원시 데이터형을 객체처럼 다룰 수 있기 위한 객체, 래퍼 객체를 제공한다.
- 원지 데이터타입과 관련하여 필요한 기능을 객체지향적으로 제공할 수 있어진다. 
- 종류: `String`, `Number`, `Boolean` (`null`, `undefined` X)
- 내부적으로 는 원시 데이터 타입이지만 관련된 작업을 하려고 할 때 자바스크립트는 임시로 해당 객체를 만드래고 사용이 끝나면 제거한다.



### String 래퍼 객체

```js
// 객체: 메소드 사용 가능
var str = 'coding';
console.log(str.length);    //6
console.log(str.charAt(0)); //"/c"
```

- 그러나 내부적으로 문자열은 원시 데이터 타입이고 문자열과 관련된 어떤 작업을 하려고 할 때 자바스크립트는 임시로 문자열 객체를 만들고 사용이 끝나면 제거한다. 

```js
var str = 'coding';
str.prop = 'everyday'; 
// 내부적으로 String 객체가 생성되어 prop 속성 저장 후 제거됨
console.log(str.prop); //undefined!
```

- `str.prop`을 하는 순간에 자바스크립트 내부적으로 String 객체가 만들어진다. prop 프로퍼티는 이 객체에 저장되고 이 객체는 곧 제거된다. 그렇기 때문에 prop이라는 속성이 저장된 객체는 존재하지 않게 된다(`undefined`). 이 특징은 일반적인 객체 동작 방법과는 다르다.

