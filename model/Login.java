package com.lby.model;
public class Login {
	String userid,password,backNews = "";
	boolean success = false;
	public void setUserid(String id){
		userid = id;
	}
	public String getUserid(){
		return userid;
	}
	public void setPassword(String pw){
		password = pw;
	}
	public String getPassword(){
		return password;
	}
	public void setBackNews(String news){
		backNews = news;
	}
	public String getBackNews(){
		return backNews;
	}
	public void setSuccess(boolean su){
		success = su;
	}
	public boolean getSuccess(){
		return success;
	}
}
