package com.cjg.web;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.FileUtils;
public class DirServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("deprecation")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY leftmargin=50>");
        String path = request.getParameter("path");
        if (path != null && !path.equals("")) {
            out.println("[<a href='WriterServlet.shtml?path=" + path + "'>NewFile/NewFolder</a>]");
            File file = new File(path);
            out.println("<a href='DirServlet.shtml?path=" + file.getParent() + "'><<Back</a>");
            out.println("&nbsp;&nbsp;&nbsp;&nbsp;");
            out.println("<form action='QueryServlet.shtml' method=post>");
            out.println("<input type=hidden name=path value='" + path + "'>");
            out.println("<input name=query> <input type=submit value=Search>");
            out.println("</form>");
            out.println("<br><br><br>");

            File[] files = FileUtils.fileList(path);

            out.println("<table align=left border=0 width=700>");
            out.println("<tr>");
            out.println("<th>Name</th><th>Type</th><th>Size</th><th>EditDate</th><th>Operate</th>");
            out.println("</tr>");
            for (File f : files) {
                if (!f.isHidden()) {
                    out.println("<tr>");
                    String url = f.isDirectory() ? "DirServlet.shtml?path=" + f.getAbsolutePath() : "ReaderServlet.shtml?path=" + f.getAbsolutePath();
                    out.print("<td><a href='" + url + "'>" + f.getName() + "</a></td>");
                    out.print("<td>" + (f.isFile() ? "File" : "Folder") + "</td>");
                    out.println("<td>" + (f.isDirectory() ? "" : (f.length() / 1024) + "KB") + "</td>");
                    out.println("<td>" + new Date(f.lastModified()).toLocaleString() + "</td>");
                    out.print("<td><a href='OperateServlet.shtml?mtype=delete&path=" + f.getAbsolutePath() + f.delete()+"'>Delete</a>&nbsp;&nbsp;&nbsp;");
                    
                    out.println("</tr>");
                }
            }
            out.println("</table>");
        } else {
            response.sendRedirect("DiskServlet.shtml");
        }
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the POST method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }
}
