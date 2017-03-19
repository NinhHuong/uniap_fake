package com.example.bonz.uniap_fake.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by HuongNTM5 on 3/15/2017.
 */

public class LectureModel extends RealmObject {
    @PrimaryKey
    private int id;
    private String date;
    private int slot;
    private ClassModel classModel;

    public static LectureModel create(int id, String date, int slot, ClassModel classModel) {
        LectureModel lecture = new LectureModel();
        lecture.id = id;
        lecture.date = date;
        lecture.slot = slot;
        lecture.classModel = classModel;

        return lecture;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public ClassModel getClassModel() {
        return classModel;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getSlot() {
        return slot;
    }
}
