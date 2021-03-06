package org.Servletxx;

import com.google.gson.Gson;
import org.zhengce.Settid;
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
import java.util.List;

/**
 * Created by xiao on 17-11-23.
 */
@WebServlet(name = "ServletZhengceArea")
public class ServletZhengceArea extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream stream = null;
        stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String s = reader.readLine();
        Gson json = new Gson();
        Settid news = json.fromJson(s, Settid.class);
        ZhengceJDBC jdbc=new ZhengceJDBC();
        List<Zhengce> news1=  jdbc.spliteerea(news.getTid());
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET");
        response.setHeader("Access-Control-Allow-Headers","Content-Type");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().write( json.toJson(news1));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
