# 1. 첫 번째 애플리케이션

## 브라우저 기반 프로그램 만들기 

- 그래픽 라이브러리를 사용하기가 편리하다. 
- 이벤트 주도 프로그래밍(event-driven programming)으로 자연스럽게 넘어가기 좋다.



## 텍스트 에디터

### 종류

- 텍스트 모드 에디터
  - `vi/vim`과 이맥스
  - 익숙해지면 SSH 등으로 원격 컴퓨터에 접속한 상태에서도 익숙한 에디터로 파일을 편집할 수 있다.
- 창 모드 에디터
  - 아톰, 서브라임 텍스트, 코다, 비주얼 스튜디오 등

### 기능

- 문법 하이라이트
  - 프로그램의 문법적 요소를 색깔로 구별하는 기능이다. 
  - 코드에서 문제가 있는 부분을 쉽게 찾을 수 있도록 도와준다.
- 괄호 맞추기
- 코드 접기
- 자동 완성(word completion, 인텔리센스)
  - 타이핑 시간 절약
  - 발견
    - ex) `encodeURLComponent`를 사용하고 싶어서 `enc`를 타이핑하면 `encodeURL` 함수를 발견하게 된
  - 자바스크립트는 **느슨한 타입**을 사용하고 **스코프 규칙도 독특**해서 다른 언어에 비해 자동 완성을 구현하기 어렵다.



## 주석

- HTML: `<!--`와 `-->`
- CSS: `/*`와 `*/`
- JavaScript : `/*`와 `*/`(블록 주석), `//`(인라인 주석)



## HTML + CSS

```html
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="main.css">
	</head>
	<body>
		<h1>My first application</h1>
		<p>Welcome to <i>Learning JavasScript, 3rd Edition</i>.</p>
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="main.js"></script>
	</body>
</html>
```

`<head>`: 브라우저에 직접 표시되지 않는 쩡보

- `<body>`: 브라우저에 렌더링될 페이지 콘텐츠(직접 표시된다)
  - **`<script>` 태그를 헤드에 넣으면 성능이 다소 떨어지고 헤드가 지나치게 복잡해지므로 마지막에 넣는다.**



## 자바스크립트 콘솔

- 콘솔: 프로그램을 진단할 때 사용하는 텍스트 전용 도구
- 브라우저 콘솔 단축키: Ctrl-Shift-i
- `console.log` 메서드
  - 원하는 내용 콘솔에 출력하는 메서드
  - 디버깅과 언어 학습에 유용하다. 
  - 프로그램을 임시로 수정하는 것도 가능하다.



## 제이쿼리

- `<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>`
  - **콘텐츠 전송 네트워크(CDN)**에서 서비스하는 제이쿼리 링크
  - 이렇게 하면 불러오는 속도가 더 빨라진다.
  - 오프라인으로 프로젝트를 만든다면 제이쿼리 파일을 내려받아 컴퓨터에서 불러와야 한다.

```js
$(document).ready(function() {
	'use strict';
	console.log('main.js loaded');
});
```

- 자바스크립트 코드를 실행하기 전에 브라우저가 HTML을 전부 불러왔는지 확인을 하는 코드
- 앞으로 만드는 자바스크립트 코드는 모두 `$(document).ready(function() {`와 `});` 사이에 들어간다.
- `use strict`
  - 자바스크립트 인터프리터에서 코드를 더 엄격하게 처리하라는 뜻
  - 코드를 엄격하게 처리하다보면 더 나은 자바스크립트 코드를 쓸 수 있게 되고, 자주 발생하는 어려운 문제를 방지할 수 있다.



## 단순한 그래픽 그리기

- HTML5의 표준화된 그래픽 인터페이스

- HTML5 캔버스를 사용해 단순한 도형을 그릴 수 있다.

- `Paper.js`를 사용해서 HTML5 캔버스를 사용해보자.

- `<canvas *id*="mainCanvas"></canvas>`

- id속성: 자바스크립트와 CSS에서 이 요소를 쉽게 찾기 위함

  - HTML의 형식을 잘 지키려면 **각 ID는 반드시 한 번씩만 사용**해야 한다. 따라서 ID를 너무 남발하지 않는 것이 좋다. ID는 원래 페이지 하나에 하나씩 쓰도록 정의되었다.

- 캔버스가 잘 보이도록 css 수정

  ```css
  #mainCanvas {
    width: 400px;
    height: 400px;
    border: solid 1px crimson;
  }
  ```

- Paper.js 라이브러리를 링크한다.

  `<script src="https://cdnjs.cloudflare.com/ajax/libs/paper.js/0.9.25/paper-full.min.js"></script>`

- 설정 작업을 한다.

```js
$(document).ready(function() {
	'use strict';
	
	// Paper.js를 전역 스코프에 설치
	paper.install(window);

	// Paper.js를 캔버스에 연결하고 Paper.js가 그림을 그릴 수 있도록 준비
	paper.setup(document.getElementById('mainCanvas'));

	//TODO
  
	// Paper.js가 실제로 화면에 그림을 그리라는 명령
	paper.view.draw();
});

```

>  **라이브러리 링크하는 순서는 중요하다**. `main.js`에서 제이쿼리와 Paper.js를 모두 **사용해야 하기 때문에** **이들을 먼저 링크**해야 한다. 제이쿼리와 Paper.js는 서로 관계가 없어 순서가 상관 없지만, 웹 개발에서 제이쿼리가 빠지는 일은 거의 없기 때문에 항상 **제이쿼리를 먼저 링크하는 습관**을 들이자.

> 보일러 플레이트(boiler plate), 템플릿: 어떤 일을 하기 전에 항상 먼저 실행해야 하는 코드

```js
$(document).ready(function() {
	'use strict';
	
	// Paper.js를 전역 스코프에 설치
	paper.install(window);

	// Paper.js를 캔버스에 연결하고 Paper.js가 그림을 그릴 수 있도록 준비
	paper.setup(document.getElementById('mainCanvas'));

	// 캔버스의 높이와 너비는 각각 400픽셀
	// Shape.Circle(x좌표, y좌표, 원의 반지름)
	var c = Shape.Circle(200, 200, 50);
	c.fillColor = 'crimson';
	
	// Paper.js가 실제로 화면에 그림을 그리라는 명령
	paper.view.draw();
});
```



**결과물**

![image](https://user-images.githubusercontent.com/50407047/95552109-13701380-0a47-11eb-97ee-cfe41a723d5c.png)



## 반복적입 작업 자동화하기

```js
var c;
for (var x = 25; x < 400; x += 50) {
  for (var y = 25; y < 400; x+= 50) {
    c = Shape.Circle(x, y, 20);
    c.fillColor = 'crimson';
  }
}
```

![image](https://user-images.githubusercontent.com/50407047/95552716-29320880-0a48-11eb-8663-bf90c1947f15.png)



## 사용자 입력 처리하기

- 프로그램을 좀 더 **대화형**으로 만들어보자!

- **사용자 입력은 항상 *비동기적*이다**. 
- *비동기 이벤트*란 이벤트가 언제 일어날지 **프로그래머가 전혀 알 수 없는 이벤트**를 말한다. 
  - ex)사용자의 마우스 클릭과 같이 사용자 마음대로 일어나는 이벤트 (비교적 직관적인 편)

- Paper.js는 툴(tool) **객체**를 통해 사용자 입력을 처리한다.

```js
// tool 객체 생성
var tool = new tool();

// onMouseDown 이벤트 핸들러 연결
// 사용자가 마우스를 클릭할 때마다 핸들러에 연결된 함수가 호출된다.
tool.onMouseDown = function(event) {
  var c = Shape.Circle(event.point.x, event.point.y, 20);
  c.fillColor = '#B29784';
};
```

- 이벤트 핸들러가 하는 일
  - 마우스를 클릭할 때 코드를 *실행*
  - *어디*를 클릭했는지 *보고*

- `Shape.Circle(event.point, 20)`와 같이 한번에 지정할 수 있다.
- **자바스크립트는 넘겨받은 매개변수를 바탕으로 추론하는 능력이 있다.**

![image](https://user-images.githubusercontent.com/50407047/95556617-8af57100-0a4e-11eb-8a30-0e5565d66c1b.png)

## Hello, World

```js
	// 텍스트의 배경으로 쓸 다른 원을 만든다.
	var c = Shape.Circle(200, 200, 80);
	c.fillColor = 'black';
	// 텍스트 객체 PointText 생성
	var text = new PointText(200, 200);
	text.justification = 'center';
	text.fillColor = 'white';
	text.fontSize = 20;
	text.content = 'hello world';
```

![image](https://user-images.githubusercontent.com/50407047/95557019-2be42c00-0a4f-11eb-84c2-5af59500a774.png)