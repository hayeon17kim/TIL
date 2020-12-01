##  7. 색상과 배경을 위한 스타일

### 7-1. 웹에서 색상 표현하기

#### 16진수 표기법

6자리의 16진수로 표시한다. 6자리는 `#RRGGBB` 형식으로 표시한다. 

#### rgb와 rbga 표기법

0부터 255까지의 숫자로 표기한다. rgba에서 a(alpha)는 불투명도 값을 나타내는 것으로 0부터 1까지의 값 중에서 사용할 수 있다. 0은 투명한 것이고 1은 완전히 불투명한 것이다.

#### hsl과 hsla 표기법

hsl은 hue(색상), saturation(채도), lightness(밝기)를 나타낸다.

```html
hsl(<hue값>, <saturation값>, <lightness값>);
hsl(<hue값>, <saturation값>, <lightness값>, <alpha값>);
```

색상(hue)는 색의 3요소 중 하나로 각도를 기준으로 색상을 둥글게 배치한 색상환으로 표시한다. 0와 360도에는 빨간색, 120도에는 초록색, 240도에는 파란색이 배치되고 그 사이사이에 나머지 색들이 배치된다.

채도(saturation)는 %로 표시하는데 아무것도 섞이지 않은 상태가 가장 채도가 높은 상태이다. 채도가 0%면 회색톤, 100%면 순색으로 표시된다. 밝기(lightness)도 %로 표시하는데 0%가 가장 어둡고, 100%가 가장 밝다.

#### 색상 이름 표기법

 잘 알려진 색상 이름으로 표시할 수 있다. 모든 브라우저에서 표현할 수 있는 색상을 웹 안전 색상(web-safe color)라고 하는데 기본 16가지 색상을 포함해 모두 216가지이다.



### 7-2. 배경 색과 배경 이미지

#### background-color 속성: 배경 색 지정하기

글꼴이나 글자 크기 등은 하위 요소에 상속된다. 그러나 `background-color` 값은 상속되지 않는다. 

> 모든 웹 문서 요소의 배경은 기본적으로 투명하다.

#### background-clip 속성: 배경 적용 범위 조절하기

박스모델 관점에서 배경 적용 범위를 조절할 수 있다. 박스 모델의 가장 외곽인 border까지 적용할 지, 테두리를 빼고 padding 범위까지 적용할 지, 아니면 content 부분에만 적용할 지 선택할 수 있다.

> 박스 모델이란 웹 요소 형태를 박스(box)로 인식하고 테두리와 여백, 요소 사이의 간격 등을 계산하고 배치하는 방법을 말한다.

```css
backgorund-clip: border-box | padding-box | content-box
```

> border-box: 기본값

#### background-image 속성: 웹 요소에 배경 이미지 넣기

배경 이미지에는 웹에서 사용 가능한 파일인 jpg,나 gif, png 파일을 사용하며 이것을 **`url(파일 경로)` 형식**으로 사용한다. 파일 경로는 **현재 웹 문서를 기준으로 상대 경로를 지정**할 수도 있고, `http://`로 시작하는 **절대 경로**를 사용할 수도 있다. 이때 파일 경로에는 **작은 따옴표(또는 큰따옴표)를 붙여도 되고 안 붙여도 된다.**

#### background-repeat 속성: 배경 이미지 반복 방법 지정하기

```css
background-repeat: repeat | repeat-x | repeat-y | no-repeat
```

- repeat: 브라우저 화면에 가득 찰 때가지 배경 이미지를 가로와 세로로 반복한다.
- repeat-x: 브라우저 창 너비와 같아질 때까지 배경 이미지를 가로로 반복한다.
- repeat-y: 브라우저 창 높이와 같아질 때까지 배경 이미지를 세로로 반복한다.
- no-repeat: 배경 이미지를 한 번만 표시하고 반복하지 않는다.



#### background-size: 배경 이미지 크기 조절하기

```css
background-size: auto | contain | cover | <크기 값> | <백분율>
```

- `auto`: **원래 배경 이미지 크기만큼** 표시된다. (기본값)
- `contain`: **요소 안**에 배경 이미지가 **다 들어오도록** 이미지를 **확대/축소**한다.
- `cover`: 배경 이미지로 **요소를 모두 덮도록** 이미지를 확대/축소한다.
- `<크기 값>`: 너비 값과 높이 값을 지정한다. **너비 값만 지정할 경우**, 원래 배경 이미지 크기를 기준으로 축소/확대 **비율을 자동으로 계산해 높이 값을 지정**한다.
  - 예: `background-size:200px 150px` 

- `<백분율>`: 배경 이미지가 들어갈 요소의 크기를 기준으로 백분율 값을 지정하고 그 크기에 맞도록 배경 이미지를 확대하거나 축소한다. 속성 값이 하나라면 주어진 값을 너비 값으로 인식하고 높이 값은 자동으로 계산한다. 예를 들어 주어진 값이 60%라면 요소의 60%만큼 너비를 채우고 높이 값도 60%만큼 채우라는 뜻이다.
  - 예: `background-size: 60% 40%;`, `background-size: 60%`



#### background-position 속성: 배경 이미지 위치 조절하기

```css
background-position: <수평 위치> <수직 위치>;
수평 위치 : left | center | right | <백분율> | <길이 값>
수직 위치 : top | center | bottom | <백분율> | <길이 값>
```

값을 하나만 지정할 경우, 그 값은 수평 위치 값으로 간주하고, 수직 위치 값은 50%나 center로 간주한다. 

##### 키워드 표시법

가장 많이 사용하는 방법이다. 수평위치는 left, center, right 중 하나를 선택할 수 있고, 수직 위치는 top과 bottom, center 중에서 선택한다. `background-position: center bottom;` 이라면 배경 이미지를 요소의 중앙 하단에 배치한다. `background-position: center center;`라면 `background-position: center`로 줄여 쓸 수 있다.

##### 백분율(%) 표시법

주어진 요소의 해당 위치에 배경 이미지의 위치를 백분율로 맞춘다.

##### 길이(px) 표시법

#### background-origin 속성: 배경 이미지 배치할 기준 조절하기

`background-position` 속성을 이용해 배경 이미지를 배치할 기준은 `background-origin` 속성으로 지정한다.

```css
background-origin: border-box | padding-box | content-box
```

#### background-attachment 속성: 배경 이미지 고정하기

```css
background-attachment: scroll | fixed
```

- scroll: 화면 스크롤과 함께 배경 이미지도 스크롤된다. 기본 값이다.
- fixed: 화면이 스크롤되더라도 배경 이미지는 고정된다.


#### background 속성: 속성 하나로 배경 이미지 제어하기

````css
background: url('images/bg3.jpg') no-repeat fixed right bottom;
````

 