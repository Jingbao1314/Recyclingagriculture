package org.Servletxx;

import com.google.gson.Gson;
import org.kejiResultJDB.Resource;
import org.kejiResultJDB.ResourceJDBC;

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
 * Created by xiao on 17-11-18.
 */
@WebServlet(name = "ServletResource")
public class ServletResource extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream stream = null;
        stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String s = reader.readLine();
        Gson json = new Gson();
        Resource news = json.fromJson(s, org.kejiResultJDB.Resource.class);
        ResourceJDBC jdbc=new ResourceJDBC();
        Resource resource=jdbc.Select(news.getTitle());
        String result=json.toJson(resource);
        response.getWriter().write(result);
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET");
        response.setHeader("Access-Control-Allow-Headers","Content-Type");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
