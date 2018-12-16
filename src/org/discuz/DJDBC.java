package org.discuz;

import com.google.gson.Gson;

import java.sql.*;
import java.util.*;

import static org.discuz.Timetest.TimeStamp2Date;

/**
 * Created by xiao on 17-11-21.
 */
public class DJDBC {
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://116.196.91.8:3306/ultrax?useUnicode=true&characterEncoding=utf8";
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
    public static ArrayList<DCount> getAll(int  authorid) {
        ArrayList<DCount> list=new ArrayList<DCount>();
        Connection conn = getConn();


        String sql="select a.telephone,a.address,b.dateline,b.message,b.subject,b.author,b.authorid " +
                "from pre_common_member_profile as a,pre_forum_post as b where a.uid=b.authorid and a.uid="+authorid;

        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                DCount count=new DCount();
                count.setTelephone(rs.getString("telephone"));
                count.setAddress(rs.getString("address"));
                count.setDataline(TimeStamp2Date(rs.getString("dateline")));
                count.setDataline(rs.getString("dateline"));
                count.setSubject(rs.getString("subject"));
                count.setMessage(rs.getString("message"));
                count.setAuthor(rs.getString("author"));
                count.setAuthorid(rs.getInt("authorid"));
                list.add(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<DCount> getAllInfo(int  from,int to) {
    ArrayList<DCount> list=new ArrayList<DCount>();
    Connection conn = getConn();

    String sql="select a.telephone,a.address,b.dateline,b.message,b.subject,b.author,b.authorid " +
            "from pre_common_member_profile  a left join pre_forum_post  b on a.uid = b.authorid order by a.uid limit "+from+","+to;
        System.out.println(sql);
    try {
        Statement statement=conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            DCount count=new DCount();
            count.setTelephone(rs.getString("telephone"));
            count.setAddress(rs.getString("address"));
            count.setDataline(TimeStamp2Date(rs.getString("dateline")));
            System.out.println(count.getDataline());
//            count.setDataline(rs.getString("dateline"));
            count.setSubject(rs.getString("subject"));
            count.setMessage(rs.getString("message"));
            count.setAuthor(rs.getString("author"));
            count.setAuthorid(rs.getInt("authorid"));
            list.add(count);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return list;
}
    public static void main(String[] args) {
        int from=0;
        int to=3;
        List<DCount> dCount=getAllInfo(from,to);
        Gson gson=new Gson();
        String json=gson.toJson(dCount);
        json="{\"name\":\"你需我求\",\"data\":"+json+"}";
        System.out.println(json);
    }
}
