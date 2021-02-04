package com.lby.controller;
import com.lby.model.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ControlAlterPassword extends HttpServlet{
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(Exception e){
		}
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
			continueWork(request,response);
		}
	}
	public void continueWork(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession(true);
		Login login = (Login)session.getAttribute("login");
		Connection con;
		String userid = login.getUserid();
		Password pw = new Password();
		request.setAttribute("password", pw);
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		try{
			con = DriverManager.getConnection("jdbc:odbc:PEDisk","sa","lby19920102");
			Statement sql = con.createStatement();
			ResultSet rs = sql.executeQuery("SELECT userid,password FROM peDisk WHERE userid = '"+userid+"' AND password = '"+oldPassword+"'");
			if(rs.next()){
				String update = "UPDATE peDisk SET password = '"+newPassword+"' WHERE userid = '"+userid+"'";
				int m = sql.executeUpdate(update);
				if(m == 1){
					pw.setBackNews("密码更改成功！");
					pw.setOldPassword(oldPassword);
					pw.setNewPassword(newPassword);
				}
				else{
					pw.setBackNews("密码更改失败！");
				}
			}
			else{
				pw.setBackNews("密码更改失败！");
			}
		}
		catch(SQLException e){
			pw.setBackNews("密码更改失败！");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("showResult.jsp");
		dispatcher.forward(request, response);
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request, response);
	}
}
