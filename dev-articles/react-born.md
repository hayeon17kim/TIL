[Mungue Lee님 블로그](https://medium.com/@RianCommunity)의 "[React: #1 React의 탄생배경과 특징](https://medium.com/@RianCommunity/react의-탄생배경과-특징-4190d47a28f)"을 읽고  공부한 내용을 정리했습니다. 감사합니다.

## React의 탄생 배경과 특징

### 탄생 배경

2006년 Jquery 발표 이후 Jquery가 DOM을 다루는 방식은 쉽고 효율적이라고 인정받아왔으며, 오랜 시간 동안 사실상 표준으로 사용되어왔다. 

그러다 Backbone, AngularJS를 위시한 Single Page Application(SPA)이 구조화된 프론트엔드 환경을 제시하기 시작했다. 이때도 DOM을 다루는 중요한 방법으로 Jquery를 사용했다.

Facebook이 React를 발표했다. React는 Angular와 같은 Framework가 아니라 Library이다. 웹을 만드는 데 꼭 필요한 도구들이 기본적으로 포함되어 있지 않지만 대신 가볍다. 또한 빠르게 배울 수 있고, 개발자의 창의성을 자극하는 매력이 있다.

### 리액트의 특징

#### Component

UI를 구성하는 개별적인 뷰 단위이다. React로 개발한다는 것은 블럭을 조립해 앱을 만드는 것과 같다. 각 블럭들은 다른 앱에서 쉽게 재사용할 수 있다. React의 목표는 성능보다는 **유지가능한 앱**을 만드는 것에 있다고 한다.

#### JSX

JSX는 React를 위해 태어난 자바스크립트 문법이다. 

```react
class HelloMessage extends React.Component {
  render() {
    return <div>Hello {this.props.name}</div>;
  }
}
ReactDOM.render(<HelloMessage name="John" />, mountNode)
```

React는 작성한 코드를 컴파일하는 과정을 꼭 거쳐야 한다. 사실상 자바스크립트 표준이 되어가고 있는 Babel을 사용한다. Babel의 React 플러그인을 통해 위의 코드를 컴파일하면 다음과 같은 모습이 된다.

```js
class HelloMessage extends React.Component {
  render() {
    return React.createElement(
      "div",
      null,
      "Hello ",
      this.props.name
    );
  }
}

ReactDOM.render(React.createElement(HelloMessage, { name: "John" }), mountNode);
```

React는 Babel과 같은 트랜스파일러를 꼭 써야 하기 때문에 Webpack 등을 통한 까다로운 초기 세팅이 필수적이다. JSX를 사용하지 않고 위 코드처럼 순수 자바스크립트 코드를 작성해서 사용할 수도 있지만, 한 눈에 이해하기 힘들다.

JSX는 Declarative한 개발을 도와주는 도구이다. 한 눈에 이해하기 쉬운 개발을 만들어준다. JSX는 형태가 html같다. 유저에게 보여주고 싶은 최종적인 View라고 할 수 있다. 개발자는 JSX를 통해 예측가능한 개발을 할 수 있고, 유지보수, 협업을 할 때도 좋다.

### Virtual DOM

DOM은 웹의 핵심으로써, 브라우저가 화면을 그리기 위한 정보가 담겨 있는 문서이다. 문제는 DOM을 효과적으로 다루는 것이 꽤나 힘들다는 것이다. DOM 자체 성능보다는 해석 과정에 문제가 있는 경우가 대부분이다. 브라우저별로 DOM을 가지고 화면을 만드는 방식이 다르기도 하다.

#### Jquery의 문제점

Jquery는 누구나 쉽게 DOM을 조작할 수 있도록 하는 도구였다. 그러나 Jquery는 구조적 대안이라기보다는 커터칼처럼 DOM을 잘라내는 방식이었다. 따라서 프론트엔드 개발의 전문성이 높아지고 대형 앱의 유지/보수가 화두가 되면서 Jquery의 한계가 드러났다.

#### DOM에 대한 완전히 새로운 접근

```react
class HelloMessage extends React.Component {
  render() {
     return (
        <div>
          <div>Hello {this.props.name}</div>
          <div>I am {this.state.chatName}</div>
       </div>
    );
  }
}
```

위의 코드에서 `this.props.name`이 Alex이고, `this.state.chatName`이 Thom이면 "Hello Alex", "I am Jenny"를 유저에게 보내준다. 여기서 `this.state.chatName`만 Mary로 바뀐다면 어떻게 될까? 

React 컴포넌트는 Render를 다시 호출하여 새로운 결과값을 리턴한다. 그런데 이 리턴값은 바로 DOM에 반영되지 않는다. 브라우저에 렌더링되지 않는다는 것이다.

이때 **render함수가 리턴**하는 것은 **새로운 Virtual DOM을 만들기 위한 재료**이다. **React는 새로운 return 값을 가지고 새로운 Virtual DOM을 만든다**. 그리고 현재 브라우저에서 보여지고 있는 **진짜 DOM과 비교**하여 **어떤 부분이 달라졌는지** 찾아낸다. 그리고 **바뀐 부분만 진짜 DOM에 적용**한다. 그러면 브라우저는 이 DOM을 해석하여 유저에게 새로운 화면을 보여준다. 	

따라서 ` “<div>Hello Alex</div><div>I am Marry</div>”`를 리턴하면 Virtual DOM은 비교 작업을 통해 this.state.chatName(Jenny -> Marry)의 변경사항을 찾아내고 진짜 DOM에서 `<div>I am Jenny</div>`만 `<div>I am Mary</div>`로 변경한다.

브라우저에게 DOM을 해석하고 렌더링하는 것은 굉장히 비싼 작업이다. Virtual DOM은 그 작업을 미리 최적화시켜줄 뿐만 아니라 컴포넌트 단위로 묶어서 관리할 수 있게 해준다.