package org.discuz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingbao on 17-11-27.
 */
public class NewNeedsJDBC {
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
    //*************************************************************
    public static List getAll() {
        String result="";
        NewNeeds needs[]=new NewNeeds[50];
        ArrayList<NewNeeds> list=new ArrayList<>();
        int flag=0;
        Connection conn = getConn();//title="+"'"+title+"'"
        String sql = "select * from newneeds limit 50;";
        System.out.println(sql);
        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()&flag<50) {
                NewNeeds news=new NewNeeds();
                news.setUsername(rs.getString("username"));
                news.setPassward(rs.getString("passward"));
                news.setContent(rs.getString("content"));
                list.add(news);
                needs[flag]=news;
                flag++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        for(int i=0;i<needs.length;i++){
//            if(!(needs[i]==null)){
//                //System.out.println(needs[i].getTitle());
//                Gson gson=new Gson();
//                result=gson.toJson(needs[i]);
//                System.out.println(result);
//                list.add(result);
//            }
//        }
        return list;
    }
    //********************************************************************************************

    public static List getBuy() {
        String result="";
        NewNeeds needs[]=new NewNeeds[50];
        ArrayList<NewNeeds> list=new ArrayList<>();
       // ArrayList<String> list=new ArrayList<>();
        int flag=0;
        Connection conn = getConn();//title="+"'"+title+"'"
        String sql = "select * from newneeds where state ='买' or state ='购' limit 50;";
        System.out.println(sql);
        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()&flag<50) {
                NewNeeds news=new NewNeeds();
                //news.setTitle(title);
                news.setUsername(rs.getString("username"));
                news.setPassward(rs.getString("passward"));
                news.setContent(rs.getString("content"));

                list.add(news);

                needs[flag]=news;
                flag++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        for(int i=0;i<needs.length;i++){
//            if(!(needs[i]==null)){
//                //System.out.println(needs[i].getTitle());
//                Gson gson=new Gson();
//                result=gson.toJson(needs[i]);
//                System.out.println(result);
//                list.add(result);
//            }
//        }
        return list;
    }
    //***********-******************************************************

    public static List getSale() {
        String result="";
        NewNeeds needs[]=new NewNeeds[50];
        ArrayList<NewNeeds> list=new ArrayList<>();
        int flag=0;
        Connection conn = getConn();//title="+"'"+title+"'"
        String sql = "select * from newneeds where state ='卖'or state ='售' limit 50;";
        System.out.println(sql);
        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()&flag<50) {
                NewNeeds news=new NewNeeds();
                //news.setTitle(title);
                news.setUsername(rs.getString("username"));
                news.setPassward(rs.getString("passward"));
                news.setContent(rs.getString("content"));
                list.add(news);
                needs[flag]=news;
                flag++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        for(int i=0;i<needs.length;i++){
//            if(!(needs[i]==null)){
//                //System.out.println(needs[i].getTitle());
//                Gson gson=new Gson();
//                result=gson.toJson(needs[i]);
//                System.out.println(result);
//                list.add(result);
//            }
//        }
        return list;
    }


}
