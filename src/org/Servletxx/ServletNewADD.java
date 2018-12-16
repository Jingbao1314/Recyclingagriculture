package org.Servletxx;

import com.google.gson.Gson;
import org.news.News;
import org.news.NewsJDBC;
import org.stat.state1;

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
 * Created by xiao on 17-11-17.
 */
@WebServlet(name = "ServletNewADD")
public class ServletNewADD extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//传值编码
        response.setContentType("text/html;charset=UTF-8");//设置传输编码
        InputStream stream = null;
        stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String s = reader.readLine();
        System.out.println(s);
        Gson json = new Gson();
        News news = json.fromJson(s, News.class);
        NewsJDBC jdbc=new NewsJDBC();
        state1 state= jdbc.insertNews(news);
        //System.out.println("result is " + i);
        response.getWriter().write(json.toJson(state));
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET");
        response.setHeader("Access-Control-Allow-Headers","Content-Type");
//        if (i == 0) {
//            state.setState(false);
//            response.getWriter().write(json.toJson(state));
//        } else {
//            state.setState(true);
//            response.getWriter().write(json.toJson(state));
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
