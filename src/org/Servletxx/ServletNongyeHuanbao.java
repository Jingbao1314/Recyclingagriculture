package org.Servletxx;

import com.google.gson.Gson;
import org.kejiResultJDB.NongyeHuanbaoJDBC;
import org.news.FromTo;

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
 * Created by xiao on 17-11-22.
 */
@WebServlet(name = "ServletNongyeHuanbao")
public class ServletNongyeHuanbao extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream stream = null;
        stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String s = reader.readLine();
        Gson json = new Gson();
        FromTo news = json.fromJson(s, FromTo.class);
        NongyeHuanbaoJDBC jdbc=new NongyeHuanbaoJDBC();
        String result = json.toJson(jdbc.getLimit(Integer.parseInt(news.getFrom()),Integer.parseInt(news.getTo())));
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET");
        response.setHeader("Access-Control-Allow-Headers","Content-Type");
        response.getWriter().write(result);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
