package com.xbz.vpase.utils;

public class TextUtil {

	public static boolean isEmpty(String str){
		if(str!=null&&!str.trim().equals("")&&!str.equals("null")){
			return false;
		}else{
			return true;
		}
	}

	public static String getNotEmptyStr(String str){
		if(!isEmpty(str)){
			return str;
		}
		return "";
	}
}
