package com.example.bonz.uniap_fake.model;

import com.example.bonz.uniap_fake.dbcontext.DBContext;

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
    private SubjectOfClassModel subjectOfClassModel;

    public static LectureModel create(int id, String date, int slot, SubjectOfClassModel subjectOfClassModel) {
        LectureModel lecture = new LectureModel();
        lecture.id = id;
        lecture.date = date;
        lecture.slot = slot;
        lecture.subjectOfClassModel = subjectOfClassModel;

        return lecture;
    }

    public static LectureModel createWithoutId(String date, int slot, SubjectOfClassModel subjectOfClassModel) {
        LectureModel lecture = new LectureModel();
        DBContext dbContext = DBContext.getInst();
        lecture.id = dbContext.getMaxLectureId() + 1;
        lecture.date = date;
        lecture.slot = slot;
        lecture.subjectOfClassModel = subjectOfClassModel;

        return lecture;
    }

    public void setSubjectOfClassModel(SubjectOfClassModel subjectOfClassModel) {
        this.subjectOfClassModel = subjectOfClassModel;
    }

    public SubjectOfClassModel getSubjectOfClassModel() {
        return subjectOfClassModel;
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
