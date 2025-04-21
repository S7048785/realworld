package com.realworld.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
	// 0表示失败，1表示成功
	private int code;
	private String msg;
	private T data;

	public static <T> Result<T> success(T data) {
		Result<T> result = new Result<T>();
		result.code = 1;
		result.data = data;
		result.msg = "success";
		return result;
	}

	public static <T> Result<T> success() {
		Result<T> result = new Result<T>();
		result.code = 1;
		result.setMsg("success");
		return result;
	}

	public static <T> Result<T> error(String msg) {
		Result<T> result = new Result<T>();
		result.setCode(0);
		result.setMsg(msg);
		return result;
	}

}