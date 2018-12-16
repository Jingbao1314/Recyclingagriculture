package org.zhengce;

import org.stat.state1;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xiao on 17-11-17.
 */
public class ZhengceJDBC {
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

    public static List getAllInfo() {
        ArrayList list = new ArrayList<Zhengce>();
        Connection conn = getConn();
        String sql = "select * from zhengce limit 50";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Zhengce zhengce = new Zhengce();
                zhengce.setId(rs.getInt("id"));
                zhengce.setZtitle(rs.getString("ztitle"));
                zhengce.setZcontent(rs.getString("zcontent"));
               // zhengce.setImage(rs.getString("zimage"));
                list.add(zhengce);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
    public static List getAllInfo1() {
        ArrayList list = new ArrayList<Zhengce>();
        Connection conn = getConn();
        String sql = "select * from zhengce ";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Zhengce zhengce = new Zhengce();
                zhengce.setId(rs.getInt("id"));
                zhengce.setZtitle(rs.getString("ztitle"));
                zhengce.setZcontent(rs.getString("zcontent"));
       //         zhengce.setImage(rs.getString("zimage"));
                list.add(zhengce);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
    public static Zhengce getAllID(int id) {
        Connection conn = getConn();
        String sql = "select * from zhengce where id="+id;
        System.out.println(sql);
        Zhengce zhengce=new Zhengce();
        zhengce.setId(id);
        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                zhengce.setZtitle(rs.getString("ztitle"));
                zhengce.setZtitle(rs.getString("zcontent"));
                zhengce.setZdate(rs.getString("zdate"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zhengce;
    }
    public static ArrayList<Zhengce> getAllInfo(int  from, int to) {
        ArrayList<Zhengce> list=new ArrayList<Zhengce>();
        Connection conn = getConn();
        String sql="select * from zhengce limit"+" "+from+","+to;
        System.out.println(sql);
        try {
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Zhengce count=new Zhengce();
                count.setId(rs.getInt("id"));
                count.setZtitle(rs.getString("ztitle"));
                count.setZcontent(rs.getString("zcontent"));
                count.setZdate(rs.getString("zdate"));
                list.add(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<Zhengce> spliteerea(int tid) {
        List<Zhengce> list=getAllInfo1();
        List<Zhengce> listarea=new ArrayList<>();
        Connection conn = getConn();
        String sql="select ar from area where id="+tid;
        Settid settid=new Settid();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                settid.setArea(rs.getString("ar"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Zhengce zhengce:list){
            if(zhengce.getZtitle().contains(settid.getArea())){
                listarea.add(zhengce);
            }
        }

        return listarea;
    }
    public static List<Zhengce> spliteyear(String year) {
        List<Zhengce> list=getAllInfo1();
        List<Zhengce> listyear=new ArrayList<>();
        for(Zhengce zhengce:list){
            if(zhengce.getZcontent().contains(year)){
                listyear.add(zhengce);
            }
        }
        return listyear;
    }
    public static int delete(int id) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from zhengce where id=" + id;
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
    public static int update(Zhengce news) {
        Connection conn = getConn();
        int i = 0;
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
        String time=format.format(date);
        String sql = "update zhengce set zcontent=" + "'" + news.getZcontent() + "'" + "," +"ztitle="+"'"+news.getZtitle()+"'"+","+"zdate="+"'"+time+"'"+ " " + " where id=" +  news.getId();
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
    public static state1 insertNews(Zhengce news) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert ignore into  zhengce(ztitle,zcontent,zdate) values(?,?,?)";
        String mysql="select id from zhengce where ztitle="+"'"+news.getId()+"'";
        PreparedStatement pstmt;
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
        String time=format.format(date);
        state1 state=new state1();
        System.out.println(time);
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, news.getZtitle());
            pstmt.setString(2, news.getZcontent());
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
            System.out.println(news.getZtitle());
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }
}

