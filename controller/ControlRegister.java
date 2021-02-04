package com.lby.controller;
import com.lby.model.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ControlRegister extends HttpServlet{
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		Connection con;
		PreparedStatement sql;
		Register reg = new Register();
		request.setAttribute("register", reg);
		String userid = request.getParameter("userid").trim();
		String password  = request.getParameter("password").trim();
		String email = request.getParameter("email").trim();
		String telephone = request.getParameter("telephone").trim();
		String qq = request.getParameter("qq").trim();
		//String uri = "jdbc:sqlserver://localhost:1433;DatabaseName=PEDisk";
		if(userid==null)
			userid = "";
		if(password==null)
			password = "";
		boolean isLD = true;
		for(int i = 0;i < userid.length();i++){
			char l = userid.charAt(0);
			char c = userid.charAt(i);
			if(!(((c<='z'&&c>='a')||(c<='Z'&&c>='A')||(c<='9'&&c>='0')) && (l<='Z'&&l>='A'))){
				isLD = false;
				}
			}
			boolean boo = userid.length()>0 && password.length()>0 && isLD;
			String backNews = "";
			try{
				con = DriverManager.getConnection("jdbc:odbc:PEDisk","sa","lby19920102");
				String insertCondition = "INSERT INTO peDisk VALUES(?,?,?,?,?,?)";
				sql = con.prepareStatement(insertCondition);
				if(boo){
					sql.setString(1, userid);
					sql.setString(2, password);
					sql.setString(3, email);
					sql.setString(4, telephone);
					sql.setString(5, qq);
					sql.setString(6, null);
					int m = sql.executeUpdate();
					if(m != 0){
						backNews = "恭喜您注册成功！";
						reg.setBackNews(backNews);
						reg.setUserid(userid);
						reg.setPassword(password);
						reg.setEmail(email);
						reg.setTelephone(telephone);
						reg.setQq(qq);
					}
				}
				else {
					backNews = "信息填写不完整或者含有非法字符";
					reg.setBackNews(backNews);
				}
				con.close();
			}
			catch(SQLException exp){
				backNews = "用户已存在，请您重新填写";
				reg.setBackNews(backNews);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("showRegisterMess.jsp");
			dispatcher.forward(request,response);
		}
		public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
			doPost(request,response);
		}
	}

