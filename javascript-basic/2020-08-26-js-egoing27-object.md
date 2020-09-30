---
title: "[JS] 생활코딩 #27: Object"
categories: JavaScript
tags: [ JavaScript ]
toc: true
---

# Object

## Object란?

- 객체의 가장 기본적인 형태를 가지고 있는 객체
- 아무것도 상속받지 않는 순수한 객체
- 자바스크립트에서 값을 저장하는 기본적인 단위
- 자바스크립트의 모든 객체는 Object 객체를 상속받는다. 따라서 모든 객체는 Object 프로퍼티를 가지고 있다.
- Object.prototype은 모든 객체가 사용할 수 있는 기능이다. 
  - 모든 객체가 공통적으로 사용해야 할 기능이 있다면 Object.prototype에 기능을 추가함으로써 목적을 달성할 수 있다.

 

## Object API 사용법

```java
//Object.keys()
var arr = ["a", "b", "c"];
console.log('Object.keys(arr)', Object.keys(arr));

//Object.prototype.toString();
var o = new Object();
console.log('o.toString()', o.toString());

var a = new Array(1,2,3);
console.log('a.toString()', o.toString());
```

### Object.메서드()

- 사용법: `Object.메서드()`
  - 예시) `Object.keys(arr)`
  - 여기서 Object: **생성자 함수**
- 정의: Object.메서드명 = function(){}
  - 예시) Object.keys = function(){}

### Object.prototype.메서드()

- 모든 객체가 사용할 수 있는 메서드

- 사용법: `객체식별자.메서드()`
  - 예시) `o.toString()`
- 정의: `Object.prototype.메서드명 = function(){}`

- `new Object()` 명령어로 생성자를 호출하면 객체가 생성된다. 이때 이 객체는 Object.prototype 속성에 저장되어 있는 객체를 원형으로 한다. 따라서 이 객체는  당연히 `toString()`를 호출할 수 있다. 이때 `toString()`은 이 객체의 메서드로써 사용이 된다. 
- 그런데 Object는 모든 객체의 부모이다. 따라서 어떤 객체를 생성하든 Object.prototype에 정의되어 있는 메서드를 사용할 수 있다.

> 생성자 함수는 객체이니 속성을 가지고 있는데, 여기에 prototype이라는 특수한 속성이 있다. 이 prototype(원형)에 정의되어 있는 함수는 이 객체의 자식객체에서 사용할 수 있다. prototype에 정의되어 있지 않은 메서드는 자식객체에서 사용할 수 없다.





## Object 확장

- Object 객체를 확장하면 모든 객체가 접근할 수 있는 API를 만들 수 있다

```javascript
Object.prototype.contain = function(monica) {
    // 메서드 안에서의 this는 메서드가 소속되어 있는 객체
    for(var name in this) {
        if (this[name] === monica) {
            return true;
        }
    }
    return false;
}

var o = {'name':'egoing', 'city':'seoul'}
console.log(o.contain('egoing')); //true
var a = ['egoing', 'leezhe', 'grapittie'];
console.log(a.contain('leezhe')); //true
```



## Object 확장의 위험성

- Object 객체를 확장하면 모든 객체에 영향을 주기 때문에 확장하지 않는 것이 바람직하다.

```javascript
for(var name in o) {
    console.log(name);
}

for(var name in a) {
    console.log(name);
}

// 결과
// name city contain
// 0 1 2 contain

// 확장한 프로퍼티 contain이 포함되어 있다. 
```

- 객체가 기본적으로 가지고 있을 것으로 예상하고 있는 객체 외에 다른 객체를 가지고 있는 것은 개발자들에게 혼란을 준다. 
- 해결책:  `Object.prototype.hasOwnProperty()` 메서드를 사용한다.
  - 프로퍼티의 해당 객체의 소속인지를 체크해 볼 수 있다.
  - 인자로 전달된 속성의 이름이 객체의 속성인지 여부를 판단한다. 
  - 만약 prototype으로 상속 받은 객체라면 false가 된다.

```javascript
for(var name in o)
    if (o.hasOwnProperty(name))
    	console.log(name);

for(var name in a)
    if (a.hasOwnProperty(name))
        console.log(name);

// 결과
// name city
// 0 1 2

// 확장한 프로퍼티 contain이 포함되지 않았다.
```

