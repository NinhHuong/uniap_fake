package com.example.bonz.uniap_fake.other;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.activity.NewsInfoActivity;
import com.example.bonz.uniap_fake.model.NewsModel;

import java.util.List;

/**
 * Created by bonz on 3/21/17.
 */

public class NewsAdapter extends BaseAdapter {

    private List<NewsModel> listNews;
    private LayoutInflater layoutInflater;
    private Context context;

    public NewsAdapter(Context aContext,  List<NewsModel> list) {
        this.context = aContext;
        this.listNews = list;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listNews.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null)
            view = layoutInflater.inflate(R.layout.news_list_row, null);
        holder = new ViewHolder();
        holder.titleNews = (TextView)view.findViewById(R.id.title_news); // title
        holder.dateNews = (TextView)view.findViewById(R.id.date_news); // artist name

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsInfoActivity.class);
                intent.putExtra("newsId",String.valueOf(i));
                context.startActivity(intent);
            }
        });

        // Setting all values in listview
        NewsModel model = this.listNews.get(i);
        holder.titleNews.setText(model.getTitle());
        holder.dateNews.setText(model.getDate());

        return view;
    }

    static class ViewHolder {
        TextView titleNews;
        TextView dateNews;
    }
}
