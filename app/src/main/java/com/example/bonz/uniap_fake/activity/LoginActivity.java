package com.example.bonz.uniap_fake.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.AccountModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText edtAccount, edtPassword;

    //firebase
    DatabaseReference mDatabase;

    //database
    private DBContext dbContext;

    //list DB
    private List<AccountModel> listAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //init
        init();
        //initFirebase
        initFirebase();

        writeNewAccount();
        //Read from the database
        readFirebase();

        dbContext = DBContext.getInst();
        // dbContext.addAccount(AccountModel.create(1, "bbb", "1", 1));
        //dbContext.addAccount(AccountModel.create(2, "aaa", "bbb", 2));
        //dbContext.addAccount(AccountModel.create(3, "aaa", "bbb", 1));
        //dbContext.addTeacher(TeacherModel.create(1,1,"aaaa","sss","sssss","3123","SE232"));
        //dbContext.addTeacher(TeacherModel.create(333,3,"aaaa","sss","sssss","3123","SE232"));
        //dbContext.deleteAllAccount();
        //list = dbContext.getAllAccount();
        listAccount = new ArrayList<AccountModel>();
        //Log.v("data", list.toString());

    }

    private void init() {
        //UI
        btnLogin = (Button) findViewById(R.id.btn_login);
        edtAccount = (EditText) findViewById(R.id.edt_account);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        //event
        btnLogin.setOnClickListener(this);
    }

    private void initFirebase() {
        mDatabase = FirebaseDatabase.getInstance().getReference("root");
    }

    private void writeNewAccount() {

        // mDatabase.child("account").child(String.valueOf(4)).setValue(AccountModel.create(4, "bbb", "1", 1));
    }

    //read Database from Firebase
    private void readFirebase() {
        mDatabase.child("account").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    AccountModel model = ds.getValue(AccountModel.class);
                    listAccount.add(model);
                    //Log.d("test", "Value is: " + ds.getValue(AccountModel.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login:
                onClickLogin(v);
                break;
            default:
                break;
        }
    }

    //function onClick
    private void onClickLogin(View v) {
        if (!edtAccount.getText().toString().equals("") && !edtPassword.getText().toString().equals("")) {
            if(checkLogin()){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else {
                Snackbar.make(v, "Account & Password Incorrect !!!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        } else {
            Snackbar.make(v, "Enter Account & Password please !!!", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }


    }

    //Check login
    private boolean checkLogin() {
        for (AccountModel model : listAccount) {
            if (edtAccount.getText().toString().equals(model.getUsername())
                    && edtPassword.getText().toString().equals(model.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
