package org.news;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by xiao on 17-11-16.
 */
public class NewsImpl {
    public String getNewsInfo(String title){
        String result = "";
        News news = NewsJDBC.getAll(title);
        Gson gson = new Gson();
        result = gson.toJson(news);
        return result;
    }
    public String getNews(){
        String result = "";
        ArrayList<News> list = (ArrayList<News>) NewsJDBC.getAllInfo();
        ArrayList<News> list1 = new ArrayList<>();
        for (News newsss : list) {
            String message = newsss.getTitle();
//            if ()
        }
        Gson gson = new Gson();
        result = gson.toJson(list);
        return result;
    }

}
