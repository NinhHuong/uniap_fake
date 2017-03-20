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
import com.example.bonz.uniap_fake.model.AttendanceModel;
import com.example.bonz.uniap_fake.model.StudentModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by HuongNTM5 on 3/15/2017.
 */

public class ListViewApdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<AttendanceModel> list;
    private Context context;
    private boolean isInTime;

    public ListViewApdapter(Context context, List<AttendanceModel> list, boolean isInTime) {
        if (list != null) {
            this.list = list;
        } else {
            this.list = new ArrayList<>();
        }
        this.context = context;
        this.isInTime = isInTime;
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

    private class ViewHolder {
        TextView txtNo;
        TextView txtStudentId;
        TextView txtStudentName;
        ImageView imgStudentPhoto;
        CheckBox ckbAttendance;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListViewApdapter.ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_attendance, null);

            holder = new ListViewApdapter.ViewHolder();
            holder.txtNo = (TextView) convertView.findViewById(R.id.txt_no);
            holder.txtStudentId = (TextView) convertView.findViewById(R.id.txt_student_id);
            holder.txtStudentName = (TextView) convertView.findViewById(R.id.txt_student_name);
            holder.imgStudentPhoto = (ImageView) convertView.findViewById(R.id.img_student_photo);
            holder.ckbAttendance = (CheckBox) convertView.findViewById(R.id.ckb_attendance);
            holder.ckbAttendance.setEnabled(isInTime);
            convertView.setTag(holder);

            holder.ckbAttendance.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    list.get(position).setIsAttendance(cb.isChecked());
                    realm.commitTransaction();
                }
            });
        }
        else {
            holder = (ListViewApdapter.ViewHolder) convertView.getTag();
        }

        AttendanceModel attendanceModel = list.get(position);
        holder.txtNo.setText(String.valueOf(position + 1));
        holder.txtStudentId.setText(attendanceModel.getStudentModel().getRollNumber());
        holder.txtStudentName.setText(attendanceModel.getStudentModel().getFirstName() + " " + attendanceModel.getStudentModel().getLastName());
        holder.imgStudentPhoto.setImageBitmap(StringToBitMap(attendanceModel.getStudentModel().getPhoto()));
        holder.ckbAttendance.setChecked(attendanceModel.getIsAttendance());
        holder.ckbAttendance.setTag(attendanceModel);

        return convertView;
    }

    public void swap(List<AttendanceModel> list1){
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
