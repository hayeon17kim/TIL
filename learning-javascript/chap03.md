# 리터럴과 변수, 상수, 데이터타입

0과 1만으로는 일을 할 수 없고, 우리에게 익숙한 형태인 숫자와 텍스트, 날짜 등을 다뤄야 한다. 이런 것을 data type이라고 한다.

## 3.1 변수와 상수

변수

- 이름이 붙은 언제든지 바뀔 수 있는 값
- 초깃값을 할당하지 않으면 `undefined`가 할당된다.
- `let` 문 하나에서 변수 여러 개를 선언할 수 있다.

상수

- ES6에서 새로 생겼다.
- 변수와 마찬가지로 값을 할당받을 수 있지만, 한 번 할당한 값을 바꿀 수 없다.
- 보통 대문자와 밑줄만 사용한다.



## 3.2 변수와 상수 중 어떤 것을 사용해야 할까?

변수보다 상수를 사용하는 것이 권장된다.

- 고정된 값이 이해하기 쉽다.
- 값을 바꾸지 말아야 할 데이터에서 실수로 값을 바꿀 일이 줄어든다.
- 우선 상수로 선언하고, 상수의 값이 바뀌는 것이 자연스럽다고 생각한다면, 변수로 바꾼다. 



## 3.3 식별자 이름

식별자(Identifier)

- 변수와 상수, 함수 이름
- 글자나 달러 기호(`$`), 밑줄(`_`)로 시작한다.
- 밑줄 한 개 또는 두 개로 시작하는 식별자는 아주 특별한 상황, 또는 '내부' 변수에서만 사용한다. 
- 제이쿼리를 사용할 경우, 달러 기호로 시작하는 식별자는 보통 제이쿼리 객체라는 의미이다.



## 3.4 리터럴

- 리터럴은 **값을 만드는 방법**이다.
- 자바스크립트는 개발자가 제공한 리터럴 값을 받아 데이터를 만든다.
- 리터럴과 식별자의 차이를 아는 것은 중요하다.



## 3.5 원시 타입과 객체

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



## 3.6 숫자

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



## 3.7 문자열

- 자바스크립트 문자열은 유니코드 텍스트이다.
- 유니코드는 텍스트 데이터에 관한 표준이며 사람이 사용하는 언어(이모티콘 포함) 대부분의 글자와 심볼에 해당하는 code point를 포함하고 있다. 
- 유니코드 자체는 모든 언어의 텍스트를 나타낼 수 있지만, 유니코드를 사용하는 소프트웨어가 모든 코드 포인트를 정확히 렌더링한다고 보장하지 않는다.
