package org.Servletxx;

import com.google.gson.Gson;
import org.discuz.DCount;
import org.discuz.DJDBC;

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
@WebServlet(name = "ServletDiscuz")
public class Servle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream stream = null;
        stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String s = reader.readLine();
        Gson json = new Gson();
        DCount news = json.fromJson(s, DCount.class);
        DJDBC jdbc=new DJDBC();
        ArrayList<DCount> dCount=jdbc.getAll(news.getAuthorid());
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET");
        response.setHeader("Access-Control-Allow-Headers","Content-Type");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");//传值编码
        response.setContentType("text/html;charset=UTF-8");//设置传输编码
        String result = "";
        result = json.toJson(dCount);
        System.out.println();
//        String result=json.toJson(dCount);
        result="{\"name\":\"xxxxxxx\",\"data\":"+result+"}";
        response.getWriter().write( result);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
