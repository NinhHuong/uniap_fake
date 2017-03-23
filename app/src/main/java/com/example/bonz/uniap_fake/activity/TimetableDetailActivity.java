package com.example.bonz.uniap_fake.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.ClassModel;
import com.example.bonz.uniap_fake.model.LectureModel;
import com.example.bonz.uniap_fake.model.SubjectModel;
import com.example.bonz.uniap_fake.model.SubjectOfClassModel;
import com.example.bonz.uniap_fake.model.TeacherModel;

import java.text.SimpleDateFormat;

public class TimetableDetailActivity extends AppCompatActivity {


    TextView subjectTv, codeTv, teacherTv, dateTv, classTv, slotTv;
    private DBContext dbContext;
    private int subjectId;
    private LectureModel lectureModel;
    private SubjectModel subjectModel;
    private ClassModel classModel;
    private TeacherModel teacherModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        subjectTv = (TextView) findViewById(R.id.subjectTextView);
        codeTv = (TextView) findViewById(R.id.codeTextView);
        dateTv = (TextView) findViewById(R.id.dateTextView);
        classTv = (TextView) findViewById(R.id.classTextView);
        slotTv = (TextView) findViewById(R.id.slotTextView);
        teacherTv = (TextView) findViewById(R.id.teacherTextView);

        dbContext = DBContext.getInst();

        Intent intent = getIntent();
        subjectId = intent.getIntExtra("lectureId", 0);

        lectureModel = dbContext.getLectureModelById(subjectId);
        SubjectOfClassModel subjectOfClassModel = lectureModel.getSubjectOfClassModel();
        subjectModel = subjectOfClassModel.getSubjectModel();
        classModel = subjectOfClassModel.getClassModel();
        teacherModel = subjectOfClassModel.getTeacherModel();

        subjectTv.setText(subjectModel.getSubjectName());
        codeTv.setText(subjectModel.getSubjectCode());
        dateTv.setText(new SimpleDateFormat("dd/MM/yyyy").format(lectureModel.getDate()));
        classTv.setText(classModel.getClassName());
        teacherTv.setText(teacherModel.getFirstName() + teacherModel.getLastName());
        slotTv.setText(String.valueOf(lectureModel.getSlot()));
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
