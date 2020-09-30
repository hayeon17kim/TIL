---
title: "[JS] 생활코딩 #19: 함수호출"
categories: JavaScript
tags: [ JavaScript ]
toc: true
---

## 함수호출

```javascript
function sum(arg1, arg2) {
    return arg1 + arg2;
}

// 함수를 호출하는 기본적인 방법
sum(1,2);

// 함수를 호출하는 특별한 방법
sum.apply(null, [1,2]);
```

- 위에서 `sum` 함수는 `Function`이라는 객체의 인스턴스이다.
- 따라서 `sum`은 객체 `Function`이 가지고 있는 메서드 (`apply()`, `call()`)를 상속받고 있고, 호출할 수 있다.
- `apply()`
  - 첫 번째 인자: 함수가 실행될 맥락
  - 두 번째 인자: 함수(`sum()`)의 인자로 대입될 인자들의 배열

```console
> sum.apply
function apply() { [native code] }
```

- apply()는 native code이기 때문에 콘솔에서 소스 내용을 볼 수 없다.

## apply를 사용하는 구체적인 이유

: 실행 순간에 다른 객체의 메서드인 것처럼 사용

![function-call](https://user-images.githubusercontent.com/50407047/90976088-2a30d880-e575-11ea-928f-40c4b671b696.jpg)

```javascript
// 다음과 같은 객체와 함수가 있다.
o1 = {val1:1, val2:2, val3:3}
o2 = {val1:10, val2:50, val3:100, v4:25}

function sum() { 
    var _sum = 0;
    for (name in this) {
        _sum += this[name];
    }
    return _sum;
}

alert(sum.apply(o1)); // 6
// o1.sum()와 같다.
alert(sum.apply(o2)); // 185
// o2.sum()와 같다.

// 즉 함수가 실행되는 그 순간에는 
// o1이라는 객체의 sum이라는 메서드가 되는 것이다.
```

- `sum.apply(o1)`는 함수 sum을 객체 o1의 메서드로 만들고 sum을 호출한 후에 sum을 삭제한다. (실행결과가 조금 다르지만)

```javascript
// 위의 코드의 실행 결과는 아래 코드와 같다.

function sum() {
    var _sum = 0;
    for (name in this) {
        if(typeof this[name] !== 'function')
            _sum += this[name];
        return sum;
    }
}

o1.sum = sum; // 함수 sum을 객체 o1의 메서드로 만든다.
alert(o1.sum()); // sum을 호출한다.
delete o1.sum(); // sum을 삭제한다.

// 이 방법이 불편할 경우 apply 메서드를 사용하여 호출하자.
```

- sum이 o1 소속의 메서드가 된다는 것의 의미
  - 호출되는 함수의 this값을 프로그래밍적으로 변경해서 마치 sum이 o1의 함수인 것처럼 사용할 수 있는 것이다.
  - 함수 sum에서 this의 값이 전역객체가 아니라 o1이 된다는 의미이다. 
  - 일반적인 객체지향 언어에서는 하나의 객체에 소속된 함수는 그 객체의 소유물이 된다. 하지만 JavaScript에서 함수는 독립적인 객체로서 존재하고, apply나 call 메서드를 통해서 다른 객체의 소유물인 것처럼 실행할 수 있다.
- 따라서 만약 apply의 첫 번째 인자로 null을 전달하면 apply가 실행된 함수 인스턴스는 전역객체(브라우저에서는 window)를 맥락으로 실행되게 된다. 




