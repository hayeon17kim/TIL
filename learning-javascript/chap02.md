# 2장: 자바스크립트 개발 도구

- git: 버전 컨트롤 도구
- Node: **브라우저 밖에서 자바스크립트를 실행할 수 있게 하는 도구**이다.
  - npm: 노드와 함께 설치된다. 이 리스트의 다른 도구를 설치할 때 필요하다.
- Gulp: 반복적인 개발 작업을 자동화하는 빌드 도구. Grunt도 많이 쓰인다.
- Babel: ES6 코드를 ES5로 변환하는 트랜스 컴파일러
- ESLint: 자주 하는 실수를 피하고 더 나은 프로그래머가 되도록 돕는 린트 프로그램



## 1. ES6 사용하기

- ES6는 실무에서는 아직 이르기 때문에 개발자가 ES6 코드를 어디서든 쓸 수 있는 **안전한 ES5** 코드로 **트랜스컴파일**해줘야 한다. 
  - 원래 자바스크립트는 **인터프리팅** 언어로, **컴파일과 링크가 필요 없다**.

- 노드 개발자라면 신경써야 할 자바스크립트 엔진이 하나뿐이기 때문에 이에 대해서 걱정할 필요가 없다.
- 프론트단의 개발자는 브라우저에 따라 사용하는 엔진이 다양하기 때문에 이를 위해 안전한 ES5 코드로 트랜스컴파일 해줘야 한다.
- 자바스크립트가 ES6로 넘어가는 과정은 **점진적**이다. 
  - 자바스크립트의 동적인 성격
  - 최근 브라우저 업데이트 방식: evergreen 브라우저 
    - 브라우저가 제대로 동작하려면 항상 **인터넷에 연결**되어 있어야 하기 때문에 **항상 최신 버전을 유지할 수 있다.**
    - 사용자가 업그레이드를 *거부할 수 없게* 한다.
  - 에버그린 브라우저도 ES6의 기능을 모두 지원할 때까지는 시간이 걸린다. 그때까지는 트랜스 컴파일을 해야만 한다.



## 2. ES6 기능

- ES6의 기능은 트랜스컴파일러조차 전부 지원하지 못한다.

###  터미널

- 터미널(명령줄 또는 명령 프롬프트): 텍스트 명령으로 컴퓨터를 조작하는 방법
- `cd ~`: `~`는 홈 디렉터리를 가리킨다.
- `pwd`: 현재 디렉토리 경로를 출력한다.

### 프로젝트 루트

- 프로젝트마다 디렉토리를 따로 만드는데, 이 디렉토리를 프로젝트 루트라고 부른다.

### 깃과 버전 컨트롤

**.gitignore**

추적하지 않았으면 하는 파일(빌드 과정에서 생기는 파일, 임시 파일 등)을 추적에서 제외한다.

```gitignore
# npm 디버그 기록
npm-debug.log*

# 프로젝트 의존성
node_modules

# macOS 폴더 속성
.DS_Store

# 임시 파일
*.tmp
*~
```



### npm 패키지 관리

- **패키지**는 *완전한 애플리케이션*일 수도 있고, *코드 샘플*일 수도 있고, 프로젝트에서 사용할 *모듈* 혹은 *라이브러리*일 수도 있다.
- npm은 패키지를 설치할 때 전역으로(globally) 혹은 로컬로(locally) 설치할 수 있다.
  - 전역 패키지: 개발 과정에서 사용하는, 터미널에서 실행하는 도구
  - 로컬 패키지:각 프로젝트에 종속되는 패키지 
- 패키지 설치: `npm install 패키지명 (@버전정보)`
- 모듈이 실제 설치된 위치
  - 로컬 모듈: 프로젝트의 node_modules 디렉터리

- **프로젝트에 설치하고 사용하는 모듈**을 **의존성(dependency)**라고 부른다.
- 설치하는 모듈이 늘어나면 모듈을 **추적하고 관리**할 방법이 필요하다. 
- npm은 **`package.json`**을 통해 **의존성을 관리**한다.
- 의존성
  - **일반 의존성**  (`--save`)
  - **개발 의존성**: 앱을 실행할 때는 필요 없지만, 프로젝트를 개발할 때 필요하거나 도움이 되는 패키지 (`--save-dev`) 

- 로컬 패키지를 설치하는 방법: `--save` 또는 `--save-dev` 플래그를 사용한다.
- 플래그를 사용하여 설치한 패키지는 package.json에 등록된다.
- 의존성 관리가 가능한 것은 package.json 파일에서 패키지 이름과 버전 번호를 읽고 필요 패키지를 다시 내려받아 설치할 수 있기 때문이다. 
  - node_modules 디렉터리를 삭제하고, npm install 명령을 내리면, **npm은 package.json 파일을 읽고 필요한 패키지를 자동으로 설치**한다. 



### 빌드 도구: 걸프와 그런트

- 반복 작업을 자동화하는 빌드 도구
- 걸프를 전역으로 설치하는 것은 개발에 사용할 컴퓨터에 한 번만 하면 된다.
- 프로젝트마다 로컬 걸프가 필요하다. 
  - `npm install --save-dev gulp`
  - 개발 의존성: 없어도 앱은 잘 동작하지만ㅡ 개발할 때 걸프가 있으면 작업이 편하다.

> The build tools for Visual Studio 2010 cannot be found  에러가 표시된다면: **npm 패키지 중 상당수가 비주얼 스튜디오의 빌드 도구가 있어야 동작한다**. 비주얼 스튜디오 무료 버전을 설치하고, 시작 메뉴에서 VS2015용 MSBuild 명령 프롬프트를 찾아보자. *비주얼 스튜디오에 의존하는 npm 모듈을 설치할 때는 이 프롬프트를 사용*하는 것이 가장 쉽다.



### 프로젝트 구조

- 소스 코드를 `src`나 `js` 디렉터리에 저장하는 경우가 많다.
- 여기서는 `es6` 디렉토리에 저장해 소스를 ES6 코드로 만들었음을 명확히 한다.
- **서버** 쪽 코드: 프로젝트 루트의 `es6` 디렉터리에 저장
- **브라우저** 코드: `public/es6`에 저장한다. 
  - **브라우저에 보내는 자바스크립트는 원래 공개된(public) 것**이다.
  - 대부분의 프로젝트에서 이렇게 저장한다.

- 변환된 ES5 코드는 distribution의 약자인 dist 디렉터리에 저장한다.

```
.git					#git
.gitignore

package.json	 #npm
node_modules

es6						# 노드 소스
dist

public/				# 브라우저 소스
	es6/
	dist/
```

## 트랜스 컴파일러

- 바벨(babel)과 트레이스(traceur)가 트랜스 컴파일러로 널리 쓰인다.
- 바벨은 ES5를 ES6로 바꾸는 트랜스 컴파일러로 시작했다.
- 지금은 ES6와 리액트, ES7 등 여러 가지를 지원하는 범용 트랜스 컴파일러가 되었다.
- 바벨 버전 6부터 ES5를 ES6으로 변환하려면 ES6 변환 프리셋을 설치하고 바벨이 해당 프리셋을 사용하게끔 설정해야 한다. 
  - `npm install --save-dev babel-preset-es2015`
  - `.babelrc` 파일 생성=> `{ "preset": ["es2015"] }` 추가
    - 프로젝트에서 바벨을 사용할 때 ES6를 사용한다는 것을 인식하게 된다.

> 프리셋(preset)? 기본값. 응용 소프트웨어, 컴퓨터 프로그램 등에 자동으로 할당되는 설정이나 값

> 파일 이름 없이 확장자만 쓰면 숨김 파일이 된다.



### 바벨을 걸프와 함께 사용하기

- 걸프는 ES6코드를 ES5 코드로 바꿀 수 있다.
- gulp-babel 패키지 설치: `npm install --save-dev gulp-babel`

```js
const gulp = require('gulp');
const babel = require('gulp-babel');

gulp.task('default', function() {
  //노드 소스
  gulp.src("es6/**/*.js")
  .pipe(babel())
  .pipe(gulp.dest("dist"));
  //브라우저 소스
  gulp.src("public/es6/**/*.js")
  .pipe(bable())
  .pipe(gulp.dest("dist"));
})
```

- 걸프는 **파이프라인** 개념으로 작업을 처리한다.
  - `gulp.src("es6/**/*.js")`:서브 디렉토리 깊이에 상관 없이 모든 `js`파일을 선택한다.
  - `.pipe(babel())`바벨은 ES6코드를 ES5코드로 변형한다.
  - `.pipe(gulp.dest("dist"))`: 컴파일된 ES5코드를 dist 디렉토리에 저장한다.

- 문제상황:

  걸프를 명령어를 실행했을 때 다음과 같이 에러가 난다.. 그런데 ES5 코드로 아주 잘 변환이 되었다. 무엇이 문제일까..

```bash
$ gulp
[21:51:13] Using gulpfile ~\git\learning-javascript\gulpfile.js
[21:51:13] Starting 'default'...
[21:51:14] 'default' errored after 939 ms
[21:51:14] Error in plugin "gulp-babel"
Message:
    Plugin/Preset files are not allowed to export objects, only functions. In C:\Users\Monica Kim\git\learning-javascript\node_modules\babel-preset-es2015\lib\index.js
```



### 린트

- 린트 프로그램은 코드를 세심히 **검토**해서 **자주 일어나는 실수를 알려준다.**
- ESLint 사용하는 방법
  - 직접 실행: `es6/test.js`
  - 에디터에 통합
  - Gulpfile에 추가: 에디터에 통합하든 하지 않든 Gulpfile에는 꼭 추가하는 것이 좋다. 빌드할 때마다 걸프를 실행하므로 여기서 코드를 체크하는 것이 좋기 때문이다.
    - `npm install --savec dev gulp-eslint`

```js
const gulp = require('gulp');
// 걸프 의존성을 여기에 쓴다.
const babel = require('gulp-babel');

gulp.task('default', function() {
	// 걸프 작업을 여기에 쓴다.

		//ESLint를 실행한다.
		gulp.src(["es6/**/*.js", "public/es6/**/*.js"])
		.pipe(eslint())
		.pipe(eslint.format());
		//노드 소스
		gulp.src("es6/**/*.js")
		.pipe(babel())
		.pipe(gulp.dest("dist"));
		//브라우저 소스
		gulp.src("public/es6/**/*.js")
		.pipe(babel())
		.pipe(gulp.dest("public/dist"));
});
```