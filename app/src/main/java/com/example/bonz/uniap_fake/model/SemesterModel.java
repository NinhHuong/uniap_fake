package com.example.bonz.uniap_fake.model;

import com.example.bonz.uniap_fake.dbcontext.DBContext;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by HuongNTM5 on 3/15/2017.
 */

public class SemesterModel extends RealmObject {
    @PrimaryKey
    private int id;
    private String semesterName;
    private String startDate;
    private String endDate;

    public static SemesterModel create(int id, String semesterName, String startDate, String endDate) {
        SemesterModel semesterModel = new SemesterModel();
        semesterModel.id = id;
        semesterModel.semesterName = semesterName;
        semesterModel.startDate = startDate;
        semesterModel.endDate = endDate;
        return semesterModel;
    }

    public static SemesterModel createWithoutId(String semesterName, String startDate, String endDate) {
        SemesterModel semesterModel = new SemesterModel();
        DBContext dbContext = DBContext.getInst();
        semesterModel.id = dbContext.getMaxSemesterId() + 1;
        semesterModel.semesterName = semesterName;
        semesterModel.startDate = startDate;
        semesterModel.endDate = endDate;
        return semesterModel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
