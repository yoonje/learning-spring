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
### DI에 사용하는 대표적인 어노테이션
* @Autowired
  * 스프링 컨테이너에 등록해논 객체들에서 스프링이 자동으로 연결 시켜주는 어노테이션
  * 생성자, 필드, setter 매소드에 어노테이션을 붙여서 주입해줄 수 있음
* @Service
  * `비지니스 로직`에 관련된 객체를 스프링 컨테이너에 등록하는 어노테이션 
* @Repository
  * `데이터`에 관련된 객체를 스프링 컨테이너에 등록하는 어노테이션
* DI의 장점
  * 스프링 컨테이너 스프링 빈을 등록할 때 싱글톤으로 등록하여 같은 스프링 빈이면 모두 같은 인스턴스


### 의존 관계 설정
* Component 스캔으로 등록
  * @Service, @Repository, @Controller 등등은 @Component를 가지고 있는 어노테이션을 활용해서 스프링 빈으로 등록
  * `SpringBootApplication` 어노테이션이 있는 메인 함수가 존재하는 패키지의 하위 패키지들은 스프링이 실행했을 때 모두 컴포넌트 스캔되어 빈으로 등록
* Java 코드로 등록
  * `@Configuration`이 붙은 클래스르 만들고 스프링이 실행할 때 구성할 수 있게 하고 그 안에 `@Bean` 어노테이션을 붙여줘서 빈으로 등록
  

### 보충할 것
* 섹션 3 / 섹션 5 - 예제
* 섹션 6 / 섹션 7
