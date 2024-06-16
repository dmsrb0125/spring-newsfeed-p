# 단위 테스트 작성하기
Controller, Service, Entity, DTO 레이어에 맞는 단위 테스트 작성하기

## 프로젝트 정보
이번주 완성한 팀프로젝트 프로젝트를 좀더 완벽하게 완성하기위해 테스트코드 작성하기
(`JUnit5` 사용)

## 테스트 작성 방법
1. `Mockito` 를 적용하여 레이어간 의존성을 격리
2. 테스트 코드 작성시에 FIRST 규칙 준수하기
- Fast : 좋은 단위 테스트는 실행이 빨라야 한다.
- Independent : 좋은 단위 테스트는 테스트하고자 하는 `단위 기능`에 집중해야 한다
- Repeatable : 좋은 단위 테스트는 반복적으로 수행하더라도 `항상 같은 결과`를 반환해야 한다
- Self-Validating : 좋은 단위 테스트는 기대하는 결과가 무엇인`단언(assert)`해야 한다.
- Timely : 좋은 단위 테스트는 미루지 않고 즉시 작성한다.

3. `given-when-then 전략` 지키기 : given(준비) - when(실행) - then(검증)
4. Test Commit Message 남기기 : 테스트 코드가 정상적으로 추가될때마다 해당 테스트에 대한 내용과 함께 커밋을 남기기


## 필수 구현 기능

- [x] AOP 추가하기
  - [x] 모든 API(Controller)가 호출될 때, Request 정보(Request URL, HTTP Method)를
    @Slf4J Logback 라이브러리를  활용하여 Log로 출력해주세요.
  - [x] 컨트롤러 마다 로그를 출력하는 코드를 추가하는것이 아닌, AOP로 구현해야만 합니다.

- [ ] DTO, Entity Test 추가하기
  - [ ] `@Test` 를 사용해서 DTO 와 Entity Test 를 추가합니다
  - [ ] User, Post, Comment, DTO 에 존재하는 메서드들에 대해서 “단위 테스트” 를 추가합니다.
  - [ ] 특정 상황에 예외가 정상적으로 발생하고 있는지도 테스트 합니다.
  
- [ ] Controller Test 추가하기 
  - [ ] `@WebMvcTest` 를 사용하여 Controller Test 를 추가합니다.
  - [ ] Post, Comment Controller 에 대해서 테스트를 추가합니다.
  - [ ] 특정 상황에 예외가 정상적으로 발생하고 있는지도 테스트 합니다.

- [ ] Service Test 추가하기
  - [ ] `@ExtendWith` 를 사용하여 Service Test 를 추가합니다
  - [ ] User, UserDetails, Post, Comment Service 에 대해서 “통합 테스트” 를 추가합니다.
  - [ ] 단순 DB CRUD 와 별개로 코드 레벨에서의 비즈니스 로직에 대한 테스트가 필요한 경우라면 “단위 테스트”를 추가합니다.
  - [ ] 특정 상황에 예외가 정상적으로 발생하고 있는지도 테스트 합니다.

##  추가 구현 기능
- [ ] JaCoCo 를 추가하여 프로젝트 코드 커버리지 (테스트 코드 적용률)를 측정 후
  퍼센티지를 60% 까지 올려주세요.
- [ ]  Fixture Monkey 를 사용하여 손쉽게 테스트용 객체를 생성해보세요 


## 테스트코드 작성 방향성
- `JUnit`, `Mockito`. `Fixture Monkey ` 사용
- `given-when-then 전략` 사용
- `assert` 사용

### Entity Test
- 엔티티가 실제 데이터베이스에 올바르게 `저장`되는지 확인
- 엔티티의 제약 조건 테스트
- Getter, Setter 메서드 테스트
- equals(), hashCode(), toString() 메서드 테스트


### DTO Test
- `데이터 전송` 객체가 올바르게 생성되고 `필드 값이 정확하게 설정`되는지 테스트합니다
- DTO 제약 조건 테스트
- Getter, Setter 메서드 테스트
- equals(), hashCode(), toString() 메서드 테스트


### Service Test
- `비즈니스 로직`을 테스트합니다
  - 로직 성공 테스트
  - 제약조건 테스트(유효값설정 및 기타예외처리)
- 레포지토리와 같은 내부 의존성을 `Mockito`를 사용하여 독립적으로 테스트합니다.
- 외부 API 호출이 있는 경우, 외부 API와의 상호작용을 `Mockito`를 사용하여 모킹합니다.


### Controller Test
- `요청`이 정상적으로 처리되는지 를 테스트
  - 기대하는 `응답을 반환`하는지 검증합니다.
  - 제약조건테스트(유효값설정 및 기타예외처리)
