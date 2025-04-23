package com.realworld.context;

public class BaseContext {
	private final static ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();
	public static void setCurrentId(Integer id){
		THREAD_LOCAL.set(id);
	}
	public static Integer getCurrentId(){
//		return THREAD_LOCAL.get();
		return 1;
	}
	public static void removeCurrentId(){
		THREAD_LOCAL.remove();
	}
}
