---
title: "[JS] 생활코딩 #23: 함수와 this"
categories: JavaScript
tags: [ JavaScript ]
toc: true
---

# this

- `this`는 함수 내에서 함수 호출 맥락(context)를 의미한다. 
- 함수를 어떻게 호출하느냐에 따라서 this가 가리키는 대상이 달라진다.
- 함수와 객체의 관계가 느슨한 자바스크립트에서 `this`는 이 둘을 연결시켜주는 실질적인 연결점 역할을 한다.



## 메서드 호출

```javascript
var o = {
    func : function() {
        if(o === this) {
            document.write("o === this");
        }
    }
}
o.func(); // o === this
```

객체의 소속인 메서드의 this는 해당 객체를 가리킨다.

## 함수 호출

```java
function func() {
    if(window == this) {
        document.write("window === this");
    }
}
func(); // window === this
```

함수의 소속을 따로 지정하지 않으면 해당 함수는 전역객체의 메서드가 된다. 따라서 전역객체의 소속인 메서드의 this는 해당 전역 객체를 가리키게 된다. 

## 생성자 호출

- 생성자의 `this`는 생성자로 만들어질 객체를 가리킨다.

```javascript
var funcThis = null;

function Func() {
    funcThis = this;
}

// 일반 함수 호출
var o1 = Func();
if (funcThis === window) {
    document.write('window <br />'); // window
}

// 생성자 호출
var o2 = new Func();
if (funcThis === o2) {
    document.write('o2 <br />'); // o2
}
```

- 생성자가 실행되기 전까지는 객체는 변수에도 할당될 수 없기 때문에 **`this`가 아니면 객체에 대한 어떠한 작업도 할 수 없다.**

```javascript
function Func() {
    document.write(o);
}
var o = new Func(); // undefined
```



## this 값 제어

- 함수의 메서드인 `apply()`, `call()`을 이용하면 this의 값을 제어할 수 있다.
- 어떤 객체 소속의 메서드로 사용할 것인지 원하는 객체를 인자로 넣는다. 그러면 `this`는 `apply`의 인자와 같기 때문에 결국 this의 값을 제어한 것과 같은 효과를 얻을 수 있다!

```javascript
var o = {}
var p = {}
function func() {
    switch(this) {
        case o:
            document.write('o<br />');
            break;
        case p:
            document.write('p<br />');
            break;
        case window:
            document.write('window<br />');
            break;
    }
}
func(); //window
func.apply(o); //o
func.apply(p); //p
```





