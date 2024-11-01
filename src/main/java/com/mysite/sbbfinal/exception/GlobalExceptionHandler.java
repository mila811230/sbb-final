package com.mysite.sbbfinal.exception;

import org.springframework.ui.Model;
import com.mysite.sbbfinal.dto.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(FileNotFoundException.class)
	public String handleFileNotFoundException(
            FileNotFoundException e,
            Model model,
            HttpServletRequest request) {

        log.error("파일 다운로드 오류: {}", e.getMessage());

        ErrorResponse errorResponse = ErrorResponse.of(
                "FILE_NOT_FOUND",
                e.getMessage(),
                request.getRequestURI()
        );
        model.addAttribute("error", errorResponse);

        return "error/error";  // error.html 템플릿을 사용
    }
    		
}
