## 6-1. 글꼴 관련 스타일

### font-family 속성: 글꼴 지정하기

텍스트 글꼴을 웹문서에서 지정한다고 해도, 사용자의 **시스템에 해당 글꼴이 설치**되어 있지 않다면 **브라우저 기본 값**이 나타나거나 다른 글꼴로 표시된다. 따라서 웹 문서에서 글꼴을 지정할 때에는 **지정한 글꼴이 없을 경우에 대비**해 두 번째, 세 번째 글꼴을 지정해야 한다. 

사용자 대부부의 시스템에 설치되어 있어서 웬만하면 제대로 표시되는 글꼴을 **기본 글꼴(web-safe font)**이라고 한다. 윈도우의 경우 영문은 sans-serif와 serif체, 한글은 굴림, 궁서, 돋움, 바탕체가 있다.

`font-family` 속성은 상속된다.

> CSS 속성 값에는 브라우저의 기본 값으로 맞추는 `initial` 속성과 부모 요소로부터 속성 값을 상속받는 `inherit` 속성이 포함되어 있다.

### `@font-face` 속성: 웹 폰트 사용하기

**웹 폰트**란 **웹 문서를 작성할 때 웹 문서 안에 글꼴 정보도 함께 저장**했다가 사용자가 **웹 문서에 접속하면 글꼴을 사용자 시스템으로 다운로드**햐고 표시하는 방식이다. 따라서 이제 웹 제작자가 의도한 대로 텍스트를 표시할 수 있"게 되었다.

#### 구글 웹 폰트 사용하기

google font -> select this style -> Embed -> @import -> 소스 복사

![image](https://user-images.githubusercontent.com/50407047/99892907-9c70a080-2cbd-11eb-8231-60ddaf3af01a.png)

```html
	<style>
		@import url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@200;400&display=swap');
		.ns-font {
			font-family: 'Noto Serif KR', 돋움;
		}
		p {
			font-size: 30px;
		}
	</style>
</head>
<body>
	<p>브라우저 기본 글꼴 사용</p>
	<p class="ns-font">노토산스KR 웹 폰트 사용</p>
</body>
```

#### 직접 웹 폰트 업로드해 사용하기

- 사이트에서 제공하지 않는 폰트이거나 자신이 가지고 있는 ttf 폰트를 변환해 사용한다면 직접 업로드해야 한다.
- 컴퓨터에서 사용하는 글꼴은 트루타입(True Type) 유형이다. 그러나 트루타입은 파일 크기가 너무 크기 때문에 다른 글꼴이 등장했다. 그 중 eot(Embedded Open Type), woff(Web Open Font)가 많이 사용된다.
- [Transfonter](https://transfonter.org/)에서 한글 폰트까지 변환할 수 있다.

> ttf 파일을 eot나 woff로 변환해 웹 폰트로 사용하려면 허락이 있어야 한다.

#### 웹 폰트 적용하기

```css
@font-face {
  font-family: 글꼴 이름;
  src:url(글꼴파일경로) format(파일유형);
}
```

`@font-face` 속성을 사용해 웹 폰트를 정의한다. 

ttf 파일은 다른 형식의 파일보다 용량이 크기 때문에 대부분의 모던 브라우저에서 지원하는 woff 글꼴 파일을 먼저 선언하고 ttf 파일은 그 후에 선언한다. 그러면 woff 형식을 지원하는 브라우저는 woff 파일을 다운로드하고 그 후 선언된 ttf 파일은 다운로드하지 않는다. 인터넷 익스플로러 8 이하 버전에서는 eot 파일만 지원하므로 woff 파일보다 먼저 선언하는데 eot 파일에서는 포맷을 따로 지정하지 않는다.

```html
<head>
    <meta charset="utf-8">
    <title>웹 폰트 사용하기</title>
    <style>
      @font-face {
        font-family: 'trana'; /* 글꼴 */
        src: local('trana'), /*사용자 시스템에서 먼저 찾는다.*/
          url('trana.eot'),
          url('trana.woff') format('woff'),
          url('trana.ttf') format('truetype');
      }
      .w-font {
        font-family: 'trana', sans-serif; /*웹 폰트 지정*/
      }
      p {
        font-size: 30px;
      }
  	</style>
</head>
<body>
  <p>Using Default Fonts</p>
  <p class="w-font">Using Trana Fonts</p>
</body>
```

### `font-size` 속성: 글자 크기 조절하기

```css
font-size: <절대크기> | <상대크기> | <크기> | <백분율>
```

- 절대크기: **브라우저**에서 지정한 글자 크기
  - `xx-small`, `x-small`, `small`, `medium`, `large`, `x-large`, `xx-large`
- 상대크기: **부모 요소의 글자 크기를 기준**으로 더 크게 표시하거나 더 작게 표시한다.
  - `larger`, `smaller`

- 크기: 브라우저에 상관 없이 글자 크기를 직접 지정
- 백분율: 부모 요소의 글자 크기를 기준으로 해당하는 `%`를 계산해 표시

주로 *크기* 값을 지정하는 방법을 사용한다.

- `em`: 해당 글꼴의 대문자 M의 너비를 기준으로 크기를 조절한다.
- `ex`: x-height. 해당 글꼴의 소문자 x의 높이를 기준으로 크기를 조절한다.
- `px`: 픽셀. **모니터에 따라 상대적 크기**가 된다.
- `pt`: 포인트. **일반 문서**에서 많이 사용하는 단위이다.

#### px 단위 사용하기

- 주로 사용하는 방법이다.
- 그러나 px 단위를 사용하면 폰트 크기가 고정되기 때문에 창 크기가 같은 **모바일 기기**로 볼 때도 같은 크기로 화면에 표시된다. 결국 **작은 화면 안에 작은 글씨**로 표시된다. 
- 따라서 모바일 기기에서 접속할 경우까지 고려한다면 `em` 단위를 사용하는 것이 좋다.

#### em 단위 사용하기

부모 요소에서 지정한 폰트의 대문자 M의 너비를 1em으로 놓고 상대적 값을 계산해 다른 요소들의 글자 크기를 조절한다. 만약 지정한 크기가 없다면 `<body>` 요소의 크기 16px이 기본값 1em으로 지정된다.

```html
<head>
    <style>
      h1{
        font-size:3em; /* 글자 크기 */
      }
      p {
        font-size:1em; /* 글자 크기 */
      }
    </style>
</head>
<body>
  <h1>3em의 크기를 가진 제목</h1>
  <p>1em의 크기를 가진 단락</p>
</body>
```

위의 코드에서 실제 폰트 크기는 제목은 48px, 단락은 16px이 된다. 

> **기본형 기호**
>
> - `|`는 나열한 옵션 중 하나가 값이 되어야 한다는 의미이다.
> - 속성 값을 나열할 때 키워드(약속한 값)는 그대로 나열한다.
> - 속성 값을 나열할 때 값이 아니라 유형이라면 꺾쇠 괄호로 묶는다. 이때 다른 속성을 유형처럼 사용할 수 있다.
>   - `font-size: <절대 크기> | <상대 크기> | <크기> | <백분율>`
>   - `font: <font-style><font-variant><font-weight>`
>     - `font-style` 속성 값을 `font` 속성으로 사용한다.

### `font-weight` 속성: 글자 굵기 지정하기

- `font-weight: normal } bold | bolder | lighter | 100 | 200 | 300 | 400 | 500 | 600 | 700 | 800 | 900 |`
- 400과 normal은 일반적인 형태로 기본 값이다. 

### `font-variant`: 작은 대문자로 표시하기

- `font-variant: normal / small-caps`
- `small-caps`: 대문자를 소문자 크기에 맞춰 작게 표시

![image](https://user-images.githubusercontent.com/50407047/99893457-d8a6ff80-2cc3-11eb-9ea2-b7804a7d0c97.png)

```html
    <style>
      .accent {
        font-variant:small-caps;  /* 작은 대문자 */
        font-weight:bold; /* 굵게 */
      } 
    </style>
</head>
<body>
  <p><span class="accent">시드니(Sydney)</span>, 호주</p>
  <p><span class="accent">리우데자네이루(Rio de Janeiro)</span>, 브라질</p>
  <p><span class="accent">나폴리(Naples)</span>, 이탈리아</p>
</body>
```

### `font-style`: 글자 스타일 지정하기

- `font-style: normal | italic | oblique`
- `italic`은 기울어진 글꼴이 처음부터 디자인되어 있는 반면, `oblique`는 원래 글꼴을 단지 기울어지게 표시할 뿐이다. 웹에서는 주로 italic을 사용한다.

### `font` 속성: 글꼴 속성을 한꺼번에 묶어 표현하기

- `font: <font-style><font-variant><font-weight><font-size/line-height><font-family> | caption | icon | menu | message-box | small-caption | status-bar`
- font 속성을 이용하면 다른 속성을 한꺼번에 묶어 표현할 수 있다.
- 특정 키워드를 입력해 그것에 어울리는 글꼴 스타일로 표시할 수 있다.
  - `caption`: 캡션에 어울리는 글꼴 스타일
  - `icon`: 아이콘에 어울리는 글꼴 스타일
  - `menu`: 드롭다운 메뉴에 어울리는 글꼴 스타일
  - `message-box`: 대화상자에 어울리는 글꼴 스타일
  - `small-caption`: 작은 캡션에 어울리는 글꼴 스타일
  - `status-bar`: 상태 표시줄에 어울리는 글꼴 스타일
- `font-size`와 `line-height`는 `12px/24px`, `120%/120%`처럼 슬래시로 연결해 함께 표현할 수 있다. 여기서 `120%/120%`에서 글자크기 120%는 부모 요소의 글자 크기를 기준으로 120%만큼 표시하고, 줄 간격 120%는 현재 요소의 글자 크기를 기준으로 한다는 점을 주의하자.

![image](https://user-images.githubusercontent.com/50407047/99893640-fa54b680-2cc4-11eb-9ac7-e1ec5f677c53.png)

```html
<style>
  p.txt {
    font: italic 12px/24px 돋움;  /* 글꼴 스타일, 크기, 줄간격, 글꼴 */
  }
</style>
</head>
<body>
  <p class="txt">여러 요소가 함께 사용된 웹 문서 안에서 텍스트가 눈에 띄게 하려면 내용에 맞는 글꼴과 글자 크기, 그리고 글자색을 선택하는 것이 중요합니다. </p>
  <p>이럴 때 사용할 수 있는 것이 글꼴 속성입니다. </p>
  <p style="font:caption"> [font:caption] 캡션에 어울리는 글꼴 스타일</p>
  <p style="font:icon"> [font:icon] 아이콘에 어울리는 글꼴 스타일</p>
  <p style="font:menu"> [font:menu] 드롭다운 메뉴에 어울리는 글꼴 스타일</p>
  <p style="font:message-box"> [font:message-box] 대화 상자에 어울리는 글꼴 스타일</p>
  <p style="font:small-caption"> [font:small-caption] 작은 캡션에 어울리는 글꼴 스타일</p>
  <p style="font:status-bar"> [font:status-bar] 상태표시줄에 어울리는 글꼴 스타일</p>
</body>
```

## 텍스트 스타일

### `color` 속성: 글자 색 지정

색상 값은 16진수나 rgb, hsl 또는 색상 이름으로 표기할 수 있다.

```css
h1 {color:rgb(0,200,0);}  /* rgb 값 사용 - 녹색 계열 */
h2 {color:blue;}  /* 색상 이름 사용 - 파랑 */
.accent {color:#f00;} /* 16진수 사용 - 빨강, #ff0000으로도 사용 */
```

### `text-decoration` 속성: 텍스트에 줄 표시하기/없애기

- ` text-decoration: none | underline | overline | line-through`
  - `overline`: 영역 위로 선을 그린다.
  - `line-through`: 영역을 가로지르는 선(취소 선)을 그린다.

### `text-tranform` 속성: 텍스트 대/소문자 변환하기

`text-transform: none | capitalize | uppercase | lowercase | full-width`

- `capitalize`: 시작하는 첫 번째 글자를 대문자로 변환

- `uppercase`: 모든 글자를 대문자로 변환

- `lowercase`: 모든 글자를 소문자로 변환

- `full-width`: 가능한 모든 문자를 전각 문자로 변환

  ![image](https://user-images.githubusercontent.com/50407047/99894321-70a5e880-2cc6-11eb-9ad7-fe9a5e96dda8.png)

  > 전각 문자: 고정 폭 영문자 너비의 두 배 정도 너비의 문자이며 전각 문자 너비의 절반 정도 너비인 문자를 반각 문자라고 한다.

### `text-shadow` 속성: 텍스트에 그림자 효과 추가하기

`text-shadow: none | <가로거리> <세로거리> <번짐정도> <색상>`

- `<가로거리>`: 텍스트부터 그림자까지의 가로 거리를 입력한다. 양수 값은 글자 오른쪽, 음수 값은 글자 왼쪽에 그림자를 만든다. 필수 속성.
- `<세로거리>`: 텍스트부터 그림자까지의 세로 거리를 입력한다. 양수 값은 글자 아래쪽, 음수 값은 글자 위쪽에 그림자를 만든다. 필수 속성.
- `<번짐정도>`: 양수 값을 사용하면 그림자가 모든 방향을 퍼져 나가고, 음수 값은 그림자가 모든 방향으로 축소되어 보인다. 기본값은 0이다.
- `<색상>`: 기본 값은 현재 글자 색이다. 공백으로 구분해 여러 색상을 지정할 수도 있다.

![image](https://user-images.githubusercontent.com/50407047/99894479-646e5b00-2cc7-11eb-8ed8-7a4d57497ab1.png)

```html
  <style>
    h1 { 
		font-size:100px;  /* 글자 크기 */
		font-family:"Arial Rounded MT"; /* 글꼴 */
    }
    .shadow1{ 
		color:orange; /* 글자색 */
		text-shadow:1px 1px;  /* 텍스트 그림자 */
    }
  	.shadow2 {
  		text-shadow: 5px 5px 3px #f00;  /* 텍스트 그림자 */
  	}
    .shadow3 { 
		color:#fff;  /* 글자색 */
		text-shadow:7px -7px 5px #000;  /* 텍스트 그림자 */
	}
  </style>
</head>
<body>
	<h1 class="shadow1">HTML5</h1> 
	<h1 class="shadow2">HTML5</h1>
	<h1 class="shadow3">HTML5</h1>
</body> 
```

여러 개의 그림자 효과를 추가할 수도 있다.

```html
<style>
  body { background:#222;}  /* 문서 배경색 */
  h1 { 
    font-size:80px;  /* 글자 크기 */
    font-family:"Arial Rounded MT";  /* 글꼴 */
    letter-spacing:4px; /* 자간 */
  }
  .shadow3 {
    color: #000; /* 글자색 */
    text-shadow: 0 0 4px #ccc, 0 -5px 4px #ff3, 2px -10px 6px #fd3, -2px -15px 11px #f80, 2px -19px 18px #f20;  /* 텍스트 그림자 */
  }
</style>
</head>
<body>
  <h1 class="shadow3">HTML5</h1>
</body> 
```

### `white-space` 속성: 공백 처리하기

- `white-space: normal | nowrap | pre | pre-lie | pre-wrap`
  - `normal`: 여러 개의 공백을 하나로 표시한다.
  - `nowrap`: 여러 개의 공백을 하나로 표시하고 **영역 너비를 넘어가는 내용은 줄을 바꾸지 않고 계속 한줄로 표시**한다.
  - `pre`: 여러 개의 공백을 그대로 표시하고 **영역 너비를 넘어가는 내용은 자동으로 줄을 바꿔 표시**한다.
  - `pre-line`: 여러 개의 공백을 하나로 표시하고 

###  `letter-spacing`과 `word-spacing` 속성: 텍스트 간격 조절하기

- `letter-spacing: normal | <크기>`: 낱 글자 사이 간격 조절. 주로 사용
  - 자간은 가능하면 **em 단위**로 지정하는 것이 좋다. 그래야만 **바뀌는 글꼴에 맞춰 자간이 유지**되기 때문이다.
- `word-spacing: normal | <크기> `: 단어 사이 간격 조절 

![image](https://user-images.githubusercontent.com/50407047/99895873-6e478c80-2cce-11eb-953f-9e4d564469f6.png)

``` html
<style>
  h1 {font-size:40px;} /* 글자 크기 */
  .letter1 {
    letter-spacing: 0.2em;  /* 자간 */
  }
  .letter2{
    letter-spacing:0.5em; /* 자간 */
  }
</style>
</head>
<body>
  <h1>HTML5</h1>
  <h1 class="letter1">HTML5</h1>
  <h1 class="letter2">HTML5</h1>
```

## 문단 스타일

### `direction` 속성: 글자 쓰기 방향 지정

`direction: ltr | rtl`

- `ltr`: 왼쪽에서 오른쪽으로. 기본값
- `rtl`: 오른쪽에서 왼쪽으로.



### `text-align` 속성: 텍스트 정렬하기

`text-align: start | end | left | right | center | justify | match-parent`

- `direction`이 `ltr`인지 `rtl`인지에 따라 `start`와 `end`의 정렬 위치가 결정된다.
- `justify`: 가운데정렬
- `match-parent`: 부모 요소에 따라 문단을 정렬한다.



### `text-justify` 속성: 정렬 시 공백 조절하기

`text-align` 값이 `justfiy`일 경우, 양쪽 끝에 맞추기 때문에 글자와 글자 사이 간격이 어색하게 벌어질 수 있따. 이때 간격을 어떻게 조절해 정렬할 것인지 지정할 수 있다.

`text-justify: auto | none | inter-word | distribute`

- `auto`: 웹 브라우저에서 자동으로 지정한다.
- `none`
- `inter-word`: 단어 사이의 공백을 조절해 정렬한다.
- `distribute`: 인접한 글자 사이의 공백을 똑같이 맞추어 정렬한다.



### `text-indent` 속성: 텍스트 들여쓰기

`text-indent: <크기> | <백분율>`

- 크기: 음수 값도 사용할 수 있다.
- 백분율: 부모 요소의 너비를 기준으로 상대적 크기를 지정한다. 따라서 웹문서 화면의 너비가 달라지면 들여쓰기 양도 달라진다.

### `line-height` 속성: 줄 간격 조절하기

- `line-height: normal | <숫자> | <크기> | <백분율> | inherit`
- 숫자나 백분율로 지정했을 때는 글자 크기를 기준으로 지정한다.

![image](https://user-images.githubusercontent.com/50407047/99899390-6696e080-2cec-11eb-8047-1d71d1e5e972.png)

```html
<style>
  p { 
    font-size:15px;
    border:1px solid gray;
    padding:10px;
  }
  .big-line {
    line-height:2;  /* 글자 크기 2배만큼의 줄간격 */
  }
  .small-line {
    line-height: 0.7;  /* 글자 크기 0.7배만큼의 줄간격 */
  }

</style>
</head>
<body>
  <h2>블루베리(Blueberry)</h2>
  <p>블루베리의 대표적인 기능은 항산화로 비타민A, C, E가 풍부하고 안토니시아민, 페놀 등이 활성 산소를 없애 노화를 억제한다.
    이밖에 블루베리의 안토니시아민은 눈의 피로와 시력 저하를 회복시키는 효능을 가지고 있다.</p>
  <p class="small-line">블루베리의 대표적인 기능은 항산화로 비타민A, C, E가 풍부하고 안토니시아민, 페놀 등이 활성 산소를 없애 노화를 억제한다.
    이밖에 블루베리의 안토니시아민은 눈의 피로와 시력 저하를 회복시키는 효능을 가지고 있다.</p>
  <p class="big-line">블루베리의 대표적인 기능은 항산화로 비타민A, C, E가 풍부하고 안토니시아민, 페놀 등이 활성 산소를 없애 노화를 억제한다.
    이밖에 블루베리의 안토니시아민은 눈의 피로와 시력 저하를 회복시키는 효능을 가지고 있다.</p>    
</body>
```



### `text-overflow`  속성: 넘치는 텍스트 표기하기

`white-space: nowrap`으로 지정해 줄바꿈을 하지 않을 때는 텍스트가 기준선을 벗어나 넘칠 수도 있다. 이렇게 **넘치는 텍스트를 어떻게 처리할 지 지정**하는 것이 `text-overflow`이다. 다만 이 속성은 해당 요소에서 `overflow` 속성 값이 `hidden`이거나 `scroll, auto`이면서 `white-space: nowrap` 속성을 함께 사용했을 경우에만 적용된다.

`text-overflow: clip | ellipsis`

- `clip`: 넘치는 텍스트를 자른다.
- `ellipsis`: 말줄임표로 잘린 텍스트가 있다고 표시한다.

아래 코드는 다음과 같이 텍스트 내용이 넘치면 **말 줄임표로 표시**되고 **그 위로 마우스를 오버하면 감추어져 있던 내용이 표시**된다. (마우스를 오버하면 표시되는 것은 clip이나 ellipsis나 같다)

![image](https://user-images.githubusercontent.com/50407047/99899478-f046ae00-2cec-11eb-8820-ca91e1fbe1aa.png)

![image](https://user-images.githubusercontent.com/50407047/99899484-ff2d6080-2cec-11eb-9f99-d91a3919eda0.png)

```html
<style>
  .content {
    border:1px solid #ccc;  /* 테두리 */
    width:300px;  /* 단락의 너비 */
    white-space:nowrap;  /* 줄바꿈 없음 */
    overflow:hidden;  /* 넘치는 부분 감춤 */
    text-overflow:ellipsis;  /* 말줄임표 */
  }
  .content:hover {
    overflow:visible;  /* 넘치는 부분 보여줌*/
  }
</style>
</head>
<body>
  <h2>귀리(Oat) </h2>
  <p class="content">귀리는 베타글루칸(항암 및 면역증강작용을 가지고 있는 불소화성 다당류) 성분을 포함하고 있다.</p>
</body> 
```

> W3C CSS 검사기
>
> - W3C에서는 HTML 소스뿐만 아니라 CSS 소스도 검사해준다.
> - 먼저 HTML 소스를 검사한 후 CSS 소스를 검사하는 것이 좋다.
> - `https://jigsaw.w3.org/css-validator/` 

## 목록 스타일

### `list-style-type` 속성: 목록의 불릿과 번호 스타일 지정하기

`list-style-type: none | <순서 없는 목록의 불릿> | <순서 목록의 번호>`

#### 순서 없는 목록에서 불릿 모양 바꾸기

- `disc`: 채운 원
- `circle`: 빈 원
- `square`: 채운 사각형
- `none`: 불릿 없애기

#### 순서 없는 목록에서 불릿 없애기

`ul { list-style-type: none; }`

#### 순서 목록에서 숫자 바꾸기

| 속성 값                      | 설명                 | 예시               |
| ---------------------------- | -------------------- | ------------------ |
| decimal                      | 1로 시작하는 십진수  | 1,2,3, …, 10, 11   |
| decimal-leading-zero         | 앞에 0이 붙는 십진수 | 01,02,03, …, 10,11 |
| lower-roman                  | 소문자 로마 숫자     | i, ii, iii, iv, v  |
| upper-roman                  | 대문자 로마 숫자     | I, II, III, IV, V  |
| lower-alpha 또는 lower-latin | 소문자 알파벳        | a, b, c, d, e      |
| upper-alpha 또는 upper-latin | 대문자 알파벳        | A, B, C, D, E      |
| armenian                     | 아르메니아 숫자      |                    |
| georgian                     | 조지 왕조시대의 숫자 | an, ban, gan       |

### `list-style-image` 속성: 불릿 대신 이미지 넣기

`list-style-image: url(이미지 경로) | none; `

```css
ul {
	list-style-image:url('images/dot.png');  /* 불릿으로 사용할 이미지 */
}
```

### `list-style-position`: 목록에서 들여 쓰는 효과 내기

불릿이나 번호는 실제 내용의 바깥쪽에 표시된다. 그러나 `list-style-position`을 이용하면 실제 내용이 시작되는 위치에 불릿이나 번호를 표시하기 때문에 화면에는 불릿이나 번호가 좀 더 안쪽으로 들여 써진 듯한 효과가 나타난다.

`list-style-position: inside | outside`

- `inside`: 불릿이나 숫자를 안쪽으로 들여 쓴다.
- `outside`: 기본 값으로 불릿이나 숫자르 밖으로 내어 쓴다.

```css
.inside { list-style-position: inside; }
```

![image](https://user-images.githubusercontent.com/50407047/99899912-03a74880-2cf0-11eb-9163-49870b115089.png)

### `list-style` 속성: 목록 속성 한꺼번에 표시하기

`list-style-type`과 `list-style-position`, `list-style-image` 속성을 한꺼번에 표시할 수 있다.

```css
ol {
  list-style-type:lower-alpha; 
  list-style-position: inside;
}
```

```css
ol {
  list=style: lower-alpha, inside;
}
```

## 문제

**Q3**

![image](https://user-images.githubusercontent.com/50407047/99900014-c68f8600-2cf0-11eb-9660-65503088ccba.png)

```css
.container {
  width:500px;
  height:50px;
  margin:0 auto;
  background-color:#444;
  text-align: center;
}    
h1 {
  color: #fff;
  text-shadow: 3px 2px 5px #000;
}
```

