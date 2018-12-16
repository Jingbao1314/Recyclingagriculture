package org.Servletxx;

import com.google.gson.Gson;
import org.discuz.NewNeedsJDBC;
import org.discuz.NewneddsDiscuz;

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
 * Created by jingbao on 17-11-27.
 */
@WebServlet(name = "SerletNewNeed")
public class SerletNewNeed extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream stream = null;
        stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String s = reader.readLine();
        Gson json = new Gson();
        NewneddsDiscuz needs = json.fromJson(s, NewneddsDiscuz.class);
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET");
        response.setHeader("Access-Control-Allow-Headers","Content-Type");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");//传值编码
        response.setContentType("text/html;charset=UTF-8");//设置传输编码
        NewNeedsJDBC jdbc=new NewNeedsJDBC();
        int flag=needs.getId();
        if (flag==1) {
            String string=json.toJson(NewNeedsJDBC.getAll());
                response.getWriter().write(string);

        } else if (flag==2){
            String string=json.toJson(NewNeedsJDBC.getBuy());
            response.getWriter().write(string);


        }else if(flag==3){
            String string=json.toJson(NewNeedsJDBC.getSale());
            response.getWriter().write(string);


        }else {
            response.getWriter().write("请求不正确");

        }

//        response.getWriter().write("" + i);
//        response.getWriter().write(news.getUname());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
