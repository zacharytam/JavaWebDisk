package com.lby.controller;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.lby.model.*;
public class ControlUpLoad extends HttpServlet{
	String newDir;
	upLoad ul = new upLoad();
	String oldDir = "D:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/PEnetworkDisk/WEB-INF/";
	String fileName = "";
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession(true);
		Login login = (Login)session.getAttribute("login");
		session.setAttribute("upLoad", ul);
		//fileDir fd = (fileDir)session.getAttribute("fileDir");
		boolean ok = true;
		if(login == null){
			ok = false;
			response.sendRedirect("login.jsp");
		}
		if(ok == true){
			newDir = login.getUserid();
			//fileName = fd.getFileName();
			//getAllFiles();
			//FileWriter fw = new FileWriter("D:/1.txt");  
			//fw.write(newDir);
			//fw.close();
		}
		String s1 = oldDir.concat(newDir);
		String fileName = null;
		try{
			String tempFileName = (String)session.getId();
			File f3 = new File(s1);
			f3.mkdir();
			String s2 = f3.toString();
			File f1 = new File(s2, tempFileName);
			FileOutputStream o = new FileOutputStream(f1);
			InputStream in = request.getInputStream();
			byte b[] = new byte[10000];
			int n;
			while((n=in.read(b))!=-1){
				o.write(b, 0, n);
			}
			o.close();
			in.close();
			RandomAccessFile random = new RandomAccessFile(f1,"r");
			int second = 1;
			String secondLine = null;
			while(second<=2){
				secondLine = random.readLine();
				second++;
			}
			int position = secondLine.lastIndexOf('=');
			fileName = secondLine.substring(position + 2, secondLine.length()-1);
			session.setAttribute("Name", fileName);
			random.seek(0);
			long forthEndPosition = 0;
			int forth = 1;
			while((n = random.readByte())!=-1 && (forth <= 4)){
				if(n=='\n'){
					forthEndPosition = random.getFilePointer();
					forth++;
				}
			}
			File f2 = new File(s2,fileName);
			RandomAccessFile random2 = new RandomAccessFile(f2,"rw");
			random.seek(random.length());
			long endPosition = random.getFilePointer();
			long mark = endPosition;
			int j = 1;
			while((mark >=0)&&(j<=5)){
				mark--;
				random.seek(mark);
				n = random.readByte();
				if(n=='\n'){
					endPosition = random.getFilePointer();
					j++;
				}
			}
			random.seek(forthEndPosition);
			long startPoint = random.getFilePointer();
			while(startPoint<endPosition-1){
				n = random.readByte();
				random2.write(n);
				startPoint = random.getFilePointer();
			}
			random2.close();
			random.close();
			f1.delete();
			ul.setFileName(fileName);
			ul.setBackNews("上传成功！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("upLoadMess.jsp");
			dispatcher.forward(request, response);
			return;
		}
		catch(Exception e){
			if(fileName != null){
				ul.setFileName(fileName);
				ul.setBackNews("上传失败！");
				RequestDispatcher dispatcher = request.getRequestDispatcher("upLoadMess.jsp");
				dispatcher.forward(request, response);
				return;
				//upFileMessage = fileName+" 上传失败！";
				//return upFileMessage;
			}
			else{
				ul.setBackNews("");
				RequestDispatcher dispatcher = request.getRequestDispatcher("upLoadMess.jsp");
				dispatcher.forward(request, response);
				return;
				//upFileMessage = "";
				//return upFileMessage;
			}
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request, response);
	}
}
