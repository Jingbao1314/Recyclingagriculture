package org.Servletxx;

import com.google.gson.Gson;
import org.stat.State;
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
 * Created by xiao on 17-11-20.
 */
@WebServlet(name = "ServletZhengceID")
public class ServletZhengceID extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream stream = null;
        stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String s = reader.readLine();
        Gson json = new Gson();
        Zhengce zhengce = json.fromJson(s, Zhengce.class);
        ZhengceJDBC jdbc=new ZhengceJDBC();
        Zhengce zhengce1=jdbc.getAllID(zhengce.getId());
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET");
        response.setHeader("Access-Control-Allow-Headers","Content-Type");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        State state=new State();
        if(zhengce1.getId()==0) {
            state.setState(false);
            response.getWriter().write(json.toJson(state));
        }
        else
        response.getWriter().write( json.toJson(zhengce1));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
