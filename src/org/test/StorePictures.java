package org.test;

/**
 * Created by xiao on 17-11-20.
 */
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class StorePictures {
        private String dbDriver;
        private String dbURL;
        private String dbUser;
        private String dbPassword;
        private Connection con;
        private PreparedStatement ps;
        public StorePictures() {
            dbDriver = "com.mysql.jdbc.Driver";
            dbURL = "jdbc:mysql://localhost:3306/test";
            dbUser = "root";
            dbPassword = "1106";
            initDB();
        }
        public StorePictures(String strDriver, String strURL, String strUser, String strPwd) {
            dbDriver = strDriver;
            dbURL = strURL;
            dbUser = strUser;
            dbPassword = strPwd;
            initDB();
        }

        public void initDB() {
            try {

                Class.forName(dbDriver).newInstance();

                con = DriverManager.getConnection(dbURL,
                        dbUser, dbPassword);
            } catch(ClassNotFoundException e) {
                System.out.println(e.getMessage());
            } catch(SQLException ex) {

                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        public boolean storeImg(String strFile) throws Exception {
            boolean written = false;
            if (con == null)
                written = false;
            else {
                int id = 0;
                File file = new File(strFile);
                FileInputStream fis = new FileInputStream(file);

                try {
                    ps = con.prepareStatement("SELECT MAX(idpic) FROM PIC");
                    ResultSet rs = ps.executeQuery();

                    if(rs != null) {
                        while(rs.next()) {
                            id = rs.getInt(1)+1;
                        }
                    } else {
                        return written;
                    }

                    ps = con.prepareStatement("insert "
                            + "into PIC values (?,?,?)");
                    ps.setInt(1, id);
                    ps.setString(2, file.getName());
                    ps.setBinaryStream(3, fis, (int) file.length());
                    ps.executeUpdate();

                    written = true;
                } catch (SQLException e) {
                    written = false;
                    System.out.println("SQLException: "
                            + e.getMessage());
                    System.out.println("SQLState: "
                            + e.getSQLState());
                    System.out.println("VendorError: "
                            + e.getErrorCode());
                    e.printStackTrace();
                } finally {
                    ps.close();
                    fis.close();
                    // close db con
                    con.close();
                }
            }
            return written;
        }

        public static void main(String[] args) {
            if (args.length != 1) {
                System.err.println("java StorePictures filename");
                System.exit(1);
            }
            boolean flag = false;
            StorePictures sp = new StorePictures();
            try {
                flag = sp.storeImg(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (flag) {
                System.out.println("Picture uploading is successful.");
            } else {
                System.out.println("Picture uploading is failed.");
            }
        }
}
