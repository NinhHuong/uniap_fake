package com.example.bonz.uniap_fake.other;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.model.AttendanceModel;
import com.example.bonz.uniap_fake.model.SubjectModel;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ninh huong on 3/21/2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<AttendanceModel>> _listDataChild;


    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<AttendanceModel>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final AttendanceModel attendanceModel = ((AttendanceModel)getChild(groupPosition, childPosition));

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_attendance_student, null);
        }

        TextView txtNo = (TextView) convertView.findViewById(R.id.txt_no);
        TextView txtDate = (TextView) convertView.findViewById(R.id.txt_date);
        TextView txtTeacher = (TextView) convertView.findViewById(R.id.txt_teacher);
        TextView txtSlot = (TextView) convertView.findViewById(R.id.txt_slot);
        CheckBox ckbIsAttendance = (CheckBox) convertView.findViewById(R.id.ckb_is_attendance);

        txtNo.setText(String.valueOf(childPosition + 1));
        txtDate.setText(attendanceModel.getLectureModel().getDate());
        txtTeacher.setText(attendanceModel.getLectureModel().getSubjectOfClassModel().getTeacherModel().getRollNumber());
        txtSlot.setText(String.valueOf(attendanceModel.getLectureModel().getSlot()));
        ckbIsAttendance.setChecked(attendanceModel.getIsAttendance());
        ckbIsAttendance.setEnabled(false);

        Date date = new Date();
        try {
            date = Constanst.KEY_DATE_FORMAT.parse(attendanceModel.getLectureModel().getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date.after(new Date())) {
            txtNo.setTextColor(_context.getResources().getColor(R.color.future_item));
            txtDate.setTextColor(_context.getResources().getColor(R.color.future_item));
            txtTeacher.setTextColor(_context.getResources().getColor(R.color.future_item));
            txtSlot.setTextColor(_context.getResources().getColor(R.color.future_item));
        } else {
            txtNo.setTextColor(_context.getResources().getColor(R.color.past_item));
            txtDate.setTextColor(_context.getResources().getColor(R.color.past_item));
            txtTeacher.setTextColor(_context.getResources().getColor(R.color.past_item));
            txtSlot.setTextColor(_context.getResources().getColor(R.color.past_item));
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_attendance, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
