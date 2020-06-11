package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity4 extends AppCompatActivity {


    TextView  tv_sign_verifier;
    TextView  tv_sign_applicant;
    private ImageView imageView_applicant;
    Bitmap photo;


    private static int RESULT_LOAD_IMAGE = 1;
    private static final String TAG = "myactivity";
    private static final int pic_id = 123;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tv_sign_verifier = findViewById(R.id.sign_verifier);
        tv_sign_applicant = findViewById(R.id.sign_applicant);
        imageView_applicant = (ImageView)this.findViewById(R.id.imageView_sign_applicant);


        //opening gallery to select image
        tv_sign_verifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });


        tv_sign_applicant.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                // Create the camera_intent ACTION_IMAGE_CAPTURE
                // it will open the camera for capture the image
                Intent camera_intent
                        = new Intent(MediaStore
                        .ACTION_IMAGE_CAPTURE);
                // Start the activity with camera_intent,
                // and request pic id
                startActivityForResult(camera_intent, pic_id);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {

            // BitMap is data structure of image file
            // which stor the image in memory
             photo = (Bitmap)data.getExtras()
                    .get("data");

            // Set the image in imageview for display
            imageView_applicant.setImageBitmap(photo);
        }



        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Log.i("cursor_data",picturePath);
            cursor.close();
            ImageView img_sign_verifier = (ImageView) findViewById(R.id.imageView_sign_verifier);
            File imgFile = new File(picturePath);
           // Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            img_sign_verifier.setImageURI(Uri.fromFile(imgFile));
           // img_sign_verifier.setImageBitmap(myBitmap);
            Log.d(TAG,"image is set");
            System.out.println("image 1");
            System.out.println("image2");

        }

    }





}

