package org.zhengce;

import com.google.gson.Gson;
import org.news.News;
import org.news.NewsJDBC;

import java.util.ArrayList;

/**
 * Created by xiao on 17-11-17.
 */
public class ZhengceImpl {
    public String getNewsInfo(String title){
        String result = "";
        News news = NewsJDBC.getAll(title);
        Gson gson = new Gson();
        result = gson.toJson(news);
        return result;
    }
    public String getNews(){
        String result = "";
        ArrayList<Zhengce> list = (ArrayList<Zhengce>) NewsJDBC.getAllInfo();
        ArrayList<News> list1 = new ArrayList<>();
        for (Zhengce zhengce : list) {
            String message = zhengce.getZtitle();
//            if ()
        }
        Gson gson = new Gson();
        result = gson.toJson(list);
        return result;
    }
}
