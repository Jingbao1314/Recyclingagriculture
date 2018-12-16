package org.environment;

import org.stat.state1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao on 17-11-20.
 */
public class environJDBC {
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://116.196.91.8:3306/mysqlfarmer?useUnicode=true&characterEncoding=utf8";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static environ getAll(int type) {
        Connection conn = getConn();
        String sql = "select * from environmental where id="+type;
        environ env=new environ();
        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                env.setId(rs.getInt("id"));
                env.setEnvironmental_type(rs.getString("environmental_type"));
                env.setContent(rs.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return env;
    }
    public static List getAllInfo() {
        Connection conn = getConn();
        String sql = "select * from environmental";
        ArrayList<environ> list=new ArrayList<>() ;

        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                environ env=new environ();
                env.setId(rs.getInt("id"));
                env.setEnvironmental_type(rs.getString("environmental_type"));
                env.setContent(rs.getString("content"));
                list.add(env);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static int updateenv(environ news) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update environmental set content=" + "'" + news.getContent() + "'" + "," + "environmental_type=" + "'" + news.getEnvironmental_type() + "'" + " " + " where id=" + news.getId();
        System.out.println(sql);
        try {
            Statement statement = conn.createStatement();
            i = statement.executeUpdate(sql);
            System.out.println("resutl: " + i);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    public static state1 insertenv(environ news) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into  environmental(environmental_type,content) values(?,?)";
        String mysql="select id from environmental where title="+"'"+news.getEnvironmental_type()+"'";
        PreparedStatement pstmt;
        state1 state=new state1();
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, news.getEnvironmental_type());
            pstmt.setString(2, news.getContent());
            i = pstmt.executeUpdate();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(mysql);
            while (rs.next()) {
                news.setId(rs.getInt("id"));
            }
            if (i==1) state.setState(true);
            else state.setState(false);
            state.setId(news.getId());
            statement.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }
    public static int deleteenv(int id) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from environmental where id=" + id;
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            i = pst.executeUpdate();
            System.out.println("resutl: " + i);
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
