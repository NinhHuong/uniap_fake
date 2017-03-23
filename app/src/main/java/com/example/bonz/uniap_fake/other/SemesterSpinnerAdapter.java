package com.example.bonz.uniap_fake.other;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bonz.uniap_fake.model.SemesterModel;

import java.util.List;

/**
 * Created by ninh huong on 3/23/2017.
 */

public class SemesterSpinnerAdapter extends ArrayAdapter<SemesterModel> {
    private Context context;
    private List<SemesterModel> list;

    public SemesterSpinnerAdapter(Context context, int textViewResourceId, List<SemesterModel> list) {
        super(context, textViewResourceId, list);
        this.context = context;
        this.list = list;
    }

    public int getCount(){
        return list.size();
    }

    public SemesterModel getItem(int position){
        return list.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(list.get(position).getSemesterName());
        label.setHeight(30);

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(list.get(position).getSemesterName());

        return label;
    }
}
