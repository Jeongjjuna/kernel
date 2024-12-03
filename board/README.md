## 🍜 새롭게 알게 된 내용

### ✔️ QueryParameter 가 많으면 객체로 받을 수 있다.
- @RequestParam(name = "issued-month") 가 여러개라면 객체로 받을 수 있다.

### ✔️ RequestBody 객체의 필드는 JSON 을 사용할 때 Wrapper 타입을 추천한다.
- int 와 같이 기본값 타입을 사용할 경우, json 요청 데이터가 잘 못됐을 때 0(기본값)을 받을 수 있기 때문이다.
  - 의도치 않게 0 값으로 동작하는 것 보다는 잘못된 값을 받았다는 null 이 의미상 낫다.
  - null 처리 validation 을 상황에 맞게 잘 해줘야 한다.

### ✔️ client 요청 json 필드와 서버쪽 request 필드 형식이 다를때
- @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) 활용

### ✔️ jackson : 스프링 부트의 기본 직렬화/역직렬화 라이브러리
- jackson 라이브러리의 기본 동작 방식을 모를경우 개발 시 여러 문제점을 마주할 수 있음.

- ObjectMapper 는 객체의 getter 메서드의 이름을 보고 직렬화를 수행한다.
  - getUserAge() -> user_age
  - @JsonIgnore : 직렬화를 하고싶지 않을 경우 해당 getter 에 달아준다.
  - @JsonProperty("user_email") : 역/직렬화시 맵핑할 필드명을 지정하기 위해 필드에 달아준다.

- ObjectMapper 는 private 생성자여도 역직렬화를 할 수 있다.
  - 리플렉션을 기반으로 객체를 생성한다.

- 역직렬화 할때 setter 혹은 getter 만 있어도 가능하다.