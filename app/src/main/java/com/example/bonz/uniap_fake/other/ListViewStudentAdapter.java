package com.example.bonz.uniap_fake.other;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.activity.ViewStudentOfClassActivity;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.AccountModel;
import com.example.bonz.uniap_fake.model.StudentModel;
import com.example.bonz.uniap_fake.model.StudentOfClassModel;
import com.example.bonz.uniap_fake.model.SubjectOfClassModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ninh huong on 3/23/2017.
 */

public class ListViewStudentAdapter extends BaseAdapter {
    private List<StudentOfClassModel> list;
    private StudentOfClassModel model;
    private Context context;
    private int role;

    public ListViewStudentAdapter(Context context, List<StudentOfClassModel> list) {
        if (list != null) {
            this.list = list;
        } else {
            this.list = new ArrayList<>();
        }
        this.context = context;
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
        TextView tvNo;
        TextView tvRollNumber;
        TextView tvFirstName;
        TextView tvLastName;
        TextView tvPhone;
        TextView tvEmail;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListViewStudentAdapter.ViewHolder holder = null;
        if(convertView == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_student, null);

            holder = new ListViewStudentAdapter.ViewHolder();
            holder.tvNo = (TextView) convertView.findViewById(R.id.txt_no);
            holder.tvRollNumber = (TextView) convertView.findViewById(R.id.txt_roll_number);
            holder.tvFirstName = (TextView) convertView.findViewById(R.id.txt_first_name);
            holder.tvLastName = (TextView) convertView.findViewById(R.id.txt_last_name);
            holder.tvPhone = (TextView) convertView.findViewById(R.id.txt_phone);
            holder.tvEmail = (TextView) convertView.findViewById(R.id.txt_email);
            convertView.setTag(holder);

            holder.tvPhone.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
//                    if(role == Constanst.KEY_ROLL_TEACHER) {
//                        Intent intent = new Intent(context, ViewStudentOfClassActivity.class);
//                        intent.putExtra("classId", list.get(position).getId());
//                        context.startActivity(intent);
//                    }
                }
            });
        }
        else {
            holder = (ListViewStudentAdapter.ViewHolder) convertView.getTag();
        }
        model = list.get(position);
        holder.tvNo.setText(String.valueOf(position + 1));
        holder.tvRollNumber.setText(model.getStudentModel().getRollNumber());
        holder.tvFirstName.setText(model.getStudentModel().getFirstName());
        holder.tvLastName.setText(model.getStudentModel().getLastName());
        holder.tvPhone.setText(model.getStudentModel().getPhoneNumber());
//        holder.tvEmail.setText(model.getStudentModel().get);

        return convertView;
    }

    public void swap(List<StudentOfClassModel> list1){
        this.list.clear();
        this.list.addAll(list1);
        notifyDataSetChanged();
    }
}
