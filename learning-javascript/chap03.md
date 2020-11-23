# 리터럴과 변수, 상수, 데이터타입

0과 1만으로는 일을 할 수 없고, 우리에게 익숙한 형태인 숫자와 텍스트, 날짜 등을 다뤄야 한다. 이런 것을 data type이라고 한다.

## 변수와 상수

변수

- 이름이 붙은 언제든지 바뀔 수 있는 값
- 초깃값을 할당하지 않으면 `undefined`가 할당된다.
- `let` 문 하나에서 변수 여러 개를 선언할 수 있다.

상수

- ES6에서 새로 생겼다.
- 변수와 마찬가지로 값을 할당받을 수 있지만, 한 번 할당한 값을 바꿀 수 없다.
- 보통 대문자와 밑줄만 사용한다.



## 변수와 상수 중 어떤 것을 사용해야 할까?

변수보다 상수를 사용하는 것이 권장된다.

- 고정된 값이 이해하기 쉽다.
- 값을 바꾸지 말아야 할 데이터에서 실수로 값을 바꿀 일이 줄어든다.
- 우선 상수로 선언하고, 상수의 값이 바뀌는 것이 자연스럽다고 생각한다면, 변수로 바꾼다. 



## 식별자 이름

식별자(Identifier)

- 변수와 상수, 함수 이름
- 글자나 달러 기호(`$`), 밑줄(`_`)로 시작한다.
- 밑줄 한 개 또는 두 개로 시작하는 식별자는 아주 특별한 상황, 또는 '내부' 변수에서만 사용한다. 
- 제이쿼리를 사용할 경우, 달러 기호로 시작하는 식별자는 보통 제이쿼리 객체라는 의미이다.



## 리터럴

- 리터럴은 **값을 만드는 방법**이다.
- 자바스크립트는 개발자가 제공한 리터럴 값을 받아 데이터를 만든다.
- 리터럴과 식별자의 차이를 아는 것은 중요하다.



## 원시 타입과 객체

### 원시 타입

원시 타입은 불변이다.

- 숫자
- **문자열** -> 자바스크립트에서 문자열은 원시타입이다.
- 불리언
- `null`
- `undefined`
- Symbol



### 객체

- 원시 값과 달리 객체는 **여러 가지 형태와 값**을 가질 수 있다.
- 객체의 유연한 성질 때문에 **커스텀 데이터 타입**을 만들 때 객체를 많이 사용한다.
- 내장된 객체 타입
  - `Array`
  - `Date`
  - `RegExp`
  - `Map`과 `WeakSet`
- 원시 타입 중 숫자, 문자열, 불리언에 대응하는 객체 타입인 `Number`, `String`, `Boolean`이 있다. 이들 대응하는 **객체에 실제 값이 저장되지는 않는다**. **대응하는 원시 값에 기능을 제공하는 역할**을 한다.



## 숫자

- 자바스크립트는 실제 숫자의 근사치를 저장할 때 IEEE-764 배정도 부동소수점 숫자 형식을 사용한다. 이 형태를 **더블**이라고 한다. 즉 자바스크립트에서 숫자는 결국 더블 형식으로 저장된다.
- 더블 형식의 근사치결과에서의 오차: 무한한 값을 유한한 메모리 안에서 가능한 정확히 짐작하려고 생긴 결과이다.
- 자바스크립트에는 숫자형 데이터 타입이 하나밖에 없다. 이는 자바스크립트를 고성능 정수 연산이나 정밀한 소수점 연산이 필요한 애플리케이션에서는 쓰기 어렵도록 만든다.
- 자바스크립트는 10진수, 2진수, 8진수, 16진수 네 가지 숫자형 리터럴을 인식한다.
- 그 외에도 Infinity, -Infinity, NaN(숫자가 아님을 나타냄)라는 특별한 값이 있다.
  - 이들은 숫자형 리터럴이 아니지만, 숫자형 값이다.
  - 이들은 계산에 사용하는 숫자라기보다는 일종의 플레이스홀더이다. 

- 10진수, 16진수, 지수 등 어떤 리터럴 형식을 사용해도 결국 **숫자는 더블 형식으로 저장**된다.
- 숫자에 대응하는 `Number` 객체에는 중요한 숫자형 값에 해당하는 유용한 프로퍼티가 있다.

```js
const small = Number.EPSILON; // 1에 더했을 때 1과 구분되는 결과를 만들 수 있는 가장 작은 값이다. 근사치는 2.2e-16이다.
const bigInt = Number.MAX_SAFE_INTEGER; // 표현할 수 있는 가장 큰 정수
const max = Number.MAX_VALUE; // 표현할 수 있는 가장 큰 숫자
const minInt = Number.MIN_SAFE_INTEGER; // 표현할 수 있는 가장 작은 정수
const nInf = Number.NEGATIVE_INFINITY; //-Infinity
const nan = Number.NaN; //NaN
const Inf = Number.POSITIVE_INFINITY; //Infinity
```



## 문자열

- 자바스크립트 문자열은 유니코드 텍스트이다.
- 유니코드는 텍스트 데이터에 관한 표준이며 사람이 사용하는 언어(이모티콘 포함) 대부분의 글자와 심볼에 해당하는 code point를 포함하고 있다. 
- 유니코드 자체는 모든 언어의 텍스트를 나타낼 수 있지만, 유니코드를 사용하는 소프트웨어가 모든 코드 포인트를 정확히 렌더링한다고 보장하지 않는다.
- 역슬래시를 써서 따옴표를 이스케이프할 수 있다. `\"`
- 역슬래시를 써서 역슬래시를 이스케이프할 수 있다. `\\`

## 특수문자

| `\n`                                   | New line                                                     |
| -------------------------------------- | ------------------------------------------------------------ |
| `\r`                                   | Carriage return: not used alone. Windows text files use a combination of two characters `\r\n` to represent a line break. |
| `\'`, `\"`                             | Quotes                                                       |
| `\\`                                   | Backslash                                                    |
| `\t`                                   | Tab                                                          |
| `\b`, `\f`, `\v`                       | Backspace, Form Feed, Vertical Tab – kept for compatibility, not used nowadays. |
| `\xXX`                                 | Unicode character with the given hexadecimal unicode `XX`, e.g. `'\x7A'` is the same as `'z'`. |
| `\uXXXX`                               | A unicode symbol with the hex code `XXXX` in UTF-16 encoding, for instance `\u00A9` – is a unicode for the copyright symbol `©`. It must be exactly 4 hex digits. |
| `\u{X…XXXXXX}` (1 to 6 hex characters) | A unicode symbol with the given UTF-32 encoding. Some rare characters are encoded with two unicode symbols, taking 4 bytes. This way we can insert long codes. |

## 불리언

모든 값을 참 같은 값(truthy), 거짓 같은 값(falsy)로 나눌 수 있다.

## 심볼

- **유일한 토큰**을 나타내기 위해 ES6에서 도입한 새 데이터 타입이다. 심볼은 항상 유일하고, 다른 어떤 심볼과도 일치하지 않다.
- 객체는 모두 유일하다는 점에서 객체는 심볼과 유사하다.
- 그러나 심볼은 그 외에 원시 값의 특징을 모두 가지고 있으므로 확장성 있는 코드를 만들 수 있다. 
- 심볼은 `Symbol()` 생성자로 만든다.
- 우연히 다른 식별자와 혼동해서는 안되는 고유한 식별자가 필요하다면 식별자를 사용한다.
- 심볼을 만들 때는 `new` 키워드를 사용할 수 없다.

```js
const RED = Symbol("The color of a sunset!");
const ORANGE= Symbol("The color of a sunset!");
RED === ORANGE; // false: 심볼은 모두 서로 다르다.
```

## `null`과 `undefined`

- 자바스크립트의 특별한 타입이다.
- `null`이 가질 수 있는 값은 `null` 하나이고, `undefined`가 가질 수 있는 값도 `undefined` 하나이다. 
- 두 타입 모두 존재하지 않는 것을 나타낸다.
- `null`은 프로그래머에게 허용된 데이터 타입이다.
- `undefined`는 자바스크립트 자체에서 사용한다.
- 단, 이 규칙이 강제는 아니다. 

## 객체

- 원시 타입은 단 하나의 값만 나타낼 수 있고 불변이지만, 객체는 **여러 가지 값이나 복잡한 값**을 나낼 수 있으며 **변할 수도 있다.**
- 객체의 본질은 **컨테이너**이다.
- 컨테이너의 **내용물은 시간이 지나면서 바뀔 수 있지만**, 내용물이 바뀐다고 **컨테이너가 바뀌는 것은 아니다**. 
- 객체의 콘텐츠는 *프로퍼티(property)* 또는 *멤버(member)*라고 부른다. **프로퍼티**는 **이름(키)**와 **값**으로 구성된다.
- 프로퍼티의 **이름**은 **반드시 문자열 또는 심볼**이어야 하며, 값은 어떤 타입이든 상관 없고 다른 객체여도 괜찮다.
- 프로퍼티 이름에 **유효한 식별자**를 써야 *멤버 접근 연산자(member access operator)*(`.`)를 사용할 수 있다.
- 프로퍼티의 이름에 **유효한 식별자가 아닌** 이름을 쓴다면 *계산된 멤버 접근 연산자(computed member access operator)*(`[]`)를 사용해야 한다. 이때 **유효한 식별자**여도 대괄호로 접근할 수 있다. **심볼 프로퍼티**에 접근할 때도 대괄호를 사용한다.

```js
const SIZE = Symbol();
obj[SIZE] = 8;
obj[SIZE]; //8
```

> 자바스크립트 콘솔에서는 SIZE를 객체의 프로퍼티로 나열하지 않는다. 대괄호 연산자를 사용해서야 접근할 수 있다. 심볼 프로퍼티는 다르게 처리되며 기본적으로 표시되지 않기 때문이다. 또한 이 프로퍼티의 키는 SIZE 심볼이며 문자열 "SIZE"가 아니다. 

```js
obj.SIZE; // undefined
obj[SIZE]; // 8
```

- obj에 저장한 것이 원시 값이었으면 수정할 때마다 다른 값을 가르킬 것이다. 그러나 객체이기 때문에 항상 같은 객체를 가리키고 있다. 프로퍼티만 달라졌을 뿐이다. 
- 프로퍼티의 값도 객체가 될 수 있다.
- 객체를 만드는 동시에 프로퍼티를 만들 수도 있다.

```js
const sam = {
  name: 'Sam',
  classification: {
    kingdom: 'Anamalia',
    phylum: 'Chardata',
    class: 'Mamalia',
    order: 'Carnivoria',
    family: 'Felidae',
    subfamily: 'Felinae',
    genus: 'Felis',
    species: 'catus',
  },s
};
```

sam의 family에 접근하는 방법

```js
sam.classification.family;
sam["classification"].family;
sam.classfication["family"];
sam["classification"]["family"];
```

객체에 함수를 담을 수도 있다.

```js
sam.speak = function() { return: "Meow!"; };
```

함수 뒤에 괄호를 붙여 함수를 호출할 수도 있다.

```js
sam.speak(); // "Meow!"
```

객체에서 프로퍼티를 제거할 때는 `delete` 연산자를 사용한다.

```js
delete sam.classification; // classification 트리 전체 삭제
delete sam.speak; // speak 함수 삭제
```

## `Number`, `String`, `Boolean` 객체

- 목적
  - `Number.INFINITY`같은 특별한 값 저장
  - 함수 형태로 기능 제공

```js
const s = "hello";
s.toUpperCase(); //"Hello"
```

자바스크립트는 **일시적인** String 객체를 만든다. 이 임시 객체에 `toUpperCase` 함수가 들어 있다. 자바스크립트는 함수를 호출하는 즉시 임시 객체를 파괴한다. 

```js
const s = 'hello';
s.rating = 3;
s.rating; // undefined
```

- 일시적인 String 객체에 프로퍼티를 할당한다.
- 임시객체는 즉시 파괴되므로 `s.rating`은 `undefined`이다.

## 배열

자바스크립트 배열은 특수한 객체이다. 일반적인 객체와 달리 배열 콘텐츠에는 항상 순서가 있고, 키는 순차적인 숫자이다. 배열은 유용한 메서드를 많이 가진 강력한 데이터 타입이다.

자바스크립트는 C언어의 효율적인 배열(index array)과 더 강력한 동적 배열, 링크드 리스트를 혼합한 것이다. 자바스크립트 배열에는 다음과 같은 특징이 있다.

- 배열 **크기는 고정되지 않는다**. 언제든 요소를 추가하거나 제거할 수 있다.
- **요소의 데이터 타입을 가리지 않는다**. 즉, 문자열만 쓸 수 있는 배열이라던가 숫자만 쓸 수 있는 배열 같은 개념이 아예 없다.
- 배열 요소는 0으로 시작한다.

> 배열은 기능이 추가된 특수한 객체이므로 숫자가 아닌 키나 분수, 음수를 키로 쓸 수는 있지만, 배열 설계 목적에 어긋날 뿐만 아니라 혼란스러운 동작과 찾기 어려운 버그를 초래할 수 있으므로 피해야 한다.

- 배열에는 요소 숫자를 반환하는 `length` 프로퍼티가 있다.
- 배열 요소에 접근할 때는 대괄호 안에 요소의 인덱스 숫자를 쓴다.
- 배열 요소의 값을 덮어슬 때는 새 값을 할당한다.



## 객체와 배열 마지막의 쉼표

- 마지막 쉼표를 trailing comman, dangling comma, terminal comma 등으로 부른다.
- 객체 마지막에 프로퍼티를 추가하는 경우가 많으므로 마지막 쉼표를 사용하는 것이 좋다.



## 날짜

- 자바스크립트의 날짜와 시간은 내장된 `Date` 객체에서 담당한다.
- `Date` 객체는 원래 자바에서 가져온 것이다. 사용하기 어려운 편이고, 특히 타임존이 다른 날짜를 다룰 때는 매우 어렵다.
- 현재 날짜와 시간을 나타내는 객체를 만들 때는 `new Date()`을 사용한다.
- 특정 날짜에 해당하는 객체를 만들 때는 `new Date(1992, 9, 2)`와 같이 한다.
- 특정 날짜와 시간에 해당하는 객체를 만들 때는 `new Date(1992, 9, 2, 19, 0)`와 같이 한다.
- 날짜 객체를 만들면 각 부분을 가져올 수 있다.

```js
const halloweenParty = new Date(2020, 9, 31, 19, 0);
halloweenParty.getFullYear(); //2020
halloweenParty.getMonth(); // 9
halloweenParty.getDate(); // 31
halloweenParty.getDay(); // 6 (토요일이다. 0은 일요일이다.
halloweenParty.getHours(); // 19
halloweenParty.getMinutes(); // 0
halloweenParty.getSeconds(); // 0
halloweenParty.getMilliseconds(); // 0
```

## 정규표현식(regular expression)

- 자바스크립트의 부속 언어에 가깝다.
- 여러 가지 프로그래밍 언어에서 일종의 확장으로 제공하며, 문자열에서 필요한 복잡한 검색과 교체 작업을 비교적 단순하게 만든다.
- `RegExp` 객체를 사용한다.
- 슬래시 한 쌍(`/.../`) 사이에 심볼을 넣는 리터럴 문법도 있다. 



## 맵과 셋

- ES6에서는 새로운 데이터타입 Map과 Set, 그리고 그들의 약한 짝인 `WeakMap`과 `WeakSet`을 도입했다.
- 맵은 객체와 마찬가지로 키와 값을 연결하지만 특정 상황에서 객체보다 유리한 부분이 있다.
- 셋은 배열과 비슷하지만 중복이 허용되지 않는다.
- 위크맵과 위크셋은 맵과 셋과 마찬가지로 동작하지만 특정 상황에서 성능이 더 높아지도록 이부 기능을 제거한 버전이다.

## 데이터 타입 변환

### 숫자로 바꾸기

- `Number` 객체 생성자를 사용한다.

  숫자로 바꿀 수 없는 문자열에서는 `NaN`이 반환된다.

  ```js
  const numStr = "33.3";
  const num = Number(numStr); // 이 행은 숫자 값을 만든다. Number 객체의 인스턴스가 아니다!!!
  const numStr2 = "16 volts";
  const num2 = Number(numStr2); //NaN
  ```

- 내장 함수인 `parseInt`나 `parseFloat` 함수를 사용한다.

  - `parseInt`를 사용할 때는 기수(radix)를 넘길 수 있다. 

  ```js
  const a = parseInt("16 volts", 10); // "volts"는 무시된다. 10진수 16이다.
  const b = parseInt("3a". 16); // 16진수 3a를 10으로 바꾼다. 결과는 58이다.
  const c = parseFloat("15.5 kph"); // " kph"는 무시된다. parseFloat은 항상 기수가 10이라고 가정한다.
  ```

- `Date` 객체를 숫자로 바꿀 대는 `valueOf` 메서드를 사용한다. 이 숫자는 UTC 1970년 1월 1일 자정으로부터 몇 밀리초가 지났는지 나타내는 숫자이다.

  ```js
  const d = new Date(); //Sun Nov 22 2020 21:03:53 GMT+0900 (대한민국 표준시)
  const ts = d.valueOf(); //1606046633159
  ```

- 불리언 값을 1(true)이나 0(false)로 바꿔야 할 때가 있다. 이렇게 변환할 때는 조건 연산자를 사용한다. 

  ```js
  const b = true;
  const n = b ? 1 : 0;
  ```

### 문자열로 변환

- 자바스크립트의 모든 객체에는 문자열 표현을 반환하는 `toString()` 메서드가 있다.
- 자바스크립트에서 `toString()` 메서드를 사용할 일은 많지 않는데, 문자열 병합에서 자동으로 숫자를 문자로 변환하므로, 숫자를 문자열로 직접 바꿀 일이 거의 많지 않기 때문이다.

```js
const n = 33.5;
const s = n.toString(); // "33.5"
```

- `Date` 객체나 배열의 `toString()` 메서드는 쓸만한 결과를 반환한다.
  - 배열의 `toString()`은 각 요소를 문자열로 바꾼 후 쉼표로 연결한 문자열을 반환한다.
- 대부분의 객체는 아무 짝에도 쓸모없는 문자열 "[object Object]"를 반환한다. 객체의 `toString()` 메서드를 수정해서 더 유용한 문자열 표현을 반환하게 할 수 있다. 

## 불리언으로 변환

- 부정(not) 연산자를 써서 모든 값을 불리언으로 바꿀 수 있다.
- 부정 연산자를 한 번 사용하면 참 같은 값은 false로 바뀌고, 한번 더 쓰면 true가 된다.
- `Boolean` 생성자를 사용해도 결과는 같다. (`new` 키워드는 사용하지 않는다.)

```js
const n = 0; // falsy value
const b1 = !!n; //false
const b2 = Boolean(n);
```



## 요약

- 자바스크립트에는 문자열, 숫자, 불리언, null, undefined, 심볼의 여섯 가지 원시 타입과 객체 타입이 있다.
- 자바스크립트의 모든 숫자는 배정도 부동소수점 숫자(더블)이다.
- 배열은 특수한 객체이며, 객체와 마찬가지로 매우 강력하고 유연한 데이터 타입이다.
- 날짜, 맵, 셋, 정규표현식 등 자주 사용할 다른 데이터 타입들은 특수한 객체 타입이다.



## 참조형과 원시형

원시값은 불변이고, 원시 값을 복사/전달할 때는 값 자체를 복사/전달한다. 따라서 **원본 값이 바뀌더라도 '사본'의 값이 따라서 바뀌지는 않는다.** 또한 값 자체를 복사했으므로 **변수와 값은 일치**한다.

```js
let a = 1;
let b = a;
a = 2;
console.log(b); // 1 사본은 바뀌지 않는다.
a === 2; //true
```

**값 자체를 전달**하므로 **함수 안에서 변수의 값이 바뀌어도 함수 외부에서는 바뀌지 않은 상태로 남는다.**

```js
function change(a) {
  a = 5;
}
a = 3;
change(a);
console.log(a); // 3
```

객체를 가리키는 변수는 **그 객체를 가리키고 있을 뿐, 객체 자체는 아니다**. 따라서 **변수와 객체는 결코 일치하지 않는다.** 또한 참조를 전달하므로 **함수 안에서 객체를 변경하면 함수 외부에서도 바뀐다**.

```js
let q = {a: 1};
q === {a: 1}; // false
function change_o(o) {
  o.a = 999;
}
change_o(q);
console.log(q); // {a: 999}
```