package com.example.bonz.uniap_fake.model;

import com.example.bonz.uniap_fake.dbcontext.DBContext;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ninh huong on 3/18/2017.
 */

public class SubjectModel extends RealmObject {
    @PrimaryKey
    private int id;
    private String subjectCode;
    private String subjectName;
    private int numberCredit;

    public static SubjectModel create(int id, String subjectCode, String subjectName, int numberCredit) {
        SubjectModel sub = new SubjectModel();
        sub.id = id;
        sub.subjectCode = subjectCode;
        sub.subjectName = subjectName;
        sub.numberCredit = numberCredit;
        return sub;
    }

    public static SubjectModel createWithoutId(String subjectCode, String subjectName, int numberCredit) {
        SubjectModel sub = new SubjectModel();
        DBContext dbContext = DBContext.getInst();
        sub.id = dbContext.getMaxSubjectId() + 1;
        sub.subjectCode = subjectCode;
        sub.subjectName = subjectName;
        sub.numberCredit = numberCredit;
        return sub;
    }

    public void setNumberCredit(int numberCredit) {
        this.numberCredit = numberCredit;
    }

    public int getNumberCredit() {
        return numberCredit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }
}
