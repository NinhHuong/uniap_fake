package com.example.bonz.uniap_fake.other;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.model.StudentModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuongNTM5 on 3/15/2017.
 */

public class ListViewApdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<StudentModel> list;
    private StudentModel model;
    private boolean isTakeAvaiable;

    public ListViewApdapter(Context context, List<StudentModel> list, boolean isTakeAvaiable) {
        if (list != null) {
            this.list = list;
        } else {
            this.list = new ArrayList<>();
        }
        this.isTakeAvaiable = isTakeAvaiable;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = inflater.inflate(R.layout., parent, false);
        View view = inflater.inflate(R.layout.list_item_attendance, parent, false);

        if (view != null) {
            model = list.get(position);
            if (model != null) {
                TextView txtNo = (TextView) view.findViewById(R.id.txt_no);
                TextView txtStudentId = (TextView) view.findViewById(R.id.txt_student_id);
                TextView txtStudentName = (TextView) view.findViewById(R.id.txt_student_name);
                ImageView imgStudentPhoto = (ImageView) view.findViewById(R.id.img_student_photo);
                CheckBox ckbAttendance = (CheckBox) view.findViewById(R.id.ckb_attendance);

                StudentModel stu = list.get(position);
                txtNo.setText(String.valueOf(position + 1));;
                txtStudentId.setText(stu.getRollNumber());
                txtStudentName.setText(stu.getFirstName() + stu.getLastName());
                imgStudentPhoto.setImageBitmap(StringToBitMap(stu.getPhoto()));
                ckbAttendance.setEnabled(isTakeAvaiable);
                ckbAttendance.setChecked(false);
            }
        }

        return view;
    }

    public void swap(List<StudentModel> list1){
        this.list.clear();
        this.list.addAll(list1);
        notifyDataSetChanged();
    }
    private Bitmap StringToBitMap(String encodedString) {
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
}
