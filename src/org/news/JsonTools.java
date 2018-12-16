package org.news;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by evan on 17-7-16.
 */
public class JsonTools {

    private String json;
    private JsonParser jsonParser;
    private JsonObject object;


    public static void main(String[] args) {
        News news = new News();
        news.setContent("sdfdsfsdfs");
        news.setImage("dlksfjskld");
        news.setTitle("skdjfklsdjf");

        Gson gson = new Gson();
        System.out.println(gson.toJson(news));

        String json = "{\"title\":\"skdjfklsdjf\",\"content\":\"sdfdsfsdfs\",\"image\":\"dlksfjskld\"}";
        News news1 = gson.fromJson(json, News.class);

        System.out.println(news1.getContent());
    }

    public JsonTools() {
    }

    public JsonTools(String json) {
        this.json = json;
        jsonParser = new JsonParser();
        object = (JsonObject) jsonParser.parse(json);

    }

    public String getDuid() {
        String result = String.valueOf(object.get("duid"));
        result = result.substring(1,result.length()-1);
        return result;
    }

    public String getBorg() {
        String result = String.valueOf(object.get("borg"));
        result = result.substring(1,result.length()-1);
        return result;
    }


    public int getBfloor() {
        int result = Integer.parseInt(String.valueOf(object.get("bfloor")));
        return result;
    }

    public String getBgroup() {
        String result = String.valueOf(object.get("bgroup"));
        result = result.substring(1,result.length()-1);
        return result;
    }

    public String getAdn() {
        String result = String.valueOf(object.get("adn"));
        result = result.substring(1,result.length()-1);
        return result;
    }


    public String getCdate() {
        String result = String.valueOf(object.get("cdate"));
        result = result.substring(1,result.length()-1);
        return result;
    }

    public String getCtime() {
        String result = String.valueOf(object.get("ctime"));
        result = result.substring(1,result.length()-1);
        return result;
    }

    public int getStatus() {
        int result = Integer.parseInt(String.valueOf(object.get("status")));
        return result;
    }


    public float getTem() {
        int a = Integer.parseInt(String.valueOf(object.get("tem")));
        float b = (float) a / 10;
        return b;
    }

    public String getBdevice() {
        String result = String.valueOf(object.get("bdevice"));
        result = result.substring(1,result.length()-1);
        return result;
    }

    public int getModeset() {
        int result = Integer.parseInt(String.valueOf(object.get("modeset")));
        return result;
    }

    public int getDevicestatus() {
        int result = Integer.parseInt(String.valueOf(object.get("devicestatus")));
        return result;
    }

    public int getWind() {
        int result = Integer.parseInt(String.valueOf(object.get("wind")));
        return result;
    }

    public float getTemset() {
        int a = Integer.parseInt(String.valueOf(object.get("temset")));
        float b = (float) a / 10;
        return b;
    }

    public int getModset() {
        int result = Integer.parseInt(String.valueOf(object.get("modset")));
        return result;
    }
}


//{id=10,duid=20,borg=12,bdevice=12,bfloor=11,bgroup=12,adn=01,room=01,title=123,cdate=1234,ctime=123456,status=1,tem=1,modeset=11,devicestatus=12,wind=12,temset=1
//id=10,duid=20,borg=aaaa,bdevice=aaaa,bfloor=11,bgroup=qwe,adn=01,room=01,title=123,cdate=1234,ctime=123456,status=1,tem=1,modeset=11,devicestatus=12,wind=12,temset=1