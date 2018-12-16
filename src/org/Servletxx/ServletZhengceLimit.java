package org.Servletxx;

import com.google.gson.Gson;
import org.discuz.From;
import org.zhengce.Zhengce;
import org.zhengce.ZhengceJDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by xiao on 17-11-21.
 */
@WebServlet(name = "ServletZhengceLimit")
public class ServletZhengceLimit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int from  = 0;
        int to = 0;
        if (request.getParameter("from") != null) {
            from = Integer.parseInt(request.getParameter("from"));
            to = Integer.parseInt(request.getParameter("to"));
        }
        Gson json = new Gson();
        InputStream stream = null;
        stream = request.getInputStream();

        if (stream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
            String s = reader.readLine();
            if (s != null) {
                From fromTo=json.fromJson(s,From.class);
                from = fromTo.getFrom();
                to = fromTo.getTo();
            }

        }
        ZhengceJDBC jdbc=new ZhengceJDBC();
        ArrayList<Zhengce> dCount=jdbc.getAllInfo(from,to);
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET");
        response.setHeader("Access-Control-Allow-Headers","Content-Type");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String result = "";
        result = json.toJson(dCount);
        System.out.println();
        result="{\"name\":\"政策\",\"data\":"+result+"}";
        response.getWriter().write( result);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
