package com.example.bonz.uniap_fake.model;

import com.example.bonz.uniap_fake.dbcontext.DBContext;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bonz on 3/14/17.
 */

public class TeacherModel extends RealmObject {
    @PrimaryKey
    private int id;

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String rollNumber;
    private String photo;
    private AccountModel accountModel;

    public static TeacherModel create(int id, String firstName, String lastName, String address,
                                       String phoneNumber, String rollNumber, String photo, AccountModel accountModel) {
        TeacherModel teacherModel = new TeacherModel();
        teacherModel.accountModel = accountModel;
        teacherModel.address = address;
        teacherModel.firstName = firstName;
        teacherModel.id = id;
        teacherModel.lastName = lastName;
        teacherModel.photo = photo;
        teacherModel.phoneNumber = phoneNumber;
        teacherModel.rollNumber = rollNumber;
        return teacherModel;
    }

    public static TeacherModel createWithoutId(String firstName, String lastName, String address,
                                       String phoneNumber, String rollNumber, String photo, AccountModel accountModel) {
        TeacherModel teacherModel = new TeacherModel();
        DBContext dbContext = DBContext.getInst();
        teacherModel.id = dbContext.getMaxTeacherId() + 1;
        teacherModel.accountModel = accountModel;
        teacherModel.address = address;
        teacherModel.firstName = firstName;
        teacherModel.lastName = lastName;
        teacherModel.photo = photo;
        teacherModel.phoneNumber = phoneNumber;
        teacherModel.rollNumber = rollNumber;
        return teacherModel;
    }

    public AccountModel getAccountModel() {
        return accountModel;
    }

    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public AccountModel getAccountId() {
        return accountModel;
    }

    public void setAccountId(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }
}
