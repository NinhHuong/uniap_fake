package com.example.bonz.uniap_fake.other;

import android.accounts.Account;
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
import com.example.bonz.uniap_fake.model.SubjectOfClassModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ninh huong on 3/23/2017.
 */

public class ListViewClassAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<SubjectOfClassModel> list;
    private SubjectOfClassModel model;
    private Context context;
    private int role;

    public ListViewClassAdapter(Context context, List<SubjectOfClassModel> list) {
        if (list != null) {
            this.list = list;
        } else {
            this.list = new ArrayList<>();
        }
        this.context = context;
        this.inflater = LayoutInflater.from(context);

        //if account is teacher => hightlight class name
        DBContext dbContext = DBContext.getInst();
        List<AccountModel> accountList = dbContext.getAllAccount();
        if(accountList != null && accountList.size() > 0) {
            role = accountList.get(0).getRoll();
        }
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
        TextView tvClassName;
        TextView tvSubjectCode;
        TextView tvSubjectName;
        TextView tvCredit;
        TextView tvStart;
        TextView tvEnd;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListViewClassAdapter.ViewHolder holder = null;
        if(convertView == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_class, null);

            holder = new ListViewClassAdapter.ViewHolder();
            holder.tvNo = (TextView) convertView.findViewById(R.id.txt_no);
            holder.tvClassName = (TextView) convertView.findViewById(R.id.txt_class_name);
            holder.tvSubjectCode = (TextView) convertView.findViewById(R.id.txt_subject_code);
            holder.tvSubjectName = (TextView) convertView.findViewById(R.id.txt_subject_name);
            holder.tvCredit = (TextView) convertView.findViewById(R.id.txt_credit);
            holder.tvStart = (TextView) convertView.findViewById(R.id.txt_start);
            holder.tvEnd = (TextView) convertView.findViewById(R.id.txt_end);
            convertView.setTag(holder);

            holder.tvClassName.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    if(role == Constanst.KEY_ROLL_TEACHER) {
                        Intent intent = new Intent(context, ViewStudentOfClassActivity.class);
                        intent.putExtra("subclassId", list.get(position).getId());
                        context.startActivity(intent);
                    }
                }
            });
        }
        else {
            holder = (ListViewClassAdapter.ViewHolder) convertView.getTag();
        }
        model = list.get(position);
        holder.tvNo.setText(String.valueOf(position + 1));
        holder.tvClassName.setText(model.getClassModel().getClassName());
        holder.tvSubjectCode.setText(model.getSubjectModel().getSubjectCode());
        holder.tvSubjectName.setText(model.getSubjectModel().getSubjectName());
        holder.tvCredit.setText(String.valueOf(model.getSubjectModel().getNumberCredit()));//
        holder.tvStart.setText(model.getClassModel().getSemesterModel().getStartDate());
        holder.tvEnd.setText(model.getClassModel().getSemesterModel().getEndDate());
        if(role == Constanst.KEY_ROLL_TEACHER)
            holder.tvClassName.setTextColor(context.getResources().getColor(R.color.text_link));


        /*View view = inflater.inflate(R.layout.list_item_class, parent, false);

        if (view != null) {
            model = list.get(position);
            if (model != null) {
                TextView tvNo = (TextView) view.findViewById(R.id.txt_no);
                TextView tvClassName = (TextView) view.findViewById(R.id.txt_class_name);
                TextView tvSubjectCode = (TextView) view.findViewById(R.id.txt_subject_code);
                TextView tvSubjectName = (TextView) view.findViewById(R.id.txt_subject_name);
                TextView tvCredit = (TextView) view.findViewById(R.id.txt_credit);
                TextView tvStart = (TextView) view.findViewById(R.id.txt_start);
                TextView tvEnd = (TextView) view.findViewById(R.id.txt_end);
                
                tvNo.setText(String.valueOf(position + 1));
                tvClassName.setText(model.getClassModel().getClassName());
                tvSubjectCode.setText(model.getSubjectModel().getSubjectCode());
                tvSubjectName.setText(model.getSubjectModel().getSubjectName());
                tvCredit.setText(String.valueOf(1));//
                tvStart.setText(model.getClassModel().getSemesterModel().getStartDate());
                tvEnd.setText(model.getClassModel().getSemesterModel().getEndDate());
            }
        }*/

        return convertView;
    }

    public void swap(List<SubjectOfClassModel> list1){
        this.list.clear();
        this.list.addAll(list1);
        notifyDataSetChanged();
    }
}
