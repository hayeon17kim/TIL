## iterable 객체

`for...of`를 사용할 수 있는 객체를 **이터러블**이라고 한다. 

### Symbol.iterator

이터러블에는 메서드 `Symbol.iterator`가 반드시 구현되어 있어야 한다. 

#### 이터러블 객체만들어보기

```js
let range = {
  from: 1,
  to: 5
};

// 1. for..of 최초 호출 시, Symbol.iterator가 출력된다. 
range[Symbol.iterator] = function() {
  // Symbol.iterator는 이터레이터 객체를 반환한다.
  // 2. 이후 for..of는 반환된 이터레이터 객체만을 대상으로 동작하는데, 이때 다음 값도 정해진다. 
  return {
    current: this.from,
    last: this.to,
    
    // 3. for..of 반복문에 의해 반복마다 next()가 호출된다.
    next() {
      // 4. next()는 값을 객체 {done:.., value:..} 형태로 반환해야 한다.
      if (this.current <= this.last) {
        return { done: false, value: this.current++ };
      } else {
        return { done: true };
      }
    }
  };
};

// 이제 의도했던 대로 동작한다.
for (let num of range) {
  alert(num); 1, 2, 3, 4, 5
}
```

- `obj[Symbol.iterator]`는 이터레이터 객체를 리턴한다. 이터레이터는 반복 과정을 처리한다.
- **이터레이터**에는 객체 `{done: Boolean, value: any}`을 반환하는 메서드 `next()`가 반드시 구현되어 있어야 한다. 여기서 `done:true`는 반복이 끝났음을 의미하고 그렇지 않은 경우에는 `value`가 다음 값이 된다. 

이터러블 객체의 핵심은 관심사의 분리(Seperation of Concern, SoC)에 있다.

- `range`에는 `next()`가 없다.
- 대신 `range[Symbol.iterator]()`를 호출해서 만든 *이터레이터* 객체와 이 객체의 메서드 `next()`에서 반복에 사용될 값을 만들어낸다.

이렇게 하면 이터레이터 객체와 반복 대상인 객체를 분리할 수 있다. 

이터레이터 객체와 반복 대상 객체를 합치면 코드가 더 간단해진다.

```js
let range = {
  from: 1,
  to: 5,
  
  [Symbol.iterator]() {
    this.current = this.from;
    return this;
  },
  
  next() {
    if (this.current <= this.to) {
      return {done:false, value:this.current++};
    } else {
      return { done: true };
    }
  }
};
```

이제 `range[Symbol.iterator]()`가 객체 `range` 자체를 반환한다. 코드가 간단해져 좋다. 그러나 **두 개의 `for..of` 반복문을 하나의 객체에 동시에 사용할 수 없다는 것이 단점**이다. **이터레이터(객체 자신)가 하나뿐이어서 두 반복문이 반복 상태를 공유**하기 때문이다.

### iterator의 명시적 호출

메서드 `Symbol.iterator`는 `for..of`에 의해 자동으로 호출되는데, 개발자가 다음과 같이 **명시적으로 호출**할 수도 있다. 이터레이터를 명시적으로 호출하는 경우는 거의 없지만, 이 방법을 사용하면 `for..of`를 사용하는 것보다 *반복 과정을 더 잘 통제*할 수 있다는 장점이 있다. 

```js
let str = "Hello!";
// for (let char of str) alert(char);

let iterator = str[Symbol.iterator]();

while (true) {
  let result = iterator.next();
  if (result.done) break;
  alert(result.value); // 글자가 하나씩 출력된다.
}
```

### iterable과 유사 배열

- **iterable**
  - 메서드 `Symbol.iterator`가 구현된 객체 -> `for..of`를 사용할 수 있다.
  - 문자열이나 배열 같은 내장 이터러블에도 `Symbol.iterator`가 구현되어 있다.
  - 문자열 이터레이터는 서로게이트 쌍을 지원한다.
- **유사 배열(array-like)**
  - **인덱스와 `length` 프로퍼티**가 있어서 배열처럼 보이는 **객체**이다.

둘은 필요충분조건이 아니다.

```js
let arrayLike = {
  0: "Hello",
  1: "World",
  length: 2
}

// Symbol.iterator가 없으므로 에러 발생
for (let item of arraylike) {}
```

이터러블과 유사배열은 **대개 배열이 아니기** 때문에 `push`, `pop` 등의 메서드를 지원하지 않는다. 그럼 어떻게 하면 이터러블과 유사 배열에 배열 메서드를 적용할 수 있을까?

### `Array.from`

`Array.from`은 이터러블이나 유사 배열을 받아 *진짜* `Array`를 만들어준다. 이 과정을 거치면 유사 배열에 배열 메서드를 사용할 수 있다.

```js
let arrayLike = {
  0: "Hello", 
  1: "World",
  length: 2
};

let arr = Array.from(arrayLike);
alert(arr.pop()); //World
```

`Array.from`은 객체를 받아 이터러블이나 유사 배열인지 조사한다. 넘겨 받은 인수가 이터러블이나 유사 배열인 경우, 새로운 배열을 만들고 모든 요소를 새롭게 만든 배열로 복사한다.

```js
let arr = Array.from(range);
alert(arr); // 1,2,3,4,5
```

`Array.from`에는 매핑(mapping) 함수를 선택적으로 넘겨줄 수 있다. `Array.from(obj[, mapFn, thisArg])`

`mapFn`을 두 번째 인수로 넘겨주면 새로운 배열에 `obj`의 요소를 추가하기 전에 각 요소를 대상으로 `mapFn`을 적용할 수 있다. 새로운 배열에는 `mapFn`을 적용하고 반환된 값이 추가된다. 세 번째 인수 `thisArg`는 각 요소의 `this`를 지정할 수 있도록 해준다.

```js
let arr = Array.from(range, num => num * num);
alert(arr); // 1,4,9,16,25
```

`Array.from`은 **`str.split`과 달리, 문자열 자체가 가진 이터러블 속성을 이용해 동작**한다. 따라서 `for..of`처럼 **서로게이트 쌍**에도 제대로 적용된다. 

```js
let str = '𝒳😂';
let chars = Array.from(str); //𝒳,😂
```

 위 예시는 다음과 같이 동작한다.

```js
let str = '𝒳😂';
let chars = [];
for (let char of str) {
  chars.push(char);
}
alert(chars);
```

`Array.from`을 사용하면 서로게이트 쌍을 처리할 수 있는 `slice`를 직접 구현할 수도 있다. 

```js
function slice(str, start, end) {
  return Array.from(str).slice(start, end).join('');
}

let str = '𝒳😂';
console.log(str, 1, 2); //😂
console.log(str.slice(1,2)); //�
```





인덱스와 `length` 프로퍼티가 있는 객체는 **유사 배열**이라고 불린다. 유사 배열 객체에는 다양한 프로퍼티와 메서드가 있을 수 있는데, 배열 내장 메서드는 없다. 

대부분의 메서드는 *진짜* 배열이 아닌 이터러블이나 유사 배열을 대상으로 동작한다고 쓰여진 것을 볼 수 있다. 이 방법이 더 추상적이기 때문ㅇ디ㅏ.

`Array.from(obj[, mapFn, thisArg])`을 사용하면 이터러블이나 유사 배열인 `obj`를 진짜 `Array`로 만들 수 있다. 이렇게 하면 `obj`에도 배열 메서드를 사용할 수 있다. 선택 인수 `mapFn`와 `thisArg`는 각 요소에 함수를 적용할 수 있게 해준다. 

