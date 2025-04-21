package com.realworld.handler;

import com.realworld.exception.BaseException;
import com.realworld.result.Result;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
