package com.example.bonz.uniap_fake.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bonz on 3/14/17.
 */

public class TeacherModel extends RealmObject {
    @PrimaryKey
    private int id;

    private int accountId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String rollNumber;
    private String photo;

    public static TeacherModel create( int id, int accountId, String firstName, String lastName, String address,
                                       String phoneNumber, String rollNumber, String photo) {
        TeacherModel teacherModel = new TeacherModel();
        teacherModel.accountId = accountId;
        teacherModel.address = address;
        teacherModel.firstName = firstName;
        teacherModel.id = id;
        teacherModel.lastName = lastName;
        teacherModel.photo = photo;
        teacherModel.phoneNumber = phoneNumber;
        teacherModel.rollNumber = rollNumber;
        return teacherModel;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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
