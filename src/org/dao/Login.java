package org.dao;

import com.google.gson.Gson;
import org.User.User;
import org.User.UserJDBC;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

/**
 * Created by xiao on 17-11-17.
 */
public class Login extends HttpServlet{
    static final String DB_URL = "jdbc:mysql://116.196.91.8:3306/mysqlfarmer?useUnicode=true&characterEncoding=utf8";
    static final String USER = "root";
    static final String PASS = "123456";

    public static ResultSet conn(String username, String password) {
        Connection connection = null;
        Statement statement = null;
        try {
            String sql = "SELECT * FROM user ";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            statement = connection.prepareStatement(sql);
            ResultSet set = statement.executeQuery(sql);
            return set;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream stream = null;
        stream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String s = null;
        s = reader.readLine();
        System.out.println(s);
        Gson json = new Gson();
        User user = json.fromJson(s, org.User.User.class);
        UserJDBC jdbc = new UserJDBC();
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET");
        response.setHeader("Access-Control-Allow-Headers","Content-Type");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        try {
            int i = jdbc.getUser(user.getIphone(),user.getUpwd());
            if (i == 0) {
            response.getWriter().write("false");
        } else {
                User user1=jdbc.SelectUser(user.getUname());
                user1.setState(true);
                System.out.println("write");
                response.getWriter().write( json.toJson(user1));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


