package com.example.bonz.uniap_fake.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.StudentModel;
import com.example.bonz.uniap_fake.model.StudentOfClassModel;
import com.example.bonz.uniap_fake.other.ListViewClassAdapter;
import com.example.bonz.uniap_fake.other.ListViewStudentAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentOfClassActivity extends AppCompatActivity {

    private ListView listView;
    private List<StudentOfClassModel> studentOfClassList;
    private DBContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_of_class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.activity_title_view_students));

        listView = (ListView) findViewById(R.id.lv_students);
        int id = getIntent().getIntExtra("subclassId", 0);
        dbContext = DBContext.getInst();
        studentOfClassList = dbContext.getStudentOfClassesBySubOfClassId(id);
        if(studentOfClassList == null)
            studentOfClassList = new ArrayList<>();
        ListViewStudentAdapter listViewStudentAdapter = new ListViewStudentAdapter(this, studentOfClassList);
        listView.setAdapter(listViewStudentAdapter);

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
