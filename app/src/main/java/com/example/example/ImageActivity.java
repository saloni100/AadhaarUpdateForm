package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        TextView textView = findViewById(R.id.text);
        ImageView imageView = (ImageView)this.findViewById(R.id.image);
        //collect our intent
        Intent intent = getIntent();
        AadharData data = intent.getParcelableExtra("Deta");
//         Toast.makeText(this,data.getFull_name(),Toast.LENGTH_LONG).show();
        if(data!=null){
            imageView.setImageBitmap(data.getBitmapImage());
            textView.setText(data.getGender());
        }

    }
}