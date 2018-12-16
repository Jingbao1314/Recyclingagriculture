package org.discuz;

/**
 * Created by xiao on 17-11-21.
 */
public class DPost {
    private String subject;
    private String message;
    private int authorid;
    private String dataline;

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
