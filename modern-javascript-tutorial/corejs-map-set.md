## 맵과 셋

- 객체: 키가 있는 컬렉션을 저장한다.
- 배열: 순서가 있는 컬렉션을 저장한다.

### Map

키가 있는 데이터를 저장한다는 점에서 객체와 유사하지만, **키에 다양한 자료형을 허용한다**는 점에서 차이가 있다. 

- `map.set(key, value)`
- `map.get(key)`
- `map.has(key)`: 존재여부 반환
- `map.delete(key)`
- `map.clear()`: 모든 요소 제거
- `map.size()`

```js
let map = new Map();
map.set('1', 'str1'); // 문자형 키
map.set(1, 'num'); // 숫자형 키
map.set(true, 'bool1'); //불린형 키

alert(map.get(1)); // 'num1'
alert(map.get('1')); // 'str1'

alert(map.size); // 3
```

**맵은 객체와 달리 키를 문자형으로 변환하지 않는다**. **키에는 자료형 제약이 없다.**

**맵은 객체를 키로 사용할 수 있다.** `객체`는 객체 키를 사용할 수 없다.

```js
let john = { name: "John" };
let visitsCountMap = new Map();
visitsCountMap.set(john, 123);
alert( visitsCountMap.get(john) ); //123
```



```js
let john = { name: "John" };
let visitsCountObj = {};
visitsCountObj[john] = 123;
alert (visitsCountObj["[object Object]"]); //123
```

`visitsContObj`는 객체이기 때문에 모든 키를 문자형으로 변환시킨다. 이 과정에서 `john`은 문자형으로 변환되어 `"[object Object]"`가 된다.


### 맵이 키를 비교하는 방식

맵은 `SameValueZero`라고 불리는 알고리즘을 사용해 값의 등가 여부를 확인한다. 이 알고리즘은 일치 연산자 `===`와 유사하지만 `NaN`과 `NaN`을 같다고 취급하는 것에서 일치 연산자와 차이가 있다. 따라서 맵에는 `NaN`도 키로 쓸 수 있다. 

### 체이닝

`map.set`을 호출할 때마다 맵 자신이 반환된다. 이를 이용하면 `map.set`을 **체이닝(chaining)**할 수 있다.

```js
map.set('1', 'str1').set(1, 'num1').set(true, 'bool1');
```



### 맵의 요소에 반복 작업하기

- `map.keys()`: 각 요소의 키를 모은 **반복 가능한(iterable, 이터러블)** 객체를 반환한다.
- `map.values()`: 각 요소의 값을 모은 이터러블 객체를 반환한다.
- `map.entries()`: 요소의 `[키, 값]`을 한 쌍으로 하는 이터러블 객체를 반환한다. 이 이터러블 객체는 `for...of` 반복문의 기초로 쓰인다.



```js
let recipeMap = new Map( [
  ['cucumber', 500],
  ['tomatoes', 350],
  ['onion', 50]
] );

// 키 순회
for (let vegetable of recipeMap.keys()) {
  alert(vegetable); // cucumber, tomatoes, onion
}

// 값 순회
for (let amount of recipeMap.values()) {
  alert(amount); // 500, 350, 50
}

for (let entry of recipeMap) {
  alert(entry); // cucumber,500
}
```

> 맵은 삽입 순서를 기억하고 값이 삽입된 순서대로 순회를 실시한다. `객체`가 프로퍼티 순서를 기억하지 못하는 것과는 다르다.

`맵`은 `배열`과 유사하게 내장 메서드 `forEach`도 지원한다.

```js
// 각 (키, 값) 쌍을 대상으로 함수를 실행
recipeMap.forEach( (value, key, map) => {
  alert(`${key}: ${value}`);
});
```



### `Object.entries`: 객체를 맵으로 바꾸기

각 요소가 키-값 쌍인 배열이나 이터러블 객체를 초기화 용도로 `맵`에 전달해 새로운 `맵`을 만들 수 있다.

```js
let map = new Map([
  ['1', 'str1'],
  [1, 'num1'],
  [true, 'bool1']
]);

alert(map.get('1')); // str1
```

평범한 객체를 가지고 `맵`을 만들고 싶다면 내장 메서드 `Object.entries(obj)`를 활용해야 한다. 이 메서드는 **객체의 키-값 쌍을 요소(`[key, value]`)로 가지는 배열을 리턴한다.** 

```js
let obj = {
  name: "John",
  age: 30
};

let map = new Map(Object.entries(obj));
alert ( map.get('name') ); // John
```

`Object.entries`를 사용해 객체 `obj`를 배열 `[["name", "John"], ["age", 30]]`로 바꾸고, 이 배열을 이용해 새로운 `맵`을 만들어보았다.



### `Object.fromEntries`: 맵을 객체로 바꾸기

 `Object.fromEntries` 메서드는 각 요소가 `[키, 값]` 쌍인 배열을 객체로 바꿔준다.

```js
let prices = Object.fromEntries([
  ['banana', 1],
  ['orange', 2],
  ['meat', 4]
]);

// prices = { banana: 1, orange: 2, meat: 4};
```

자료가 `맵`에 저장되어 있는데, 서드파티 코드에서 자료를 개체 형태로 넘겨받길 원할 때 이 방법을 사용할 수 있다.

```js
let map = new Map();
map.set('banana', 1);
map.set('orange', 2);
map.set('meat', 4);

let obj = Object.fromEntries(map.entries()); // 맵을 일반 객체로 변환 
// let obj = Object.fromEntrie s(map); => .entries 생략
```

`map.entries()`를 호출하면 맵의 **`[키, 값]`을 요소로 가지는 이터러블을 반환**한다. 

다음과 같이 `.entries()`를 생략할 수도 있다.

```js
 let obj = Object.fromEntries(map);
```

### Set

**중복을 허용하지 않는** 값을 모아놓은 특별한 컬렉션이다. **셋에 키가 없는 값**이 저장된다.

- `new Set(iterable)`: 셋을 만든다. `이터러블` 객체를 전달받으면 (대개 배열을 전달받음) 그 안의 값을 복사해 셋에 넣어준다.
- `set.add(value)`
- `set.delete(value)`
- `set.has(value)`
- `set.clear()`
- `set.size`

셋 내 동일한 값(value)이 있다면 `set.add(value)`을 호출하더라도 반응이 없을 것이다. 한 방문자가 여러번 방문해도 방문자를 중복해서 기록하지 않겠다고 할 때 적합한 자료구조가 `셋`이다

중복 값 여부는 배열 메서드인 `arr.find`를 이용해 확인할 수 있다. 그러나 `arr.find`는 배열 내 요소 전체를 뒤져 중복 값을 찾기 때문에, 셋보다 성능 면에서 떨어진다. 

### 셋의 값에 반복 작업하기

`for..of`나 `forEach`를 사용하면 셋의 값을 대상으로 반복 작업을 수행할 수 있다.

```js
let set = new Set(["oranges", "apples", "bananas"]);
for (let value of set) alert(value);
set.forEach((value, valueAgain, set) => {
  alert(value);
});
```

- 리틀 엔디언, 빅엔디언 개념 정리
- modern js: 오늘 한 내용 (`map, set, weakmap, weakset)
- passport. -> auth



- 원스레드에 비동기 방식: nodeJS 특이한 경우.
- async 를 쓰는 거 자체가 이상(특이)
- 자바스크립트는 지원하는 브라우저에 대해서.. 

