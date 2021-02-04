package com.lby.model;
public class Register {
	String userid = "",password = "",email = "",telephone = "",qq = "";
	String backNews = "";
	public void setBackNews(String news) {
		backNews = news;
	}
	public String getBackNews(){
		return backNews;
	}
	public void setUserid(String id) {
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
	public void setEmail(String mail){
		email = mail;
	}
	public String getEmail(){
		return email;
	}
	public void setTelephone(String ph){
		telephone = ph;
	}
	public String getTelephone(){
		return telephone;
	}
	public void setQq(String tencent){
		qq = tencent;
	}
	public String getQq(){
		return qq;
	}
}
