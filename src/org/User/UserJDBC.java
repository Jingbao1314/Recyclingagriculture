package org.User;

import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class UserJDBC {
    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://116.196.91.8:3306/mysqlfarmer?useUnicode=true&characterEncoding=utf8";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "123456";
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static int insert(User student) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert ignore into user (uimage,uname,upwd,iphone,email,address) values (?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);

            pstmt.setString(1, student.getUimage());
            pstmt.setString(2, student.getUname());
            pstmt.setString(3, student.getUpwd());
            pstmt.setString(4, student.getIphone());
            pstmt.setString(5, student.getEmail());
            pstmt.setString(6, student.getAddress());
            i = pstmt.executeUpdate();
            System.out.println("result " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int update(User student) {
        Connection conn = getConn();
        int i = 0;
        String sql = "UPDATE user SET uimage =" + "'"+student.getUimage()+"'"+"," + "upwd="
                +"'"+ student.getUpwd()+"'"+"," + "email=" + "'"+student.getEmail()+
                "'"+ ","+"address=" +"'"+ student.getAddress()+"'"+","+"uname=" +"'"+student.getUname()+"'"+" "+ "where iphone="+student.getIphone();
        System.out.println(sql);
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;

    }
    public static User SelectUser(String iphone) {
        System.out.println(iphone);
        User user = new User();
        Connection conn = getConn();
        String sql = "select * from user where iphone = '"+iphone+"'";
//        String sql = "select * from us where iphone = '"+iphone+"'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("hello");
                user.setUimage(rs.getString("uimage"));
                user.setUname(rs.getString("uname"));
                user.setUpwd(rs.getString("upwd"));
                user.setIphone(rs.getString("iphone"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                System.out.println(user.getAddress());
                System.out.println(user.getUimage());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(new Gson().toJson(user));
        return user;
    }

    public static int AdminLogin(String iphone,String upwd) {
        User user = new User();
        int flag=0;
        if(iphone!="111111111111"&&upwd!="111111"){
            flag=0;
        }
        else flag=1;
//        Connection conn = getConn();
//        String sql = "select * from user where iphone = '"+iphone+"'";
//        PreparedStatement pstmt;
//        try {
//            pstmt = (PreparedStatement)conn.prepareStatement(sql);
//            ResultSet rs = pstmt.executeQuery();
//            int col = rs.getMetaData().getColumnCount();
//            while (rs.next()) {
//                System.out.println(rs.getString("uname"));
//                user.setUname(rs.getString("uname"));
//                user.setUpwd(rs.getString("upwd"));
//                user.setIphone(rs.getString("iphone"));
//                user.setEmail(rs.getString("email"));
//                user.setAddress(rs.getString("address"));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
        return flag;
    }
public static List getAllUser() {
    Connection conn = getConn();
    ArrayList<User> list=new ArrayList<>();
    String sql = "select * from user ";
    PreparedStatement pstmt;
    try {
        pstmt = (PreparedStatement)conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        int col = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            User user = new User();
            System.out.println(rs.getString("uname"));
            user.setUimage(rs.getString("uimage"));
            user.setUname(rs.getString("uname"));
            user.setUpwd(rs.getString("upwd"));
            user.setIphone(rs.getString("iphone"));
            user.setEmail(rs.getString("email"));
            user.setAddress(rs.getString("address"));
            list.add(user);

        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

    public static int getUser(String iphone,String pass) throws SQLException {
        int flag=0;
        User user = new User();
        Connection conn = getConn();
        String sql = "select * from user where iphone = '"+iphone+"';";
        PreparedStatement pstmt;
        pstmt = (PreparedStatement)conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        int col = rs.getMetaData().getColumnCount();
        try {
            while (rs.next()) {
                if(!(rs.getString("iphone")==null)){
                    flag=1;
                }

                System.out.println(rs.getString("uname"));
                user.setUimage(rs.getString("uimage"));
                user.setUname(rs.getString("uname"));
                user.setUpwd(rs.getString("upwd"));
                user.setIphone(rs.getString("iphone"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));

            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static int deleteUser(String name) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from user where uname='" + name + "'";
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
    public static Count count() {
        Connection conn = getConn();
        String sql = "select COUNT(uname) from user ";
        PreparedStatement pstmt;
        Count c=new Count();
        int col = 1;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            col = rs.getInt(1);
            conn.close();
            c.setCount(col);
            } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return c;
    }
    public static int deleteUserIphone(String iphone) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from user where iphone='" + iphone + "'";
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
