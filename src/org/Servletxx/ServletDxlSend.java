package org.Servletxx;

import com.google.gson.Gson;
import org.Need.DisJDBC;
import org.Need.Send;
import org.stat.State;

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
 * Created by xiao on 17-11-30.
 */
@WebServlet(name = "ServletDxlSend")
public class ServletDxlSend extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream stream = null;
        stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String s = reader.readLine();
        System.out.println(s);
        Gson json = new Gson();
        Send news = json.fromJson(s, Send.class);
        DisJDBC jdbc = new DisJDBC();
        int i = jdbc.insert(news);
        System.out.println("result is " + i);
        State state = new State();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        if (i == 0) {
            state.setState(false);
            response.getWriter().write(json.toJson(state));
        } else {
            state.setState(true);
            response.getWriter().write(json.toJson(state));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
