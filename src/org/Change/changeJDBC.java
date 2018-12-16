package org.Change;

import org.stat.state1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao on 17-11-24.
 */
public class changeJDBC {
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

    public static change getAllID(int id) {
        Connection conn = getConn();
        String sql = "select * from changef where id=" + id;
        System.out.println(sql);
        change change = new change();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                change.setId(rs.getInt("id"));
                change.setTitle(rs.getString("title"));
                change.setContent(rs.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return change;
    }

    public static List getAllInfo() {
        ArrayList list = new ArrayList<change>();
        Connection conn = getConn();
        String sql = "select * from changef  ";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                change news = new change();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static state1 insertchange(change change) {
        Connection conn = getConn();
        String sql = "insert  into changef(title,content) values (?,?)";
        String mysql="select id from changef where title="+"'"+change.getTitle()+"'";
        state1 state=new state1();
        PreparedStatement pstmt;
        int i=0;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, change.getTitle());
            pstmt.setString(2, change.getContent());
            i = pstmt.executeUpdate();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(mysql);
            while (rs.next()) {
                change.setId(rs.getInt("id"));
            }

            if (i==1) state.setState(true);
            else state.setState(false);
            state.setId(change.getId());
            statement.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }
    public static int changeUpdate(change news) {

        Connection conn = getConn();
        int i = 0;
        String sql = "update changef set title=" + "'" + news.getTitle() + "'" + "," + "content=" + "'" + news.getContent() + "'" + " " + " where id=" + news.getId();
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
    public static int delete(int id) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from changef where id=" + id;
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