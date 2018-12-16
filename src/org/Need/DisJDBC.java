package org.Need;

import org.User.User;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by xiao on 17-11-30.
 */
public class DisJDBC {
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
    public static ArrayList<Dis> getPerson(String  iphone) {
        ArrayList<Dis> list=new ArrayList<Dis>();
        Connection conn = getConn();
        String sql="select a.uimage,a.iphone,a.address,a.uname,b.title,b.content " +
                "from user as a,discuz as b where a.iphone=b.iphone and a.iphone="+"'"+iphone+"'";
        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Dis count=new Dis();
                count.setIphone(rs.getString("iphone"));
                count.setUname(rs.getString("uname"));
                count.setUimage(rs.getString("uimage"));
                count.setAddress(rs.getString("address"));
                count.setTitle(rs.getString("title"));
                count.setContent(rs.getString("content"));
                list.add(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static int Regi(String  iphone) {
        Connection conn = getConn();
        String sql="select iphone from discuz WHERE iphone="+"'"+iphone+"'";
        int flag=0;
        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User user=new User();
                user.setIphone(rs.getString("iphone"));
                flag=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    public static ArrayList<Dis> getAll() {
        ArrayList<Dis> list=new ArrayList<Dis>();
        Connection conn = getConn();
        String sql="select a.uimage,a.iphone,a.address,a.uname,b.title,b.content,b.date " +
                "from user as a,discuz as b where a.iphone=b.iphone";
        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Dis count=new Dis();
                count.setIphone(rs.getString("iphone"));
                count.setUname(rs.getString("uname"));
                count.setUimage(rs.getString("uimage"));
                count.setAddress(rs.getString("address"));
                count.setTitle(rs.getString("title"));
                count.setContent(rs.getString("content"));
                count.setDate(rs.getString("date"));
                list.add(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static int insertPPL(Send dis) {
        Connection conn = getConn();
        int i = 0;
        java.util.Date date=new java.util.Date();
        DateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
        String time=format.format(date);
        String sql = "insert  into discuz (iphone,title,content,date,goods) values (?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, dis.getIphone());
            pstmt.setString(2, dis.getTitle());
            pstmt.setString(3, dis.getContent());
            pstmt.setString(4,time);
            pstmt.setString(5,dis.getGoods());
            i = pstmt.executeUpdate();
            System.out.println("result " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    public static int insert(Send dis) {
        Connection conn = getConn();
        int i = 0;
        java.util.Date date=new java.util.Date();
        DateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
        String time=format.format(date);
        String sql = "insert  into discuz (iphone,title,content,date) values (?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, dis.getIphone());
            pstmt.setString(2, dis.getTitle());
            pstmt.setString(3, dis.getContent());
            pstmt.setString(4,time);
            i = pstmt.executeUpdate();
            System.out.println("result " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
