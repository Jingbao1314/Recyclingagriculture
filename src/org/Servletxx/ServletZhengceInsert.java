package org.Servletxx;

import com.google.gson.Gson;
import org.stat.state1;
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

/**
 * Created by xiao on 17-11-25.
 */
@WebServlet(name = "ServletZhengceInsert")
public class ServletZhengceInsert extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream stream = null;
        stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String s = reader.readLine();
        System.out.println(s);
        Gson json = new Gson();
        Zhengce news = json.fromJson(s, Zhengce.class);
        ZhengceJDBC jdbc=new ZhengceJDBC();
        state1 state= jdbc.insertNews(news);
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET");
        response.setHeader("Access-Control-Allow-Headers","Content-Type");
       response.getWriter().write(json.toJson(state));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
