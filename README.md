# ERD
![scheduler](https://github.com/user-attachments/assets/a4fefde2-ca16-4f13-a555-ced6dc80e1b8)


# API DESC
# API 명세서

## 일정 (Schedule)

| 기능 | Method | Endpoint | Request Body | Response | Status Code |
|------|--------|----------|--------------|----------|-------------|
| 일정 생성 | POST | /api/schedules | `{ "title": "<string>", "contents": "<string>" }` | HttpStatus.CREATED | 201 |
| 전체 일정 조회 | GET | /api/schedules |  | `[ { "title": "<string>", "memberName": "<string>", "contents": "<string>", "createdAt": "<datetime>", "updatedAt": "<datetime>" } ]` | 200 |
| 작성자 기준 일정 조회 | GET | /api/schedules/member/{memberId} |  | `[ { "title": "<string>", "memberName": "<string>", "contents": "<string>", "createdAt": "<datetime>", "updatedAt": "<datetime>" } ]` | 200 |
| 일정 수정 | PATCH | /api/schedules/{schedule_id} | `{ "title": "<string>", "contents": "<string>" }` |  | 200 |
| 일정 삭제 | DELETE | /api/schedules/{schedule_id} | 없음 | 없음 | 204 |

## 회원 (Member)

| 기능 | Method | Endpoint | Request Body | Response | Status Code |
|------|--------|----------|--------------|----------|-------------|
| 회원 가입 | POST | /api/members/signup | `{ "email": "<string>", "name": "<string>", "password": "<string>" }` |  | 201 |
| 로그인 | POST | /api/members/signin | `{ "email": "<string>", "password": "<string>" }` | `{ "id": <long>, "name": "<string>" }` | 200 |
| 회원 정보 조회 | GET | /api/members/{id} |  | `{ "email": "<string>", "name": "<string>" }` | 200 |
| 로그아웃 | POST | /api/members/logout |  |  | 200 |
| 회원 정보 수정 | PATCH | /api/members/{id} | `{ "name": "<string>", "password": "<string>" }` |  | 200 |
| 회원 삭제 | DELETE | /api/members/{id} |  |  | 204 |

## 댓글 (Comment)

| 기능 | Method | Endpoint | Request Body | Response | Status Code |
|------|--------|----------|--------------|----------|-------------|
| 댓글 작성 | POST | /api/posts/{postId}/comments | `{ "contents": "<string>" }` |  | 201 |
| 특정 일정의 댓글 조회 | GET | /api/posts/{postId}/comments |  | `[ { "content": "<string>", "updatedAt": "<datetime>", "writer": "<string>" } ]` | 200 |
| 댓글 수정 | PATCH | /api/posts/{postId}/comments/{id} | `{ "contents": "<string>" }` |  | 200 |
| 댓글 삭제 | DELETE | /api/posts/{postId}/comments/{id} |  |  | 204 |

![로그인 성공](https://github.com/user-attachments/assets/d6ad6aa5-7c5f-4a5b-8ea3-73cc151208b4)
![로그인한 상태에서  ppost](https://github.com/user-attachments/assets/88a65ba9-6635-4fa9-9926-35be8d2e1b9e)




