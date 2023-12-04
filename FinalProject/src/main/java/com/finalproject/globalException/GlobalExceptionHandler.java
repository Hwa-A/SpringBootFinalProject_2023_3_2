package com.finalproject.globalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice	// 전역 에러 핸들러 생성
public class GlobalExceptionHandler {	// 다양한 예외 유형을 처리 및 응답 반환
	// @ExceptionHandler: 해당 컨트롤러에서 발생하는 특정 타입의 예외를 처리
	//					  매개변수로 발생한 예외 객체 지정 가능
	//					  컨트롤러에서 발생하는 예외를 처리하는 메서드 지정할 때 사용
    @ExceptionHandler(NoResourceFoundException.class)	// NoResourceFoundException 타입의 예외가 발생하면 이를 처리하는 메소드를 지정
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    // 다른 예외 핸들러를 필요에 따라 추가
}
