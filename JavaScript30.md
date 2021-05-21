# JavaScript 30일 챌린지

키보드 누르면 해당 키 번호가 몇번인지 알려주는 사이트

http://keycode.info/

data attribute가 무엇인가? 

```html
<audio data-key="65" src="sounds/clap.wav"></audio>
```

src, class 와 같이 이미 있는 속성을 사용하는 것이 아니라, 개발자가 속성을 만들 때 앞에 data-를 붙인다. 일종의 약속.

그런데 이대로 하면 연달아 키를 눌러도 키를 누른 만큼 소리가 나지 않는다. audio.play()가 실행 중일때는 또 실행하지 않는 것이다. 이 문제를 해결하기 위해서 다음과 같이 audio 의 currentTime 속성을 0으로 지정한다. 맨 처음으로 돌아가는 것이다. 

```js
window.addEventListener("keydown", function(e) {

 const audio = document.querySelector(`audio[data-key="${e.keyCode}"]`);

 if (!audio) return; // stop the function from running all together

 audio.currentTime = 0; // rewind to the start

 audio.play();

});
```

