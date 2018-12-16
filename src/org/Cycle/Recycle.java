package org.Cycle;

/**
 * Created by xiao on 17-11-20.
 */
public class Recycle {
    private int Gid;
    private String Gname;
    private String Uname;
    private float sum;
    private int tid;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public int getGid() {
        return Gid;
    }
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setGid(int gid) {
        Gid = gid;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    private float price;

}
