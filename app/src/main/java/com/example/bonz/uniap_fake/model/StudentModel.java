package com.example.bonz.uniap_fake.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.bonz.uniap_fake.dbcontext.DBContext;

import java.io.ByteArrayOutputStream;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bonz on 3/14/17.
 */

public class StudentModel extends RealmObject {
    @PrimaryKey
    private int id;

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String rollNumber;
    private String photo;
    private AccountModel accountModel;

    public static StudentModel create(int id, String firstName, String lastName, String address,
                                      String phoneNumber, String rollNumber, String photo, AccountModel accountModel) {
        StudentModel studentModel = new StudentModel();
        studentModel.accountModel = accountModel;
        studentModel.address = address;
        studentModel.firstName = firstName;
        studentModel.id = id;
        studentModel.lastName = lastName;
        studentModel.phoneNumber = phoneNumber;
        studentModel.rollNumber = rollNumber;
        studentModel.photo = photo;
        return studentModel;
    }

    public static StudentModel createWithoutId(String firstName, String lastName, String address,
                                      String phoneNumber, String rollNumber, String photo, AccountModel accountModel) {
        StudentModel studentModel = new StudentModel();
        DBContext dbContext = DBContext.getInst();
        studentModel.accountModel = accountModel;
        studentModel.address = address;
        studentModel.firstName = firstName;
        studentModel.id = dbContext.getMaxStudentId() + 1;
        studentModel.lastName = lastName;
        studentModel.phoneNumber = phoneNumber;
        studentModel.rollNumber = rollNumber;
        studentModel.photo = photo;
        return studentModel;
    }

    public AccountModel getAccountId() {
        return accountModel;
    }

    public void setAccountId(AccountModel accountId) {
        this.accountModel = accountId;
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

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}
