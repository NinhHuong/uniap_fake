package com.example.bonz.uniap_fake.dbcontext;

import com.example.bonz.uniap_fake.model.AccountModel;
import com.example.bonz.uniap_fake.model.AttendanceModel;
import com.example.bonz.uniap_fake.model.ClassModel;
import com.example.bonz.uniap_fake.model.LectureModel;
import com.example.bonz.uniap_fake.model.SemesterModel;
import com.example.bonz.uniap_fake.model.StudentModel;
import com.example.bonz.uniap_fake.model.StudentOfClassModel;
import com.example.bonz.uniap_fake.model.SubjectModel;
import com.example.bonz.uniap_fake.model.SubjectOfClassModel;
import com.example.bonz.uniap_fake.model.TeacherModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by bonz on 3/14/17.
 */

public class DBContext {
    private static DBContext inst;
    public Realm realm;

    public DBContext() {
        realm = Realm.getDefaultInstance();
    }

    public static DBContext getInst() {
        if (inst == null) {
            return new DBContext();
        }
        return inst;
    }

    //==============================================================================================
    //AccountModel
    //addAccount or updateAccount
    public void addAccount(AccountModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public List<AccountModel> getAllAccount(){
        return realm.where(AccountModel.class).findAll();
    }

    public AccountModel getAccountByID(int id) {
        return realm.where(AccountModel.class).equalTo("id",id).findFirst();
    }

    public void removeSingleAccount(int id){
        final AccountModel result = realm.where(AccountModel.class).equalTo("id",id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
    }

    public void deleteAllAccount(){
        final RealmResults<AccountModel> result = realm.where(AccountModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }


    //==============================================================================================
    //StudentModel
    public void addStudent(StudentModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public List<StudentModel> getAllStudent(){
        return realm.where(StudentModel.class).findAll();
    }

    public StudentModel getStudentByID(int id) {
        return realm.where(StudentModel.class).equalTo("id",id).findFirst();
    }

    public void removeSingleStudent(int id){
        final StudentModel result = realm.where(StudentModel.class).equalTo("id",id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
    }

    public void deleteAllStudent(){
        final RealmResults<StudentModel> result = realm.where(StudentModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }

    //==============================================================================================
    //TeacherModel
    public void addTeacher(TeacherModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public List<TeacherModel> getAllTeacher(){
        return realm.where(TeacherModel.class).findAll();
    }

    public TeacherModel getTeacherByID(int id) {
        return realm.where(TeacherModel.class).equalTo("id",id).findFirst();
    }

    public void removeSingleTeacher(int id){
        final TeacherModel result = realm.where(TeacherModel.class).equalTo("id",id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
    }

    public void deleteAllTeacher(){
        final RealmResults<TeacherModel> result = realm.where(TeacherModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }

    //==============================================================================================
    //ClassModel
    public void addClass(ClassModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public List<ClassModel> getAllClass(){
        return realm.where(ClassModel.class).findAll();
    }

    public ClassModel getClassByID(int id) {
        return realm.where(ClassModel.class).equalTo("id",id).findFirst();
    }

    public void removeSingleClass(int id){
        final ClassModel result = realm.where(ClassModel.class).equalTo("id",id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
    }

    public void deleteAllClass(){
        final RealmResults<ClassModel> result = realm.where(ClassModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }

    //==============================================================================================
    //StudentOfClassModel
    public void addStudentOfClass(StudentOfClassModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public List<StudentOfClassModel> getAllStudentOfClass(){
        return realm.where(StudentOfClassModel.class).findAll();
    }

    public StudentOfClassModel getStudentOfClassByID(int id) {
        return realm.where(StudentOfClassModel.class).equalTo("id",id).findFirst();
    }

    public void removeSingleStudentOfClassModel(int id){
        final StudentOfClassModel result = realm.where(StudentOfClassModel.class).equalTo("id",id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
    }

    public void deleteAllStudentOfClass(){
        final RealmResults<StudentOfClassModel> result = realm.where(StudentOfClassModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }

    //==============================================================================================
    //AttendanceModel
    public void addAttendance(AttendanceModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public List<AttendanceModel> getAllAttendance(){
        return realm.where(AttendanceModel.class).findAll();
    }

    public AttendanceModel getAttendanceByID(int id) {
        return realm.where(AttendanceModel.class).equalTo("id",id).findFirst();
    }

    public List<AttendanceModel> getAttendanceByLectureId(int id) {
        return realm.where(AttendanceModel.class).equalTo("lectureModel.id",id).findAll();
    }

    public void removeSingleAttendance(int id){
        final AttendanceModel result = realm.where(AttendanceModel.class).equalTo("id",id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
    }

    public void deleteAllAttendance(){
        final RealmResults<AttendanceModel> result = realm.where(AttendanceModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }

    //==============================================================================================
    //SemesterModel
    public void addSemesterModel(SemesterModel model) {
        realm.beginTransaction();;
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public List<SemesterModel> getAllSemesterModel() {
        return realm.where(SemesterModel.class).findAll();
    }

    public SemesterModel getSemesterModelById(int id) {
        return realm.where(SemesterModel.class).equalTo("id", id).findFirst();
    }

    public void removeSingleSemesterModel(int id) {
        final SemesterModel result = realm.where(SemesterModel.class).equalTo("id", id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
    }

    public void deleteAllSemesterModel() {
        final RealmResults<SemesterModel> result = realm.where(SemesterModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }

    //==============================================================================================
    //LectureModel
    public void addLectureModel(LectureModel model) {
        realm.beginTransaction();;
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public List<LectureModel> getAllLectureModel() {
        return realm.where(LectureModel.class).findAll();
    }

    public LectureModel getLectureModelById(int id) {
        return realm.where(LectureModel.class).equalTo("id", id).findFirst();
    }

    public List<LectureModel> getLectureModelByDate(String date) {
        return realm.where(LectureModel.class).equalTo("date", date).findAll();
    }

    public void removeSingleLectureModel(int id) {
        final LectureModel result = realm.where(LectureModel.class).equalTo("id", id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
    }

    public void deleteAllLectureModel() {
        final RealmResults<LectureModel> result = realm.where(LectureModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }

    //==============================================================================================
    //SubjectOfClassModel
    public void addSubjectOfClassModel(SubjectOfClassModel model) {
        realm.beginTransaction();;
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public List<SubjectOfClassModel> getAllSubjectOfClassModel() {
        return realm.where(SubjectOfClassModel.class).findAll();
    }

    public SubjectOfClassModel getSubjectOfClassModelById(int id) {
        return realm.where(SubjectOfClassModel.class).equalTo("id", id).findFirst();
    }

    public void removeSingleSubjectOfClassModel(int id) {
        final SubjectOfClassModel result = realm.where(SubjectOfClassModel.class).equalTo("id", id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
    }

    public void deleteAllSubjectOfClassModel() {
        final RealmResults<SubjectOfClassModel> result = realm.where(SubjectOfClassModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }

    //==============================================================================================
    //SubjectModel
    public void addSubjectModel(SubjectModel model) {
        realm.beginTransaction();;
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public List<SubjectModel> getAllSubjectModel() {
        return realm.where(SubjectModel.class).findAll();
    }

    public SubjectModel getSubjectModelById(int id) {
        return realm.where(SubjectModel.class).equalTo("id", id).findFirst();
    }

    public void removeSingleSubjectModel(int id) {
        final SubjectModel result = realm.where(SubjectModel.class).equalTo("id", id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
    }

    public void deleteAllSubjectModel() {
        final RealmResults<SubjectModel> result = realm.where(SubjectModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }


}
