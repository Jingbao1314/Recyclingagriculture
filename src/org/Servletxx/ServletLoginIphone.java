package org.Servletxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiao on 17-11-20.
 */
@WebServlet(name = "ServletLoginIphone")
public class ServletLoginIphone extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        ResultSet conn = conn(username, password);
//        PrintWriter writer = response.getWriter();
//        try {
//            boolean b=false;
//            while (conn.next()) {
//                String name = conn.getString("uname");
//                String pass = conn.getString("upwd");
//                if (username.equals(name) && password.equals(pass)) {
//                    writer.write("true");
//                    writer.write(username);
//
//                    Gson gson=new Gson();
//
//
//                    b=true;
//                    break;
//                }
//            }
//            if(!b)  response.getWriter().write("false");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (writer!=null) {
//                writer.close();
//            }
//        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
