package com.lby.model;
public class Password {
	String oldPassword,newPassword,backNews="";
	public void setOldPassword(String old){
		oldPassword = old;
	}
	public String getOldPassword(){
		return oldPassword;
	}
	public void setNewPassword(String nw){
		newPassword = nw;
	}
	public String getNewPassword(){
		return newPassword;
	}
	public void setBackNews(String news){
		backNews = news;
	}
	public String getBackNews(){
		return backNews;
	}
}
