package org.Need;

import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingbao on 17-11-20.
 */
public class NeedJDBC {
    public static Connection getConn() {
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
    public static int insertNews(Needs needs) {
        Connection conn = getConn();//Image,Title,Details,Price,Place,Tel,Date
        int i = 0;
        String sql = "insert into  publish(Pid,Image,Type,Title,Gid,Quality,Details,Price,Place,Tel,Uid,Date) values(?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,needs.getPid());
            pstmt.setString(2,needs.getImage());
            pstmt.setString(3,needs.getType());
            pstmt.setString(4,needs.getTitle());
            pstmt.setString(5,needs.getGid());
            pstmt.setString(6,needs.getQuality());
            pstmt.setString(7, needs.getDetails());
            pstmt.setString(8, needs.getPrice());
            pstmt.setString(9, needs.getPlace());
            pstmt.setString(10, needs.getTel());
            pstmt.setString(11,needs.getUid());
            pstmt.setString(12, needs.getDate());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
//    public static int update(News news) {
//        Connection conn = getConn();
//        int i = 0;
//        String sql = "update datafarmer set content="+"'" + news.getContent() +"'"+","+"image="+"'"+news.getImage()+"'"+ " "+" where title="+"'" + news.getTitle() + "'";
//        System.out.println(sql);
//        // PreparedStatement pstmt;
//        try {
//            Statement statement=conn.createStatement();
//            //  pstmt = (PreparedStatement) conn.prepareStatement(sql);
//            // i = pstmt.executeUpdate();
//            i=statement.executeUpdate(sql);
//            System.out.println("resutl: " + i);
//            statement.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return i;
//    }


    public static List getAll(String title) {
        String result="";
        Needs needs[]=new Needs[50];
        ArrayList<String> list=new ArrayList<>();
        int flag=0;
        Connection conn = getConn();//title="+"'"+title+"'"
        String sql = "select * from publish where Title LIKE '%"+title+"%"+"';";
        System.out.println(sql);
        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()&flag<50) {
                Needs news=new Needs();
                //news.setTitle(title);
                news.setTitle(rs.getString("Title"));
                news.setDate(rs.getString("Date"));
                news.setPrice(rs.getString("Price"));
                news.setPlace(rs.getString("Place"));
                news.setTel(rs.getString("Tel"));
                needs[flag]=news;
                flag++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i=0;i<needs.length;i++){
            if(!(needs[i]==null)){
                //System.out.println(needs[i].getTitle());
                Gson gson=new Gson();
                result=gson.toJson(needs[i]);
                list.add(result);
            }
        }
        return list;
    }

    public static List getAllInfo() {
        ArrayList<String> list = new ArrayList<>();
        String result="";
        Needs needs[]=new Needs[50];
        int flag=0;
        Connection conn = getConn();
        String sql = "select * from publish limit 50;";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            //   System.out.println("============================");

            while (rs.next()) {
                Needs news = new Needs();
                news.setTitle(rs.getString("Title"));
                news.setDate(rs.getString("Date"));
                news.setPrice(rs.getString("Price"));
                news.setPlace(rs.getString("Place"));
                news.setTel(rs.getString("Tel"));
                needs[flag]=news;
                flag++;
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i=0;i<needs.length;i++){
            if(!(needs[i]==null)){
                //System.out.println(needs[i].getTitle());
                Gson gson=new Gson();
                result=gson.toJson(needs[i]);
                list.add(result);
            }
        }
        return list;
    }

//    public static int delete(News news) {
//        Connection conn = getConn();
//        int i = 0;
//        String sql = "delete from datafarmer where title=" +"'"+ news.getTitle() +"'";
//        PreparedStatement pst;
//        try {
//            pst = (PreparedStatement) conn.prepareStatement(sql);
//            i = pst.executeUpdate();
//            System.out.println("resutl: " + i);
//            pst.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return i;
//    }

    public static void main(String[] args) {
        Needs needs1=new Needs();
        needs1.setTitle("Test");
        needs1.setType("88");
        needs1.setGid("1");
        needs1.setPid("1");
        needs1.setQuality("2002");
        needs1.setPrice("4");
        needs1.setUid("7");
        needs1.setPlace("DASASD");
        needs1.setDate("2017-05-26 11:02:28");
        needs1.setTel("555");
        needs1.setDetails("7");
        insertNews(needs1);
        List list=getAll("购");
        for (Object s:list) {
            System.out.println(s);
        }
    }
}
