package com.example.bonz.uniap_fake.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.NewsModel;

public class NewsInfoActivity extends AppCompatActivity {

    private String newsId;

    private DBContext dbContext;

    //Textview
    private TextView txtTitleNews, txtDateNews, txtContentNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbContext =DBContext.getInst();

        txtTitleNews = (TextView)findViewById(R.id.txt_title_news);
        txtDateNews = (TextView)findViewById(R.id.txt_date_news);
        txtContentNews = (TextView)findViewById(R.id.txt_content_news);

        Intent intent = getIntent();
        newsId = intent.getStringExtra("newsId");
        NewsModel model = dbContext.getNewsByID(Integer.parseInt(newsId));
        //Toast.makeText(getApplicationContext(),model.toString(),Toast.LENGTH_LONG).show();

        txtTitleNews.setText(model.getTitle());
        txtDateNews.setText(model.getDate());
        txtContentNews.setText(model.getContent());

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
