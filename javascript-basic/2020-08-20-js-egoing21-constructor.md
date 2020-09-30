---
title: "[JS] 생활코딩 #21: 생성자와 new"
categories: JavaScript
tags: [ JavaScript ]
toc: true
---

# 생성자와 new 

- 자바스크립트는 `Prototype-based programming` 언어이다.

> prototype-based programming이란? 객체지향 프로그래밍의 한 갈래이다. 클래스가 없고, 클래스 기반 언어의 상속과 달리 객체를 원형(프로토타입)으로 하여 복제의 과정을 통해 객체의 동작 방식을 다시 사용할 수 있다. 클래스리스, 프로토타입 지향, 인스턴스 기반 프로그래밍이라고도 한다.

## 객체를 만드는 방법

객체 만드는 방법1: 만드는 과정 분산

```javascript
var person = {}
person.name = 'egoing';
person.introduce = function() {
    return 'My name is ' + this.name;
}
document.write(person.introduce());
```

객체 만드는 방법2: 정의할 때 값을 셋팅

```javascript
var person = {
    'name' : 'egoing',
    'introduce' : function() {
        return 'My name is ' + this.name;
    }
}
document.write(person.introduce());
```

그렇다면 같은 방법으로 **여러 개의 객체**를 만들 때는 어떻게 해야 할까?

```javascript
var person1 = {
    'name' : 'egoing',
    'introduce' : function() { return 'My name is ' + this.name}
}
var person2 = {
    'name' : 'monica',
    'introduce' : function() { return 'My name is ' + this.name}
}
```

위와 같이 객체의 정의를 반복해야 한다. 

어떻게 하면 객체의 구조를 재활용할 수 있을까? => 생성자를 사용하자!



## 생성자와 new

- 생성자(constructor)는 객체를 만드는 역할을 하는 함수이다.
- 자바스크립트에서 함수는 단순히 재사용 가능한 로직의 묶음이 아니라 **객체를 만드는 창조자**라고 할 수 있다. 
- 함수를 호출할 때 **new**를 붙이면 **새로운 객체를 만든 후에 이를 리턴한다.** 

```javascript
function Person(){}

// new 없이 함수 호출
var p0 = Person(); 
console.log(p0) // undefined

// new와 함께 함수 호출
var p0 = new Person(); 
console.log(p0) // Person {}
// 비어있는 객체를 만들고 객체를 반환하였다.
```

위의 코드에서 볼 수 있듯, 생성자가 따로 있는 게 아니라 new를 붙여서 호출하면 그 함수는 객체를 생성하는 생성자가 된다!

```javascript
function Person(){}
var p = new Person();
p.name  = 'egoing';
p.introduce = function() {
    return 'My name is ' + this.name;
}
document.write(p.introduce);
```

위의 코드와 같이 빈 객체를 만들고 변수와 메서드를 정의할 수 있다.

```javascript
function Person(){}

var p1 = new Person();
p1.name = 'egoing';
p1.introduce = function() {
    return 'My name is ' + this.name;
}
document.write(p1.introduce() + "<br />");

var p2 = new Person();
p2.name = 'egoing';
p2.introduce = function() {
    return 'My name is ' + this.name;
}
document.write(p2.introduce());
```

여러 사람을 위한 객체를 만든다면 다음과 같이 코드를 작성해야 한다. 이 방법으로는 생성자를 사용하지 않았을 때랑 별반 다를 것이 없다.

```javascript
function Person(name) {
    this.name = name;
    this.introduce = function() {
        return 'My name is ' + this.name;
    }
}
var p1 = new Person('egoing');
document.write(p1.introduce() + "<br />");

var p2 = new Person('monica');
document.write(p2.introduce() + "<br />");
```

생성자를 제대로 쓰기 위해서는 **생성자 내에서 객체의 프로퍼티를 정의**해야 한다! 이러한 작업을 **초기화**라고 한다. 이를 통해서 코드의 재사용성이 높아졌다. 

> 생성자 함수는 일반 함수와 구분하기 위해서 첫글자를 대문자로 표시한다.



## 자바스크립트 생성자의 특징

- 일반적인 객체지향 언어: 클래스 소속인 생성자가 클래스(설계도)에 따라 변수와 메서드의 그룹인 객체를 생성한다.
- 자바스크립트: 객체를 만드는 주체는 함수이다. 