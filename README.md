
2023년도 3학년 2학기 응용 SW(Spring boot) 기말 프로젝트_ 웹 사이트 제작


## 개발 기간
- 전체 개발 기간: 12월(약 2주)

- 기말 프로젝트로 다른 과목 프로젝트 진행도 병행하여 실질적 시간은 1주일도 채 안되서 규모가 작았지만 SECURITY 부분 및 세세한 부분까지 처음 제대로 신경써서  다뤄본 프로젝트이였다는 점에 의의를 두었음.

## 멤버 구성
### 팀장: 조성은 - Hwa-A [https://github.com/Hwa-A]
### 팀원
- 홍서빈- girin17 [https://github.com/girin17]


## 업무 분할
 [조성은] 
  > 전체적인 백엔드 작업 <br>
     - 로그인| 로그아웃 | 회원가입 (SPRING SECURITY 이용) <br> 
     - 게시물 작성 | 게시물 삭제 | 게시물 수정 
     - 댓글 작성 | 댓글삭제 

[홍서빈]
  > 전체적인 프론트 작업
    - 백엔드 기능이 완성된후 각 요소들의 UI 코드 작성
    
  

## 프로젝트 로직
 1) 로그인 및 회원가입 
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/95976d7b-2cb9-43e0-8f02-7cd092449d11)
 2) 게시판  및 댓글  
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/af7b725a-1c76-4604-93f8-5dab076610d2)
 3) 게시판 및 질문
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/d0c2de8e-f47d-4461-a76c-92fbb9034b34)
 4) 마이 페이지
 ![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/9bb8fc1c-92e4-44e3-8de4-74074353a1e9)


## DB구조
### 관계형 데이터베이스(RDB)
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/b2c4c26b-a00b-4cf1-88c8-fb2be433d643)


 
## 기능 및 상세화면
### 로그인 및 회원가입
1. 로그인 페이지
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/b50f2c67-9d7f-45cd-b786-94174e541e8b)
  ![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/716c3bd5-5f8f-4c99-9945-37a81c703d3e)
2. 회원가입 페이지
   ![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/fa369afb-741e-4435-acc2-876c31d0eb85)

3. 게시판 페이지
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/8b9d8c41-2008-46ee-b21e-49edc1fe6333)
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/2d714c6b-e915-4938-b555-1363f9f23c0a)

4. 게시글(질문) 등록 페이
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/b82e1bc2-b268-4b83-ac75-1af10edcc770)

5. 게시글 상세 페이지
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/85b9f0d4-be22-4551-a8c3-cbce8945d7f5)
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/6f388805-d843-471f-b2eb-0730782cc491)

6. 댓글 작성 페이지
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/0f01ee74-98c0-411b-b4e9-b810d71073b0)
7. 질문 게시글 수정 페이지 
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/286c10c6-39cf-4197-8b0b-ee0d49a96d8a)
8. 프로필 페이지 
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/26456293-1084-4172-b36e-5d56b049964d)
10. 내 질문들만 모아둔 게시판 페이지
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/a94f2b33-2175-4ed3-ab9d-f331b21f27da)
11. 나의 게시글- 상세페이지
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/a917f8ea-77df-45b8-9a9e-02bd367cb071)
12. 나의 질문 게시글 수정
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/4f9cfbf1-b972-45fb-bb87-e8a322944d2a)
13. 에러창
![image](https://github.com/Hwa-A/SpringBootFinalProject_2023_3_2/assets/96507136/7b19c3c2-66a6-404c-8dd8-df218ae1ef83)




## 관련 이론
 # spring security
  : 애플리케이션의 보안을 담당하는 스프링 하위 프레임워크를 이용해 인증과 권한에 대한 부분을 Filter 흐름에 따라 처리하였다.
