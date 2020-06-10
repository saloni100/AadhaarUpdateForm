package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.PendingIntent.getActivity;
import static com.example.example.R.id.fullname;

public class MainActivity4 extends AppCompatActivity {


    TextView  tv_sign_verifier;
    TextView  tv_sign_applicant;
    private ImageView imageView_applicant;
    private EditText edt_fullname , edt_email ,edt_mobileno , edt_address ,edt_dob ,edt_aadhaarno;
    Button submitbtn;
    RadioGroup radiobtn_gender;

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
        edt_fullname = findViewById(R.id.EditText_fullname);
        edt_aadhaarno = findViewById(R.id.EditText_hofbasedaadharnumber);
        edt_address = findViewById(R.id.EditText_address);
        edt_mobileno = findViewById(R.id.EditText_mobileno);
        edt_email = findViewById(R.id.EditText_email);
        edt_dob = findViewById(R.id.dob);
        submitbtn = findViewById(R.id.submit);
        radiobtn_gender = findViewById(R.id.radio_group_gender);

        submitbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                final String mobileNumber = edt_mobileno.getText().toString();
                if( mobileNumber.length() == 0 )
                    edt_mobileno.setError( "Mobile Number is required!" );

                if( mobileNumber.length()<10)
                    edt_mobileno.setError( "Mobile Number is invalid" );

                final String aadharNumber = edt_aadhaarno.getText().toString();
                if( aadharNumber.length() == 0 )
                    edt_aadhaarno.setError( "Aadhar number is required!" );

                final String name = edt_fullname.getText().toString();
                if( name.length() == 0 )
                    edt_fullname.setError( "Name is required!" );

                final String email = edt_email.getText().toString();
                if (!isValidEmail(email)) {
                    edt_email.setError("Invalid Email");
                }

               if(radiobtn_gender.getCheckedRadioButtonId() == -1)
               {
                   Toast.makeText(getApplicationContext(), "Gender is not selected",
                           Toast.LENGTH_LONG).show();
               }

               final String dob = edt_dob.getText().toString();
               if(dob.length()==0)
                   edt_dob.setError("date of birth is required");

            }
        });



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


    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {

            // BitMap is data structure of image file
            // which stor the image in memory
            Bitmap photo = (Bitmap)data.getExtras()
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

