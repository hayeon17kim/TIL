한 문제는 풀지 않고 놓는다.

간단하게 설명하고, input, output, constraint, 

이 문제를 풀지 않은 사람이 푼다. 





캐시

- 메모리가 무제한이 아니다. 
- 그렇기 때문에 거기 안에 들어있는 메모리를 실시간으로 계속 모니터링한다.
- 최근에 쓴 것들은 메모리 맨 앞으로 넣는다. 가장 쓰지 않는 것은 뒤로 가고 뺀다.
- DS 는 hash table 과 doubly linked list를 사용한다.



반드시 알아야 하는 것

- 최대한 팀장님이 고민하셔서 optimal solution을 공유하고, 알고리즘 풀면서 정말 필요한 개념은 설명하면 좋을 것 같다. 



```sql
CREATE TABLE `statistics_accumulate` (
  `base_date` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`new_user_cnt` BIGINT NOT NULL,
  `new_user_cnt_accu` BIGINT NOT NULL,
  `withdraw_user_cnt` BIGINT NOT NULL,
  `withdraw_user_cnt_accm` BIGINT NOT NULL,
  `new_product_reg_cnt` BIGINT NOT NULL,
  `new_product_reg_cnt_accm` BIGINT NOT NULL,
  `user_auto_product_cnt` BIGINT NOT NULL,
  `reg_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(`base_date`)
);
```



## Node



- 팀버너스리
- 넷스케이프
- 넷스케이프 중 한명이 자바스크립트 창시



- 

- 구글이 V8 엔진을 오픈소스로 공개 => 탈웹화 가속화



- 라이언 달 : Node JS 창시
- V8 엔진을 자바스크립트 엔진으로 사용
- event driven, non-blocking IO 방식을 결합

## 자바스크립트의 개념

- language
- runtime(자바스크립트가 동작하는 환경): 웹브라우저와 NodeJS

> **즉, Node JS는 자바스크립트라는 언어가 동작하는 환경이다!!!!**. 자바스크립트를 알고 있다면 Web Browser, Node JS 를 제어할 수 있다. 그러나, 자바스크립트를 알아도 Web Browser와 Node JS 에서 어떤 기능을 제공하는 지 모르면 잘 제어할 수 없다.

## Ndoe JS 장점

- 성능이 좋다: V8 엔진 때문이다.
- Event Driven, None Blocking IO  => 이것들이 적합한 환경에서는 성능이 좋다.
- 자바스크립트로 하나의 완결된 어플리케이션을 구현할 수 있다.

## 





valid

```js
valid: function(){
  if (!this.parameters.startDate || !this.parameters.endDate) {
    alert('기간을 설정하여 검색해주세요.');
    return false;
  }
  if(!this.parameters.userId && !this.parameters.nickname ) {
    alert("아이디, 닉네임 중에 한개의 검색 조건을 추가 입력해주세요.");
    return false;
  }
  return true;
},
```



```json
valid:function(){
  if(this.parameters.exposeStartDate === '' || this.parameters.exposeEndDate === '' ){
    alert('기간을 설정하여 검색해주세요.');
    return false;
  }
},
load: function () {
  if(!this.valid()){
    return ;
  }
```



```js
valid:function(){
  if(!this.parameters.startDate || !this.parameters.endDate ){
    alert('기간을 설정하여 검색해주세요.');
    return false;
  }
  return true;
},
  initializeSearch: function() {
    if(!this.valid()){
      return ;
    }
```



```java
package com.joongna.api.v2.fraud.service;

import com.joongna.api.global.util.StringMaskingUtils;
import com.joongna.api.report.service.CyberCopService;
import com.joongna.api.report.service.CyberCopService.FieldType;
import com.joongna.api.v2.fraud.domain.FraudDto;
import com.joongna.api.v2.fraud.domain.TheCheatDto;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.Markers;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class FraudService {

    private final CyberCopService cyberCopService;
    private final TheCheatService theCheatService;

    private static final String FRAUD_MARKER_PREFIX = "fraud-check";

    public FraudDto.Res getFraud(FraudDto.Req req){
        // 사이버캅 : 3개월간 사기조회 3회 이상 카운트
        Integer cyberCopCount = cyberCopService.getFraudResult(req.getType(), req.getKeyword());
        // bool이 맞을까...
        TheCheatDto.Res theCheat = theCheatService.getTheCheat(req);

        // 사기 조회 호출 확인을 위한 로그 추가
        Map<String, Object> logData = new HashMap<>();
        logData.put("keyword", req.getKeyword());
        logData.put("keyword_name", req.getType().getCyberCop());
        logData.put("cybercop", cyberCopCount);
        logData.put("theCheat", theCheat);
        logData.put("category", "fraud");
        log.info(Markers.append(FRAUD_MARKER_PREFIX, logData),
            "Fraud inquiry");

        return FraudDto.Res.builder()
            .keyword(FieldType.H.equals(req.getType())
                ? StringMaskingUtils.maskingMobile(req.getKeyword())
                : StringMaskingUtils.maskingAccountNo(req.getKeyword()))
            .type(req.getType())
            .cyberCop(cyberCopCount)
            .theCheat(theCheat)
            .build();
    }

}

```





```java
package com.joongna.api.v2.fraud.domain;

import com.joongna.api.report.service.CyberCopService.FieldType;
import com.joongna.api.v2.fraud.exception.FraudError;
import com.joongna.api.v2.fraud.exception.FraudException;
import io.swagger.annotations.ApiModelProperty;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

public class FraudDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Req {
        @ApiModelProperty(value = "검색어", required = true)
        private String keyword;
        @ApiModelProperty(value = "검색종류(H:핸드폰번호, A:계좌번호)", required = true)
        private FieldType type;

        public void valid(){
            if(StringUtils.isBlank(this.keyword) || this.type == null
                || !StringUtils.isNumeric(this.keyword)) {
                throw new FraudException(FraudError.INCORRECT_ENTER);
            }

            if (FieldType.H.equals(this.type)) {
                String regex = "^01([0-1|6-9])([0-9]{3,4})([0-9]{4})$";
                Matcher matcher = Pattern.compile(regex).matcher(this.keyword);
                if (!matcher.find()) {
                    throw new FraudException(FraudError.INCORRECT_ENTER);
                }
            }
        }
    }

    @Getter
    public static class Res {
        private String keyword;
        private FieldType type;
        private TheCheatDto.Res theCheat;
        private Integer cyberCop;

        @Builder
        public Res(String keyword, FieldType type , TheCheatDto.Res theCheat , Integer cyberCop){
            this.keyword = keyword;
            this.type = type;
            this.theCheat = theCheat;
            this.cyberCop = cyberCop;
        }
    }
}

```



```java
package com.joongna.api.v2.fraud.service;

import com.google.gson.Gson;
import com.joongna.api.common.enums.UseYnType;
import com.joongna.api.config.aws.SecretsMangerProperties;
import com.joongna.api.feign.client.TheCheatApiClient;
import com.joongna.api.global.util.AESUtils;
import com.joongna.api.v2.fraud.domain.FraudDto;
import com.joongna.api.v2.fraud.domain.TheCheatDto;
import com.joongna.api.v2.fraud.domain.TheCheatDto.ApiReq;
import com.joongna.api.v2.fraud.domain.TheCheatDto.ApiRes;
import com.joongna.api.v2.fraud.domain.TheCheatDto.Res;
import com.joongna.api.v2.fraud.exception.FraudError;
import com.joongna.api.v2.fraud.exception.FraudException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TheCheatService {

    private final Gson gson;
    private final SecretsMangerProperties properties;
    private final TheCheatApiClient theCheatApiClient;

    /**
     * 사기 이력 조회
     * @param fraudReq 사기 이력 조회 요청
     * @return 사기 이력 결과 (CyberCop과 달리 건수가 아닌 유무)
     */
    public TheCheatDto.Res getTheCheat(FraudDto.Req fraudReq) {
        // API 요청
        String theCheatApiResult = requestFraudSearch(fraudReq);

        // 응답 결과 추출
        ApiRes response = decodeResponse(theCheatApiResult);

        // 검색 결과 추출
        TheCheatDto.Content content = decodeContent(response.getContent());

        TheCheatDto.Res res = new TheCheatDto.Res();

        if (StringUtils.isNotEmpty(content.getCaution())) {
            res.setCount(content.getCaution().equals(UseYnType.Y.getYn()) ? 1 : 0);
        } else if (StringUtils.isNotEmpty(content.getKeywordUrl())) {
            res.setUrl(content.getKeywordUrl());
        } else {
            throw new FraudException(FraudError.INVALID_RESPONSE_DATA,
                "It has problem with an the cheat result data. - content of original data : "
                    + content);
        }
        return res;
    }

    /**
     * 더치트 API 요청
     * @param fraudReq
     * @return
     */
    public String requestFraudSearch(FraudDto.Req fraudReq) {
        // API 호출
        ApiReq request = ApiReq.of(fraudReq, properties.getTheCheatCredential().getApiKey());

        // Base64 인코딩
        byte[] encoded = Base64.encodeBase64(gson.toJson(request).getBytes(StandardCharsets.UTF_8));
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("contents", new String(encoded));

        // API 요청
        return theCheatApiClient.checkFraud(parameters);
    }

    /**
     * 더치트 조회 결과를 Content 로 변환.
     *
     * @param data 더치트 사기 이력 조회 API 를 통해 받은 원본 결과 값. 암호화되어 있음.
     * @return 복호화한 Response 값
     * @exception FraudException string 타입의 원본 데이터를 Object 로 변환하는 과정에서 문제 발생 시.
     */

    public ApiRes decodeResponse(String data) {
        try {
            ApiRes response = gson.fromJson(data, ApiRes.class);

            response.setResultMessage(
                new String(Base64.decodeBase64(response.getResultMessage().getBytes(StandardCharsets.UTF_8)))
            );

            if (response.isError()) {
                throw new FraudException(FraudError.API_FAILURE, response.getResultMessage());
            }

            return response;
        } catch (Exception e) {
            throw new FraudException(FraudError.INVALID_RESPONSE_DATA,
                "It has problem with an the cheat result data. - original data : " + data, e);
        }
    }

    /**
     * 더치트 API 응답에서 실제 검색 결과 추출
     * @param encodedContent 암호화된 검색결과(content) 값
     * @return Content 복호화된 검색결과
     */
    private TheCheatDto.Content decodeContent(String encodedContent) {
        try {
            // JSON content 값을 base64 형태로 decode
            String decodeJsonStr = new String(Base64.decodeBase64(encodedContent.getBytes()));

            // decode한 문자열을 hex2bin / ASCII 형태로 변환. 문자열 중 ","는 ""로 치환
            StringBuilder decodeSb = new StringBuilder();
            String[] a = decodeJsonStr.split(",");
            for (String item : a) {
                decodeSb.append((char) Integer.parseInt(item, 16));
            }

            // 비밀키
            String secretKey = properties.getTheCheatCredential().getSecretKey();

            // 암호 알고리즘을 적용하여 복호화
            String decryptedContent = aesDecrypt(decodeSb.toString(), secretKey);

            // 직렬화
            return gson.fromJson(decryptedContent, TheCheatDto.Content.class);
        } catch (Exception e) {
            throw new FraudException(FraudError.INVALID_RESPONSE_DATA,
                "It has problem with an the cheat result data. - content of original data : " + encodedContent, e);
        }
    }

    public static String aesDecrypt(String encryptedText, String secretKey) {
        // AES 복호화
        String temp = AESUtils.decrypt(encryptedText, secretKey);

        // iv 와 초기 16바이트 값을 XOR 연산
        String first16Bytes = temp.substring(0, 16);
        String iv = secretKey.substring(0, 16);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < iv.length(); i++) {
            sb.append((char) (first16Bytes.charAt(i)^iv.charAt(i)));
        }

        // XOR 연산 후 정상적인 값이 된 초기 16바이트 값 + 기존 방법으로 복호화된 나머지 값
        return sb.append(temp.substring(16)).toString();
    }

}

```



```java
package com.joongna.api.v2.fraud.domain;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class TheCheatDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class Res {
        private Integer count;
        private String url;
    }


    @Getter
    @Setter
    @Builder
    public static class ApiReq {
        @ApiModelProperty("API 키")
        @SerializedName("api_key")
        private String apiKey;

        @ApiModelProperty("검색 키워드")
        private String keyword;

        @ApiModelProperty("검색 키워드 유형")
        @SerializedName("keyword_type")
        private String keywordType;

        @ApiModelProperty("조회 유형")
        @SerializedName("access_type")
        private String accessType;

        @ApiModelProperty("부가 정보")
        @SerializedName("add_info")
        private String addInfo;

        public static ApiReq of(FraudDto.Req req, String apiKey) {
            return builder()
                .apiKey(apiKey)
                .keyword(req.getKeyword())
                .keywordType(req.getType().getTheCheat())
                .accessType("user")
                .addInfo("")
                .build();
        }
    }

    @Getter
    @Setter
    @ToString
    @ApiModel(description = "더치트 사기 조회 결과 정보")
    public static class ApiRes {

        @ApiModelProperty("상태코드")
        @SerializedName("result_code")
        private String resultCode;

        @ApiModelProperty("내용")
        private String content;

        @ApiModelProperty("결과 메시지")
        @SerializedName("result_msg")
        private String resultMessage;

        public boolean isError() {
            return ApiResCode.findByCode(resultCode).isError();
        }
    }

    @Getter
    @AllArgsConstructor
    protected enum ApiResCode {

        SUCCESS_CODE("1", "정상", false),
        ERROR_DB_CONNECTION_FAIL("-1", "DB 접속 오류", true),
        ERROR_NOT_ALLOWED_IP("-2", "허용된 IP가 아님", true),
        ERROR_INVALID_INFORMATION("-3", "유효한 정보 아님", true),
        ERROR_NOT_SPECIFIED("-9999", "미지정 오류", true);

        private final String code;
        private final String category;
        private final boolean isError;

        public static ApiResCode findByCode(String code) {
            return Arrays.stream(ApiResCode.values())
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElse(ERROR_NOT_SPECIFIED);
        }

    }

    @Getter
    @Setter
    @ToString
    @ApiModel(description = "더치트 사기 조회 결과 내용")
    public static class Content {
        @ApiModelProperty("검색 키워드")
        private String keyword;

        @ApiModelProperty("검색 키워드 유형")
        @SerializedName("keyword_type")
        private String keywordType;

        @ApiModelProperty("검색 결과")
        private String caution;

        @ApiModelProperty("부가 정보")
        @SerializedName("add_info")
        private String addInfo;

        @ApiModelProperty("검색구간 시작일시")
        @SerializedName("date_start")
        private String dateStart;

        @ApiModelProperty("검색구간 종료일시")
        @SerializedName("date_end")
        private String dateEnd;

        @ApiModelProperty("피해사례 조회 URL")
        @SerializedName("keyword_url")
        private String keywordUrl;
    }

}

```





```json
"data": {
  "keyword": "keyword",
  "type": "Type",
  "theCheat": 1,
  "cyberCop": 0
}
```

변경

```json
"data": {
  "keyword": "keyword",
  "type": "Type",
  "theCheat": {
    "count": 1,
    "url": "url"
  },
  "cyberCop": {
    "count": 0
  }
  "cyberCop": 0
}
```



- 



- 시각화 툴



- 지금 현재 구조와 비교를 하면서 어떻게 차이점이 있고 어떻게 적용을 할 것인지. 





- 이 프로젝트가 왜 필요한지
- 현재 구조와 비교를 하면서 어떻게 적용할 수 있을지
- 어떤 데이터를 담아야 할지

로그: 클라우드 워치(일주일) => S3 ()



S3 에 다 저장되어 있음 

=> 젠킨스에서 Elastic Search 에 담기 위해서 



WBS 설정하고 공유해드리겠습니다. 

- live db로 직접 추출을 해서 



- 돌아가면서 회의록 작성



## Node JS: 비동기

- 장점
- 단점



라우팅

라우터(Controller)

- 요청이 들어왔을 때 요청이 길을 찾을 수 있게 해주는 것. 



- module: 프로그램에서 부품으로 사용되는 작은 프로그램. (expres, underscore, jade)
- npm: 모듈들을 관리



- router: 사용자의 요청을 어떤 controller 에 전달할 지, 중개자의 역할
- controller: 회원가입, 홈페이지, 에러화면 컨트롤러. 



## Express 정적 파일을 서비스하는 법



정적인 파일은 수정하면 바로 된다.

정적인 파일을 수정하는 것이 좀 더 편리하다. 동적 파일은 껐다 다시 켜야 하기 때문이다. 



## Template Engine

app.js

```js
var express = require('express');
var app = express(); // 모듈은 함수이다. 함수를 실행하면 application을 리턴한다. express 만든 사람이 약속으로 정해놓은 것임. 받아들여.

app.locals.pretty = true;

app.set('view engine', 'jade'); // view engine 이라는 것은 약속되어 잇는 것 (express)
app.set('views', './views'); // 보통 views 라는 폴더에다 놓기 때문에 이렇게.

app.get('/template', function(req, res) {
  res.render('temp', {time:Date(), _title: 'Jade'}); // temp 라는 템플릿 파일을 웹 페이지로 렌더링해서 전송한다.
});
```

views/temp.jade

```jade
html
    head 
        title= _title
    body 
        h1 Hello Jade
        ul
            -for (let i = 0; i < 5; i++)
                li coding
        div= time
```

- 템플릿 엔진의 종류는 여러 가지가 있지만, 그 중에서 Jade를 사용할 것이다.
- Jade를 express 로 import 한다.
- 환경설정한다.
- 특정한 템플릿을 사용할 때, response의 render 메서드를 사용하고 첫 번째 인자에 템플릿 이름을 넣는다. 템플릿에 어떤 데이터를 주입하고 싶을 때는 두번째 인자로 객체를 전달한다. 이것은 이제  jade 소스에서 `= 변수` 형태로 사용할 수 있다.
- `app.locals.pretty` 값을 `true` 로 바꿈으로서  express의 html을 이쁘게 바꿔줄 수 있다.



## Express, URL을 이용한 정보 전달: 쿼리스트링

- 사용자가 주입하는 입력에 따라서 다른 걸 보여주는 앱
- path에 따라서 다르게 동작하고 있긴 하지만, path가 같으면 항상 결과가 같다.
- 같은 path라도 다른 결과를 보여주기 위해 query string 을 사용한다.
- path가 일종의 라우터와 연결된다.
- 사용자의 입력값에 따라 다른 출력값이 나오게 하고 싶다.

```js

```

- controller가 알아낼 수 있는 방법: 



express 공식문서 : https://expressjs.com/en/4x/api.html



```js
app.get('/topic', function(req, res) {
  let topics = [
    'JavaScript is....',
    'NodeJS is....',
    'Express is....'
  ];

  let output = `
    <a href="/topic?id=0">JavaScript</a><br>
    <a href="/topic?id=1">NodeJS</a><br>
    <a href="/topic?id=2">Express</a><br>
    ${topics[req.query.id]}
  `

  res.send(output);
});
```





## Path 방식

semantic URL

- semantic: 의미론, 의미에 좀 더 부합하는

|                     Original URL                      |                Clean URL                 |
| :---------------------------------------------------: | :--------------------------------------: |
|            `http://example.com/about.html`            |        `http://example.com/about`        |
|          `http://example.com/user.php?id=1`           |       `http://example.com/user/1`        |
|       `http://example.com/index.php?page=name`        |        `http://example.com/name`         |
|     `http://example.com/kb/index.php?cat=1&id=23`     |       `http://example.com/kb/1/23`       |
| `http://en.wikipedia.org/w/index.php?title=Clean_URL` | `http://en.wikipedia.org/wiki/Clean_URL` |

출처: https://en.wikipedia.org/wiki/Clean_URL

=> query string 에서 path variables 방식으로 변경

```js
app.get('/topic/:id', function(req, res) {
  let topics = [
    'JavaScript is....',
    'NodeJS is....',
    'Express is....'
  ];

  let output = `
    <a href="/topic?id=0">JavaScript</a><br>
    <a href="/topic?id=1">NodeJS</a><br>
    <a href="/topic?id=2">Express</a><br>
    ${topics[req.params.id]}
  `

  res.send(output);
```

- path 방식을 사용할 경우 app.get 메서드의 첫 번째 인자 문자열에 path방식을 사용할 곳에 colon을 사용한다. (`/topic/:id`)
- `req.params` 객체에서 꺼내 쓸 수 있다.

```js
app.get('/topic/:id/:mode', function(req, res) {
  res.send(req.params.id + ", " + req.params.mode);
});
```



## POST 방식을 이용한 정보의 전달

- GET 방식: 사용자가 정보를 서버에 요청해서 **가져오는** 방식
- POST 방식: **사용자의 정보를 서버로 전송**하는 방식

```jade
html 
    head 
        meta(charset='utf-8')
    body 
        
```

태그에 속성을 추가할 때는 `(속성="속성값")`  형식을 사용하면 된다.

