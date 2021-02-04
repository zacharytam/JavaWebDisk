package com.lby.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class upLoad {
	String fileName,userid,oldDir,newDir;
	String backNews = "";
	HttpServletRequest request;
	HttpSession session;
	public void setNewDir(String nd){
		newDir = nd;
	}
	public String getNewDir(){
		return newDir;
	}
	public void setOldDir(String on){
		oldDir = on;
	}
	public String getOldDir(){
		return oldDir;
	}
	public void setFileName(String fn){
		fileName = fn;
	}
	public String getFileName(){
		return fileName;
	}
	public void setUserid(String id){
		userid = id;
	}
	public String getUserid(){
		return userid;
	}
	public void setBackNews(String news){
		backNews = news;
	}
	public String getBackNews(){
		return backNews;
	}
	public void setRequest(HttpServletRequest request){
		this.request = request;
	}
	public void setSession(HttpSession session){
		this.session = session;
	}
}
