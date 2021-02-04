package com.lby.controller;
import com.lby.model.*;
import java.util.*;
import java.io.*;
//import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ControlFileDir extends HttpServlet{
	
	String newDir;
	String fileName;
	fileDir fd1 = new fileDir();
	//fileDir fd2 = new fileDir();
	String userid;
	String oldDir = "D:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/PEnetworkDisk/WEB-INF";
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		StringBuffer allFiles = new StringBuffer();
		allFiles.setLength(0);
		HttpSession session = request.getSession(true);
		Login login = (Login)session.getAttribute("login");
		//session.setAttribute("fileDir", fd2);
		boolean ok = true;
		request.setAttribute("fileDir", fd1);
		if(login == null){
			ok = false;
			response.sendRedirect("login.jsp");
		}
		if(ok == true){
			newDir = login.getUserid();
			//getAllFiles();
			FileWriter fw = new FileWriter("D:/1.txt");  
			fw.write(newDir);
			fw.close();
		}

			File dir = new File(oldDir,newDir);
			File a[] = dir.listFiles();
			List<String> L = new ArrayList<String>();
			for(int i = 0;i < a.length;i++){
				if(a[i].isFile())
				{
					allFiles.append("<BR><BR><div class = 'a'>"+a[i].getName()+"<a href = downFile.jsp?downFile="+a[i].getName()+">"+a[i].getName()+"</a></div>");
					L.add(a[i].getName());
				}
						
		}
			fd1.setAllFiles(allFiles);
			fd1.setFileNameList(L);
			RequestDispatcher dispatcher = request.getRequestDispatcher("show.jsp");
			dispatcher.forward(request, response);
		//return allFiles;
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request, response);
	}
}
