package org.Servletxx;

import com.google.gson.Gson;
import org.Need.Dis;
import org.Need.DisJDBC;
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
@WebServlet(name = "ServletDxlFindPerson")
public class ServletDxlFindPerson extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream stream = null;
        stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String s = reader.readLine();
        Gson json = new Gson();
        Dis news = json.fromJson(s, Dis.class);
        DisJDBC jdbc = new DisJDBC();
        int flag=jdbc.Regi(news.getIphone());
        State state = new State();
        if(flag==1) {
            String result = json.toJson(jdbc.getPerson(news.getIphone()));
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST,GET");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().write(result);
        }
        else{
            state.setState(false);
            response.getWriter().write(json.toJson(state));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
