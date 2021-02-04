package com.lby.model;
import java.util.*;
public class fileDir {
	String newDir,oldDir,userid,fileName;
	StringBuffer allFiles;
	List<String> fileNameList;
	public void setFileNameList(List<String> L)
	{
		fileNameList = L;
	}
	public List<String> getFileNameList()
	{
		return fileNameList;
	}
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
	public void setUserid(String id){
		userid = id;
	}
	public String getUserid(){
		return userid;
	}
	public void setAllFiles(StringBuffer sb){
		allFiles = sb;
	}
	public StringBuffer getAllFiles(){
		return allFiles;
	}
	public void setFileName(String f){
		fileName = f;
	}
	public String getFileName(){
		return fileName;
	}
}
