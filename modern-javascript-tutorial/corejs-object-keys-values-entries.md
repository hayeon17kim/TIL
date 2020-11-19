## `Object.keys`, `values`, `entries`

`keys()`, `values()`, `entries()`는 `Map`, `Set`, `Array`에 적용할 수 있다.

일반 객체에는 다음과 같은 메서드를 사용할 수 있다.

- `Object.keys(obj)`: 키가 담긴 배열을 반환한다.
- `Object.values(obj)`: 값이 담긴 배열을 반환한다.
- `Object.entries(obj)`: `[key, value]` 쌍이 담긴 배열을 반환한다.

`Map`, `Set`, `Array`에 적용하는 메서드와 객체에 적용하는 메서드의 차이를 맵을 기준으로 비교하면 다음과 같다.

|           | 맵            | 객체                                   |
| --------- | ------------- | -------------------------------------- |
| 호출 문법 | `map.keys()`  | `obj.keys()`가 아닌 `Object.keys(obj)` |
| 반환 값   | iterable 객체 | '진짜' 배열                            |

유연성 때문에 `obj.keys()`가 아닌 `Object.keys(obj)`를 호출한다. 이 이유는 유연성 때문이다. 자바스크립트에서는 복잡한 자료구조 전체가 객체에 기반한다. 그러다 보니 객체 `data`에 자체적으로 메서드 `data.values()`를 구현해 사용하는 경우가 있다. 이렇게 커스텀 메서드를 구현한 상태여도 `Object.valuse(data)`와 같이 다른 형태로 메서드를 호출할 수 있으면 커스텀 메서드와 내장 메서드 둘 다 사용할 수 있다. 

두 번째 차이는 메서드 `Object.*`를 호출하면 하위 호환성을 위해 iterable 객체가 아닌 **객체의 한 종류인 배열을 반환**한다는 점이다.

```js
let user = {
  name: "John",
  age: 30
};
```

- `Object.keys(user) = ["name", "age"]`
- `Object.values(user) = ["John", 30]`
- `Object.entries(user) = [["name", "John"], ["age", 30]]`

> Object.keys, values, entries는 심볼형 프로퍼티를 무시한다. 대개 심볼형 키를 연산에 포함시키지 않는 것이 좋지만, 필요한 경우 `Object.getOwnPropertySymbols`(심볼형 키만 배열 형태로 반환해주는 메서드), `Reflect.ownKeys(obj)`(모든 키를 배열 형태로 변환해주는 메서드)를 사용할 수 있다. 

### 객체 변환

객체에 배열 전용 메서드를 사용하는 방법

1. `Object.entries(obj)`를 사용해 객체의 키-값 쌍을 요소로 갖는 배열을 얻는다.
2. 배열에 배열 전용 메서드를 적용한다.
3. 적용 후 반환된 배열에 `Object.fromEntries(array)`를 적용해 배열을 다시 객체로 되돌린다.

```js
let prices =W {
  banana: 1,
  orange: 2,
  meat: 4,
};

let doublePrices = Object.fromEntries(
  Object.enries(prices).map(([key, value]) => [key, value * 2])
)

alert(doublePrices.meat);
```

> map 메서드: https://www.zerocho.com/category/JavaScript/post/5acafb05f24445001b8d796d

### 과제

**Q1. 프로퍼티 값 더하기**

```js
let salaries = {
  "John": 100,
  "Pete": 300,
  "Mary": 250
};

function sumSalaries(obj) {
  let result = 0;
  for (let value of Object.values(obj)) {
    result += value;
  }
  return result;
}

alert( sumSalaries(salaries) ); // 650
```

**Q2. 프로퍼티 개수 세기**

```js
let user = {
  name: 'John',
  age: 30
};

function count(obj) {
  return Object.keys(obj).length;
}

alert( count(user) ); // 2
```