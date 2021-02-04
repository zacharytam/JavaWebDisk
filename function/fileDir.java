package com.lby.function;
import com.lby.model.*;
import com.lby.controller.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
/*import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;*/
public class fileDir extends HttpServlet{
	String oldDir = "D:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/PEnetworkDisk/WEB-INF";
	String newDir;
	StringBuffer allFiles;
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	public fileDir(){
		allFiles = new StringBuffer();
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession(true);
		Login login = (Login)session.getAttribute("login");
		boolean ok = true;
		if(login == null){
			ok = false;
			response.sendRedirect("login.jsp");
		}
		if(ok == true){
			newDir = login.getUserid();
			FileWriter fw = new FileWriter("D:/1.txt");  
			
			fw.write(newDir);
			fw.close();
			
			
			//s = newDir;
			//getAllFiles(request,response,newDir);
			//StringBuffer sb = new StringBuffer(newDir);
			//setAllFiles(sb);
		}
	}
	public StringBuffer getAllFiles() throws ServletException,IOException{
			File dir = new File(oldDir,newDir);
			File a[] = dir.listFiles();
			for(int i = 0;i < a.length;i++){
				if(a[i].isFile())
					allFiles.append("<BR><BR>"+"<a href = downFile.jsp?downFile="+a[i].getName()+">"+a[i].getName()+"</a>");
		}

		return allFiles;
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request, response);
	}
}
