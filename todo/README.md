## 🍜 API 요구사항

- [x] 할 일 생성 기능
    - 제목은 필수로 입력해야한다.
    - 내용은 필수로 입력해야한다.

- [x] 할 일 조회 기능
    - 날짜 별로 할 일 목록을 조회 할 수 있는 API
    - 상태 별로 할 일 목록을 조회할 수 있는 API
    - 특정 할 일을 조회할 수 있는 API

- [x] 할 일 수정 기능
    - 할 일을 수정 할 수 있는 API
    - 할 일의 상태를 수정할 수 있는 API

- [x] 할 일 삭제 기능

## 🍜 새롭게 알게 된 내용

### ✔️ createdAt, updatedAt 를 자동으로 관리하는데 여러 가지 방식들

1. Hibernate 구현체에서 제공하는 어노테이션 활용 ex) @CreationTimestamp, @DynamicInsert, @DynamicUpdate
2. SpringDataJpa 에서 지원하는 Auditing 을 활용 ex) @CreatedDate, @LastModifiedDate
   - @LastModifiedDate 에서 updateAt가 갱신되는 시점은? flush()가 호출될 때 갱신된다. 
3. 데이터베이스 자체 기능을 활용해서 관리
    - Hibernate 구현체보다는 추상화된 Auditing 을 사용
    - 여러 라이브러리를 중구난방 섞는것보다는 통일성있게 사용하자
    - 어노테이션 기능을 적용하고, 실제 쿼리가 어떻게 날라가는지 알고있자.

### ✔️ save vs saveAndFlush
- save를 호출 시
  - 만약 save한 엔티티의 id가 null 이라면 영속성컨텍스트에 저장.
  - 만약 save한 엔티티의 id가 영속성컨텍스트에 없다면 저장.
  - 만약 save한 엔티티의 id값이 존재하고, 영속성컨텍스트에 해당 id의 엔티티가 존재하면 업데이트.
- saveAndFlush 는 위의 save 동작 이후에 flush()를 수행해 DB에 쿼리를 날린다.
  - flush()를 한다는 것은, 실제 DB의 트랜잭션 구간 사이에 SQL문을 날리는 것 과 같다.
    ```mysql
    START TRANSACTION; -- 트랜잭션 시작
    
    -- flush() 호출시 DB에 쿼리가 날라간다.
    INSERT INTO my_entity (id, name, value) VALUES (1, 'example', 100);
    
    -- 만약 flush() 이후에 예외가 발생한다면, 트랜잭션 롤백 처리가 수행된다.
    
    COMMIT; -- 트랜잭션 커밋
    ```
- JPA 사용시 엔티티와 도메인모델 객체를 분리하면 영속성 컨텍스트에 직접 save() 해주는 작업이 필요하다.
  - 즉, 더티체킹과 같은 기능은 활용하기 어렵다.
  - 더티체킹과 같은 긴으을 활용하려면 엔티티를 도메인 객체로 간주하고 내부에 메서드를 정의해보자.


### ✔️ @RestControllerAdvice
- @RestControllerAdvice = @ControllerAdvice + @ResponseBody
- @ControllerAdvice 의 return 값은 응답헤더의 Forwarded URL 에 들어간다.
- @ControllerAdvice 에서 @ResponseBody 를 사용하면 return 값이 body 에 들어간다.
  - 여기서 상태 코드를 적용해주고 싶다면 @ResponseStatus 사용(가독성이 더 좋은 것 같기도함)