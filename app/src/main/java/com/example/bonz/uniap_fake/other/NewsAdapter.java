package com.example.bonz.uniap_fake.other;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.model.NewsModel;

import java.util.List;

/**
 * Created by bonz on 3/21/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private List<NewsModel> listNews;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitleNews, txtDateNews;

        public MyViewHolder(View view) {
            super(view);
            txtTitleNews = (TextView) view.findViewById(R.id.title_news);
            txtDateNews = (TextView) view.findViewById(R.id.date_news);

        }
    }

    public NewsAdapter(List<NewsModel> listNews) {
        this.listNews = listNews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.MyViewHolder myViewHolder, int i) {
        NewsModel model = listNews.get(i);
        //Log.v("test","NewsModel "+model.getTitle());
        myViewHolder.txtTitleNews.setText(model.getTitle());
        myViewHolder.txtDateNews.setText(model.getDate());

    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }
}
