package com.lby.controller;
import com.lby.model.Login;
import java.sql.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
public class ControlLogin extends HttpServlet{
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(Exception e){
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String oldDir = "D:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/PEnetworkDisk/WEB-INF/";
		Connection con;
		PreparedStatement sql;
		Login loginBean = null;
		String backNews = "";
		HttpSession session = request.getSession(true);
		try{
			loginBean = (Login)session.getAttribute("login");
			if(loginBean == null){
				loginBean = new Login();
				session.setAttribute("login",loginBean);
			}
		}
		catch(Exception ee){
			loginBean = new Login();
			session.setAttribute("login", loginBean);
		}
		String userid = request.getParameter("userid").trim();
		String password = request.getParameter("password").trim();
		boolean ok = loginBean.getSuccess();
		if(ok == true && userid.equals(loginBean.getUserid())){
			backNews = userid+"已经登录";
			loginBean.setBackNews(backNews);
		}
		else{
			boolean boo = (userid.length() > 0 && password.length() > 0);
			try{
				con = DriverManager.getConnection("jdbc:odbc:PEDisk","sa","lby19920102");
				String condition = "SELECT userid,password FROM peDisk WHERE (userid = ? AND password = ?)";
				sql = con.prepareStatement(condition);
				if(boo){
					sql.setString(1, userid);
					sql.setString(2, password);
					ResultSet rs = sql.executeQuery();
					boolean m = rs.next();
					if(m == true){
						backNews = "登录成功";
						String s1 = oldDir.concat(userid);
						File f3 = new File(s1);
						f3.mkdir();
						loginBean.setBackNews(backNews);
						loginBean.setSuccess(true);
						loginBean.setUserid(userid);
					}
					else{
						backNews = "您输入的用户名不存在或密码不正确！";
						loginBean.setBackNews(backNews);
						loginBean.setSuccess(false);
						loginBean.setUserid(userid);
						loginBean.setPassword(password);
					}
				}
				else{
					backNews = "您输入的用户名不存在或密码不正确！";
					loginBean.setBackNews(backNews);
					loginBean.setSuccess(false);
					loginBean.setUserid(userid);
					loginBean.setPassword(password);
				}
				con.close();
			}
				catch(SQLException exp){
					backNews = ""+exp;
					loginBean.setBackNews(backNews);
					loginBean.setSuccess(false);
				}
			}
		RequestDispatcher dispatchar = request.getRequestDispatcher("showLoginMess.jsp");
		dispatchar.forward(request, response);
		}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request, response);
	}
}
