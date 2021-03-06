package com.example.bonz.uniap_fake.model;

import java.util.Date;

import com.example.bonz.uniap_fake.dbcontext.DBContext;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by HuongNTM5 on 3/15/2017.
 */

public class LectureModel extends RealmObject {
    @PrimaryKey
    private int id;
    private Date date;
    private int slot;
    private SubjectOfClassModel subjectOfClassModel;

    public LectureModel() {
    }

    public LectureModel(int id, Date date, int slot, SubjectOfClassModel subjectOfClassModel) {
        this.id = id;
        this.date = date;
        this.slot = slot;
        this.subjectOfClassModel = subjectOfClassModel;
    }

    public static LectureModel create(int id, Date date, int slot, SubjectOfClassModel subjectOfClassModel) {
        LectureModel lecture = new LectureModel();
        lecture.id = id;
        lecture.date = date;
        lecture.slot = slot;
        lecture.subjectOfClassModel = subjectOfClassModel;

        return lecture;
    }

    public static LectureModel createWithoutId(Date date, int slot, SubjectOfClassModel subjectOfClassModel) {
        LectureModel lecture = new LectureModel();
        DBContext dbContext = DBContext.getInst();
        lecture.id = dbContext.getMaxLectureId() + 1;
        lecture.date = date;
        lecture.slot = slot;
        lecture.subjectOfClassModel = subjectOfClassModel;

        return lecture;
    }

    public SubjectOfClassModel getSubjectOfClassModel() {
        return subjectOfClassModel;
    }

    public void setSubjectOfClassModel(SubjectOfClassModel subjectOfClassModel) {
        this.subjectOfClassModel = subjectOfClassModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
