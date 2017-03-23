package com.example.bonz.uniap_fake.model;

import com.example.bonz.uniap_fake.dbcontext.DBContext;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bonz on 3/14/17.
 */

public class StudentOfClassModel extends RealmObject {
    @PrimaryKey
    private int id;

    private SubjectOfClassModel subjectOfClassModel;
    private StudentModel studentModel;

    public static StudentOfClassModel create(int id, SubjectOfClassModel subjectOfClassModel, StudentModel studentModel) {
        StudentOfClassModel studentOfClassModel = new StudentOfClassModel();
        studentOfClassModel.id = id;
        studentOfClassModel.subjectOfClassModel = subjectOfClassModel;
        studentOfClassModel.studentModel = studentModel;
        return studentOfClassModel;
    }

    public static StudentOfClassModel createWithoutId(SubjectOfClassModel subjectOfClassModel, StudentModel studentModel) {
        StudentOfClassModel studentOfClassModel = new StudentOfClassModel();
        DBContext dbContext = DBContext.getInst();
        studentOfClassModel.id = dbContext.getMaxStudentOfClassId() + 1;
        studentOfClassModel.subjectOfClassModel = subjectOfClassModel;
        studentOfClassModel.studentModel = studentModel;
        return studentOfClassModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SubjectOfClassModel getClassModel() {
        return subjectOfClassModel;
    }

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public void setClassModel(SubjectOfClassModel subjectOfClassModel) {
        this.subjectOfClassModel = subjectOfClassModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }
}
