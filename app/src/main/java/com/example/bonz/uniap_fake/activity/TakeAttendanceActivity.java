package com.example.bonz.uniap_fake.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.AttendanceModel;
import com.example.bonz.uniap_fake.model.ClassModel;
import com.example.bonz.uniap_fake.model.LectureModel;
import com.example.bonz.uniap_fake.model.SemesterModel;
import com.example.bonz.uniap_fake.model.StudentModel;
import com.example.bonz.uniap_fake.model.TeacherModel;
import com.example.bonz.uniap_fake.other.ListViewApdapter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TakeAttendanceActivity extends AppCompatActivity {
    //database
    private DBContext dbContext;

    private List<StudentModel> studentModelList;
    private List<AttendanceModel> attendanceModelList;
    private boolean isTakeAvaiable;
    private LectureModel lectureModel;

    private TextView tvClass;
    private Button btnTake, btnSave;
    private ListViewApdapter apdapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        //get para
        int lectureId = getIntent().getIntExtra("lectureId", -1);
        dbContext = DBContext.getInst();
        lectureModel = dbContext.getLectureModelById(lectureId);
        //update data
        tvClass.setText("Class: " + lectureModel.getClassModel().getClassName());
        isTakeAvaiable = isTakeAttendanceAvaiable(lectureModel.getSlot());
        btnTake.setEnabled(isTakeAvaiable);
        btnSave.setVisibility(View.INVISIBLE);
        attendanceModelList = dbContext.getAttendanceByLectureId(lectureId);
        studentModelList = new ArrayList<>();
        for(int i=0; i<attendanceModelList.size(); i++) {
            studentModelList.add(attendanceModelList.get(i).getStudentModel());
        }
        apdapter = new ListViewApdapter(this, studentModelList, false);
        listView.setAdapter(apdapter);

        //list
        btnTake.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                btnSave.setVisibility(View.VISIBLE);
                apdapter = new ListViewApdapter(TakeAttendanceActivity.this, studentModelList, true);
                listView.setAdapter(apdapter);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //
                apdapter = new ListViewApdapter(TakeAttendanceActivity.this, studentModelList, false);
                listView.setAdapter(apdapter);
                btnTake.setText("Edit");
            }
        });
    }

    private void init() {
        tvClass = (TextView) findViewById(R.id.tvClass);
        btnTake = (Button) findViewById(R.id.btnTake);
        listView = (ListView) findViewById(R.id.list_student);
        btnSave = (Button) findViewById(R.id.btn_save);
    }

    private boolean isTakeAttendanceAvaiable(int slot) {
        //only take attendance when this time in slot
        Date date = new Date();
        int second = date.getHours() * 3600 + date.getMinutes() * 60 + date.getSeconds();
        int start, end;
        switch (slot){
            case 1:
                start = 7 * 3600 + 30 * 60;
                end = 9 * 3600;
                if(second <= end && second >= start)
                    return true;
                break;
            case 2:
                start = 9 * 3600 + 10 * 60;
                end = 10 * 3600 + 40 * 60;
                if(second <= end && second >= start)
                    return true;
                break;
            case 3:
                start = 10 * 3600 + 50 * 60;
                end = 12 * 3600 + 20 * 60;
                if(second <= end && second >= start)
                    return true;
                break;
            case 4:
                start = 12 * 3600 + 50 * 60;
                end = 14 * 3600 + 20 * 60;
                if(second <= end && second >= start)
                    return true;
                break;
            case 5:
                start = 14 * 3600 + 30 * 60;
                end = 16 * 3600;
                if(second <= end && second >= start)
                    return true;
                break;
            case 6:
                start = 16 * 3600 + 10 * 60;
                end = 17 * 3600 + 40 * 60;
                if(second <= end && second >= start)
                    return true;
                break;
        }
        return false;
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
