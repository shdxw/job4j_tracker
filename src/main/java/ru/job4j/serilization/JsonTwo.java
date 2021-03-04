package ru.job4j.serilization;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTwo {
    /* JSONObject из json-строки строки */
    public static void main(String[] args) {
        /* JSONObject напрямую методом put */
        Cat cat = new Cat("myao", true, 23, new String[]{"myao2", "myao3"},
                new Contact(234, "89242640011"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", cat.getName());
        jsonObject.put("strerilized", cat.isSterilized());
        jsonObject.put("age", cat.getAge());
        jsonObject.put("children", new JSONArray(cat.getChildren()));
        jsonObject.put("contact", new JSONObject(cat.getContact()));

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект cat в json-строку */
        System.out.println(new JSONObject(cat).toString());
    }

}
