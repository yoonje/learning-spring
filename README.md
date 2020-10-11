# Spring Boot 정리 자료
코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술 정리 문서

Table of contents
=================
<!--ts-->
   * [Spring Boot Basis](#Spring-Boot-Basis)
   * [Spring Boot Bean DI](#Spring-Boot-Bean-DI)
   * [Spring Boot DB](#Spring-Boot-DB)
   * [Spring Boot AOP](#Spring-Boot-AOP)
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
  * `@Configuration`이 붙은 클래스를 만들고 스프링이 실행할 때 구성할 수 있게 하고 그 안에 `@Bean` 어노테이션을 붙여줘서 빈으로 등록
  

Spring Boot DB
=======
### JDBC
* 괴거 자바를 통해서 DB를 조작하던 자바 API 라이브러리로 JDBC API로 직접 코딩
* DB에 대한 반복 작업이 많아서 최근 개발에서는 직접 활용하는 경우는 거의 없음
* SQL 구문을 직접 작성하며 각각의 DB 별로 맞는 드라이버가 필요

### Spring JDBC
* 스프링 `JdbcTemplate`는 MyBatis 같은 라이브러리로 JDBC API에서 본 반복 코드를 대부분 제거한 라이브러리
* 템플릿 매서드 패턴과 콜백 메소드 구조로 JDBC API의 반복되는 코드를 줄임
* `JdbcTemplate`을 통해서 쿼리를 날리고 받아 오는 콜백 메소드를 통해 데이터를 받아옴
* SQL 구문은 직접 작성하며 각각의 DB 별로 맞는 드라이버가 필요

### JPA
* JPA는 자바 표준 인터페이스로 내부 구현체는 `Hibernate`가 주로 이용됨
* JPA는 기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행하여 객체 중심의 설계로 패러다임을 전환시켜줌
* `EntityManager`의 API 통해서 쿼리를 날리고 이것이 SQL로 변형되어 DB에 전달됨
* SQL 구문을 작성할 필요가 없이 간단하게 `JPQL`을 짜야하며 각각의 DB 별로 맞는 드라이버가 필요

### Spring Data JPA
* 스프링 데이터 JPA는 JPA를 한번 더 래핑한 라이브러리
* 인터페이스 안에 구현체가 들어 있고 거기에 기본 CRUD 함수들이 오버라이딩 되어있고 빈으로 자동으로 등록됨
* SQL, JPQL 구문을 작성할 필요가 없으며 각각의 DB 별로 맞는 드라이버만 필요
* 복잡한 동적 쿼리는 Querydsl로 활용

Spring Boot AOP
=======
### AOP
* AOP란 관점 지향 프로그래밍(Aspect Oriented Programming)의 약자로, 공통 관심 사항이 있는 경우에 활용
* 공통 관심 사항과 핵심 관심 사항을 분리하여 비지니스 로직의 코드에 영향을 끼치 않도록 공통 관심 사항을 정리할 수 있음
* `@Aspect` 어노테이션을 통해서 공통 관심 사항을 정의하고 빈으로 등록하거나 컴토넌트로 등록
* `@Aroud` 어노테이션의 `execution`을 통해서 공통 관심 사항을 적용할 패키지를 지정
* `JoinPoint`를 통해서 커스터마이징이 가능
* 스프링은 프록시를 통해서 AOP를 구현