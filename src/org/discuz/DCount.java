package org.discuz;

/**
 * Created by xiao on 17-11-21.
 */
public class DCount {
   // private int uid;
    private String name;
    private String telephone;
    private String address;
    private String subject;
    private String message;
    private int authorid;
    private String dataline;
    private String author;
    public String getAuthor() {
        //language=JSON
        String ss = "{\"aaa\":1,\"bbb\":2}";
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getDataline() {
        return dataline;
    }

    public void setDataline(String dataline) {
        this.dataline = dataline;
    }
}
