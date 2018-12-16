package org.Servletxx;

import com.google.gson.Gson;

/**
 * Created by jingbao on 18-4-25.
 */
class Student{
    private int id=0;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class Test {
    public static void main(String[] args) {
        Student student=new Student();
        student.setId(1);
        Gson gson=new Gson();
        String flag=gson.toJson(student);
        System.out.println(flag);
        Student s=gson.fromJson(flag,Student.class);
        System.out.println(s.getId());
    }
}
