package com.example.example;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    TextView Fullname;
     TextView Age;
    TextView uid;
    TextView address;
    TextView mobilenumber;
    TextView AdhaarNumber;
    TextView gender;
    TextView pincode;
    TextView email;
    ImageView image;
    Button btn;
    private static final int STORAGE_CODE = 1000;
    PdfHelper objectpdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        objectpdf = new PdfHelper();


        Fullname = findViewById(R.id.fullname);
        Age= findViewById(R.id.Age);
        mobilenumber = findViewById(R.id.mobileNumber);
        gender = findViewById(R.id.Gender);
        uid = findViewById(R.id.Uid);
        pincode = findViewById(R.id.pincode);
        email = findViewById(R.id.email);
        address = findViewById(R.id.Address);
        AdhaarNumber = findViewById(R.id.AadharNumber);
        image = findViewById(R.id.image);
        btn = findViewById(R.id.download_btn);


        Intent intent = getIntent();
        final AadharData data = intent.getParcelableExtra("Deta");



        //Test data . Remove this code
//        data.setFull_name("test");
//        data.setUid("dsddsdfffdfsd");
//        data.setMobile_no("dddddd");
//        data.setPincode("fffdfsd");
//        data.setAadhaar_num("fffdfsd");
//        data.setAddress("fffdfsd");
//        data.setGender("fffdfsd");
//        data.setEmail("fffdfsd");
//        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.face);
//        data.setBitmapImage(bitmap);
//

        if (data != null) {
            Fullname.setText("FullName : " + data.getFull_name());
            Age.setText("Age : "+data.getAge());
            uid.setText("UID :" + data.getUid());
            mobilenumber.setText("Mobile Number :" + data.getMobile_no());
            pincode.setText("Pincode : " + data.getPincode());
            AdhaarNumber.setText("Aadhaar Number :" + data.getAadhaar_num());
            address.setText("Address :" + data.getAddress());
            gender.setText("Gender :" + data.getGender());
            email.setText("Email : " + data.getEmail());
            image.setImageBitmap(data.getBitmapImage());
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data!=null)
                    objectpdf.createPdf(data);

            }
        });

    }


}
