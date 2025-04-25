package com.realworld.handler;

import com.realworld.exception.BaseException;
import com.realworld.result.Result;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author YYJYP
 */
@Slf4j
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public Result exceptionHandler(BaseException ex){
		log.error("异常信息：{}", ex.getMessage());
		return Result.error(ex.getMessage());
	}

	@ExceptionHandler
	public Result<Void> exceptionHandler(MethodArgumentNotValidException ex){
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
//		allErrors.forEach(error -> {
//			// 字段
//			String fieldName = ((FieldError) error).getField();
//			// 注解中的message
//			String errorMessage = error.getDefaultMessage();
//		});
		String errorMessage = allErrors.get(0).getDefaultMessage();
		log.error("数据校验失败：{}", errorMessage);
		return Result.error(errorMessage);
	}
}
