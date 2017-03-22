package com.example.bonz.uniap_fake.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.AttendanceModel;
import com.example.bonz.uniap_fake.model.LectureModel;
import com.example.bonz.uniap_fake.other.Constanst;
import com.example.bonz.uniap_fake.other.ListViewApdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TakeAttendanceActivity extends AppCompatActivity {

    DateFormat KEY_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    //database
    private DBContext dbContext;

    private List<AttendanceModel> attendanceModelList;
    private boolean isInTime;
    private LectureModel lectureModel;

    private TextView tvClass, tvSlot;
    private Button btnEdit, btnSave;
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
        tvClass.setText(getResources().getString(R.string.txt_class) + ": " + lectureModel.getSubjectOfClassModel().getClassModel().getClassName());
        tvSlot.setText(getResources().getString(R.string.txt_slot) + ": " + lectureModel.getSlot());
//        isInTime = isInTime(lectureModel.getSlot());
        isInTime = true;
        btnEdit.setEnabled(isInTime);
        boolean isEdited = false;
        attendanceModelList = dbContext.getAttendanceByLectureId(lectureId);
        for (AttendanceModel attendanceModel : attendanceModelList) {
            if (attendanceModel.getIsAttendance() == true) {
                isEdited = true;
                break;
            }
        }
        if (isEdited) {
            btnEdit.setVisibility(View.VISIBLE);
            btnSave.setEnabled(false);
            apdapter = new ListViewApdapter(this, attendanceModelList, false);
        } else {
            apdapter = new ListViewApdapter(this, attendanceModelList, true);
        }
        listView.setAdapter(apdapter);

        //list
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSave.setEnabled(true);
                btnEdit.setEnabled(false);
                apdapter = new ListViewApdapter(TakeAttendanceActivity.this, attendanceModelList, isInTime);
                listView.setAdapter(apdapter);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEdit.setEnabled(true);
                btnEdit.setVisibility(View.VISIBLE);
                btnSave.setEnabled(false);
                apdapter = new ListViewApdapter(TakeAttendanceActivity.this, attendanceModelList, false);
                listView.setAdapter(apdapter);
            }
        });
    }

    private void init() {
        tvClass = (TextView) findViewById(R.id.tvClass);
        tvSlot = (TextView) findViewById(R.id.tvSlot);
        btnEdit = (Button) findViewById(R.id.btn_edit);
        listView = (ListView) findViewById(R.id.list_student);
        btnSave = (Button) findViewById(R.id.btn_save);
    }

    private boolean isInTime() {
        //only take attendance when this time in slot
        Date date = lectureModel.getDate();


        long current = (new Date()).getTime();
        long time;
        switch (lectureModel.getSlot()) {
            case 1:
                date.setTime(Constanst.KEY_SLOT_1);
                time = date.getTime();
                return current >= time && current <= time + Constanst.KEY_TIME_ALLOWANCE + Constanst.KEY_SLOT_DURATION;
            case 2:
                date.setTime(Constanst.KEY_SLOT_2);
                time = date.getTime();
                return current >= time && current <= time + Constanst.KEY_TIME_ALLOWANCE + Constanst.KEY_SLOT_DURATION;
            case 3:
                date.setTime(Constanst.KEY_SLOT_3);
                time = date.getTime();
                return current >= time && current <= time + Constanst.KEY_TIME_ALLOWANCE + Constanst.KEY_SLOT_DURATION;
            case 4:
                date.setTime(Constanst.KEY_SLOT_4);
                time = date.getTime();
                return current >= time && current <= time + Constanst.KEY_TIME_ALLOWANCE + Constanst.KEY_SLOT_DURATION;
            case 5:
                date.setTime(Constanst.KEY_SLOT_5);
                time = date.getTime();
                return current >= time && current <= time + Constanst.KEY_TIME_ALLOWANCE + Constanst.KEY_SLOT_DURATION;
            case 6:
                date.setTime(Constanst.KEY_SLOT_6);
                time = date.getTime();
                return current >= time && current <= time + Constanst.KEY_TIME_ALLOWANCE + Constanst.KEY_SLOT_DURATION;
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
