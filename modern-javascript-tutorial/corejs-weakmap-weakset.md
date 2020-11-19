## weakmap과 weakset

자료구조 구성요소(객체의 프로퍼티, 배열의 요소, 맵이나 셋 구성 요소)도 자신이 속한 자료구조가 메모리에 남아있는 동안 **도달 가능한 값으로 취급**되어 **메모리에서 삭제되지 않는다.** (가비지 컬렉터의 대상이 되지 않는다)

> 자바스크립트 엔진은 도달 가능한 값을 메모리에 유지한다.

```js
let john = { name: "John" };

let map = new Map();
map.set(john, "...");

john = null; // 참조를 null로 덮어씀

// map.keys()를 이용하면 해당 객체를 얻는 것도 가능하다.
for (let obj of map.keys()) {
  alert(JSON.stringify(obj)); //{"name":"John"}
}

alert(map.size); // 1
```

`위크맵`은 일반 맵과 달리 키로 쓰인 객체가 가비지 컬렉션의 대상이 된다. 

### 위크맵

`위크맵`의 키는 반드시 객체여야 한다. 

```js
let weakMap = new WeakMap();
let obj = {};
weakMap.set(obj, "ok");
weakMap.set("test", "Whoops"); // Error: Invalid value used as weak map key
```

위크맵의 키로 사용된 객체를 **참조하는 것이 아무것도 없다면** 해당 객체는 **메모리와 위크맵에서 자동으로 삭제**된다.

```js
let john = { name: "John" };
let weakMap = new WeakMap();
weakMap.set(john, "...");
john = null; // 참조를 덮어씀
// john을 나타내는 객체는 이제 메모리에서 지워진다.
```

위크맵은 **반복 작업**과 **`keys()`, `values()`, `entries()`** 메서드를 **지원하지 않는다.** 따라서 위크맵에서는 **키나 값 전체를 얻는 게 불가능**하다.

- `weakMap.get(key)`
- `weakMap.set(key, value)`
- `weakMap.delete(key)`
- `weakMap.has(key)`

가비지 컬렉션의 동작 방식 때문에 적은 메서드만 제공한다. 객체는 모든 참조를 잃게 되면 자동으로 가비지 컬렉션의 대상이 되는데, **가비지 컬렉션의 동작 시점은 정확히 알 수 없다**. 가비지 컬렉터는 한 번에 메모리를 청소할 수도 있고, 부분 부분 메모리를 청소할 수도 있다. 따라서 **위크맵의 요소(키/값) 전체를 대상으로 무언가를 하는 메서드는 동작 자체가 불가능하다.**

#### 유스케이스: 추가데이터

**부차적인 데이터를 저장**할 곳이 필요할 때 사용할 수 있다. 서드파티 라이브러리와 같은 **외부 코드에 *속한* 객체를 가지고 작업**을 한다면, 이 객체에 데이터를 추가해줘야 하는데, **추가해 줄 데이터는 객체가 살아있는 동안에만 유효한 상황**에 사용한다. 이럴 때 `위크맵`에 원하는 데이터를 저장하고, 키는 객체를 사용하면 된다. 이렇게 하면 **객체가 가비지 컬렉션의 대상이 될 때, 데이터도 함께 사라지게 된다.**

> 서드파티: 편하고 효율적인 개발을 위해 프로그래밍 개발과 개발자 사이의 중간다리 역할을 하는 플러그인, 라이브러리, 프레임워크를 서드파티로 볼 수 있다.

```js
weakMap.set(john, "비밀문서");
// john이 사망하면, 비밀문서는 자동으로 파기된다.
```

`맵`을 사용해 사용자의 방문 횟수를 세보자.

```js
//visitsCount.js
let visitsCountMap = new Map();

// 사용자가 방문하면 방문 횟수를 늘려준다.
function countUser(user) {
  let count = visitsCountMap.get(user) || 0;
  visitsCountMap.set(user, count + 1);
}
```

아래는 John 이라는 사용자가 방문했을 때, 어떻게 방문 횟수가 증가하는 지 보여준다.

```js
let john = { name: "John" };
countUser(john); // John의 방문 횟수를 증가시킨다.
// John의 방문 횟수를 셀 필요가 없어지면 아래와 같이 john을 null로 덮어쓴다.
john = null;
```

`john`을 나타내는 객체는 `visitCountMap`의 키로 사용되고 있어 메모리에서 삭제되지 않는다. 특정 사용자를 나타내는 객체가 메모리에서 사라지면 해당 객체에 대한 정보도 손수 지워줘야 한다. 이렇게 하지 않으면 `visitsCountMap`가 차지하는 메모리 공간이 한없이 커질 것이다. 수동으로 비워주는 것은 꽤 골치 아픈데, `위크맵`을 사용해 사용자 방문 횟수를 저장하면 `visitsCountMap`을 수동으로 청소해줄 필요가 없다. `위크맵`의 **키(`john`)에 대응하는 값**(john의 방문 횟수)도 **자동으로 가비지 컬렉션의 대상이 된다.**

#### 유스케이스: 캐싱

캐싱은 **시간이 오래 걸리는 작업의 결과**를 **저장**해서 **연산 시간과 비용을 절약**해주는 기법이다. 동일한 함수를 여러 번 호출해야 할 때, 최초 호출 시 반환된 값을 저장해 놓았다가 그 다음엔 함수 호출 대신 저장된 값을 사용하는 것이 캐싱의 실례이다.

```js
// 📁 cache.js
let cache = new Map();
// 연산을 수행하고 그 결과를 맵에 저장합니다.
function process(obj) {
  if (!cache.has(obj)) {
    let result = /* 연산 수행 */ obj;
    cache.set(obj, result);
  }
  return cache.get(obj);
}

// 함수 process()를 호출해봅시다.
// 📁 main.js
let obj = {/* ... 객체 ... */};
let result1 = process(obj); // 함수를 호출합니다.

// 동일한 함수를 두 번째 호출할 땐,
let result2 = process(obj); // 연산을 수행할 필요 없이 맵에 저장된 결과를 가져오면 됩니다.
// 객체가 쓸모없어지면 아래와 같이 null로 덮어씁니다.
obj = null;
alert(cache.size); // 1 (엇! 그런데 객체가 여전히 cache에 남아있네요. 메모리가 낭비되고 있습니다.)
```

`맵`을 사용할 때는 객체가 필요 없어져도 `cache`를 수동으로 청소해야 하는데, `위크맵`으로 교체하면 객체가 메모리에서 삭제될 때, 캐시에 저장된 결과(함수 연산 결과) 역시 메모리에서 자동으로 삭제된다. 

```js
// 📁 cache.js
let cache = new WeakMap();

// 연산을 수행하고 그 결과를 위크맵에 저장합니다.
function process(obj) {
  if (!cache.has(obj)) {
    let result = /* 연산 수행 */ obj;

    cache.set(obj, result);
  }

  return cache.get(obj);
}

// 📁 main.js
let obj = {/* ... 객체 ... */};

let result1 = process(obj);
let result2 = process(obj);

// 객체가 쓸모없어지면 아래와 같이 null로 덮어씁니다.
obj = null;

// 이 예시에선 맵을 사용한 예시처럼 cache.size를 사용할 수 없습니다.
// 하지만 obj가 가비지 컬렉션의 대상이 되므로, 캐싱된 데이터 역시 메모리에서 삭제될 겁니다.
// 삭제가 진행되면 cache엔 그 어떤 요소도 남아있지 않을겁니다.
```

### 위크셋

- 셋과 유사하지만 객체만 저장할 수 있다는 점이 다르다.
- 위크셋도 부차적인 데이터를 저장할 때 사용할 수 있다.
- 다만, 위크맵처럼 복잡한 데이터를 저장하지 않고, "예"나 "아니오" 같은 간단한 답변을 얻는 용도로 사용한다. 

```js
let visitedSet = new WeakSet();

let john = { name: "John" };
let pete = { name: "Pete" };
let mary = { name: "Mary" };

visitedSet.add(john); // John이 사이트를 방문합니다.
visitedSet.add(pete); // 이어서 Pete가 사이트를 방문합니다.
visitedSet.add(john); // 이어서 John이 다시 사이트를 방문합니다.

// visitedSet엔 두 명의 사용자가 저장될 겁니다.

// John의 방문 여부를 확인해보겠습니다.
alert(visitedSet.has(john)); // true

// Mary의 방문 여부를 확인해보겠습니다.
alert(visitedSet.has(mary)); // false

john = null;
```

이후 `alert(visitedSet.has(john))`을 실행해보니 `false`가 출력되었다. 

**객체**엔 **‘주요’ 자료**를, `위크맵`과 `위크셋`엔 **‘부수적인’ 자료**를 저장하는 형태로 위크맵과 위크셋을 활용할 수 있다. 객체가 메모리에서 삭제되면, (그리고 오로지 `위크맵`과 `위크셋`의 키만 해당 객체를 참조하고 있다면) 위크맵이나 위크셋에 **저장된 연관 자료들 역시 메모리에서 자동으로 삭제**됩니다.

### 과제

**Q1. ** '읽음' 상태인 메시지 저장하기

```js
let messages = [
  {text: "Hello", from: "John"},
  {text: "How goes?", from: "John"},
  {text: "See you soon", from: "Alice"}
];

let readMessages = new WeakSet();

readMessages.add(messages[0]);
readMessages.add(messages[1]);
readMessages.add(messages[2]);

readMessages.has(messages[1]); // true

messages.shift(); // 배열의 맨 앞에 값을 제거한다.
// 이제 readMessages에는 요소가 두 개만 남게 되었다.
```

위크셋은 반복 작업을 수행하는 메서드가 없지만, 배열에 저장된 모든 메시지를 대상으로 반복 작업을 수행해 해당 메시지가 위크셋에 저장되어있는지 화인하면 읽음 상태의 메시지를 한 번에 얻어올 수 있다. 

위크셋을 사용하지 않고 메시지 객체에 `message.isRead=true`같은 프로퍼티를 추가해도 메시지가 읽음 상태인지 확인할 수 있다. 그런데 messages와 메시지 객체는 외부 코드에서 관리하고 있기 때문에 이 방법은 권장되지 않는다. 심볼형 프로퍼티를 사용하면 충돌을 피할 수는 있다.

```js
let isRead = Symbol("isRead");
messages[0][isRead] = true;
```

이렇게 하면 서드파티 코드에서는 위에서 추가한 여분의 프로퍼티를 볼 수 없다. 그러나 위크셋을 쓰는 게 보다 건설적인 접근법이다. 

> 심볼을 이용하면 hidden 프로퍼티를 만들 수 있다. 심볼은 서드파티 코드에서 접근할 수 없기 때문에, 심볼을 사용하면 서드파티 코드가 모르게 객체에 식별자를 부여할 수 있다.

**Q2. ** 읽은 날짜 저장하기

```js
let messages = [
  {text: "Hello", from: "John"},
  {text: "How goes?", from: "John"},
  {text: "See you soon", from: "Alice"}
];

let readMap = new WeakMap();

readMap.set(messages[0], new Date(2017, 1, 1));
```



