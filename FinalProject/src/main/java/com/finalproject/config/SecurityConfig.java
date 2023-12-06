package com.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.finalproject.security.CustomAuthenticationFailureHandler;
import com.finalproject.security.CustomAuthenticationSuccessHandler;
import com.finalproject.service.PrincipalDetailsService;

@Configuration	// xml 설정을 대신하는 스프링 설정 클래스 => xml 파일 역할을 함
@EnableWebSecurity	// Security 활성화
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailsService principalDetailsService;
	
	// @Bean: 사용자가 직적 클래스 정의해 Bean으로 등록할 때 사용
	// 		  @Configuration이 사용된 클래스 내부에서 사용 => Bean의 Singletone Pattern 보장
	// 		  내부에서 xml 태그 역할 => 추가적인 설정 등록 및 변경 가능
	
	// BCryptPasswordEncoder : Spring Security에서 제공하는 비밀번호 암호화 클래스
	//							BCrypt 해시 알고리즘 이용해 비밀번호를 암호화
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired	//  AuthenticationManager 설정에 PasswordEncoder 추가 why? 로그인의 비밀번호와 DB에 저장된 암호화된 비밀번호를 비교하는 과정에 필요
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(principalDetailsService).passwordEncoder(passwordEncoder);
    }
	
	// 기존에 상속받아 구현하던 WebSecurityConfigurerAdapter가 Spring Security 5 이후 더 이상 사용X
	// 대신 SeurityFilerChain을 Bean으로 등록해 사용
	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		// h2 console을 위한 설정
		http.csrf().disable();	// CSRF 중지
		http.headers().frameOptions().disable()	// X-Frame-Options in Spring Security 중지
			.and()
			.exceptionHandling().accessDeniedPage("/fail");
		http.authorizeHttpRequests()	// 요청에 대한 보안 설정
				.requestMatchers("/h2-console/**", "/images/**", "/", "/fail", "/login_proc", "/login", "/signUp", "/checkEmail", 
						"/checkUname", "/error", "/home")
				.permitAll()	// 모든 접근 허용( Authentication, Authorization 필요X)
				.anyRequest().authenticated();	// 나머지 모든 요청은 모두 인증(로그인)된 사용자(Authentication)만 접근하도록 설정
				
		// 인증되지 않은 사용자가 인증이 필요한 URL로 접근 시, 특정 URL로 리다이렉트
		http.exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
		
		// form 로그인 설정
		http.formLogin()	
				.usernameParameter("email")	// default: username
				.passwordParameter("password")	// default: password
				.loginPage("/login") 	// 로그인 페이지(사용자 정의) 설정
				.successHandler(new CustomAuthenticationSuccessHandler())  // 성공 핸들러 설정
//				.defaultSuccessUrl("/home") 	// 로그인 성공 후 이동할 페이지 설정(= form action url)
				.failureHandler(new CustomAuthenticationFailureHandler())	//  로그인 실패 시 이동할 URL
				.loginProcessingUrl("/login_proc");	// 로그인 Form Action Url / default: /login
			
		// 로그아웃 설정
		http.logout()									// CRSF를 비활성화한 경우, GET 방식으로 처리가 좋음 => 로그아웃 처리 URL 메서드 따로 존재
				.logoutUrl("/logout")	// 로그아웃 처리 URL (= form action url)
				.logoutSuccessUrl("/login") 	// 로그아웃 성공 후 리다이렉트될 URL 지정
				.invalidateHttpSession(true)	// 로그아웃 시 HTTP 세션 무효화
				.deleteCookies("JSESSIONID");	// 로그아웃 시 쿠키 삭제
												// JSESSIONID:웹 서버가 클라이언트 식별하기 위해 사용하는 쿠키 => 이 쿠키에 세션 ID 저장 -> 클라이언트에게 전송
												//													=> 클라이언트는 이 쿠키를 사용해 서버에 요청 보낼때마다 본인의 세션ID를 전송
				
		 return http.build();	// 체인으로 연결된 보안 설정들이 적용된 SecurityFilterChain 객체를 생성하고 반환
	}
}
