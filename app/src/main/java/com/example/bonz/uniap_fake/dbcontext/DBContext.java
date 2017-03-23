package com.example.bonz.uniap_fake.dbcontext;

import com.example.bonz.uniap_fake.model.AccountModel;
import com.example.bonz.uniap_fake.model.AttendanceModel;
import com.example.bonz.uniap_fake.model.ClassModel;
import com.example.bonz.uniap_fake.model.LectureModel;
import com.example.bonz.uniap_fake.model.NewsModel;
import com.example.bonz.uniap_fake.model.SemesterModel;
import com.example.bonz.uniap_fake.model.StudentOfClassModel;
import com.example.bonz.uniap_fake.model.StudentModel;
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
    private Realm realm;

    public DBContext() {
        realm = Realm.getDefaultInstance();
    }

    private static DBContext inst;

    public static DBContext getInst() {
        if (inst == null) {
            return new DBContext();
        }
        return inst;
    }

    //==============================================================================================
    //region AccountModel
    //addAccount or updateAccount
    public void addAccount(AccountModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public int getMaxAccountId() {
        try{
            return realm.where(AccountModel.class).max("id").intValue();
        } catch(Exception ex){}
        return 0;
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
    //endregion

    //==============================================================================================
    //region NewsModel
    //addNews or updateNews
    public void addNews(NewsModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public int getMaxNewId() {
        try{
            return realm.where(NewsModel.class).max("id").intValue();
        } catch(Exception ex){}
        return 0;
    }

    public List<NewsModel> getAllNews(){
        return realm.where(NewsModel.class).findAll();
    }

    public NewsModel getNewsByID(int id) {
        return realm.where(NewsModel.class).equalTo("id",id).findFirst();
    }

    public void removeSingleNews(int id){
        final NewsModel result = realm.where(NewsModel.class).equalTo("id",id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
    }

    public void deleteAllNews(){
        final RealmResults<NewsModel> result = realm.where(NewsModel.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }
    //endregion

    //==============================================================================================
    //region StudentModel
    public void addStudent(StudentModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public int getMaxStudentId() {
        try{
            return realm.where(StudentModel.class).max("id").intValue();
        } catch(Exception ex){}
        return 0;
    }

    public StudentModel getStudentByAccountID(int id) {
        return realm.where(StudentModel.class).equalTo("accountModel.id",id).findFirst();
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
    //endregion

    //==============================================================================================
    //region TeacherModel
    public void addTeacher(TeacherModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public int getMaxTeacherId() {
        try{
            return realm.where(TeacherModel.class).max("id").intValue();
        } catch(Exception ex){}
        return 0;
    }

    public TeacherModel getTeacherByAccountID(int id) {
        return realm.where(TeacherModel.class).equalTo("accountModel.id",id).findFirst();
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
    //endregion

    //==============================================================================================
    //region ClassModel
    public void addClass(ClassModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public int getMaxClassId() {
        try{
            return realm.where(ClassModel.class).max("id").intValue();
        } catch(Exception ex){}
        return 0;
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
    //endregion

    //==============================================================================================
    //region StudentOfClassModel
    public void addStudentOfClass(StudentOfClassModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public int getMaxStudentOfClassId() {
        try{
            return realm.where(StudentOfClassModel.class).max("id").intValue();
        } catch(Exception ex){}
        return 0;
    }

    public List<StudentOfClassModel> getAllStudentOfClass(){
        return realm.where(StudentOfClassModel.class).findAll();
    }

    public List<StudentOfClassModel> getStudentOfClassesBySubOfClassId(int id){
        return realm.where(StudentOfClassModel.class).equalTo("subjectOfClassModel.id", id).findAll();
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
    //endregion

    //==============================================================================================
    //region AttendanceModel
    public void addAttendance(AttendanceModel model){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public int getMaxAttendanceId() {
        try{
            return realm.where(AttendanceModel.class).max("id").intValue();
        } catch(Exception ex){}
        return 0;
    }

    public List<AttendanceModel> getAllAttendance(){
        return realm.where(AttendanceModel.class).findAll();
    }

    public AttendanceModel getAttendanceByID(int id) {
        return realm.where(AttendanceModel.class).equalTo("id",id).findFirst();
    }

    public List<AttendanceModel> getAttendanceBySemesterId(int id) {
        return realm.where(AttendanceModel.class).equalTo("lectureModel.subjectOfClassModel.classModel.semesterModel.id",id).findAll();
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
    //endregion

    //==============================================================================================
    //region SemesterModel
    public void addSemesterModel(SemesterModel model) {
        realm.beginTransaction();;
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public int getMaxSemesterId() {
        try{
            return realm.where(SemesterModel.class).max("id").intValue();
        } catch(Exception ex){}
        return 0;
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
    //endregion

    //==============================================================================================
    //region LectureModel
    public void addLectureModel(LectureModel model) {
        realm.beginTransaction();;
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public int getMaxLectureId() {
        try{
            return realm.where(LectureModel.class).max("id").intValue();
        } catch(Exception ex){}
        return 0;
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
    //endregion

    //==============================================================================================
    //region SubjectOfClassModel
    public void addSubjectOfClassModel(SubjectOfClassModel model) {
        realm.beginTransaction();;
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public int getMaxSubjectOfClassId() {
        try{
            return realm.where(StudentOfClassModel.class).max("id").intValue();
        } catch(Exception ex){}
        return 0;
    }

    public List<SubjectOfClassModel> getAllSubjectOfClassModel() {
        return realm.where(SubjectOfClassModel.class).findAll();
    }

    public List<SubjectOfClassModel> getSubjectOfClassModelsBySemesterId(int semesterId) {
        return realm.where(SubjectOfClassModel.class).equalTo("classModel.semesterModel.id", semesterId).findAll();
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
    //endregion

    //==============================================================================================
    //region SubjectModel
    public void addSubjectModel(SubjectModel model) {
        realm.beginTransaction();;
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
    }

    public int getMaxSubjectId() {
        try{
            return realm.where(SubjectModel.class).max("id").intValue();
        } catch(Exception ex){}
        return 0;
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
    //endregion

}
