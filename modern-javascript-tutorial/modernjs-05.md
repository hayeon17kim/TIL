## 5.1. 원시값의 메서드

- 문자열이나 숫자 같은 원시값을 다루어야 하는 작업이 많은데, 메서드를 활용하면 작업을 수월하게 할 수 있을 것이다. 그러나 원시값은 가능한 한 빠르고 가벼워야 한다.

- 자바스크립트는 원시값(문자열, 숫자 등)을 마치 객체처럼 다룰 수 있게 해준다. 'null'과 'undefined'를 제외한 원시값에 다양한 메서드를 호출할 수 있다. 그런데 자바스크립트 엔진은 내부 최적화가 잘 되어있어 메서드를 호출해도 많은 리소스를 쓰지 않는다. 

- 방법

  - 원시값은 원시값 그대로 남겨둬 단일 값 형태를 유지한다.
  - 문자열, 숫자, 불린, 심볼의 메서드와 프로퍼티에 접근할 수 있도록 언엉 차원에서 허용한다.
  - 이를 가능하게 하기 위해, 원시값이 메서드나 프로퍼티에 접근하려 하면 추가 기능을 제공해주는 특수한 객체 "원시 래퍼 객체(object wrapper)"를 만들어준다. 이 객체는 곧 삭제된다. 

- 래퍼 객체는 원시 자료형의 이름을 그대로 차용해 `String`, `Number`, `Boolean`, `Symbol`이라고 부른다. 래퍼 객체마다 제공하는 메서드 역시 다르다.

- 예시:

  ```js
  let str = "Hello";
  alert( str.toUpperCase() ); // HELLO
  ```

  - 문자열 `str`은 원시값이므로 원시값의 프로퍼티(`toUpperCase`)에 접근하는 순간 특별한 객체가 만들어진다. 이 객체는 문자열의 값을 알고 있고, `toUpperCase()`와 같은 유용한 메서드를 갖고 있다.
  - 메서드가 실행되고, 새로운 문자열이 반환된다(`alert` 창에 이 문자열이 출력된다.)
  - 특별한 객체는 파괴되고, 원시값 `str`만 남는다.

- `String/Number/Boolean`을 생성자로 사용하면 혼동이 일어나므로 지양하자.

  ```js
  alert (typeof 0); // "number"
  alert (typeof new Number(0)); // "object"
  ```

  ```js
  let zero = new Number(0);
  
  if (zero) {
    alert("zero는 객체이므로 조건문이 참이 된다.");
  }
  ```

  `new` 없이 사용하면 괜찮다.

- `null/undefined`는 래퍼 객체도, 메서드도 제공하지 않는다. 이런 면에서 "가장 원시적"이라고도 할 수 있다.

### 과제

**문자열에 프로퍼티를 추가할 수 있을까?**

```js
let str = "Hello";
str.test = 5; (*)
alert(str.test);
```

비 엄격모드이면 `undefined`가 출력되고, 엄격모드이면 에러가 발생한다.

- `str`의 프로퍼티에 접근하려 하면 래퍼 객체가 만들어진다.
- **엄격 모드에서는 래퍼 객체를 수정하려 할 때 에러가 발생한다.**
- 비 엄격모드에서는 에러가 발생하지 않고 프로퍼티 `test`가 추가된다. 그런데 **래퍼 객체는 바로 삭제되기 때문에** 마지막 줄이 실행될 땐 프로퍼티 `test`를 찾을 수 없다. 

<mark>원시값은 객체와 달리, 추가 데이터를 저장할 수 없다!!!</mark>

## 5.2. 숫자형

- 0이 많이 붙은 숫자는 다음과 같이 표현할 수 있다.

  - 0의 개수를 `'e'` 뒤에 추가한다. `123e6`은 0이 6개인 숫자, 123000000을 나타낸다.
  - `'e'` 다음에 음수가 오면, 음수의 절대값 만큼 10을 거듭제곱한 숫자로 주어진 숫자를 나눈다. `123e-6`은 `0.000123`을 나타낸다. 

- 진법을 이용할 수 있다.

  - 자바스크립트는 특별한 변환 없이 16진수(`0x`), 8진수(`0o`), 2진수(`0b`)를 바로 사용할 수 있게 지원한다.
  - `parseInt(str, base)`를 사용하면 `str`을 `base` 진수로 바꿔준다. (단, `2 <= base < 26`)
  - `num.toString(base)`는 숫자를 `base` 진수로 바꾸고, 이를 문자열 형태로 반환한다.

- `12pt`나 `100px`과 같은 값을 숫자로 변환할 수 있다.

  - `parseInt/parseFloat`을 사용하면 문자열에서 숫자만 읽고, 읽은 숫자를 에러가 발생하기 전에 반환해주는 '약한' 형변환을 사용할 수 있다.

- 부정확한 계산

  숫자는 0과 1로 이루어진 이진수로 변환되어 연속된 메모리 공간에 저장된다. 그런데 10진법을 사용하면 쉽게 표현할 수 있는 0.1, 0.2 같은 분수는 이진법으로 표현하면 무한 소수가 된다.

  0.1은 1을 10으로 나눈 수인 1/10이다. 10의 거듭제곱으로 나눈 값은 10진법에서는 잘 작동하지만, **3으로 나누게 되면 10진법에서 제대로 동작하지 않는다**. 같은 이유로 **2진법 체계에서는 2의 거듭제곱으로 나눈 값은 잘 동작하지만, 1/10과 같이 2의 거듭제곱이 아닌 값으로 나누게 되면 무한 소수가 되어버린다.**

  10진법에서 1/3을 정확히 나타낼 수 없듯이, 2진법을 사용해 0.1 또는 0.2를 정확하게 저장하는 방법은 없다. IEEE-754에서는 가능한 가장 가까운 숫자로 반올림하는 방법을 사용해 이 문제를 해결한다. 

- 소수를 처리하는 데 쓰이는 메서드

  `Math.floor`, `Math.ceil`, `Math.trunc`, `Math.round`, `num.toFixed(precision)`

- `isNaN`와 `isFinit`

  -  `Infinity`와 `-Infinity`, `NaN`은 숫자형에 속하지만 정상적인 숫자는 아니기 때문에, 정상적인 숫자와 구분하기 위한 특별한 함수가 존재한다.

  - `isNaN(value)`: 인수를 숫자로 변환한 다음 `NaN`인지 테스트함

    `NaN`은 `NaN` 자기 자신을 포함하여 그 어떤 결과값과도 갖지 않다. 따라서 동등비교연산이 아니라 이 메서드가 필요한 것이다.

    ```js
    alert(isNaN(NaN)); //true
    alert(isNaN("str")); // true
    alert( NaN === NaN ); // false
    ```

  - `isFinite(value)`: 인수를 숫자로 변환하고, 변환한 숫자가 `NaN/Infinity/-Infinity`가 아닌 일반 숫자인 경우 `true`를 반환함.

- `Object.is`와 비교하기

  두 가지 에지 케이스에서는 `===`보다 신뢰할 만한 결과를 보여준다.

  - `NaN`을 대상으로 비교할 때: `Object.is(NaN, NaN) === true`
  - `0`과 `-0`이 다르게 취급될 때: `Object.is(0, -0) === false`



## 5.3. 문자열

- 자바스크립트에서는 UTF-16을 사용해 문자열을 코딩한다.

- `\n`을 사용할 수 있다.

- `\u...`를 사용하면 해당 문자의 유니코드를 사용해 글자를 만들 수 있다.

- 문자열 내의 글자 하나를 얻으려면 대괄호 `[]`를 사용한다.

- 부분 문자열을 얻으려면 `slice`나 `substring`을 사용한다.

  - `str.slice(start[, end])` 

    문자열의 start부터 end 까지(end 미포함)를 반환한다.

    두 번째 인수가 생략된 경우 명시한 위치부터 문자열 끝까지 반환한다.

    start와 end는 음수가 될 수 있다. 음수를 넘기면 문자열 끝에서부터 카운팅을 시작한다.

  - `str.substring(start [, end])`

    start와 end **사이에** 있는 문자열을 반환한다.

    start가 end보다 커도 괜찮다.

    substring은 음수 인수를 허용하지 않는다. 음수는 `0`으로 처리된다.

  - `str.substr(start [, length)`

- 소문자로 바꾸려면 `toLowerCase`, 대문자로 바꾸려면 `toUpperCase`를 사용한다.

- 부분 문자열의 위치를 얻으려면 `indexOf`를 사용한다.

- 부분 문자열 여부만 알고 싶다면 `include/startsWith/endsWith`를 사용한다.

- 특정 언어에 적합한 비교 기준을 사용해 문자열을 비교하려면 `localeCompare`를 사용한다. 이 메서드를 사용하지 않으면 글자 코드를 기준으로 문자열이 비교된다.

- `str.trim()`: 문자열 앞과 끝의 공백 제거

- `str.repeat(n)`: 문자열을 n번 반복

### 과제

**첫 글자를 대문자로 변경하기**

```js
unFirst("john") = "John";
```

답

```js
function unFirst(str) {
  // 빈 문자열인지 확인
  if (!str) return str;
  return str[0].toUpperCase() + str.slice(1);
}
```

**스팸 문자열 걸러내기**

```js
checkSpam('buy ViAgRA now') == true
checkSpam('free xxxxx') == true
checkSpam("innocent rabbit") == false
```

내가 쓴 답

```js
function checkSpam(str) {
  let lowerStr = str.toLowerCase();
  if (lowerStr.include('viagra') || lowerStr.include('xxx')) {
    return true;
  }
  return false;
}
```

정답

```js
function checkSpam(str) {
  let lowerStr = str.toLowerCase();
  return lowerStr.include('viagra') || lowerStr.include('xxx'); 
}
```

**문자열 줄이기**

```js
truncate("What I'd like to tell on this topic is:", 20) = "What I'd like to te…"

truncate("Hi everyone!", 20) = "Hi everyone!"
```

내가 쓴 답

```js
function truncate(str, maxlength) {
  if (str.length() > maxlength) {
    return str.slice(0, maxlength - 1) + "...";
  }
  return str;
}
```

정답

```js
function truncate(str, maxlength) {
  return str.length() > maxlength ? 
    str.slice(0, maxlength - 1) + "..." : str;
}
```



**숫자만 추출하기**

```js
alert( extractCurrencyValue('$120') === 120 ); // true
```

답

```js
function extractCurrencyValue(str) {
  return +str.slice(1);
}
```



## 5.4. 배열

- 순서가 있는 컬렉션을 다뤄야할 때 객체를 사용하면 순서와 관련된 메서드가 없어 편리하지 않다. 객체는 순서를 고려하지 않고 만들어진 자료구조이기 때문이다. 따라서 객체를 이용하면 새로운 프로퍼티를 기존 프로퍼티 '사이에' 끼워넣는 것도 불가능하다.

- 배열은 특수한 형태의 객체이다.

- 순서가 있는 자료를 저장하고 관리하는 용도에 최적화되어 있다.

- 선언 방법

  ```js
  let arr = [item1, item2...]; // 가장 많이 사용됨 
  let arr = new Array(item1, item2...);
  ```

  `new Array(number)`를 호출하면 길이가 `number`인 배열이 만들어진다. 

- `length` 프로퍼티는 배열의 길이를 나타내준다. 배열 메서드는 `length` 프로퍼티를 자동으로 조절해준다. `length` 값을 수동으로 줄이면 배열 끝이 잘린다.

- 배열을 데큐처럼 사용하기

  - `push(...items)`: `items`를 배열 끝에 넣어준다.
  - `pop()`: 배열 끝 요소를 제거하고, 제거한 요소를 반환한다.
  - `shift()`: 배열 처음 요소를 제거하고, 제거한 요소를 반환한다.
  - `unshift(...items)`: `items`를 배열 처음에 더해준다.

- 배열의 내부 동작 원리

  - 배열은 키가 숫자라는 점만 다른 특별한 종류의 객체이다. 
  - 숫자형 키를 사용함으로써 배열은 객체 기본 기능 이외에도 순서가 있는 컬렉션을 제어하게 해주는 특별한 메서드와 `length`같은 프로퍼티도 제공한다.
  - 자바스크립트 엔진은 배열의 요소를 인접한 메모리 공간에 차례로 저장해 연산 속도를 높인다.
  - 배열은 객체이므로 원하는 프로퍼티를 추가해도 문제가 발생하지 않는다. 그러나 이러면 자바스크립트 엔진이 배열을 일반 객체처럼 다루게 되어 배열을 다룰 때만 적용되는 최적화 기법이 동작하지 않아 배열 특유의 이점이 사라진다.

- 성능

  `push`와 `pop`은 빠르지만 `shift`와 `unshift`는 느리다. 제거하거나 추가할 뿐만 아니라 이를 위해 다른 요소들을 이동해야 하기 때문이다. 배열에 요소가 많으면 요소가 이동하는 데 걸리는 시간이 길고 메모리 관련 연산도 많아진다. `pop`과 `push`의 경우 요소 이동을 수반하지 않는다.

- 모든 요소를 대상으로 반복 작업하기

  - `for (let i = 0; i < arr.length; i++)`: 가장 빠른 방법, 오래된 브라우저와 호환 가능

  - `for (let item of arr)`: **배열 요소에만 사용되는 모던한 문법**

  - `for (let item in arr)`: 배열엔 사용하지 말자!

    - **모든 프로퍼티를 대상**으로 순회한다. 

      유사 배열(array-like) 객체의 경우 배열과는 달리 키가 숫자형이 아닌 프로퍼티와 메서드가 있을 수 있다. 따라서 `for...in`을 사용하면 필요 없는 프로퍼티들까지 순회가 이루어지고, 문제를 일으킬 수 있다.

    - 배열이 아니라 **객체와 함께 사용할 때 최적화**되어 있어 속도가 느리다.

- 과제

  - 배열과 관련된 연산

    1. 요소 “Jazz”, "Blues"가 있는 `styles` 배열을 생성합니다.
    2. "Rock-n-Roll"을 배열 끝에 추가합니다.
    3. 배열 정 중앙에 있는 요소를 "Classics"로 바꿉니다. 가운데 요소를 찾는 코드는 요소가 홀수 개인 배열에서도 잘 작동해야 합니다.
    4. 배열의 첫 번째 요소를 꺼내서 출력합니다.
    5. "Rap"과 "Reggae"를 배열의 앞에 추가합니다.

    ```js
    Jazz, Blues
    Jazz, Blues, Rock-n-Roll
    Jazz, Classics, Rock-n-Roll
    Classics, Rock-n-Roll
    Rap, Reggae, Classics, Rock-n-Roll
    ```

    답

    ```js
    let styles = ["Jazz", "Blues"];
    styles.push("Rock-n-Roll");
    styles[Math.floor(styles.length - 1) / 2] = "Classics";
    alert(styles.shift());
    styles.unshift("Rap", "Reggae");
    ```

  - 배열 컨텍스트에서 함수 호출하기

    ```js
    let arr = ["a", "b"];
    
    arr.push(function() {
      alert( this );
    })
    
    arr[2](); // a, b, function() { alert( this )}
    ```

  - 입력한 숫자의 합 구하기

    아래 조건을 만족하는 함수 `sumInput()`을 작성해 봅시다.

    - `prompt` 창을 띄워 사용자에게 숫자를 입력해 달라고 요청한 후, 입력받은 값들을 배열에 저장합니다.
    - 숫자가 아닌 값, 혹은 빈 문자열을 입력하거나 ‘Cancel’ 버튼을 누르면 질문을 멈춥니다.
    - 배열 요소의 합을 계산하고 리턴합니다.

    ```js
    function sumInput() {
      let numbers = [];
      while (true) {
        let response = prompt("숫자를 입력해주세요.");
        if (response === "" || response === null || !isFinite(response)) 
          break;
        numbers.push(+response);
      }
      
      let sum = 0;
      for (let number of numbers) {
        sum += number;
      }
      return sum;
    }
    ```

  - 최대합 부분배열

    인접한 요소의 총합이 최대인 arr의 부분배열을 찾아 그 요소들의 합을 리턴하는 함수 `getMaxSubSum(arr)`을 작성해봅니다.

    ```js
    getMaxSubSum([-1, 2, 3, -9]) == 5 (강조 표시된 요소들의 합)
    getMaxSubSum([2, -1, 2, 3, -9]) == 6
    getMaxSubSum([-1, 2, 3, -9, 11]) == 11
    getMaxSubSum([-2, -1, 1, 2]) == 3
    getMaxSubSum([100, -9, 2, -3, 5]) == 100
    getMaxSubSum([1, 2, 3]) == 6 (모든 요소)
    ```

    요소 전체가 음수라면 아무런 요소도 선택하지 않아야 최댓값이 된다. 그리고 합은 0이 된다.

    ```js
    getMaxSubSum([-1, -2, -3]) = 0;
    ```

    답

    ```js
    function getMaxSubSum(arr) {
      let maxSum = 0;
      let partSum = 0;
      
      for (let item of arr) {
        partSum += item;
        maxSum = Math.max(maxSum, partSum);
        if (partSum < 0) partSum = 0;
      }
      return maxSum;
    }
    ```



## 5.5. 배열과 메서드

- 요소 추가 및 삭제

  - `push(...items)`: 맨 끝에 요소 추가

  - `pop()`: 맨 끝 요소 추출

  - `shift()`: 첫 요소 추출

  - `unshift()`: 맨 앞에 요소 추가

  - `splice(pos, deleteCount, ...items)`: `pos`부터 `deleteCount`개의 요소를 지우고, `items` 추가하기

    `delete`을 사용해 배열에서 요소를 지우면 해당 자리는 비어있는 채로 남아있다. `length`에는 변화가 없다. 

    ```js
    let arr = ["I", "go", "home"];
    
    delete arr[1]; // "go"를 삭제합니다.
    
    alert( arr[1] ); // undefined
    
    // delete를 써서 요소를 지우고 난 후 배열 --> arr = ["I",  , "home"];
    alert( arr.length ); // 3
    ```

    빈 공간을 나머지 요소들이 자동으로 채울 것을 기대한다면 이 메서드를 사용해야 한다. `splice`를 사용하면 요소 추가, 삭제, 교체가 모두 가능하다.

    ```js
    let arr = ["I", "study", "JavaScript"];
    
    arr.splice(1, 1); // 인덱스 1부터 요소 한 개를 제거
    
    alert( arr ); // ["I", "JavaScript"]
    ```

    `splice` 메서드의 `deleteCount`를 0으로 설정하면 요소를 제거하지 않으면서 새로운 요소를 추가할 수 있다. 

  - `splice(start, end)`: `start` 부터 `end` 바로 앞까지의 요소를 복사해 새로운 배열을 만듦

  - `concat(...items)`: 배열의 모든 요소를 복사하고 `items`를 추가해 새로운 배열을 만든 후 이를 반환한다. `items`가 배열이면 이 배열의 인수를 기존 배열에 더해준다.

    `concat` 메서드에 객체가 인자로 넘어오면 (배열처럼 보이는 유사 배열 객체이더라도) 객체는 분해되지 않고 통으로 복사되어 더해진다. 그런데 인자로 받은 유사 배열 객체에 특수한 프로퍼티 `Symbol.isConcatSpreadable`이 있으면 `concat`은 이 객체를 배열처럼 취급한다. 따라서 객체 전체가 아닌 객체 프로퍼티의 값이 더해진다. 

- 원하는 요소 찾기

  - `indexOf/lastIndexOf(item, from)`: `from`부터 원하는 `item`을 찾는다. 찾게 되면 해당 요소의 인덱스를, 아니면 `-1`을 반환한다.

  - `incudes(value, from)`: 배열에 `value`가 있으면 `true`를, 없으면 `false`를 반환한다.

    `indexOf`, `lastIndexOf`, `include`는 요소들을 찾을 때 **완전 항등 연산자**인 `===`를 사용한다. `false`를 검색하면 정확히 `false`만을 검색하지, 0을 검색하지는 않는다. 

    `includes`는 `NaN`도 제대로 처리한다는 점에서 `indexOf/lastIndexOf`와 차이가 있다.

    ```js
    const arr = [NaN];
    alert( arr.indexOf(NaN) ); // -1 (완전항등비교)
    alert( arr.includes(NaN) ); // true (NaN 여부 확인)
    ```

  - `find/filter(func)`: `func`의 반환 값을 `true`로 만드는 첫 번째/전체 요소를 반환한다.

  - `findIndex`: `find`와 유사하다. 다만 요소 대신 인덱스를 반환한다. 

- 배열 전체 순회하기

  - `forEach(func)`: 모든 요소에 `func`를 호출한다. 결과는 반환되지 않는다.

    ```js
    // for each element call alert
    ["Bilbo", "Gandalf", "Nazgul"].forEach(alert);
    ```

    ```js
    ["Bilbo", "Gandalf", "Nazgul"].forEach((item, index, array) => {
      alert(`${item} is at index ${index} in ${array}`);
    });
    ```

    이때, 인수로 넘겨준 함수의 반환값은 무시된다. 

- 배열 변형하기

  - `map(func)`: 모든 요소에 `func`를 호출하고, 반환된 결과를 가지고 새로운 배열을 만듦
  - `sort(func)`: 배열을 정렬하고 정렬된 배열을 반환함
  - `reverse(func)`: 배열을 뒤집어 반환함
  - `split/join`: 문자열을 배열로, 배열을 문자열로 변환함
  - `reduce(func, initial)`: 요소를 차례로 돌면서 `func`을 호출함. 반환값은 다음 함수 호출에 전달함. 최종적으로 하나의 값이 도출됨

- 기타

  - `Array.isArray(arr)`: `arr`이 배열인지 여부를 판단함

  - `arr.some(fn)`와 `arr.every(fn)`: 배열을 확인함

    `map`과 유사하게 모든 요소를 대상으로 함수를 호출한다. `some`은 함수의 반환 값을 `true`로 만드는 요소가 하나라도 있는지 여부를 확인하고, `every`는 모든 요소가 함수의 반환 값을 `true`로 만드는 지 여부를 확인한다. 모두 조건을 충족시키면 `true`를, 아니면 `false`를 반환한다.

  - `arr.fill(value, start, end)`: start부터 end까지 `value`를 채워넣음

  - `arr.copyWithin(tartget, start, end)`: start부터 end까지 요소를 복사하고, 복사한 요소를 target으로 붙여넣음. 기존 요소가 있다면 덮어씀

- `sort`, `reverse`, `splice`는 **기존 배열을 변형시킨다.**
- 보통 `slice` 메서드뿐만 아니라 배열 관련 메서드엔 음수 인덱스를 사용할 수 있다. 마이너스 부호 앞의 숫자는 배열 끝에서부터 센 요소를 나타낸다. 예를 들어 인덱스 -1은 배열 끝에서부터 첫 번째 요소이다. 



## 5.6. iterable 객체

`for..of`을 사용할 수 있는 객체를 이터러블이라고 부른다. 배열뿐만 아니라 문자열 등 다수의 내장 객체가 이터러블이다. 

- 이터러블엔 메서드 `Symbol.iterator`가 반드시 구현되어 있어야 한다.

  - `obj[Symbol.iterator]`의 결과는 *이터레이터*라고 부른다. 이터레이터는 이어지는 반복 과정을 처리한다.
  - 이터레이터엔 객체 `{done: Boolean, value: any}`을 반환하는 메서드 `next()`가 반드시 구현되어 있어야 한다. 여기서 `done:true`는 반복이 끝났음을 의미하고 그렇지 않은 경우엔 `value`가 다음 값이 된다. 

- 이터러블 객체 만들어보기

  ```js
  let range = {
    from: 1,
    to: 5
  };
  ```

  range 를 이터레이터로 만들려면 객체에 `Symbol.iterator`라는 메서드를 추가한다. 그럼 다음과 같은 일이 벌어진다.

  - `for..of`가 시작되지 마자 `for..of`는 `Symbol.iterator`를 호출한다. 없다면 에러가 발생한다. `Symbol.iterator`는 반드시 `iterator`(메서드 `next`가 있는 객체) 객체를 반환해야 한다.
  - 이후 `for..of`는 **반환된 객체만을 대상으로 동작**한다.
  -  `for..of`에 다음 값이 필요하면, `for..of`는 이터레이터의 `next()` 메서드를 호출한다.
  - ` next()`의 반환값은 `{done: Boolean, value: any}`와 같은 형태여야 한다. `done=true`는 반복이 종료되었음을 나타낸다. `false`일 땐 `value`에 다음 값이 저장된다.

  ```js
  let range = {
    from: 1,
    to: 5
  };
  
  range[Symbol.iterator] = function() {
    return {
      current: this.from,
      last: this.to,
      
      next() {
        if (this.current <= this.last) {
          return { done: false, value: this.current++ };
        } else {
          return { done: true };
        }
      }
    };
  };
  
  for (let num of range) {
    alert(num); // 1, 2, 3, 4, 5
  }
  ```

- 이터러블 객체의 핵심은 **관심사의 분리(Seperation of concern, SoC)**에 있다.

  - `range`에는 메서드 `next()`가 없다.
  - 대신 `range[Symbol.iterator]()`를 호출해서 만든 '이터레이터' 객체와 이 객체의 메서드 `next()`에서 반복에 사용될 값을 만들어낸다.

  이렇게 하면 이터레이터 객체와 반복 대상인 객체를 분리할 수 있다.

- 이터레이터 객체와 반복 대상인 객체를 합쳐서 객체 자체를 이터레이터로 만들면 더 간단하다.

  ```js
  let range = {
    from: 1,
    to: 5
    [Symbol.iterator]() {
    	this.current = this.from;
    	return this;
  	},
  
    next() {
      if (this.current <= this.last) {
        return { done: false, value: this.current++ };
      } else {
        return { done: true };
      }
     }
  };
  
  for (let num of range) {
    alert(num); // 1, 2, 3, 4, 5
  }
  ```

  이러면 `range[Symbol.iterator]()`는 객체 `range` 자체를 반환한다. 이 방식의 단점은 두 개의 `for..of` 반복문을 하나의 객체에 동시에 사용할 수 없다는 점이다. 이터레이터(객체 자신)가 하나뿐이어서 두 반복문이 반복 상태를 공유하기 때문이다. 

- 메서드 `Symbol.iterator`는 `for..of`에 의해 자동으로 호출되는데, 개발자가 명시적으로 호출하는 것도 가능하다.

  ```js
  let str = "Hello";
  // for (let char of str) alert(char); 와 같음
  let iterator = str[Symbol.iterator]();
  
  while (true) {
    let result = iterator.next();
    if (result.done) break;
    alert(result.value); // H, e, l, l, o
  }
  ```

  이런 경우는 거의 없지만, `for..of`보다 반복 과정을 더 잘 통제할 수 있다는 장점이 있다. 반복을 시작했다가 잠시 멈춰 다른 작업을 하다가 다시 반복을 시작하는 등 반복 과정을 여러개로 쪼갤 수 있다.

- 문자열이나 배열 같은 내장 이터러블에도 `Symbol.iterator`가 구현되어 있다.

- 문자열 이터레이터는 서로게이트 쌍을 지원한다.

- 인덱스와 `length` 프로퍼티가 있는 객체는 *유사 배열*이라고 한다. 유사 배열 객체에는 다양한 프로퍼티와 메서드가 있을 수 있는데, **배열 내장 메서드는 없다.**

- 이터러블 객체라고 해서 유사 배열 객체는 아니다. 유사 배열 객체라고 해서 이터러블 객체인 것도 아니다. `range`는 이터러블 객체이지만 인덱스도 없고 `length` 프로퍼티도 없으므로 유사 배열 객체가 아니다.

  ```js
  let arrayLike = {
    0: "Hello",
    1: "World",
    length: 2
  }
  
  // Symbol.iterator가 없으므로 에러 발생
  for (let item of arrayLike) {}
  ```

  arrayLike는 유사 배열이지만 이터러블 객체가 아니다. 

- 대부분의 메서드는 '진짜' 배열이 아닌 이터러블이나 유사 배열을 대상으로 동작한다고 쓰여있는 걸 명세서에서 볼 수 있다. 이 방법이 더 추상적이기 때문이다.

- `Array.from(obj[, mapFn, thisArg])`을 사용하면 이터러블이나 유사 배열인 `obj`를 진짜 `Array`로 만들 수 있다. 이렇게 하면 `obj`에도 배열 메서드를 사용할 수 있다. 선택 인수 `mapFn`와 `thisArg`는 각 요소에 함수를 적용할 수 있게 해준다.

  ```js
  let arr = Array.from(range, num => num * num);
  
  alert(arr); // 1, 4, 9, 16, 25
  ```

  

## 5.7. 맵과 셋

### 맵

`맵`은 키가 있는 값이 저장된 컬렉션이다.

**주요 메서드와 프로퍼티**

- `new Map([iterable])`: 맵을 만든다. `[key, value]` 쌍이 있는 `iterable`(예: 배열)을 선택적으로 넘길 수 있는데, 이때 넘긴 이터러블 객체는 맵 초기화에 사용된다.

- `map.set(key, value)`: 키를 이용해 값을 저장한다.

  `set`을 호출할 때마다 맵 자신이 반환되기 때문에 `map.set`을 체이닝할 수 있다.

  ```js
  map.set('1', 'str1')
    .set(1, 'num1')
    .set(true, 'bool1');
  ```

- `map.get(key)`: 키에 해당하는 값을 반환한다. `key`가 존재하지 않으면 `undefined`를 반환한다.

- `map.has(key)`: 키가 있으면 `true`, 없으면 `false`를 반환한다.

- `map.delete(key)`: 키에 해당하는 값을 삭제한다.

- `map.clear()`: 맵 안에 모든 요소를 제거한다.

- `map.size`: 요소의 개수를 반환한다.

- `map.keys()`: 각 요소의 키를 모은 반복 가능한(iterable, 이터러블) 객체를 반환한다.

- `map.values()`: 각 요소의 값을 모은 이터러블 객체를 반환한다.

- `map.entries()`: 요소의 `[키, 값]`을 한 쌍으로 하는 이터러블 객체를 반환한다. 이 이터러블 객체는 `for..of`반복문의 기초로 쓰인다.

- `forEach(value, key, map)`

- `new Map(Object.entries(obj))`: 객체를 맵으로 바꾸기

- `Object.fromEntries(map)`: 맵을 객체로 바꾸기

**일반 객체와 차이점**

- **키의 타입에 제약이 없다. 객체도 키가 될 수 있다.**

  ```js
  let john = { name: "John" };
  
  let visitsCountMap = new Map();
  visitsCountMap.set(john, 123);
  alert( visitsCountMap.get(john) ); // 123
  
  let visitsCountObj = {};
  visitsCountObj[john] = 123;
  alert( visitsCountObj["object Object"]); // 123
  ```

- `size` 프로퍼티 등 유용한 메서드나 프로퍼티가 있다.

- 맵은 값이 삽입된 순서대로 순회를 실시한다. 반면 객체는 프로퍼티 순서를 기억하지 못한다.

**특징**

- `map.set`을 

### 셋

`셋`은 중복이 없는 값을 저장할 때 쓰이는 컬렉션이다.

**주요 메서드와 프로퍼티**

- `new Set([iterable])`: 셋을 만든다. `iterable` 객체를 선택적으로 전달받을 수 있는데(대개 배열), 이터러블 객체 안의 요소는 셋을 초기화하는 데 쓰인다.
- `set.add(value)`: 값을 추가하고 셋 자신을 반환한다. **셋 내에 이미 `value`가 있는 경우 아무런 작업을 하지 않는다.**
- `set.delete(value)`: 값을 제거한다. 호출 시점에 셋 내에 값이 있어서 제거에 성공하면 `true`, 아니면 `false`를 반환한다.
- `set.has(value)`
- `set.clear()`
- `set.size()`
- `set.keys()`: 셋 내의 모든 값을 포함하는 이터러블 객체를 반환한다. 
- `set.values()`: `set.keys()`와 동일한 작업을 한다. `맵`과의 호환성을 위해 만들어진 메서드다.
- `set.entries()`: 요소의 `[값, 값]`을 한 쌍으로 하는 이터러블 객체를 반환한다. `맵`과의 호환성을 위해 만들어진 메서드다.
- `forEach(value, key, map)`
- `new Map(Object.entries(obj))`: 객체를 맵으로 바꾸기
- `Object.fromEntries(map)`: 맵을 객체로 바꾸기

맵과 셋에 반복 작업을 할 땐, 해당 컬렉션에 요소나 값을 추가한 순서대로 반복 작업이 수행된다. 따라서 이 **두 컬렉션은 정렬이 되어 있지 않다고 할 수 없다**. 그러나 컬렉션 내 요소나 **값을 재 정렬하거나** (배열에서 인덱스를 이용해 요소를 가져오는 것처럼) 숫**자를 이용해 특정 요소나 값을 가지고 오는 것은 불가능하다.** 

## 5.8. 위크맵과 위크셋

### 위크맵

- 맵에서 객체를 키로 사용한 경우 맵이 메모리에 있는 한 객체도 메모리에 남는다. 가비지 컬렉터의 대상이 아니다.

  ```js
  let john = { name: "John" };
  let map = new Map();
  map.set(john, "...");
  john = null; // 참조를 null로 덮어씀
  
  // john을 나타내는 객체는 맵 안에 저장되어 있다.
  // map.keys()를 이용하면 해당 객체를 얻는 것도 가능하다.
  for (let obj of map.keys()) {
    alert(JSON.stringify(obj));
  }
  
  alert(map.size())
  ```

- 그러나 위크맵을 사용하면 키로 사용된 객체를 참조하는 것이 아무것도 없다면 해당 객체는 메모리와 위크맵에서 자동으로 삭제된다. 

  ```js
  let john = { name: "John" };
  let weakMap = new WeakMap();
  weakMap.set(john, "...");
  john = null;
  ```

- `위크맵`은 `맵`과 유사한 컬렉션이다. `위크맵`을 구성하는 요소의 **키는 오직 객체만 가능**하다. 키로 사용된 객체가 메모리에서 삭제되면 이에 대응하는 값 역시 삭제된다.

- 위크맵이 지원하는 메서드: `get`, `set`, `delete`, `has`

- 위크맵이 적은 메서드만 지원하는 이유: 가비지 컬렉션 때문이다. 객체는 모든 참조를 잃게 되면 자동으로 가비지 컬렉션의 대상이 되는데 가비지 컬렉션의 동작 시점은 정확히 알 수 없다. 현재 위크맵에 요소가 몇 개 있는지 파악하는 것도, 위크맵의 요소 전체를 대상으로 무언가를 하는 동작 자체도 불가능한 이유이다. 가비지 컬렉터가 한 번에 메모리를 청소할 수도 있고, 부분 부분 메모리를 청소할 수도 있기 때문이다.

- 위크맵의 유스케이스

  - 추가 데이터: 부차적인 데이터를 저장할 곳이 필요할 때

    ```js
    weakMap.set(john, "비밀문서");
    // john이 사망하면, 비밀문서는 자동으로 파기된다
    ```

    맵을 사용해 사용자 방문 횟수를 저장한다고 해보자. 어떤 사용자의 정보를 저장할 필요가 없어지면(가비지 컬렉션의 대상이 되면) 해당 사용자의 방문 횟수도 저장할 필요가 없어진다.

    ```js
    // 📁 visitsCount.js
    let visitsCountMap = new Map(); // 맵에 사용자의 방문 횟수를 저장함
    
    // 사용자가 방문하면 방문 횟수를 늘려줍니다.
    function countUser(user) {
      let count = visitsCountMap.get(user) || 0;
      visitsCountMap.set(user, count + 1);
    }
    
    // 📁 main.js
    let john = { name: "John" };
    
    countUser(john); // John의 방문 횟수를 증가시킵니다.
    
    // John의 방문 횟수를 셀 필요가 없어지면 아래와 같이 john을 null로 덮어씁니다.
    john = null;
    ```

    null로 덮어줘도 visitsCountMap의 키로 사용되고 있어서 메모리에서 삭제되지 않는다. 우리가 손수 지워주지 않으면 `visitsCountMap`은 한없이 커질텐데, 앱이 복잡할 땐 이렇게 쓸모 없는 데이터를 수동으로 바꿔주는 것이 골치아프다. 이때 위크맵을 사용하면 수동으로 청소해줄 필요가 없다.

  - 캐싱: 시간이 오래 걸리는 작업의 결과를 저장해서 연산 시간과 비용을 절약해준다.

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

    반면 위크맵을 사용하면 캐싱된 데이터 역시 메모리에서 삭제될 것이고, cache에 어떤 요소도 남지 않을 것이다. 단, size 프로퍼티는 이용할 수 없다.

### 위크셋

- `위크셋`은 `셋`과 유사한 컬렉션이다. 위크셋엔 객체만 저장할 수 있다. 위크셋에 저장된 객체가 도달 불가능한 상태가 되면 해당 객체는 메모리에서 삭제된다.

- 위크셋엔 위크맵처럼 복잡한 데이터를 저장하지 않는다. 대신 '예'나 '아니오' 같은 간단한 답변을 얻는 용도로 사용된다. 

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
  
  // visitedSet에서 john을나타내는 객체가 자동으로 삭제됩니다.
  ```

**두 자료구조 모두 구성 요소 전체를 대상으로 하는 메서드를 지원하지 않는다.** 구성 요소 하나를 대상으로 하는 메서드만 지원한다.

객체에는 '주요' 자료를, `위크맵`과 `위크셋`에는 '부수적인' 자료를 저장하는 형태로 위크맵과 위크셋을 활용할 수 있다. 객체가 메모리에서 삭제되면, 그리고 오로지 위크맵과 위크셋의 키만 해당 객체를 참조하고 있다면 위크맷이나 위크셋에 저장된 연관 자료들 역시 메모리에서 자동으로 삭제된다.

## 5.9. Object.keys, values, entries

- iteration에 필요한 메서드들이다. 
- Map, Set, Array에서 사용할 수 있다.
- 일반 객체에도 순회 관련 메서드가 있지만 `keys()`, `values()`, `entries()`와는 문법에 차이가 있다.

### Object.keys, values, entries

- `Object.keys(obj)`: 객체의 키만 담은 **배열**을 반환한다.
- `Object.values(obj)`: 객체의 값만 담은 **배열**을 반환한다.
- `Object.entries(obj)` – `[키, 값]` 쌍을 담은 **배열**을 반환한다.

|           | 맵            | 객체                                  |
| :-------- | :------------ | :------------------------------------ |
| 호출 문법 | `map.keys()`  | `Object.keys(obj)`(`obj.keys()` 아님) |
| 반환 값   | iterable 객체 | ‘진짜’ 배열                           |

- 진짜 배열을 반환하는 이유는 하위 호환성 때문이다.

- Object.keys, values, entries는 심볼형 프로퍼티를 무시한다.

  심볼형 키가 필요한 경우 심볼형 키만 비열 형태로 반환해주는 `Object.getOwnPropertySymbols`를 사용하도록 하자. 키 전체를 배열 형태로 반환해주는 `Reflect.ownKeys(obj)`도 있다.

### 객체 변환

객체에 배열 전용 메서드를 사용하고 싶다면 

- `Object.entries(obj)` 를 통해 키-값 쌍이 요소인 배열을 얻는다.
- 이 배열에 배열 전용 메서드(`map`, `filter` 등)를 사용한다.
- `Object.fromEntries(array)`를 적용해 배열을 다시 객체로 되돌린다.

```js
let prices = {
  banana: 1,
  orange: 2,
  meat: 4,
};

let doublePrices = Object.fromEntries(
  Object.entries(prices).map(([key, value]) => [key, value * 2])
);

alert(doublePrices.meat); // 8
```



