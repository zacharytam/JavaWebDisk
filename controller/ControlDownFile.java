package com.lby.controller;
import com.lby.model.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ControlDownFile extends HttpServlet {
	downLoad dl = new downLoad();
	String s;
	String newDir;
	String oldDir = "D:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/PEnetworkDisk/WEB-INF/";
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession(true);
		Login login = (Login)session.getAttribute("login");
		fileDir fd = (fileDir)session.getAttribute("fileDir");
		boolean ok = true;
		request.setAttribute("downLoad", dl);
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
		String s = oldDir.concat(newDir);
		
		String filename = request.getParameter("fileName");
		File fileLoad = new File(s,filename);
		response.setHeader("Content-disposition", "attachment;fileName =" + filename);
		try{
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
		catch(Exception e){	
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request, response);
	}
}
