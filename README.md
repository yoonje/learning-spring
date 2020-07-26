# Spring Boot 정리 자료
코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술 정리 문서

Table of contents
=================
<!--ts-->
   * [Spring Boot Basis](#Spring-Boot-Basis)
   * [Spring Boot Bean DI](#Spring-Boot-Bean-DI)
<!--te-->

Spring Boot Basis
=======
### 정적 컨텐츠
* 정적 컨텐츠 요청 처리 시나리오
  * 스프링은 웹 요청이 들어왔을 때 내장 톰캣 서버가 요청을 핸들링하고 컨트롤러가 없는 경우 `resources/static`에서 요청에 맞는 html 파일을 찾고 있을 경우 반환한다.

### MVC와 템플릿 엔진
* MVC
  * Model: 데이터 및 DB 관련 부분
  * View: 화면과 관련된 부분
  * Controller: 비지니스 로직 및 요청에 대한 처리 관련 부분
* 템플릿 엔진의 요청 처리 시나리오
  * 스프링은 웹 요청이 들어왔을 때 내장 톰캣 서버가 요청을 핸들링하고 요청에 맞는 컨트롤러에서 동적 컨텐츠 내용을 추가하고  `viewResolver`에 의해 `resources/templates`에서 요청에 맞는 html 파일을 찾고 변환한 이후 반환한다.

### API
* API
  * MVC에서 템플릿 엔진 방식과 유사하지만 html 파일을 내리는 것이 아닌 데이터를 바로 내리는 것이 차이
  * `MVC` 방식에서 `@ResponseBody` 어노테이션을 추가하여 쉽게 구현 가능하고 `json`이 디폴트로 리턴됨
* API 방식의 요청 처리 시나리오
  * 스프링은 웹 요청이 들어왔을 때 내장 톰캣 서버가 요청을 핸들링하고 요청에 맞는 컨트롤러에서 `@ResponseBody`가 있으면 `HttpMessageConverter`에서 객체는 `MappingJackson2HttpMessageConverter`로 문자는 `StringHttpMessageConverter` 변경하여 반환
  * 클라이언트가 요청하는 HTTP Accept 헤더와 서버의 컨트로럴러 번환 타입 정보를 조합해서 `HttpMessageConverter`가 자동으로 선택
  
Spring Boot Bean DI
=======

### 보충할 것

