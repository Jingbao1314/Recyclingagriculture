package org.kejiResultJDB;

import org.stat.state1;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao on 17-11-17.
 */
public class ResourceJDBC {
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

    public static state1 insertNews(Resource resource) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert ignore into  kejiresource(title,xinxi,date) values(?,?,?)";
        String mysql="select id from kejiresource where title="+"'"+resource.getTitle()+"'";
        java.util.Date date=new java.util.Date();
        DateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
        String time=format.format(date);
        state1 state=new state1();
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, resource.getTitle());
            pstmt.setString(2, resource.getXinxi());
            pstmt.setString(3,time);
            i = pstmt.executeUpdate();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(mysql);
            while (rs.next()) {
                resource.setId(rs.getInt("id"));
            }
            if (i==1) state.setState(true);
            else state.setState(false);
            state.setId(resource.getId());
            statement.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }

    public static int updateresource(Resource resource)  {
        Connection conn = getConn();
        int i = 0;
        java.util.Date date=new java.util.Date();
        DateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
        String time=format.format(date);
        String sql = "update kejiresource set title=" + "'" + resource.getTitle()
                + "'" +","+"xinxi="+"'"+resource.getXinxi()+"'"+","+"date="+"'"+time+"'"+ " " + " where id=" + resource.getId() ;
        System.out.println(sql);
        // PreparedStatement pstmt;
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


    public static Resource Select(String title) {

        Connection conn = getConn();
        String sql = "select * from kejiresource where title=" + "'" + title + "'";
        System.out.println(sql);
        Resource news = new Resource();
        news.setTitle(title);
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                news.setXinxi(rs.getString("xinxi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    public static Resource SelectID(int id) {

        Connection conn = getConn();
        String sql = "select * from kejiresource where id="  + id ;
        System.out.println(sql);
        Resource resource = new Resource();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                resource.setId(rs.getInt(id));
                resource.setTitle(rs.getString("title"));
                resource.setXinxi(rs.getString("xinxi"));
                resource.setDate(rs.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resource;
    }
    public static List<Resource> limit() {
        Connection conn = getConn();
        ArrayList<Resource> list = new ArrayList<>();
        int i = 0;
        String sql = "select * from kejiresource limit 50";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Resource news = new Resource();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setXinxi(rs.getString("xinxi"));
                news.setDate(rs.getString("date"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
    public static List<Resource> getAll() {
        Connection conn = getConn();
        ArrayList<Resource> list = new ArrayList<>();
        int i = 0;
        String sql = "select * from kejiresource ";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Resource news = new Resource();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setXinxi(rs.getString("xinxi"));
                news.setDate(rs.getString("date"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static int delete(int id) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from kejiresource where id=" + id;
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
    public static List<Resource> getAllInfo1(int  from, int to) {
        ArrayList<Resource> list=new ArrayList<Resource>();
        Connection conn = getConn();

        String sql="select * from kejiresource limit "+from+","+to;
        System.out.println(sql);
        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Resource count=new Resource();
                count.setId(rs.getInt("id"));
                count.setTitle(rs.getString("title"));
                count.setXinxi(rs.getString("xinxi"));
                count.setDate(rs.getString("date"));
                list.add(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}