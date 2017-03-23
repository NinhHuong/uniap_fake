package com.example.bonz.uniap_fake.model;

import com.example.bonz.uniap_fake.dbcontext.DBContext;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by HuongNTM5 on 3/17/2017.
 */

public class SubjectOfClassModel extends RealmObject {
    @PrimaryKey
    private int id;
    private SubjectModel subjectModel;
    private ClassModel classModel;
    private TeacherModel teacherModel;

    public static SubjectOfClassModel create(int id, SubjectModel subjectModel, ClassModel classModel, TeacherModel teacherModel) {
        SubjectOfClassModel sub = new SubjectOfClassModel();
        sub.id = id;
        sub.subjectModel= subjectModel;
        sub.classModel = classModel;
        sub.teacherModel = teacherModel;
        return sub;
    }

    public static SubjectOfClassModel createWithoutId(SubjectModel subjectModel, ClassModel classModel, TeacherModel teacherModel) {
        SubjectOfClassModel sub = new SubjectOfClassModel();
        DBContext dbContext = DBContext.getInst();
        sub.id = dbContext.getMaxSubjectOfClassId() + 1;
        sub.subjectModel= subjectModel;
        sub.classModel = classModel;
        sub.teacherModel = teacherModel;
        return sub;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
    }

    public void setTeacherModel(TeacherModel teacherModel) {
        this.teacherModel = teacherModel;
    }

    public int getId() {
        return id;
    }

    public void setSubjectModel(SubjectModel subjectModel) {
        this.subjectModel = subjectModel;
    }

    public SubjectModel getSubjectModel() {
        return subjectModel;
    }

    public ClassModel getClassModel() {
        return classModel;
    }

    public TeacherModel getTeacherModel() {
        return teacherModel;
    }
}
