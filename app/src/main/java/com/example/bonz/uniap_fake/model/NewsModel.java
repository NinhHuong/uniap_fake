package com.example.bonz.uniap_fake.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bonz on 3/21/17.
 */

public class NewsModel extends RealmObject{
    @PrimaryKey
    private int id;

    private String title;
    private String content;
    private String date;

    public NewsModel() {
    }

    public NewsModel(int id, String title, String content, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public static NewsModel create(int id, String title, String content, String date) {
        NewsModel model = new NewsModel();
        model.id = id;
        model.title = title;
        model.content = content;
        model.date = date;
        return model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

