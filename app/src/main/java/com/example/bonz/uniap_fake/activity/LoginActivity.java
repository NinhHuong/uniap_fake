package com.example.bonz.uniap_fake.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.AccountModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogin;
    private EditText edtAccount, edtPassword;

    //database
    private DBContext dbContext;

    //list DB
    private List<AccountModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);

        dbContext = DBContext.getInst();
        dbContext.addAccount(AccountModel.create(1,"bbb","1",1));
        dbContext.addAccount(AccountModel.create(2,"aaa","bbb",2));
        dbContext.addAccount(AccountModel.create(3,"aaa","bbb",1));
        //dbContext.addTeacher(TeacherModel.create(1,1,"aaaa","sss","sssss","3123","SE232"));
        //dbContext.addTeacher(TeacherModel.create(333,3,"aaaa","sss","sssss","3123","SE232"));
        //dbContext.deleteAllAccount();
        list = dbContext.getAllAccount();
        Log.v("data", list.toString());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login:
                Log.v("mess", "ok");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
