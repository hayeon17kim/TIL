---
title: "[JS] 생활코딩 #24: 상속"
categories: JavaScript
tags: [ JavaScript ]
toc: true
---

# 상속

## 상속이란?

- 객체: 연관된 로직들로 이루어진 작은 프로그램
- 상속: 객체의 로직을 그대로 물려 받는 또 다른 객체를 만들 수 있는 기능
- 상속은 기존의 로직을 수정하고 변경해서 파생된 새로운 객체를 만들 수 있게 해준다.



## 상속의 사용법

객체의 로직을 물려 받는 또 다른 객체를 만들기

```javascript
function Person(name) {
    this.name = name;
}

Person.prototype.name = null;
Person.prototype.introduce = function() {
    return 'My name is ' + this.name;
}

function Programmer(name) {
    this.name = name;
}
Programmer.prototype = new Person();

var p1 = new Programmer('egoing');
document.write(p1.introduce() + "<br />");
```

`Programmer` 생성자의 `prototype`과 `Person`의 객체를 연결했더니 Programmer 객체도 `Person`의 메서드 `introduce`를 사용할 수 있게 되었다. Programmer가 Person의 **기능**을 상속하고 있는 것이다. 

## 기능의 추가

기존의 로직에 기능을 추가하여 새로운 객체를 만들기

```javascript
function Person(name) {
    this.name = name;
}
Person.prototype.name = null;
Person.prototype.introduce = function() {
    return 'My name is ' + this.name;
}

function Programmer(name) {
    this.name = name;
}
Programmer.prototype = new Person();
Programmer.prototype.coding = function(){
    return "hello world";
}

var p1 = new Programmer('egoing');
document.write(p1.introduce() + "<br />");
document.write(p1.coding() + "<br />");
```

`Programmer`는 `Person`의 기능을 가지고 있으면서 `Person`이 **가지고 있지 않은 기능인 메서드 `coding`을 가지고 있다.**