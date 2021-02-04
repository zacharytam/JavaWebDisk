package com.lby.function;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.lby.model.Login;
public class DownLoadFile extends HttpServlet{
	HttpServletResponse response;
	String fileName;
	String newDir;
	String oldDir = "D:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/PEnetworkDisk/WEB-INF/";
	public void setResponse(HttpServletResponse response){
		this.response = response;
	}
	public void setFileName(String s){
		fileName = s;
	}
	public String getFileName(){
		return fileName;
	}
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession(true);
		Login login = (Login)session.getAttribute("login");
		boolean ok = true;
		//request.setAttribute("fileDir", fd);
		if(login == null){
			ok = false;
			response.sendRedirect("login.jsp");
		}
		if(ok == true){
			newDir = login.getUserid();
		}
		String l = oldDir.concat(newDir);
		FileWriter fw = new FileWriter("D:/2.txt");  
		fw.write(l);
		fw.close();
		File fileLoad = new File(oldDir.concat("Use"),fileName);
		response.setHeader("Content-disposition", "attachment;fileName =" + fileName);

			FileInputStream in = new FileInputStream(fileLoad);
			OutputStream out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int n = -1;
			while((n = in.read(buffer))!= -1){
				out.write(buffer,0, n);
			}
			out.close();
			in.close();
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request, response);
	}
}