package com.example.bonz.uniap_fake.model;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bonz on 3/14/17.
 */

public class StudentOfClassModel extends RealmObject {
    @PrimaryKey
    private int id;

    private ClassModel classModel;
    private StudentModel studentModel;

    public static StudentOfClassModel create(int id, ClassModel classModel, StudentModel studentModel) {
        StudentOfClassModel studentOfClassModel = new StudentOfClassModel();
        studentOfClassModel.id = id;
        studentOfClassModel.classModel = classModel;
        studentOfClassModel.studentModel = studentModel;
        return studentOfClassModel;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClassModel getClassModel() {
        return classModel;
    }

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }
}
