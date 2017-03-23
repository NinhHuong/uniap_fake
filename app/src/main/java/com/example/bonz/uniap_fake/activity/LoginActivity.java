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
import com.example.bonz.uniap_fake.model.AttendanceModel;
import com.example.bonz.uniap_fake.model.ClassModel;
import com.example.bonz.uniap_fake.model.LectureModel;
import com.example.bonz.uniap_fake.model.NewsModel;
import com.example.bonz.uniap_fake.model.SemesterModel;
import com.example.bonz.uniap_fake.model.StudentModel;
import com.example.bonz.uniap_fake.model.StudentOfClassModel;
import com.example.bonz.uniap_fake.model.SubjectModel;
import com.example.bonz.uniap_fake.model.SubjectOfClassModel;
import com.example.bonz.uniap_fake.model.TeacherModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

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
        //load Account From Firebase
        loadAccountFromFirebase();


        //DBContext.getInst
        dbContext = DBContext.getInst();
        dbContext.deleteAllClass();
        dbContext.deleteAllSemesterModel();


        //dbContext.addAccount(AccountModel.create(1, "bbb", "1", 1));
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

        //mDatabase.child("account").child(id).setValue(AccountModel.create(4, "bbb", "1", 1));
    }

    //read Database from Firebase
    private void loadAccountFromFirebase() {
        mDatabase.child("account").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    AccountModel model = ds.getValue(AccountModel.class);
                    listAccount.add(model);
                    Log.d("test", "Value is: " + ds.getValue(AccountModel.class));
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
            if (checkLogin()) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
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
                dbContext.deleteAllAccount();
                dbContext.addAccount(model);
                loadDataFromFirebase(model.getId(), model.getRoll());
                //Log.v("acc",dbContext.getAllAccount().toString());
                return true;
            }
        }
        return false;
    }

    // Load Data After Login Success
    private void loadDataFromFirebase(final int accountId, int rollAccount) {
        final int accId =accountId;
        mDatabase.child("news").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    NewsModel model = ds.getValue(NewsModel.class);
                    dbContext.addNews(model);
                    //listAccount.add(model);
                    //Log.d("test", "Value is: " + ds.getValue(NewsModel.class));
                }
//                List<NewsModel> listNews = new ArrayList<NewsModel>();
//                listNews = dbContext.getAllNews();
                // Log.d("data", "Value is: " + listNews.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (rollAccount == 2) {
            mDatabase.child("teacher").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        //Log.d("testTeacher", "Value is: " + ds.toString());
                        TeacherModel model = ds.getValue(TeacherModel.class);
                        int getAccountId = 0;
                        try {
                            getAccountId = Integer.parseInt(model.getAccountId().toString());
                        }catch (Exception e){

                        }
                        if (accId == getAccountId) {
                            dbContext.deleteAllTeacher();
                            dbContext.addTeacher(model);
                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else if (rollAccount == 1) {
            mDatabase.child("student").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        StudentModel model = ds.getValue(StudentModel.class);
                        int getAccountId = 0;
                        try {
                            getAccountId = Integer.parseInt(model.getAccountId().toString());
                        }catch (Exception e){

                        }
                        if (accId == getAccountId) {
                            dbContext.deleteAllStudent();
                            dbContext.addStudent(model);
                        }
                    }
                    //Log.d("testStudent", "Value is: " + dbContext.getAllStudent().toString());

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        mDatabase.child("attendance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    AttendanceModel model = ds.getValue(AttendanceModel.class);
                    dbContext.deleteAllAttendance();
                    dbContext.addAttendance(model);

                }
                //Log.d("testdata", "Value is: " + dbContext.getAllAttendance().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("lecture").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    LectureModel model = ds.getValue(LectureModel.class);
                    dbContext.deleteAllLectureModel();
                    dbContext.addLectureModel(model);

                }
                //Log.d("testdata", "Value is: " + dbContext.getAllLectureModel().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("subjectOfClass").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    SubjectOfClassModel model = ds.getValue(SubjectOfClassModel.class);
                    dbContext.deleteAllSubjectOfClassModel();
                    dbContext.addSubjectOfClassModel(model);

                }
                //Log.d("testdata", "Value is: " + dbContext.getAllSubjectOfClassModel().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("subject").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    SubjectModel model = ds.getValue(SubjectModel.class);
                    dbContext.deleteAllSubjectModel();
                    dbContext.addSubjectModel(model);

                }
                //Log.d("testdata", "getAllSubjectModel is: " + dbContext.getAllSubjectModel().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("studentOfClass").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    StudentOfClassModel model = ds.getValue(StudentOfClassModel.class);
                    dbContext.deleteAllStudentOfClass();
                    dbContext.addStudentOfClass(model);

                }
                //Log.d("testdata", "getAllStudentOfClass is: " + dbContext.getAllStudentOfClass().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("class").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ClassModel model = ds.getValue(ClassModel.class);
                    dbContext.deleteAllClass();
                    dbContext.addClass(model);

                }
                //Log.d("testStudent", "Value is: " + dbContext.getAllClass().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("semester").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    SemesterModel model = ds.getValue(SemesterModel.class);
                    dbContext.deleteAllSemesterModel();
                    dbContext.addSemesterModel(model);

                }
                //Log.d("testStudent", "Value is: " + dbContext.getAllSemesterModel().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
