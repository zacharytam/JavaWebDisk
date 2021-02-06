package com.cjg.web;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cjg.web.utils.FileUtils;
public class WriterServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>WriterServlet.doGet</TITLE></HEAD>");
        out.println("  <BODY>");
        String path = request.getParameter("path");
        if (path != null && !path.equals("")) {
            out.println("<form action='WriterServlet.shtml' method=post>");
            out.println("<input type=hidden name=path value='" + path + "'>");
            out.println("<input name=type type=radio value=1>File");
            out.println("<input name=type type=radio value=2>Folder");
            out.println("<br>文件/文件夹名称:<input name=name size=30>");
            out.println("<br>");
            out.println("<textarea rows=5 cols=50 name=content></textarea><br>");
            out.println("<input type=submit>");
            out.println("</form>");
            out.println("  </BODY>");
            out.println("</HTML>");
        }
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>WriterServlet.doPost</TITLE></HEAD>");
        out.println("  <BODY>");
        String path = request.getParameter("path");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        if (type.equals("1")) {
            String content = request.getParameter("content");
            FileUtils.writeFile(path + "\\" + name, content);
        } else {
            boolean result = FileUtils.mkdirs(path + "\\" + name);
            if (!result) {
                out.println("Creation failed");
                out.println("<a href='#'>Return</a>");
            }
        }
        response.sendRedirect("DirServlet.shtml?path=" + path);
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }
}
