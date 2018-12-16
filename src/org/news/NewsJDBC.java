package org.news;

import org.stat.state1;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao on 17-11-16.
 */
public class NewsJDBC {
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

    public static state1 insertNews(News news) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into  datafarmer(title,content,date) values(?,?,?)";
        java.util.Date date=new java.util.Date();
        DateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
        String time=format.format(date);
        String mysql="select id from datafarmer where title="+"'"+news.getTitle()+"'";
        state1 state=new state1();
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, news.getTitle());
            pstmt.setString(2, news.getContent());
            pstmt.setString(3, time);
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

    public static int update(News news) {
        Connection conn = getConn();
        int i = 0;
        java.util.Date date=new java.util.Date();
        DateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
        String time=format.format(date);
        String sql = "update datafarmer set content=" + "'" + news.getContent() + "'" + "," + "date=" + "'" + time + "'" + " " + " where title=" + "'" + news.getTitle() + "'";
        System.out.println(sql);
        // PreparedStatement pstmt;
        try {
            Statement statement = conn.createStatement();
            //  pstmt = (PreparedStatement) conn.prepareStatement(sql);
            // i = pstmt.executeUpdate();
            i = statement.executeUpdate(sql);
            System.out.println("resutl: " + i);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static News getAll(String title) {
        Connection conn = getConn();
        String sql = "select * from datafarmer where title=" + "'" + title + "'";
        System.out.println(sql);
        News news = new News();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                news.setContent(rs.getString("content"));
                news.setImage(rs.getString("image"));
                news.setDate(rs.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    public static News getAllID(int id) {
        Connection conn = getConn();
        String sql = "select * from datafarmer where id=" + id;
        System.out.println(sql);
        News news = new News();;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setDate(rs.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    public static List getAllInfo() {
        ArrayList list = new ArrayList<News>();
        Connection conn = getConn();
        String sql = "select * from datafarmer limit 50 ";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                News news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setImage(rs.getString("image"));
                news.setDate(rs.getString("date"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List NewGetAll() {
        ArrayList list = new ArrayList<News>();
        Connection conn = getConn();
        String sql = "select * from datafarmer ";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                News news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setImage(rs.getString("image"));
                news.setDate(rs.getString("date"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List getLimit(int from, int to) {
        ArrayList list = new ArrayList<News>();
        Connection conn = getConn();
        String sql = "select * from datafarmer limit " + from + "," + to;
        System.out.println(sql);
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                News news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setImage(rs.getString("image"));
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
        String sql = "delete from datafarmer where id=" + id;
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
