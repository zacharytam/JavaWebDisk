package com.lby.model;
import javax.servlet.http.HttpServletResponse;
public class downLoad {
	String fileName,userid;
	HttpServletResponse response;
	public void setFileName(String s){
		fileName = s;
	}
	public String getFileName(){
		return fileName;
	}
	public void setResponse(HttpServletResponse response){
		this.response = response;
	}
	public void setUserid(String id){
		userid = id;
	}
	public String getUserid(){
		return userid;
	}
}
