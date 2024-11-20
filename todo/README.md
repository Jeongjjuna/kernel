## API 요구사항

- [x] 할 일 생성 기능

- [x] 할 일 조회 기능
    - 날짜 별로 할 일 목록을 조회 할 수 있는 API
    - 상태 별로 할 일 목록을 조회할 수 있는 API
    - 특정 할 일을 조회할 수 있는 API

- [x] 할 일 수정 기능
    - 할 일을 수정 할 수 있는 API
    - 할 일의 상태를 수정할 수 있는 API

- [x] 할 일 삭제 기능

## 새롭게 알게 된 내용

createAt, updateAt 를 자동으로 관리하는데 여러 가지 방식들

1. Hibernate 구현체에서 제공하는 어노테이션 활용 ex) @CreationTimestamp, @DynamicInsert, @DynamicUpdate
2. SpringDataJpa 에서 지원하는 Auditing 을 활용 ex) @CreatedDate, @LastModifiedDate
3. 데이터베이스 자체 기능을 활용해서 관리
    - Hibernate 구현체보다는 추상화된 Auditing 을 사용
    - 여러 라이브러리를 중구난방 섞는것보다는 통일성있게 사용하자
    - 어노테이션 기능을 적용하고, 실제 쿼리가 어떻게 날라가는지 알고있자.

save vs saveAndFlush

- JPA의 영속성 컨텍스트를 사용하든, 사용하지 않든 정확히 알아야 의도한대로 동작한다.