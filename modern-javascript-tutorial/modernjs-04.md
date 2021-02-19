## 4.1. 객체

- 여덟 개의 자료형 중 일곱 개는 **오직 하나의 데이터만 담을 수 있어** **원시형(primitive type)**이라 부른다. 

- 반면 **객체형**은 **키로 구분된 데이터 집합이나 복잡한 개체(entity)**와 같이 다양한 데이터를 **저장할 수 있다.** 

- 객체는 몇가지 **특수한 기능을 가진 연관 배열(associative array)**

- 프로퍼티는 키(key) : 값(value) 쌍으로 이루어져 잇다.

  - **key엔 문자열이거나 심볼이 허용된다.** 보통 문자열이다.
  - **value엔 모든 자료형이 허용**된다.

- 프로퍼티 키는 프로퍼티 **이름 **또는 **식별자**라고도 부른다.

- 객체에선 키를 이용해 프로퍼티를 쉽게 찾을 수 있다.

  점 표기법(dot notation)을 이용해 프로퍼티 값을 읽는다.

- 프로퍼티 삭제: `delete user.age;`

- 프로퍼티 이름이 복수의 단어일 경우 따옴표로 묶어 줘야 한다.

- trailing, hanging 쉼표: 마지막 프로퍼티 끝은 쉼표로 끝날 수 있다. => 프로퍼티 추가, 삭제, 이동 용이

- **상수(const로 선언된) 객체는 수정될 수 있다.** const는 객체의 값을 고정하지만, 그 내용은 고정하지 않는다. 

- 빈 객체를 만드는 방법

  - **객체 생성자 문법**: `let user = new Object()`
  - **객체 리터럴(object literal)문법**: `let user = {}`

### 대괄호 표기법

- 유효한 변수 식별자
  - 공백이 없다.
  - 숫자로 시작하지 않는다.
  - `$`, `_`를 제외한 특수 문자가 없어야 한다.

- **키가 유효한 변수 식별자가 아닌 경우** **대괄호 표기법**(square bracket notation)을 사용해야 한다. 

  대괄호 표기법 안에서 문자열을 사용할 땐 **문자열을 따옴표로** 묶어줘야 한다. 

  대괄호 표기법을 사용할 경우 **문자열 뿐만 아니라 모든 표현식**의 평가 결과를 프로퍼티로 사용할 수 있다. 다음 코드에서는 **변수를 키로 사용**하였다.

  ```js
  let key = "like birds";
  
  user[key] = true;
  ```

  변수 key는 런타임에 평가되기 때문에 사용자 입력값 변경 등에 따라 값이 변경될 수 있다. 어떤 경우든, 평가가 끝난 이후의 결과가 프로퍼티 키로 사용된다. 이를 응용하면 코드를 유연하게 작성할 수 있다. **점 표기법은 이런 방식이 불가능하다.**

#### 계산된 프로퍼티(computed property)

- 객체를 만들 때 **객체 리터럴 안의 프로퍼티 키가 대괄호로 둘러싸여 있는 경우,** 이를 **계산된 프로퍼티(computed property)**라고 부른다.

  ```js
  let fruit = prompt("어떤 과일을 구매하시겠습니까?", "apple");
  
  let bag = {
    [fruit] = 5, // 변수 fruit에서 프로퍼티 이름을 동적으로 받아온다.
  };
  
  alert(bag.apple); // fruit에 apple이 할당되었다면, 5가 출력된다.
  ```

  아래 예시는 위 예시와 동일하게 동작한다.

  ```js
  let fruit = prompt("어떤 과일을 구매하시겠습니까?", "apple");
  
  let bag = {};
  
  // 변수 fruit을 사용해 프로퍼티 이름을 만든다.
  bag.fruit = 5;
  ```

  대괄호 안에는 **복잡한 표현식**이 올 수 있다.

  ```js
  let fruit = 'apple';
  let bag = {
    [fruit + 'Computers']: 5 // bag.appleComputers = 5
  };
  ```

- 대괄호 표기법은 프로퍼티 이름과 값의 제약을 없애주기 때문에 **점 표기법보다 강력하다**. 그러나 작성하기 번거롭다. 

- **프로퍼티 이름이 확정된 상황**이고, 단순한 이름이라면 점 표기법을 사용하다가 **복잡한 상황이 발생**했을 때 대괄호 표기법으로 바꾸는 경우가 많다.

### 단축 프로퍼티

- 실무에서는 **프로퍼티 값을 기존 변수에서 받아와 사용하는 경우가 종종 있다.** 이 경우 **프로퍼티 값 단축 구문(property value shorthand)**를 사용하면 변수를 사용해 프로퍼티를 쉽게 만들 수 있다.

- 사용 전

  ```js
  function makeUser(name, age) {
    return {
      name: name,
      age: age,
      // ...등등
    };
  }
  
  let user = makeUser("John", 30);
  alert(user.name); // John
  ```

  사용 후

  ```js
  function makeUser(name, age) {
    return {
      name, // name: name 과 같음
      age,  // age: age 와 같음
      // ...
    };
  }
  ```



### 프로퍼티 이름의 제약사항

- 예약어를 객체 프로퍼티 키로 사용해도 된다.

  ```js
  let obj = {
    for: 1,
    let: 2,
    return: 3
  };
  
  alert( obj.for + obj.let + obj.return );  // 6
  ```

  프로퍼티 이름엔 특별한 제약이 없다. 어떤 **문자형, 심볼형 값도 프로퍼티 키가 될 수 있다.**

- 문자형이나 심볼형에 **속하지 않은 값은 문자열로 자동 형 변환된다.**

- `__proto__`의 경우 역사적인 이유 때문에 특별 대우를 받는다. 

  ```js
  let obj = {};
  obj.__proto__ = 5; // 숫자를 할당한다.
  alert(obj.__proto__); // [object Object] - 숫자를 할당했지만 값은 객체가 되었다. 의도한대로 동작하지 않는다.
  ```

### `in` 연산자로 프로퍼티의 존재 여부 확인하기

- **존재하지 않는 프로퍼티에 접근**하려 해도 에러가 발생하지 않고 **`undefined`를 반환**한다. (null을 반환하는 자바와 다르다.) 따라서 **일치연산자를 사용해 `undefined`과 프로퍼티 이름을 비교**하여 **프로퍼티 존재 여부**를 확인할 수 있다.

  그러나 프로퍼티는 존재하는데 값에 `undefined`를 할당한 경우 프로퍼티 존재 여부를 제대로 판단할 수 없다. 이 경우 프로퍼티는 존재하지만 판단 결과는 false가 되기 때문이다.

- 이 외에도 연산자 `in`을 사용하면 프로퍼티 존재 여부를 확인할 수 있다.

- `"key" in object`

- ```js
  let obj = {
    test: undefined
  };
  
  alert( obj.test ); // 값이 `undefined`이므로, 얼럿 창엔 undefined가 출력된다. 그런데 프로퍼티 test는 존재한다.
  
  alert( "test" in obj ); // `in`을 사용하면 프로퍼티 유무를 제대로 확인할 수 있다(true가 출력됨).
  ```

### `for...in` 반복문

- `for...in` 반복문을 사용하면 **객체의 모든 키를 순회**할 수 있다.

- 문법

  ```js
  for (key in object) {
    // 각 프로퍼티 키(key)를 이용하여 본문(body)을 실행한다.
  }
  ```

- 반복 변수 명은 자유롭게 정할 수 있다.

#### 객체 정렬 방식

- 객체는 **특별한 방식으로 정렬된다.** 정수 프로퍼티(integer property)는 자동으로 정렬되고, **그 외**의 프로퍼티는 **객체에 추가한 순서 그대로 정렬된다.**

  ```js
  let codes = {
    "49": "독일",
    "41": "스위스",
    "44": "영국",
    // ..,
    "1": "미국"
  };
  
  for (let code in codes) {
    alert(code); // 1, 41, 44, 49
  }
  ```

  

- 정수 프로퍼티: **변형 없이** 정수에서 왔다 갔다 할 수 있는 문자열

- 위 예시에서 객체에 추가한 순서대로 정렬하기 위해서는 각 나라 번호가 정수로 취급되지 않도록 각 나라 번호 앞에 `"+"`를 붙이면 된다.

자바스크립트에는 일반 객체 외에도 다양한 종류의 객체가 있다.

- `Array`: 정렬된 데이터 컬렉션을 저장할 때 쓰인다.
- `Date`: 날짜와 시간 정보를 저장할 때 쓰인다.
- `Error`: 에러 정보를 저장할 때 쓰인다.

Array와 Date은 객체형에 속한다. 객체에 다양한 기능을 넣어 확장한 또 다른 객체이다. 



### 과제

#### 객체야 안녕?

```js
let user = {};
user.name = "John";
user.surname = "Smith";
user.name = "Pete";
delete user.name;
```

#### 객체가 비어있는지 확인하기

```js
let schedule = {};

alert( isEmpty(schedule) ); // true

schedule["8:30"] = "get up";

alert( isEmpty(schedule) ); // false
```

```js
function isEmpty(obj) {
  for (let key in obj) {
    return false;
  }
  return true;
}
```

#### 프로퍼티 합계 구하기

```js
let salaries = {
  John: 100,
  Ann: 160,
  Pete: 130
}

let sum = 0;

for (let key in salaries) {
  sum += salaries[key]; 
}

alert(sum);
```

#### 프로퍼티 값 두 배로 부풀리기

```js
function multiplyNumeric(obj) {
  for (let key in obj) {
    if (typeof obj[key] === "number") {
      obj[key] *= 2;
    }
  }
}
```



## 4.2. 참조에 의한 객체 복사

- 객체는 **참조에 의해(by reference) 저장되고 복사**된다.
- 원시값은 **값 그대로** 저장, 할당되고 복사된다.
- **변수에는 객체가 그대로 저장되는 것이 아니라, ** 객체가 저장되어 있는 **메모리 주소**인 객체에 대한 **참조 값**이 저장된다. 
- 따라서 객체가 할당된 **변수를 복사**할 때는 객체의 **참조 값이 복사**되고 **객체는 복사되지 않는다.**
- **객체에 접근하거나 객체를 조작할 때는 여러 변수를 사용**할 수 있다.

**참조에 의한 비교**

- 객체 비교 **시 동등 연산자 `==`와 일치 연산자 `===`는 동일하게 동작**한다.

- 두 변수가 같은 객체를 참조할 때 일치, 동등 비교 모두 참이 반환된다.

- 독립된 객체이면 일치, 동등 비교 시 거짓이 반환된다.

  ```js
  let a = {};
  let b = {};
  
  alert (a == b); // false
  ```

- `obj1 > obj2` 같은 **대소 비교**나 `obj == 5` 같은 **원시값과의 비교**에서는 **객체가 원시형으로 변환**된다.

### 객체 복사, 병합과 Object.assign

- 자바스크립트는 객체 복제 내장 메서드를 지원하지 않는다.

- 새로운 객체를 만든 후 기존 객체의 프로퍼티를 순회해 원시 수준까지 프로퍼티를 복사하면 된다.

  ```js
  let user = {
    name: "John",
    age: 30
  };
  
  let clone = {};
  
  for (let key in user) {
    clone[key] = user[key];
  }
  ```

- `Object.assign`을 이용한다.

  ```js
  Object.assgin(dest, [src1, src2, src3...]);
  ```

  - `dest`: 목표로 하는 객체
  - `src1, src2, ..., srcN`: 복사하고자 하는 객체
  - 객체 `src1, ..., srcN`의 프로퍼티를 `dest`에 복사한다. `dest`를 제외한 인수의 프로퍼티 전부가 첫 번째 인수(객체)로 복사된다.
  - 마지막으로 `dest`를 반환한다.

  ```js
  let user = { name: "John" };
  
  let permission1 = { canView: true };
  let permission2 = { canEdit: true };
  
  // permission1과 permission2의 프로퍼티를 user로 복사한다.
  Object.assign(user, permission1, permission2);
  
  // now user = { name: "John", canView: true, canEdit: true };
  ```

  만약 목표 객체에 **동일한 이름을 가진 프로퍼티가 있는 경우 기존 값이 덮어씌워진다.**

  ```js
  let user = { name: "John" };
  
  Object.assign(user, { name: "Pete" });
  // user = { name: "Pete" }
  ```

  `Object.assign`을 사용하면 반복문 없이도 간단하게 객체를 복사할 수 있다.

  ```js
  let user = {
    name: "John",
    age: 30
  };
  
  let clone = Object.asign({}, user);
  ```

### 중첩 객체 복사

프로퍼티가 다른 객체에 대한 참조값일 경우 프로퍼티를 복사하는 것만으로 객체를 복사할 수 없다. 프로퍼티의 참조값이 복사되기 때문이다. 즉 복사된 객체와 복사한 객체가 같은 프로퍼티를 공유하게 된다.

이 문제를 해결하기 위해서는 `obj[key]`의 각 값을 검사하면서, 그 값이 객체인 경우 객체의 구조도 복사해주는 반복문을 사용한다. 이런 방식을 **깊은 복사(deep cloning)**이라고 한다. 이를 직접 구현하거나 자바스크립트 라이브러리 loadash의 `_.cloneDeep(obj)` 메서드를 사용한다. 


## 4.3. 가비지 컬렉션

### 가비지 컬렉션 기준

- **도달 가능한(reachable)** 값은 메모리에서 삭제되지 않는다.
- 도달 가능한(reachable) 값은 어떻게든 접근하거나 사용할 수 있는 값을 의미한다.
- 루트(root): 태생부터 도달 가능하기 때문에 명백한 이유 없이 삭제되지 않는 값
  - 현재 함수의 지역변수와 매개변수
  - 중첩 함수의 체인에 있는 함수에서 사용되는 변수와 매개변수
  - 전역 변수
- 루트가 참조하는 값이나 체이닝으로 루트에서 참조할 수 있는 값은 도달 가능한 값이 된다. 
- 자바스크립트 엔진 내에서는 가비지 컬렉터가 끊임없이 동작한다.
- 가비지 컬렉터는 모든 객체를 모니터링하고, 도달할 수 없는 객체는 삭제한다. 

### 연결된 객체

```js
function marry(man, woman) {
  woman.husband = man;
  man.wife = woman;
  
  return {
    father: man,
    mother:woman
  }
}

let family = marry({
  name: "John"
}, {
  name: "Ann"
});
```

함수 `marry`(결혼하다)는 매개변수로 받은 두 객체를 서로 참조하게 하면서 '결혼'시키고, 두 객체를 포함하는 새로운 객체를 반환한다. 모든 객체가 도달 가능한 상태다. 이때 참조 두개를 지워보겠다.

```js
delete family.father;
delete family.mother.husband;
```

John으로 들어오는 참조는 모두 사라져 도달 가능한 상태에서 벗어난다. John은 wife란 이름으로 Ann을 가리키고 있긴 하지만, **외부로 나가는 참조는 도달 가능한 상태에 영향을 주지 않는다.** 외부에서 **들어오는 참조만이 도달 가능한 상태에 영향을 준다.** John에 저장된 데이터(프로퍼티) 역시 메모리에서 사라진다.

### 도달할 수 없는 섬

객체들이 연결되어 섬 같은 구조를 만드는데, 이 섬에 도달할 방법이 없는 경우, 섬을 구성하는 객체 전부가 메모리에서 삭제된다.

```js
family = null;
```

"family" 객체와 루트의 연결이 사라지면 루트 객체를 참조하는 것이 아무것도 없게 된다. 섬 전체가 도달할 수 없는 상태가 되고, 섬을 구성하는 객체 전부가 메모리에서 제거된다. 

### 내부 알고리즘

'mark-and-sweep'이라 불리는 가비지 컬렉션 기본 알고리즘

- 가비지 컬렉터는 루트(root) 정보를 수집하고 이를 'mark(기억)'한다.
- 루트가 참조하고 있는 모든 객체를 방문하고 이것들을 'mark'한다.
- mark된 모든 객체에 방문하고 *그 객체들이* 참조하는 객체도 mark한다. 한번 방문한 객체는 전부 mark하기 때문에 같은 객체를 다시 방문하는 일은 없다.
- 루트에서 도달 가능한 모든 객체를 방문할 때까지 위 과정을 반복한다.
- mark되지 않은 모든 객체를 메모리에서 삭제한다.

**루트에서 페인트를 들이부어 루트를 시작으로 참조를 따라가면서 도달 가능한 객체 모두에 페인트가 칠해진다고 생각하면 된다.** 이때 **페인트가 묻지 않은 객체는 메모리에서 삭제**된다.

**최적화 기법**

- generational collection(세대별 수집): 객체를 새로운 객체와 오래된 객체로 나눈다. 
  - 객체 상당수는 생성 이후 제 역할을 빠르게 수행해 금방 쓸모가 없어지는데, 이를 '새로운 객체'로 구분한다. 
  - 반면 일정 시간 이상 살아남은 객체는 '오래된 객체'로 분류한다. 
  - 가비지 컬렉터는 **새로운 객체를 공격적으로 메모리에서 제거하고, 오래된 객체는 덜 감시한다.**
- incremental collection(점진적 수집)
  - 가비지 컬렉션을 **여러 부분으로 분리한 다음, 각 부분을 별도로 수행**한다. 
  - 긴 지연을 짧은 지연 여러 개로 분산시킨다.
- idle-time collection(유후 시간 수집)
  - 실행에 주는 영향을 최소화하기 때문에 **CPU가 유휴 상태일 때만 가비지 컬렉션을 실행**한다.

## 4.4. 메서드와 this

- 객체는 사용자, 주문 등 실제 존재하는 개체(entity)를 표현하고자 할 때 생성된다.
- 사용자는 현실에서 장바구니에서 물건 선택하기, 로그인하기 등 행동을 한다. 마찬가지로 사용자를 나타내는 객체 user도 특정한 **행동**을 할 수 있다.
- 자바스크립트에서는 **객체의 프로퍼티에 함수를 할당해 객체에게 행동할 수 있는 능력을 부여**한다.

### 메서드 만들기

객체 `user`에게 인사할 수 있는 능력을 부여한다.

```js
let user = {
  name: "John",
  age: 30
};

user.sayHi = function() {
  alert("안녕하세요!");
};

user.sayHi(); // 안녕하세요!
```

- **객체 프로퍼티에 할당된 함수**를 **메서드(method)**라고 부른다.

- 메서드는 이미 정의된 함수를 이용해서 만들 수도 있다.

  ```js
  function sayHi() {
    alert("안녕하세요!");
  };
  
  user.sayHi = sayHi;
  
  user.sayHi(); // 안녕하세요!
  ```

- 메서드 단축 구문을 사용하면 객체 리터럴 안에 메서드를 선언할 수 있다.

  ```js
  user = {
    sayHi: function() {
      alert("Hello");
    }
  };
  
  // 단축 구문
  user = {
    sayHi() { // "sayHi: function()"과 동일
      alert("Hell");
    }
  }
  ```

  

### 메서드와 `this`

- **메서드 내부에서 `this` 키워드를 사용하면 객체에 접근**할 수 있다.

  이때 점 앞의 `this`는 메서드를 호출할 때 사용된 객체를 나타낸다. (`user.sayHi()`가 실행되는 동안에 `this`는 `user`를 나타냅니다.)

  ```js
  let user = {
    name: "John",
    age: 30,
    
    sayHi() {
      // 'this'는 '현재 객체'를 나타낸다.
      alert(this.name);
    }
    
  };
  
  user.sayHi(); // John
  ```

- `this`를 사용하지 않고 외부 변수를 참조해 객체에 접근하는 것도 가능하다.

- 그러나 외부 변수를 사용해 객체를 참조하면 예상치 못한 에러가 발생할 수 있다. 

  ```js
  let user = {
    name: "John",
    age: 30,
  
    sayHi() {
      alert( user.name ); // Error: Cannot read property 'name' of null
    }
  
  };
  
  
  let admin = user;
  user = null; // user를 null로 덮어 쓴다.
  
  admin.sayHi(); // sayHi()가 엉뚱한 객체를 참고하면서 에러가 발생했다.
  ```

### 자유로운 `this`

- 자바스크립트에서는 모든 함수에 `this`를 사용할 수 있다.

- `this` 값은 **런타임에 결정**된다. **컨텍스트에 따라 달라진다.**

  **동일한 함수라도 다른 객체에서 호출했다면 `this`가 참조하는 값이 달라진다.** 

  ```js
  let user = { name: "John" };
  let admin = { name: "Admin" };
  
  function sayHi() {
    alert( this.name );
  }
  
  // 별개의 객체에서 동일한 함수를 사용함
  user.f = sayHi;
  admin.f = sayHi;
  
  // 'this'는 '점(.) 앞의' 객체를 참조하기 때문에
  // this 값이 달라짐
  user.f(); // John (this == user);
  admin.f(); // Admin (this == admin);
  
  admin['f'](); // Admin (점과 대괄호는 동일하게 동작함)
  ```

- 객체 없이 호출하면 `this == undefined`

  ```js
  sayHi(); // undefined
  ```

  엄격 모드에서 실행하면 `this`에는 `undefined`가 할당된다. 

  엄격 모드가 아닐 때는 `this`가 전역 객체(브라우저에서는 `window`)를 참조한다.

**자유로운 `this`가 만드는 결과**

-  **bound this**: 다른 언어에서 `this`는 항상 메서드가 정의된 객체를 참조한다.
- 자바스크립트에서 `this`는 런타임 결정되고, 메서드가 어디서 정의되었는지 상관 없이 `this`는 점 앞의 객체가 무엇인가에 따라 자유롭게 결정된다. 
- **함수를 하나만 만들어 여러 객체에서 재사용**할 수 있다는 것은 장점이지만, 이런 **유연함이 실수로 이어질 수 있다**는 단점이 있다.

### 'this'가 없는 화살표 함수

- 화살표 함수는 일반 함수와 달리 '고유한' `this`를 가지지 않는다.

- **화살표 함수에서 `this`를 참조**하면 화살표 함수가 아닌 **평범한 외부 함수에서 `this` 값을 가져온다.**

  ```js
  let user = {
    firstName: "보라",
    sayHi() {
      let arrow = () => alert(this.firstName);
      arrow();
    }
  };
  
  user.sayHi(); // 보라
  ```

- 별개의 `this`가 만들어지는 것은 원치 않고, **외부 컨텍스트에 있는 `this`를 이용하고 싶은 경우 화살표함수가 유용하다.** 



### 과제

#### 객체 리터럴에서 'this' 사용하기

```js
function makeUser() {
  return {
    name: "John",
    ref: this
  };
};

let user = makeUser(); // { name: "John", ref: undefined }

alert( user.ref.name ); // Error: Cannot read property 'name' of undefined
```

`this` 값은 호출 시점에 결정된다. `makeUser()`가 메서드로써 호출된 게 아니라 함수로써 호출되었기 때문에 `this`는 undefined가 된다. 

에러가 발생하지 않게 하려면 코드를 다음과 같이 수정한다.

```js
function makeUser() {
  return {
    name: "John",
    ref() {
      return this;
    }
  };
};

let user = makeUser();

alert( user.ref().name ); // John
```

#### 계산기 만들기

```js
let calculator = {
  
  read() {
  	this.firstParam = +prompt("첫 번째 피연산자", "");
    this.secondParam = +prompt("두 번째 피연산자", "");
  },
  
  sum() {
    return this.firstParam + this.secondParam;
  },
  
  mul() {
    return this.firstParam * this.secondParam;
  }
  
};

calculator.read();
alert( calculator.sum() );
alert( calculator.mul() );
```

#### 체이닝

```js
let ladder = {
  step: 0,
  up() {
    this.step++;
  },
  down() {
    this.step--;
  },
  showStep: function() { // 사다리에서 몇 번째 단에 올라와 있는지 보여줌
    alert( this.step );
  }
};
```

메서드를 연이어 호출하려면 다음과 같이 해야 한다.

```js
ladder.up();
ladder.up();
ladder.down();
ladder.showStep(); // 1
```

`up`, `down`, `showStep`을 수정해 메서드 호출 체이닝이 가능하게 해보자.

```js
ladder.up().up().down().showStep(); // 1
```

해답

```js
let ladder = {
  step: 0,
  up() {
    this.step++;
    return this;
  },
  down() {
    this.step--;
    return this;
  },
  showStep: function() { // 사다리에서 몇 번째 단에 올라와 있는지 보여줌
    alert( this.step );
    return this;
  }
};
```



## 4.5. `new` 연산자와 생성자 함수

`new` 연산자와 생성자 함수를 사용하면 유사한 객체 여러 개를 쉽게 만들 수 있다.

### 생성자 함수(construction function)

- 함수의 이름의 첫 글자는 대문자로 시작한다.
- 반드시 `new` 연산자를 붙여 실행한다.

```js
function User(name) {
  // this = {}; (빈 객체가 암시적으로 만들어짐)
  this.name = name;
  this.isAdmin = false;
  // return this; (this가 암시적으로 반환됨)
}

let user = new User("Jack");

alert(user.name); // Jack
alert(user.isAdmin); // false
```

`new User(...)`를 써서 함수를 실행하면 아래와 같은 **알고리즘이 동작**한다.

- **빈 객체를 만들어 `this`에 할당**한다.
- **함수 본문을 실행**한다. **`this`에 새로운 프로퍼티를 추가해 `this`를 수정**한다.
- **`this`를 반환**한다.

`let user = new User("Jack")`은 아래 코드를 입력한 것과 동일하게 동작한다.

```js
let user = {
  name = "Jack";
  isAdmin = false;
};
```

- **객체 리터럴 문법으로 일일이 객체를 만드는 방법보다 훨씬 간단하고 읽기 쉽게 객체를 만들 수 있다.**(`new User("Ann")`, `new User("Alice")`) 

- 생성자는 재사용할 수 있는 객체 생성 코드를 구현할 수 있는 데에 의의가 있다. 

- 모든 함수는 생성자가 될 수 있다. `new`를 붙여 실행하면 어떤 함수라도 위에 언급된 알고리즘이 실행된다.

- **재사용할 필요가 없는 복잡한 객체**는 **익명 생성자 함수로 감싸주는 방식**을 사용한다.

  ```js
  let user = new function() {
    this.name = "John";
    this.isAdmin = false;
    // 사용자 객체를 만들기 위한 여러 코드
    // 지역변수, 복잡한 로직, 구문 등의 다양한 코드가 들어간다.
  }
  ```

  

### `new.target`과 생성자 함수

- `new.target` 프로퍼티를 사용하면 함수가 `new`와 함께 호출되었는지 아닌지를 알 수 있다.
- 일반적인 방법으로 함수를 호출했다면 `undefined`를, `new`와 함께 호출한 경우에는 함수 자체를 반환한다.

```js
function User() {
  alert(new.target);
}

User(); // undefined

new User(); // function User() {...}
```

이를 활용해 **일반적인 방법으로 함수를 호출해도 `new`를 붙여 호출한 것과 같이 동작하게 만들 수 있다.**

```js
function User(name) {
  if (!new.target) { // new 없이 호출해도
    return new User(name); // new를 붙여준다.
  }
  
  this.name = name;
}

let john = User("John"); // new User를 쓴 것처럼 바꿔준다.
alert(john.name); //John
```

`new`를 생략하면 코드가 정확히 무슨 일을 하는지 알기 어렵다. 그러므로 이 방법은 정말 필요한 경우에만 사용하고 남발하지 않는 것이 좋다.

### 생성자와 return 문

생성자 함수에는 보통 `return`문이 없다. 반환해야 할 것은 모두 `this`에 저장되고 `this`는 자동으로 반환되기 때문에 반환문을 명시적으로 써 줄 필요가 없다. 

만약 `return` 문이 있다면

- 객체를 `return`한다면 `this` 대신 객체가 반환된다.
- 원시형을 `return`한다면 `return`문이 무시된다.

`return` 뒤에 객체가 오면 생성자 함수는 해당 객체를 반환해주고, 이 외의 경우는 `this`가 반환된다.

### 괄호 생략하기

**인수가 없는 생성자 함수는 괄호를 생략해 호출할 수 있다.** 

```js
let user = new User;
// 아래 코드는 위 코드와 똑같이 동작한다.
let user = new User();
```

### 생성자 내 메서드

**생성자 함수를 사용하면 매개변수를 이용해 객체 내부를 자유롭게 구성**할 수 있기 때문에 엄청난 유연성이 확보된다. 이때 **프로퍼티뿐만 아니라 메서드를 `this`에 더해주는 것도 가능하다.**

```js
function User(name) {
  this.name = name;
  
  this.sayHi = function() {
    alert("My name is: " + this.name);
  };
}

let john = new User("John");

john.sayHi(); // My name is: John

/*
john = {
	name: "John";
	sayHi: function() {...}
}
*/
```

class 문법을 사용하면 생성자 함수를 사용하는 것과 마찬가지로 복잡한 객체를 만들 수 있다.

자바 스크립트는 언어 차원에서 다양한 생성자 함수를 제공한다. `Date`, `Set` 등 내장 객체는 이런 생성자 함수를 이용해 만들 수 있다.



### 과제

#### 함수 두 개로 동일한 객체 만들기

```js
let obj = {};

function A() {
  return obj;
}

function B() {
  return obj;
}
```

`this` 대신 객체를 반환하게 하면 된다.

#### 계산기 만들기

```js
function Calculator() {
  this.read = function() {
    this.a = +prompt("첫 번째 값을 입력하세요.");
    this.b = +prompt("두 번째 값을 입력하세요.");
  };
  
  this.sum = function() {
    return a + b;
  };
  
  this.mul = function() {
    return a * b;
  };
}
```

#### 누산기 만들기

```js
function Accumulator(startingValue) {
  this.value = startingValue;
  
  this.read = function () {
    this.value += +prompt("value", 0);
  };
}
```



## 4.6. 옵셔널 체이닝 '?."

옵셔널 체이닝(optional chaining) `?.`을 사용하면 프로퍼티가 없는 중첩 객체를 에러 없이 안전하게 접근할 수 있다.

### 옵셔널 체이닝이 필요한 이유

undefined 값의 프로퍼티를 읽으려 할 때 에러 발생

```js
let user = {}; // 주소 정보가 없는 사용자.
alert(user.address.street); // TypeError: Cannot read property 'street' of undefined
```

null 값의 프로퍼티를 읽으려 할 때 에러 발생

```js
// 페이지에 존재하지 않는 요소 접근
let html = document.querySelector(".my-element").innerHTML;
```

이 문제를 해결하기 위해 기존에는 `&&`를 사용하곤 했다.

```js
let user = {};
alert( user && user.address && user.address.street ); // undefined, 에러가 발생하지 않는다.
```

### 옵셔널 체이닝

평가후 결과가 `null`이나 `undefined`가 아닌 경우 값이 '있다', '존재한다'라고 표현한다.

`user.address.street`에 안전하게 접근해보자.

```js
let user = {}; // 주소 정보가 없는 사용자
alert (user?.address?.street); // undefined, 에러가 발생하지 않는다.
```

**`?.`은 `?.` '앞' 평가 대상에만 동작되고, 확장은 되지 않는다.**

```js
let user = null;

alert( user?.address ); // undefined
alert( user?.address.street ); // undefined
```

한편, `?.`는 존재하지 않아도 괜찮은 대상에만 사용해야 한다. 사용자 주소를 다루는 예시에서 논리상 `user`는 반드시 있어야 하는데, `address`는 필수값이 아니다. 그러니 `user.address?.street`을 사용하는 것이 바람직하다. 

`?.` 앞의 변수는 꼭 선언되어 있어야 한다. 변수 `user`가 선언되어 있지 않다면 `user?.anything` 평가 시 에러가 발생한다. 

옵셔널 체이닝은 선언된 변수에만 작동한다. 

### 단락 평가

`?.`은 왼쪽 평가 대상에 값이 없으면 즉시 평가를 멈추는 **단락 평가(short-circuit)**을 한다. 그렇기에 함수 호출을 비롯한 `?.` 오른쪽에 있는 부가 동작은 `?.`의 평가가 멈췄을 때 더는 일어나지 않는다.

```js
let user = null;
let x = 0;

user?.sayHi(x++); // 아무 일도 일어나지 않는다.
alert(x); // 0, x는 증가하지 않는다.
```

### `?.()`와 `?.[]`

- `?.`는 연산자가 아니라 함수나 대괄호와 함께 동작하는 특별한 **문법 구조체(syntax construct)**이다.

- `?.()`를 사용해 존재 여부를 확인하고 메서드를 호출한다.

  ```js
  let user1 = {
    admin() {
      alert("관리자 계정입니다.");
    }
  }
  
  let user2 = {};
  
  user1.admin?.(); // 관리자 계정입니다.
  user2.admin?.(); // 에러 없이 평가가 멈춘다.
  ```

- `?.[]`를 사용하면 객체 존재 여부가 확실치 않은 경우에도 안전하게 프로퍼티를 읽을 수 있다.

  ```js
  let user1 = {
    firstName: "Violet"
  };
  let user2 = null; //user2는 권한이 없는 사용자라고 가정해보자. 
  
  let key = "firstName";
  
  alert( user1?.[key] ); // Violet
  alert( user2?.[key] ); // undefined
  
  alert ( user1?.[key]?.something?.not?.existing ); // undefined
  ```

- `?.`은 `delete`와 조합해 사용할 수 있다.

  ```js
  delete user?.name; //user가 존재하면 user.name을 삭제한다.
  ```

- `?.`은 읽기나 삭제하기에는 사용할 수 있지만 **쓰기에는 사용할 수 없다.**

  ```js
  user?.name = "Violet"; // SyntaxError: Invalid left-hand side in assignment
  // 에러가 발생하는 이유는 undefined = "Violet"이 되기 때문이다.
  ```

  

## 4.7 심볼형

- 객체 프로퍼티 키로 오직 문자형과 심볼형만 가능하다. 

- `Symbol`은 원시형 데이터로, **유일한 식별자**(unique identifier)를 만드는 데 사용한다.

- `Symbol()`을 호출하면 심볼을 만들 수 있다. 설명(이름)은 선택적으로 추가할 수 있다. 심볼 이름은 디버깅 시 유리하다.

- 심볼은 유일성이 보장되는 자료형이기 때문에, 설명(이름)이 같더라도 값이 항상 다르다. 심볼 설명은 어떤 것에도 영향을 주지 않는 이름표 역할만을 한다.

- **심볼형은 다른 자료형으로 암시적 형변환(자동 형변환)되지 않는다.** 자바스크립트에서는 '언어 차원의 보호 장치(language guard)'를 마련해 심볼형이 다른 형으로 변환되지 않게 막아준다. 심볼을 반드시 출력해줘야 할 경우 `toString()`을 명시적으로 호출해주자. `symbol.description` 프로퍼티를 이용하면 설명만 보여주는 것도 가능하다.

- **이름이 같을 때 값도 같**길 원한다면 **전역 레지스트리**를 사용해야 한다. `Symbol.for(key)` 는 `key`라는 이름을 가진 전역 심볼을 반환한다. **`key`라는 이름의 전역 심볼이 없으면 새로운 전역 심볼을 만들어준다.** `key`가 같다면 `Symbol.for`은 어디서 호출하든 상관없이 항상 같은 심볼을 반환해준다. 전역 심볼 레지스트리 안에 있는 심볼은 *전역 심볼*이라고 불린다. 

  전역 심볼을 찾을 때 사용되는 `Symbol.for(key)`에 반대되는 `Symbol.keyFor(sym)`을 사용하면 이름을 얻을 수 있다. 전역 심볼이 아닌 인자가 넘어오면 `Symbol.keyFor`은 `undefined`를 반환한다. 

  ```js
  // 이름을 이용해 심볼을 찾음
  let sym = Symbol.for("name");
  
  // 심볼을 이용해 이름을 얻음
  alert( Symbol.keyFor(sym) ); // name
  ```

  전역 심볼이 아닌 모든 심볼은 `description` 프로퍼티가 있기 때문에 일반 심볼에서 이름을 얻고 싶다면 `description` 프로퍼티를 사용한다. 

**심볼의 주요 유스테이스**

- **객체의 '숨김(hidden)' 프로퍼티**

  숨김 프로퍼티는 외부 코드에서 접근이 불가능하고 값도 덮어쓸 수 없는 프로퍼티이다. 

  외부 스크립트나 라이브러리에 '속한' 객체에 새로운 프로퍼티를 추가해 주고 싶다면 심볼을 만들고, 이를 프로퍼티 키로 사용하면 된다. 

  ```js
  let user = { // 서드파티 코드에서 가져온 객체
    name: "John"
  };
  
  let id = Symbol("id");
  user[id] = 1;
  alert( user[id] ); // 심볼을 키로 사용해 데이터에 접근할 수 있다.
  ```

  서드파티 코드에서 가지고 온 객체는 함수로 새로운 프로퍼티를 추가할 수 없는데, 심볼을 사용하면 서드파티 코드가 모르게 해당 객체에 식별자를 부여할 수 있기 때문이다. 

  심볼은 유일성이 보장되므로 개발자가 만든 식별자와 제3의 스크립트에서 만든 식별자가 이름이 같더라도 충돌하지 않는다. 만약 심볼 대신 문자열을 사용해 식별자를 만들었다면 *충돌이 발생할 가능성*이 있다.

  ```js
  let id = Symbol("id");
  user[id] = "제3 스크립트 id값"
  ```

  ```js
  let user = { name: "John" };
  // 우리가 만든 문자열 식별자
  user.id = "스크립트 id 값";
  // 제3의 스크립트가 만든 식별자
  // 값이 덮어 쓰여서 위에서 만든 식별자는 무의미해진다.
  user.id = "제3 스크립트 id 값";
  ```

  이런 특징을 이용하면 원하는 것을 객체 안에 '은밀하게' 숨길 수 있다. 외부 스크립트에선 우리가 숨긴 것을 절대 볼 수 없다.

- 객체리터럴을 사용해 객체를 만든 경우, 대괄호를 사용해 심볼형 키를 만들어야 한다.

  ```js
  let id = Symbol("id");
  
  let user = {
    name: "John",
    [id]: 123
  }
  ```

- **심볼형 프로퍼티 숨기기(hiding symbolic property)** 원칙: 키가 심볼인 프로퍼티는 `for...in`에서 배제된다. `Object.keys(user)`에서도 키가 심볼인 프로퍼티는 배제된다.

  이 원칙 덕분에 외부 스크립트나 라이브러리는 심볼형 키를 가진 프로퍼티에 접근하지 못한다.
  
- `Object.assgin`은 **키가 심볼인 프로퍼티를 배제하지 않고 객체 모든 프로퍼티를 복사**한다. 

- **자바스크립트 내부에서 사용**되는 **시스템 심볼**은 `Symbol.*`로 접근할 수 있다. 시스템 심볼을 이용하면 내장 메서드 등 **객체의 기본 동작을 변경할 수 있다**. 

  - `Symbol.hasInstance`
  - `Symbol.isConcatSpreadable`
  - `Symbol.iterator`
  - `Symbol.toPrimitive`

심볼을 완전히 숨길 방법은 없다. 내장 메서드 `Object.getOwnPropertySymbols(obj)`를 사용하면 모든 심볼을 볼 수 있고, 메서드 `Reflect.ownKeys(obj)`는 심볼형 키를 포함한 객체의 *모든 키*를 반환해준다. 그런데 대부분의 라이브러리, 내장 함수 등은 이런 메서드를 사용하지 않는다. 

## 객체를 원시형으로 변환하기

객체는 논리 평가 시 `true`를 반환한다. 객체는 숫자형이나 문자형으로만 형변환이 일어난다고 생각하면 된다.

원시값을 기대하는 내장함수나 연산자를 사용할 때 객체-원사형으로의 형 변환이 자동으로 일어난다.

특수 객체 메서드를 사용하면 숫자형이나 문자형으로의 형변환을 원하는 대로 조절할 수 있다. 객체-원시형으로의 형 변환은 hint라고 불리는 값을 기준으로 세 종류로 구분할 수 있다. hint는 목표로 하는 자료형이다. 

- `"string"`: `alert` 같이 문자열을 필요로 하는 연산, 객체를 프로퍼티 키로 사용할 때(`anotherObj[obj]-123`)

- `"number"`: 명시적 형변환(`Number(obj)`), 수학 연산, 크고 작음 비교하기

  ex) 크고 작음을 비교할 때 쓰이는 연산자 `<`, `>`는 피연산자에 문자형과 숫자형을 다 허용하는데, 이 연산자들은 hint를 default가 아닌 number로 고정한다. (하위 호환성 때문에 정해진 규칙)

- `"default"`: 연산자가 기대하는 자료형이 '확실치 않을 때'. 드물게 발생

  ex) 이항덧셈연산자 `+`: 피연산자의 자료형에 따라 문자열을 합치는 연산을 할 수도 있고, 숫자를 더해주는 연산을 할 수도 있다.

  ex) 동등 연산자 `==`를 사용해 객체-문자형, 객체-숫자형, 객체-심볼형끼리 비교할 때도, 객체를 어떤 자료형으로 바꿔야 할  지 확신이 안 서므로 hint는 default가 된다. 

내장 객체는 대개 hint가 `"default"`일 때와 `"number"`일 때를 동일하게 처리한다. 따라서 실무에서는 hint가 `"default"`인 경우와 `"number"`인 경우를 합쳐서 처리하는 경우가 많다.

`"boolean"` hint는 존재하지 않는다. 모든 객체는 그냥 `true`로 평가된다. 우리도 내장 객체에 사용되는 규칙처럼 `"default"`와 `"number"`를 동일하게 처리하면, 결국엔 두 종류의 형변환(객체-문자형, 객체-숫자형)만 남게 된다. 

객체-원시형 변환엔 다음의 알고리즘이 적용된다.

- 객체엔 `obj[Symbol.toPrimitive](hint)` 메서드가 있는지 찾고, 있다면 호출한다. 

- 1에 해당하지 않고 hint가 `"string"`이라면

  `obj.toString()`이나 `obj.valueOf`를 호출한다. (존재하는 메서드만 실행됨)

- 1과 2에 해당하지 않고 hint가 `"number"`나 `"default"`라면

  `obj.valueOf()`나 `obj.toString()`을 호출한다. (존재하는 메서드만 실행됨)

**Symbol.toPrimitive**

자바스크립트에는 `Symbol.toPrimitive`라는 내장 심볼이 존재하는데, 이 심볼은 목표로 하는 자료형(hint)을 명명하는 데 사용된다.

```js
obj[Symbol.toPrimitive] = function(hint) {
  // 반드시 원시값을 반환해야 한다. 
  // hint는 "string", "number", "default" 중 하나가 될 수 있다. 
}
```

예시: `user` 객체에 객체-원시형 반환 메서드 구현하기

```js
let user = {
  name: "John",
  money: 1000,
  
  [Symbol.toPrimitive](hint) {
    alert(`hint: ${hint}`);
    return hint == "string" ? `{name: "${this.name}"}` : this.money;
  }
};

// 데모:
alert(user); // hint: string -> {name: "John"}
alert(+user); // hint: number -> 1000
alert(user + 500); // hint: default -> 1500
```

**toString과 valueOf**

이 메서들은 반드시 원시값을 반환해야 한다. 객체를 반환하면 그 결과는 무시된다. 일반 객체는 기본적으로 `toString`과 `valueOf`에 적용되는 다음 규칙을 따른다.

- `toString`은 문자열 `"[object Object]"`를 반환한다.
- `valueOf`는 객체 자신을 반환한다. 

예시: `user`는 `toString`과 `valueOf`를 조합해 만들었다. `Symbol.toPrimitive`를 사용한 예시와 동일하게 동작한다.

```js
let user = {
  name: "John",
  money: 1000,
  
  // hint가 "string"인 경우
  toString() {
    return `{name: "${this.name}"}`;
  },
  // hint가 "number"나 "default"인 경우
  valueOf() {
    return this.money;
  }
}
```

간혹 모든 형변환을 한 곳에서 처리해야 하는 경우도 생긴다. 이럴 땐 `toString`만 구현해준다.

```js
let user = {
  name: "John",
  
  toString() {
    return this.name;
  }
};

alert(user); // toString -> John
alert(user + 500); // toString -> John500
```

`obj.toString()`만 사용해도 '모든 변환'을 다 다룰 수 있기 때문에, 실무에서는 `obj.toString()` 만 구현해도 충분한 경우가 많다. 반환 값도 '사람이 읽고 이해할 수 있는' 형식이기 때문에 실용성 측면에서 다른 메서드에 뒤쳐지지 않는다. `obj.toString()`은 로깅이나 디버깅 목적으로 자주 사용된다.

**반환 타입**

위 세 개의 메서드는 'hint'에 명시된 자료형으로의 형변환을 보장해주지 않는다. 확신할 수 있는 단 한 가지는 객체가 아닌 원시값을 반환해준다는 것 뿐이다. 

**추가 형변환**

객체가 피연산자일 때는 다음과 같은 단계를 거쳐 형변환이 일어난다.

- 객체를 원시형으로 변환한다.
- 변환 후 원시값이 원하는 형이 아닌 경우 또 다시 형변환이 일어난다.

```js
let obj = {
  toString() {
    return "2";
  }
};

alert(obj * 2); // 4, 객체가 문자열 "2"로 바뀌고, 곱셈 연산 과정에서 문자열 "2"는 숫자 2로 변경된다.
```

