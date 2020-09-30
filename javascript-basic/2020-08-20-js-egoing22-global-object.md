---
title: "[JS] 생활코딩 #22: 전역객체"
categories: JavaScript
tags: [ JavaScript ]
toc: true
---

## 전역객체란?

- 전역객체(global object)는 특수한 객체이다.
- 모든 객체는 전역객체의 프로퍼티이다.
- 모든 전역변수와 함수는 `window` 객체의 프로퍼티이다.
- 객체를 명시하지 않으면 암시적으로 `window`의 프로퍼티로 간주된다.

```javascript
function func() {
    alert('Hello?');
}
func(); //Hello?
window.func(); //Hello?
```

```javascript
var o = {'func':function(){
    alert("Hello?");
}}
o.func();
window.o.func();
```

## 전역객체 API

- ECMAScript에서는 전역객체의 API를 정의해두었다. 
  - 그 외의 API는 호스트 환경에서 필요에 따라서 추가로 정의하고 있다.
  - ex) 웹 브라우저 자바스크립트에서는 alert()이라는 전역객체의 메서드가 존재하지만 node.js에서는 존재하지 않는다.
- 호스트환경에 따라 전역객체의 이름이 다르다.
  - 웹브라우저 전역객체: `window`
  - node.js 전역객체: `global`