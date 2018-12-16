package org.zhengce;

/**
 * Created by ysl on 17-11-23.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class luntanJDBC {
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

    public static List<luntan> getAllInfo() {
        ArrayList<luntan> list = new ArrayList<luntan>();
        Connection conn = getConn();
        String sql = "select * from userlocal ";
        PreparedStatement pstmt;

        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                luntan luntan1 = new luntan();
                luntan1.setid(rs.getInt("id"));
                luntan1.setTitle(rs.getString("title"));
                luntan1.setPersonname(rs.getString("personname"));
                luntan1.setTime(rs.getString("time"));
                luntan1.setText(rs.getString("text"));

                list.add(luntan1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
}

