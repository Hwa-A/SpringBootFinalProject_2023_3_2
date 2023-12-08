	package com.finalproject.security;
	
	import java.io.IOException;
	import java.io.PrintWriter;
	
	import org.json.JSONObject;
	import org.springframework.http.HttpStatus;
	import org.springframework.security.core.AuthenticationException;
	import org.springframework.security.web.authentication.AuthenticationFailureHandler;
	
	import jakarta.servlet.ServletException;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	
	public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			
			response.setStatus(HttpStatus.UNAUTHORIZED.value());	// 응답의 HTTP 상태코드를 401번(UNAUTHORIZED)으로 설정
	        response.setContentType("application/json");	// 응답의 Content-Type을 "application/json"으로 설정
	        response.setCharacterEncoding("UTF-8");  // 응답의 문자 인코딩을 UTF-8로 설정
	        
	        // 응답 본문에 {"error": "Invalid username or password"}라는 JSON 문자열({키:값}형식)을 작성
	        response.getWriter().write("{\"errorMsg\": \"계정이 없거나 비밀번호가 일치하지 않습니다\"}");
	        response.getWriter().flush();	// 응답을 클라이언트에게 전달	
	        								// flush(): 출력 버퍼를 비워, 즉시 클라이언트에게 응답 전달
			
		}
	
	}
