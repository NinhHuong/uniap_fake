package com.example.bonz.uniap_fake.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.AccountModel;
import com.example.bonz.uniap_fake.model.StudentModel;
import com.example.bonz.uniap_fake.model.TeacherModel;

public class ProfileActivity extends AppCompatActivity {

    private TextView txtUsername, txtFirstName, txtLastName, txtRollNumber, txtPhoneNumber, txtAdress;
    private DBContext dbContext;
    private StudentModel studentModel;
    private TeacherModel teacherModel;
    private AccountModel accountModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dbContext = DBContext.getInst();
        accountModel = dbContext.getAllAccount().get(0);
        if (accountModel.getRoll() == 1) {
            studentModel = dbContext.getStudentByAccountID(accountModel.getId());
        } else if (accountModel.getRoll() == 2) {
            teacherModel = dbContext.getTeacherByAccountID(accountModel.getId());
        }

        init();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //init()
    private void init() {
        txtUsername = (TextView) findViewById(R.id.txt_username_profile);
        txtFirstName = (TextView) findViewById(R.id.txt_firstname_profile);
        txtLastName = (TextView) findViewById(R.id.txt_lastname_profile);
        txtRollNumber = (TextView) findViewById(R.id.txt_rollnumber_profile);
        txtPhoneNumber = (TextView) findViewById(R.id.txt_phonenumber_profile);
        txtAdress = (TextView) findViewById(R.id.txt_address_profile);

        if (accountModel.getRoll() == 1) {
            txtUsername.setText(studentModel.getAccountModel().getUsername());
            txtFirstName.setText(studentModel.getFirstName());
            txtLastName.setText(studentModel.getLastName());
            txtAdress.setText(studentModel.getAddress());
            txtRollNumber.setText(studentModel.getRollNumber());
            txtPhoneNumber.setText(studentModel.getPhoneNumber());
        } else if (accountModel.getRoll() == 2) {
            txtUsername.setText(teacherModel.getAccountModel().getUsername());
            txtFirstName.setText(teacherModel.getFirstName());
            txtLastName.setText(teacherModel.getLastName());
            txtAdress.setText(teacherModel.getAddress());
            txtRollNumber.setText(teacherModel.getRollNumber());
            txtPhoneNumber.setText(teacherModel.getPhoneNumber());
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
