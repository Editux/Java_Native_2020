package com.example.app.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.app.R;

public class Details extends AppCompatActivity {

    TextView detail_title;
    TextView author_title;
    TextView detail_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detail_title= (TextView)findViewById(R.id.detail_title);
        author_title= (TextView)findViewById(R.id.detail_author);
        detail_detail= (TextView)findViewById(R.id.textView5);
        Intent intent = getIntent();

        String title =intent.getStringExtra("title");
        String author= intent.getStringExtra("author");
        String description= intent.getStringExtra("description");

        detail_title.setText(title);
        author_title.setText(author);
        detail_detail.setText(description);



    }
}