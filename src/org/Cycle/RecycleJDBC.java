package org.Cycle;

import java.sql.*;

/**
 * Created by xiao on 17-11-20.
 */
public class RecycleJDBC {
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
    public static Price getAll(int type,int count,int tid) {
        Connection conn = getConn();
        String sql = "select * from goods where Gid="+type;
        System.out.println(sql);
        Price recycle=new Price();
        Recycle recycle1=new Recycle();
        recycle.setGid(type);
        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                recycle1.setPrice(rs.getFloat("Gprice"));
                switch (tid){
                    case 1:recycle.setSun((recycle1.getPrice()*count*1000));break;
                    case 2:recycle.setSun((recycle1.getPrice()*count));break;
                    case 3:recycle.setSun((recycle1.getPrice()*count/1000));break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recycle;
    }
}
