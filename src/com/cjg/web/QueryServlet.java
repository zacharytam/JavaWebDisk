package com.cjg.web;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cjg.web.utils.FileUtils;
public class QueryServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the GET method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

    @SuppressWarnings("deprecation")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        String path = request.getParameter("path");
        String query = request.getParameter("query");
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY leftmargin=50>");
        if (query.equals("")) {
            out.println("Select Search Content");
        } else {
            File[] files = FileUtils.fileList(path, query);

            out.println("<table align=left border=0 width=700>");
            out.println("<tr>");
            out.println("<th>Name</th><th>Type</th><th>Size</th><th>EditDate</th><th>Operation</th>");
            out.println("</tr>");
            for (File f : files) {
                if (!f.isHidden()) {
                    out.println("<tr>");
                    String url = f.isDirectory() ? "DirServlet.shtml?path=" + f.getAbsolutePath() : "ReaderServlet.shtml?path=" + f.getAbsolutePath();
                    out.print("<td><a href='" + url + "'>" + f.getName() + "</a></td>");
                    out.print("<td>" + (f.isFile() ? "File" : "Folder") + "</td>");
                    out.println("<td>" + (f.isDirectory() ? "" : (f.length() / 1024) + "KB") + "</td>");
                    out.println("<td>" + new Date(f.lastModified()).toLocaleString() + "</td>");
                    out.print("<td><a href='OperateServlet.shtml?mtype=delete&path=" + f.getAbsolutePath() + "'>Delete</a>&nbsp;&nbsp;&nbsp;");
                    out.print("<a href='OperateServlet.shtml?mtype=rename&path=" + f.getAbsolutePath() + "'>Rename</a></td>");
                    out.println("</tr>");
                }
            }
            out.println("</table>");
        }
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }
}
