package com.example.bonz.uniap_fake.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bonz on 3/14/17.
 */

public class ClassModel extends RealmObject {
    @PrimaryKey
    private int id;

    private String className;
    private SemesterModel semesterModel;

    public static ClassModel create(int id, String className, SemesterModel semesterModel) {
        ClassModel classModel = new ClassModel();
        classModel.id = id;
        classModel.className = className;
        classModel.semesterModel = semesterModel;
        return classModel;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public SemesterModel getSemesterModel() {
        return semesterModel;
    }

    public void setSemesterModel(SemesterModel semesterModel) {
        this.semesterModel = semesterModel;
    }
}
