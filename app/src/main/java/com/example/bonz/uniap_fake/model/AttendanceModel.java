package com.example.bonz.uniap_fake.model;

import com.example.bonz.uniap_fake.dbcontext.DBContext;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bonz on 3/14/17.
 */

public class AttendanceModel extends RealmObject {
    @PrimaryKey
    private int id;
    private boolean isAttendance;
    private StudentModel studentModel;
    private LectureModel lectureModel;

    public static AttendanceModel create(int id, boolean isAttendance, StudentModel student, LectureModel lectureModel) {
        AttendanceModel attendanceModel = new AttendanceModel();
        attendanceModel.id = id;
        attendanceModel.isAttendance = isAttendance;
        attendanceModel.studentModel = student;
        attendanceModel.lectureModel = lectureModel;
        return attendanceModel;
    }

    public static AttendanceModel createWithoutId(boolean isAttendance, StudentModel student, LectureModel lectureModel) {
        AttendanceModel attendanceModel = new AttendanceModel();
        DBContext dbContext = DBContext.getInst();
        attendanceModel.id = dbContext.getMaxAttendanceId() + 1;
        attendanceModel.isAttendance = isAttendance;
        attendanceModel.studentModel = student;
        attendanceModel.lectureModel = lectureModel;
        return attendanceModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsAttendance() {
        return isAttendance;
    }

    public void setIsAttendance(boolean isAttendance) {
        this.isAttendance = isAttendance;
    }

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public LectureModel getLectureModel() {
        return lectureModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }

    public void setLectureModel(LectureModel lectureModel) {
        this.lectureModel = lectureModel;
    }
}
