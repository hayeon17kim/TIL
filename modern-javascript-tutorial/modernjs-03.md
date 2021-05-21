## 3.1. Chrome으로 디버깅하기

- 디버깅(debugging): **스크립트 내 에러를 검출해 제거**하는 일련의 과정
- F12: 개발자 도구 여는 단축키

### Source 패널

- **파일 탐색 영역**: 페이지를 구성하는 데 쓰인 모든 리소스(HTML, JavaScript, CSS, 이미지 파일 등)를 트리 형태로 보여준다. Chrome 익스텐션이 여기 나타날 때도 있다.
- **코드 에디터 영역**: 리소스 영역에서 선택한 파일의 소스 코드를 보여준다. 여기서 **소스 코드를 편집할 수도 있다.**
- **자바스크립트 디버깅 영역**: **디버깅**에 관련된 기능을 제공한다.

### 콘솔

콘솔 창에 구문(statement)을 입력하고 실행하면 아랫줄에 실행 결과가 출력된다.

### 중단점(breakpoint)

breakpoint는 자바스크립트의 실행이 중단되는 코드 내 지점을 의미한다. breakpoint를 이용하면 **실행이 중지된 시점에 변수가 어떤 값을 담고 있는 지** 알 수 있다. 또한 **실행이 중지된 시점을 기준으로 명령어를 실행**할 수도 있다. 

디버깅 영역을 이용하면 다음과 같은 작업을 할 수 있다.

- 항목을 클릭해 해당 중단점이 설정된 곳으로 바로 이동할 수 있다.
- 체크 박스 선택을 해제해 해당 중단점을 비활성화 할 수 있다.
- 마우스 오른쪽 버튼을 클릭했을 때 나오는 Remove breakpoint 옵션을 통해 중단점을 삭제할 수도 있다.

줄 번호에 커서를 옮긴 후 마우스 오른쪽 버튼을 클릭하면 조건부 중단점(conditional breakpoint)을 설정할 수 있다. `Add conditional breakpoint`를 클릭했을 때 뜨는 작은 창에 표현식을 입력하면, **표현식이 참인 경우에만 실행을 중지시킬 수 있다.** 조건부 중단점을 설정하면 **변수에 특정 값이 할당될 때나 함수의 매개 변수에 특정 값이 들어올 때만 실행을 중단시킬 수 있어 디버깅 시 유용하게 활용**할 수 있다. 

### debugger 명령어

스크립트 내에 `debugger` 명령어를 적어주면 중단점을 설정한 것과 같은 효과를 본다.

```js
function hello(name) {
  let phrase = `Hello, ${name}!`;

  debugger;  // <-- 여기서 실행이 멈춥니다.

  say(phrase);
}
```

### 멈추면 보이는 것들

- `watch`: 표현식을 평가하고 결과를 보여준다.
- `Call Stack`: 코드를 해당 중단점으로 안내한 실행 경로를 역순으로 표시한다. 콜 스택 내의 항목을 클릭하면 디버거가 해당 코드로 휙 움직이고, 변수 역시 재평가된다. 
- `Scope`: 현재 정의된 모든 변수를 출력한다.

### 실행 추적하기

- Resume: 스크립트 실행을 다시 시작함 (F8)
- Step: 다음 명령어를 실행한다. Step 버튼을 계속 누르면 스크립트 전체를 문 단위로 하나하나 실행할 수 있다. (F9)
- Step over: 다음 명령어를 실행하되, 함수 안으로 들어가진 않는다. (F10) 보이지 않는 곳에서 중첩 함수를 실행하긴 하지만 함수 내로 진입하지 않는다. 함수 호출 시 내부에서 어던 일이 일어나는지 궁금하지 않을 때 유용하다.
- Step into: Step은 `setTimeout` 같은 비동기 동작은 무시한다. 반면 Step into는 비동기 동작을 담당하는 코드로 진입하고, 필요하다면 비동기 동작이 완료될 때까지 대기한다. (F11)
- Step out: 실행 중인 함수의 실행이 끝날 때까지 실행을 계속함

- Continue to here: 중단점을 설정하기는 귀찮은데 해당 줄에서 실행을 재개하고 싶을 때 유용하다.



## 3.2. 코딩 스타일

### 문법

![image](https://user-images.githubusercontent.com/50407047/105571928-37a5f500-5d97-11eb-8c1b-4798ebbe9384.png)

- 가로 들여쓰기: 스페이스 두 개 혹은 네개를 사용해 만든다. 탭 대신 스페이스를 이용하면 들여쓰기 정도를 유연하게 변경할 수 있다는 장점이 있다. 

- 반복문 사용시 중첩문의 깊이가 깊어지면 `continue` 지시자를 사용하는 것이 좋은 대안이 될 수 있다. 

  ```js
  for (let i = 0; i < 10; i++) {
    if (cond) {
      ... // <- 중첩 레벨이 하나 더 늘어났습니다.
    }
  }
  ```

  ```js
  for (let i = 0; i < 10; i++) {
    if (!cond) continue;
    ...  // <- 추가 중첩 레벨이 추가되지 않습니다.
  }
  ```

- `if/else`와 `return`문을 조합하면 중첩 레벨을 줄여 코드의 가독성을 높일 수 있다. 

- 헬퍼 함수를 여러 개 만들어 사용하고 있다면, 코드를 먼저 함수는 그 다음에 선언한다. 사람들은 이 코드가 무엇을 하는 지 생각하며 코드를 읽기 때문에 코드가 먼저 나오는 것이 자연스럽기 때문이다. 

- 코딩 스타일 가이드: 코드를 어떻게 작성할 지 전반적인 규칙을 담은 문서이다. 어떤 따옴표를 쓸지, 들여쓰기를 할 때 스페이스를 몇 개 사용할지, 최대 가로 길이는 몇까지 제한할 지 등의 내용이 담겨있다. 요즘엔 이미 작성된 가이드 중 하나를 선택해 팀의 가이드로 삼는 편이다.

  - [Google의 자바스크립트 스타일 가이드](https://google.github.io/styleguide/jsguide.html)
  - [Airbnb의 자바스크립트 스타일 가이드](https://github.com/airbnb/javascript)
  - [Idiomatic.JS](https://github.com/rwaldron/idiomatic.js)
  - [StandardJS](https://standardjs.com/)

- Linter를 이용하면 내가 작성한 코드가 스타일 가이드를 준수하고 있는지를 자동으로 확인할 수 있고, 스타일 개선과 관련한 제안도 받을 수 있다. 자동으로 스타일 체크를 받다보면 변수나 함수 이름에 난 오타 등이 유발하는 버그를 예방할 수 있다.

  - [JSLint](http://www.jslint.com/) – 역사가 오래된 linter
  - [JSHint](http://www.jshint.com/) – JSLint보다 세팅이 좀 더 유연한 linter
  - [ESLint](http://eslint.org/) – 가장 최근에 나온 linter

  대부분의 linter는 플러그인 형태로 유명 에디터와 통합해 사용할 수 있다. 

  - Node.js 설치
  - npm을 사용해 `npm install -g eslint` 설치
  - 현재 작성 중인 자바스크립트 프로젝트의 루트 폴더(프로젝트 관련 파일이 담긴 폴더) `.exlintrc`라는 설정 파일을 생성한다.
  - 에디터에 ESLint 플러그인을 설치하거나 활성화한다. 주요 에디터들은 모두 ESLint 플러그인을 지원한다. 

### 과제

#### 좋지 않은 코드 스타일

```js
function pow(x,n)  // <- 인수 사이에 공백이 없음
{  // <- 별도의 줄에 있는 중괄호
  let result=1;   // <- 할당 연산자 = 앞/뒤에 공백이 없음
  for(let i=0;i<n;i++) {result*=x;}   // 비교 연산자 < 앞/뒤에 공백이 없음
  // { ... }안의 코드는 새로운 줄에 위치해야 함
  return result;
}

let x=prompt("x?",''), n=prompt("n?",'') // <-- 에러를 발생시키는 코드는 아니나,
// 두 줄로 나눠서 작성하는 게 좋고, 연산자 앞/뒤 공백과 문장 끝 ;를 넣어주는 게 좋음
if (n<=0)  // <- (n <= 0) 같이 공백을 넣는 게 좋고, 윗줄은 비워놓아야 함(세로 들여쓰기)
{   // <- 별도의 줄에 있는 중괄호
  // 아랫줄같이 가로 길이가 길어지면 가독성을 위해 코드를 여러 줄로 쪼개는 게 좋음
  alert(`Power ${n} is not supported, please enter an integer number greater than zero`);
}
else // <- "} else {"같이 else와 중괄호는 한 줄에 작성하는 게 좋음
{
  alert(pow(x,n))  // 공백과 ; 가 없음
}
```

다음과 같이 고칠 수 있다.

```js
function pow(x,n) {
  let result = 1;
  
  for (let i=0;i<n;i++) {
    result *= x;
  }
  
  return result;
}

let x = prompt("x?",''); 
let n = prompt("n?",'');

if (n <= 0) {
  alert(`Power ${n} is not supported, 
		please enter an integer number greater than zero`);
} else {
  alert( pow(x,n) )
}
```



## 3.3. 주석

좋은 코드엔 설명이 담긴(explanatory) 주석이 많아선 안 된다. 주석 없이 코드 자체만으로 코드가 무슨 일을 하는지 쉽게 이해할 수 있어야 한다.

함수 내 코드 일부를 새로운 함수로 옮기고 적절한 이름을 붙이면 함수 이름 자체가 주석 역할을 하므로 코드를 쉽게 이해할 수 잇따. 이런 코드를 자기 설명적인(self-descriptive) 코드라 부른다.

그러나 실무에서는 설명이 담긴 주소를 작성하는 것이 불가피한 경우가 있다. 

### 좋은 주석

- 아키텍처를 설명하는 주석: 고차원 수준 컴포넌트 개요, 컴포넌트 간 상호작용에 대한 설명, 상황에 따른 제어 흐름은 주석에 넣는 것이 좋다. 이런 주석은 조감도 역할을 해준다.

- 함수 용례와 매개변수 정보를 담고 있는 주석: JSDoc 이라는 특별한 문법을 사용하면 함수에 관한 문서를 쉽게 작성할 수 있다. 여기엔 함수 용례, 매개변수. 반환값 정보가 들어간다.

  ```js
  /**
   * x를 n번 곱한 수를 반환함
   *
   * @param {number} x 거듭제곱할 숫자
   * @param {number} n 곱할 횟수, 반드시 자연수여야 함
   * @return {number} x의 n 거듭제곱을 반환함
   */
  function pow(x, n) {
    ...
  }
  ```

  JSDoc 3 등 툴을 사용하면 주석으로 HTML 문서를 만들 수 있다.

- 왜 이런 방법으로 문제를 해결했는지 설명하는 주석

  다른 개발자 혹은 시간이 흐른 후에 그 코드를 작성한 개발자가 코드를 보고 그때 선택한 방식이 가장 좋은 방식은 아니란 걸 알아낸다. 그리고 이전보다 더 명확하고 올바른 방법이라고 생각하는 방법으로 코드를 개선한다. 리팩토링 과정에서 그 방법을 적용하면 문제가 발생한다는 것을 그제서야 알 수 있다. 

- 미묘한 기능이 있고, 이 기능이 어디에 쓰이는 지 설명하는 주석

### 요약

**주석에 들어가면 좋은 내용**

- 고차원 수준의 아키텍처
- 함수 용례
- 당장 봐선 명확해 보이지 않는 해결방법에 대한 설명

**주석에 들어가면 좋지 않은 내용**

- 코드가 어떻게 동작하는지와 코드가 무엇을 하는지에 대한 설명
- 코드를 간결하게 짤 수 없는 상황이나 코드 자체만으로 어떤 일을 하는지 충분히 판단할 수 없는 경우에만 주석을 넣는다. 



## 3.4. 닌자 코드

다음과 같은 코드를 작성하는 것은 지양하자. 유지보수가 어렵기 때문이다.

- 코드 가능한 짧게 쓰기

- 글자 하나만 사용하기

  그 글자가 무엇을 의미하는 지 해석하기 어렵다. 

- 약어 사용하기

- 포괄적인 명사 사용하기

  - data, value 등과 같은 아무 의미 없는 변수명 사용하기
  - str, num과 같이 자료형과 연관된 변수명 사용하기: 자료형 파악하는 것은 디버깅 툴을 사용하면 되지만 변수의 의미는 파악이 쉽지 않다.
  - data1, item2, elem3 처럼 옆에 숫자를 붙이기

- 철자가 유사한 단어 사용하기

  - date와 data같이 유사한 철자를 가진 단어를 조합해 사용하기

- 동의어 사용하기: display, show, renter, paint, print 등

- 이름 재사용하기

  - 변수에 현재 어떤 값이 들어가있는지, 값의 유래는 어디인지 쉽게 파악하지 못한다.
  - 특히 함수나 반복문 중간에서 할당 값을 바꾸면 더 파악하기 어렵다.

- 재미로 언더스코어 사용하기

- 과장 형용사 사용하기

- 외부 변수 덮어쓰기: 함수 내부와 외부에 동일한 이름을 가진 변수를 선언해 사용하기

- 부작용이 있는 코드 작성하기

  - `isReady`, `checkPermission`, `findTags` 같은 함수는 단순 확인용으로 사용되고 외부의 무언가를 바꾸진 않는다. 부작용이 없는 함수이다. 이런 함수에 본래 기능을 넘어선 유용한 기능을 더해주거나 예상치 않은 결과를 반환한다(확인여부와 다른 정보를 함께 엮은 객체를 반환)

- 함수에 다양한 기능 넣기: 함수 하나에 여러 기능을 욱여넣으면 코드 재사용이 어려워진다.



## 3.5. 테스트 자동화와 Mocha

### 테스트를 하는 이유

- 코드를 수동으로 재실행하면서 테스트를 하면 무언가를 놓치기 쉽다.
- 테스팅 자동화는 테스트 코드가 실제 동작에 관여하는 코드와 별개로 작성되었을 때 가능하다. 테스트 코드를 이용하면 함수를 다양한 조건에서 실행해볼 수 있는데, 이때 실행 결과와 기대 결과를 비교할 수 있다.
- 잘 테스트된 코드는 더 나은 아키텍처를 만든다. 테스트를 작성하려면 함수가 어떤 동작을 하는지, 입력값은 무엇이고 출력값은 무엇인지 정의하고 난 후에 구현을 시작한다. 코드는 정의된 사항을 뒷받침할 수 있게 작성해야 한다. 구현을 시작하는 순간부터 이미 좋은 아키텍처가 보장된다. 

### Behavior Driven Development

- BBD는 테스트(test), 문서(documentation), 예시(example)을 한데 모아놓은 개념이다.

#### 거듭제곱 함수와 명세서

코드를 작성하기 전에 코드가 무슨 일을 하는 지 상상한 후 이를 자연어로 표현해야 한다. 이때 만들어진 산출물을 BDD에서는 명세서(specification) 또는 스펙(spec)이라고 부른다. 명세서엔 유스 케이스에 대한 자세한 설명과 테스트가 담겨 있다.

```js
describe("pow", function() {
  it("주어진 숫자의 n 제곱", function() {
    assert.equal(pow(2, 3), 8);
  });
});
```

스펙은 세 가지 주요 구성요소로 이루어진다.

- `describe("title", function() {...})`: 구현하고자 하는 기능에 대한 설명이 들어간다. `it` 블록을 한 데 모아주는 역할도 한다.
- `it("유스 케이스 설명", function() {...})`: 첫 번째 인수에는 특정 유스 케이스에 대한 설명이 들어간다. 이 설명은 **누구나 읽을 수 있고 이해할 수 있는 자연어**로 적어준다. 두 번째 인수에는 유스 케이스 테스트 함수가 들어간다.
- `assert.equal(value1, value2)`: 기능을 제대로 구현했다면 `it` 블록 내의 코드 `assert.equal(value1, value2)`이 에러 없이 실행된다. 함수 `assert.*`는 `pow`가 예상한 대로 동작하는지 확인해준다. 

명세서는 실행 가능하다. 명세서를 실행하면 `it` 블록 안의 코드가 실행된다.



### 개발 순서

1. 명세서 초안을 작성한다. 초안엔 기본적인 테스트도 들어간다.
2. 명세서 초안을 보고 코드를 작성한다.
3. 코드가 작동하는지 확인하기 위해 [Mocha](http://mochajs.org/)라 불리는 테스트 프레임워크를 사용해 명세서를 실행한다. 이때, 코드가 잘못 작성되었다면 에러가 출력된다. 개발자는 테스트를 모두 통과해 에러가 더는 출력되지 않을 때까지 코드를 수정한다.
4. 모든 테스트를 통과하는 코드 초안이 완성되었다.
5. 명세서에 지금까진 고려하지 않았던 유스케이스 몇 가지를 추가한다. 테스트가 실패하기 시작할 것이다.
6. 세 번째 단계로 돌아가 테스트를 모두 통과할 때까지 코드를 수정한다.
7. 기능이 완성될 때까지 3~6단계를 반복한다.

위와 같은 방법은 *반복적인(iterative)* 성격을 지닌다.

### 스펙 실행하기

- [Mocha](http://mochajs.org/) – 핵심 테스트 프레임워크로, `describe`, `it`과 같은 테스팅 함수와 테스트 실행 관련 주요 함수를 제공한다.
- [Chai](http://chaijs.com/) – 다양한 assertion을 제공해 주는 라이브러리이다.
- [Sinon](http://sinonjs.org/) – 함수의 정보를 캐내는 데 사용되는 라이브러리로, 내장 함수 등을 모방한다. 

세 라이브러리 모두 브라우저나 서버사이드 환경을 가리지 않고 사용 가능하다. 

```js
<!DOCTYPE html>
<html>
<head>
  <!-- 결과 출력에 사용되는 mocha css를 불러옵니다. -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mocha/3.2.0/mocha.css">
  <!-- Mocha 프레임워크 코드를 불러옵니다. -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/mocha/3.2.0/mocha.js"></script>
  <script>
    mocha.setup('bdd'); // 기본 셋업
  </script>
  <!-- chai를 불러옵니다 -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/chai/3.5.0/chai.js"></script>
  <script>
    // chai의 다양한 기능 중, assert를 전역에 선언합니다.
    let assert = chai.assert;
  </script>
</head>

<body>

  <script>
    function pow(x, n) {
      /* 코드를 여기에 작성합니다. 지금은 빈칸으로 남겨두었습니다. */
    }
  </script>

  <!-- 테스트(describe, it...)가 있는 스크립트를 불러옵니다. -->
  <script src="test.js"></script>

  <!-- 테스트 결과를 id가 "mocha"인 요소에 출력하도록 합니다.-->
  <div id="mocha"></div>

  <!-- 테스트를 실행합니다! -->
  <script>
    mocha.run();
  </script>
</body>

</html>
```

1. `<head>` – 테스트에 필요한 서드파티 라이브러리와 스타일을 불러옴
2. `<script>` – 테스트할 함수(`pow`)의 코드가 들어감
3. 테스트 – `describe("pow", ...)`를 외부 스크립트(`test.js`)에서 불러옴
4. HTML 요소 `<div id="mocha">` – Mocha 실행 결과가 출력됨
5. `mocha.run()` – 테스트를 실행시켜주는 명령어

#### 코드 초안

```js
function pow(x, n) {
  return 8;
}
```

#### 스펙 개선하기

스펙을 개선할 때 기존 `it` 블록에 `assert` 하나 더 추가하는 방법과, **테스트 하나 더 추가하는 방법 (`it` 블록 하나 더 추가하기)**이 있다. `assert`에서 에러가 발생하면 `it` 블록은 즉시 종료된다. 따라서 **기존 `it` 블록에 `assert`를 하나 더 추가하면 첫 번째 `assert`가 실패했을 때 두 번째 `assert`의 결과를 알 수 없다.** 따라서 두 번째 방법처럼 `it` 블록을 하나 더 추가해 테스트를 분리해서 작성하면 더 많은 정보를 얻을 수 있기 때문에 두 번째 방법이 권장된다. 

또한 **테스트 하나에선 한 가지만 확인하는 것이 좋다.** 연관이 없는 사항 두 개를 테스트 하나에서 점검한다면 이 둘을 분리하자.

#### 코드 개선하기

```js
function pow(x, n) {
  let result = 1;

  for (let i = 0; i < n; i++) {
    result *= x;
  }

  return result;
}
```

함수가 제대로 동작하는지 확인하기 위해 더 많은 값을 테스트해보자. 수동으로 여러 개의 `it` 블록을 만드는 대신 `for`문을 사용해 자동으로 `it` 블록을 만들 수도 있다.

```js
describe("pow", function() {

  function makeTest(x) {
    let expected = x * x * x;
    it(`${x}을/를 세 번 곱하면 ${expected}입니다.`, function() {
      assert.equal(pow(x, 3), expected);
    });
  }

  for (let x = 1; x <= 5; x++) {
    makeTest(x);
  }

});
```

#### 중첩 describe

중첩 `describe`는 새로운 테스트 '하위그룹(subgroup)'을 정의할 때 사용된다. 이렇게 새로 정의된 테스트 하위 그룹은 테스트 결과 보고서에 들여쓰기 된 상태로 출력된다. 

```js
describe("pow", function() {

  describe("x를 세 번 곱합니다.", function() {

    function makeTest(x) {
      let expected = x * x * x;
      it(`${x}을/를 세 번 곱하면 ${expected}입니다.`, function() {
        assert.equal(pow(x, 3), expected);
      });
    }

    for (let x = 1; x <= 5; x++) {
      makeTest(x);
    }

  });

  // describe와 it을 사용해 이 아래에 더 많은 테스트를 추가할 수 있습니다.
});
```

#### `before/after`와 `beforeEach/afterEach`

함수 `before`은 전체 테스트가 실행되기 전에 실행되고, 함수 `after`는 전체 테스트가 실행된 후에 실행된다. 함수 `beforeEach`는 *매* `it`이 실행되기 전에 실행되고, 함수 `afterEach`는 *매* `it`이 실행된 후에 실행된다.

#### 스펙 확장하기

자바스크립트에선 수학 관련 연산을 수행하다 에러가 발생하면 `NaN`을 반환한다. 함수 `pow`도 `n`이 조건에 맞지 않으면 `NaN`을 반환해야 한다. 이를 검사해주는 테스트를 추가해보자.

```js
describe("pow", function() {

  // ...

  it("n이 음수일 때 결과는 NaN입니다.", function() {
    assert.isNaN(pow(2, -1));
  });

  it("n이 정수가 아닐 때 결과는 NaN입니다.", function() {
    assert.isNaN(pow(2, 1.5));
  });

});
```

기존엔 `n`이 음수이거나 정수가 아닌 경우를 생각하지 않고 구현했기 때문에, 새롭게 추가한 테스트는 실패할 수밖에 없다. 

**BDD의 핵심은 여기에 있다. 실패할 수밖에 없는 테스트를 추가하고, 테스트를 통과할 수 있게(에러가 발생하지 않게) 코드를 개선하는 것이다.**

테스트를 통과할 수 있게 다음과 같이 코드를 개선한다.

```js
function pow(x, n) {
  if (n < 0) return NaN;
  if (Math.round(n) != n) return NaN;

  let result = 1;

  for (let i = 0; i < n; i++) {
    result *= x;
  }

  return result;
}
```



## 3.6. 폴리필

- 자바스크립트는 끊임없이 진화하는 언어이다. 새로운 제안이 정기적으로 등록, 분석되고 가치가 있다고 판단되는 제안은 https://tc39.github.io/ecma262/에 추가된다. 그리고 궁극적으로 명세서(specification)에 등록된다.
- 자바스크립트 엔진을 만드는 각 조직은 우선순위를 매겨 명세서 내 어떤 기능을 먼저 구현할 지 결정한다. 즉 엔진이 표준 전체를 지원하지 않고 일부만 지원하는 것은 흔한 일이다.

### 바벨

바벨(Babel)은 트랜스파일러(transpiler)로, 모던 자바스크립트 코드를 구 표준을 준수하는 코드로 바꿔준다. 바벨의 주요 역할은 다음과 같다.

- 트랜스파일러(transpiler): 코드를 재작성해준다. 바벨은 개발자의 컴퓨터에서 돌아가는데, 이를 실행하면 **기존 코드가 구 표준을 준수하는 코드로 변경**된다. 변경된 코드는 웹사이트 형태로 사용자에게 전달된다. **웹팩(webpack)**과 같은 **모던 프로젝트 빌드 시스템**은 **코드가 수정될 때마다 자동으로 트랜스파일러를 동작**해준다.
- **폴리필**(polyfill): **변경된 표준을 준수할 수 있게 기존 함수의 동작 방식을 수정하거나, 새롭게 구현한 함수의 스크립트**를 "폴리필(polyfill)"이라 부른다. 폴리필은 **구현이 누락된 새로운 기능을 메꿔주는 역할**을 한다. (예: [core js](https://github.com/zloirock/core-js), [polyfill.io](http://polyfill.io/) )