package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.ProgressDialog.show;

public class MainActivity3 extends AppCompatActivity {

    EditText edt_sign_introducer , edt_sign_applicant;
    ImageView img_sign_verifier;
    TextView tv_sign_applicant , tv_sign_verifier , tv_sign_introducer;
    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        edt_sign_applicant = findViewById(R.id.EditText_sign_applicant);
        tv_sign_applicant = findViewById(R.id.sign_applicant);
        tv_sign_verifier = findViewById(R.id.sign_verifier);
        tv_sign_introducer = findViewById(R.id.sign_hofORintroducer);

        tv_sign_verifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            img_sign_verifier = findViewById(R.id.imageView_sign_verifier);
            img_sign_verifier.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    }

