# ERD
![scheduler](https://github.com/user-attachments/assets/a4fefde2-ca16-4f13-a555-ced6dc80e1b8)


# API 명세서

### 일정 (Schedule)

| 기능 | Method | Endpoint | Request Body | Response | Status Code |
|------|--------|----------|--------------|----------|-------------|
| 일정 생성 | POST | /api/schedules | `{ "title": "<string>", "contents": "<string>" }` | HttpStatus.CREATED | 201 |
| 전체 일정 조회 | GET | /api/schedules |  | `[ { "title": "<string>", "memberName": "<string>", "contents": "<string>", "createdAt": "<datetime>", "updatedAt": "<datetime>" } ]` | 200 |
| 작성자 기준 일정 조회 | GET | /api/schedules/member/{memberId} |  | `[ { "title": "<string>", "memberName": "<string>", "contents": "<string>", "createdAt": "<datetime>", "updatedAt": "<datetime>" } ]` | 200 |
| 일정 수정 | PATCH | /api/schedules/{schedule_id} | `{ "title": "<string>", "contents": "<string>" }` |  | 200 |
| 일정 삭제 | DELETE | /api/schedules/{schedule_id} |  |  | 204 |

### 회원 (Member)

| 기능 | Method | Endpoint | Request Body | Response | Status Code |
|------|--------|----------|--------------|----------|-------------|
| 회원 가입 | POST | /api/members/signup | `{ "email": "<string>", "name": "<string>", "password": "<string>" }` |  | 201 |
| 로그인 | POST | /api/members/signin | `{ "email": "<string>", "password": "<string>" }` | `{ "id": <long>, "name": "<string>" }` | 200 |
| 회원 정보 조회 | GET | /api/members/{id} |  | `{ "email": "<string>", "name": "<string>" }` | 200 |
| 로그아웃 | POST | /api/members/logout |  |  | 200 |
| 회원 정보 수정 | PATCH | /api/members/{id} | `{ "name": "<string>", "password": "<string>" }` |  | 200 |
| 회원 삭제 | DELETE | /api/members/{id} |  |  | 204 |

### 댓글 (Comment)

| 기능 | Method | Endpoint | Request Body | Response | Status Code |
|------|--------|----------|--------------|----------|-------------|
| 댓글 작성 | POST | /api/posts/{postId}/comments | `{ "contents": "<string>" }` |  | 201 |
| 특정 일정의 댓글 조회 | GET | /api/posts/{postId}/comments |  | `[ { "content": "<string>", "updatedAt": "<datetime>", "writer": "<string>" } ]` | 200 |
| 댓글 수정 | PATCH | /api/posts/{postId}/comments/{id} | `{ "contents": "<string>" }` |  | 200 |
| 댓글 삭제 | DELETE | /api/posts/{postId}/comments/{id} |  |  | 204 |

## Lv.0 API 명세 및 ERD 작성

## Lv.1 일정 CRUD
기본 C(생성),  R(전체 조회, 사용자별 조회(개인 일정 조회용)), U(수정), D(삭제)로 우선 구현했다.
## Lv.2 유저 CRUD + Lv.3 회원가입
마찬가지로 기본 CRUD 및 추후 로그인을 고려한 password를 포함해 구현했다. <br/>
C(회원가입, signup), R(회원 정보 조회), U(수정), D(삭제) 와 로그인과 로그아웃을 Post로 구현하였다.
이 단계에서 로그인, 로그아웃은 틀만 구현된 것으로 실질적 기능은 포함되지 않는 handler였다.

## Lv.4 로그인
2주차 Servlet Filter 실습 내용을 바탕으로 구현하였고, 로그인 상태가 필요한 로직을 나눴다.

- 로그인 상태를 알 필요가 있는 상황
  - login : 이미 로그인 된 상태에서는 로그인 방지
  - showUserInfo : 로그인 된 사용자가 누구인지 구분
  - update & delete : 수정/삭제하려고 접근하는 사용자가 해당 사용자인지 구분
  - schedule C&U&D : 가입한 사용자만 생성 가능하고, 수정/삭제하려는 사용자가 작성자여야 함

-> 이 중 로그인은, 로그인 요청 때 인증이 필요할 경우 로그인을 하기 위해 로그인이 되어 있어야 하는 모순이 생기므로, 
과제 명세와 같이 회원가입과 로그인은 인증 처리에서 제외했다.

## Lv.5 다양한 예외처리 적용하기
#### Bean Validation

요청 DTO에 유효성 검사 규칙과 예외 처리를 적용했다.

유저
- 이름: 최대 5자 이하
- 이메일: 이메일 형식 준수
- 비밀번호: 8~15자의 영문 및 숫자 조합
일정
- 제목: 최대 10자 이하
 
## Lv.6 비밀번호 암호화

## Lv.7 댓글 CRUD
(제일 마지막에 구현됨)
일정에 종속되는 entity이므로 `schedule`을 FK로 설정했다. <br/>
schedule과 별개로 작성한 유저를 구분하기 위해, `member`도 FK로 설정했다.

## Lv.8 pagination
페이지네이션은 일정 조회 기능에만 적용했다.

로그인 성공
![로그인 성공](https://github.com/user-attachments/assets/d6ad6aa5-7c5f-4a5b-8ea3-73cc151208b4)
로그인 성공 시, JSESSIONID가 쿠키에 생성됨.
<br/>
로그인 상태에서 일정 등록
![로그인한 상태에서  ppost](https://github.com/user-attachments/assets/88a65ba9-6635-4fa9-9926-35be8d2e1b9e)
제목과 내용만 입력해도 HttpServletRequest의 Session을 통해 유저 정보가 자동으로 포함되어 등록됨.