package org.dao;

import com.google.gson.Gson;
import org.User.User;
import org.User.UserJDBC;

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
@WebServlet(name = "ServletRegister")
public class ServletRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream stream = null;
        stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String s = null;
        s = reader.readLine();
        System.out.println(s);
        Gson json = new Gson();
        User user = json.fromJson(s, User.class);
        System.out.println(user.getAddress()+"123123123");
        UserJDBC jdbc=new UserJDBC();
        int i =jdbc.insert(user);
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET");
        response.setHeader("Access-Control-Allow-Headers","Content-Type");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        if (i == 0) {
            response.getWriter().write("false");
        } else {
            response.getWriter().write("true");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
