package com.example.bonz.uniap_fake.model;

import com.example.bonz.uniap_fake.dbcontext.DBContext;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bonz on 3/14/17.
 */

public class AccountModel extends RealmObject {
    @PrimaryKey
    private int id;

    private String username;
    private String password;
    private int roll;

    public static AccountModel create(int id, String username, String password, int roll) {
        AccountModel accountModel = new AccountModel();
        accountModel.id = id;
        accountModel.username = username;
        accountModel.password = password;
        accountModel.roll = roll;
        return  accountModel;
    }

    public static AccountModel createWithoutId(String username, String password, int roll) {
        AccountModel accountModel = new AccountModel();
        DBContext dbContext = DBContext.getInst();
        accountModel.id = dbContext.getMaxAccountId() + 1;
        accountModel.username = username;
        accountModel.password = password;
        accountModel.roll = roll;
        return  accountModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
