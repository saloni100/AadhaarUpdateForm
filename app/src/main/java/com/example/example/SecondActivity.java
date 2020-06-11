package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView preEnrollId ,  dob , address , fullname , gender , postOffice , district , subdistrict , state , email , pincode , mobileno,
             poi , poa , por , dob_docbased , detailsOf_name , detailsof_aadhaarno;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        preEnrollId = findViewById(R.id.preEnroll_id);
        dob = findViewById(R.id.dob);
        address = findViewById(R.id.Address);
        fullname = findViewById(R.id.fullname);
        gender = findViewById(R.id.gender);
        postOffice = findViewById(R.id.postOffice);
        district = findViewById(R.id.district);
        subdistrict = findViewById(R.id.subdistrict);
        state = findViewById(R.id.state);
        email = findViewById(R.id.email);
        pincode = findViewById(R.id.pincode);
        mobileno = findViewById(R.id.mobile);
        poi = findViewById(R.id.poi);
        por = findViewById(R.id.por);
        poa = findViewById(R.id.poa);
        dob_docbased = findViewById(R.id.dob_docbased);
        detailsOf_name = findViewById(R.id.name);
        detailsof_aadhaarno = findViewById(R.id.AadhaarNo);
        img = findViewById(R.id.imageView);

        //collect our intent
          Intent intent = getIntent();
        AadharData data = intent.getParcelableExtra("Deta");
//         Toast.makeText(this,data.getFull_name(),Toast.LENGTH_LONG).show();
          img.setImageBitmap(data.getBitmapImage());



        String PreEnroll_id  = data.getPreEnroll_id();
        String Full_name = data.getFull_name();
        String postOffice = data.getPostOffice();
        String Address = data.getAddress();
        String district = data.getDistrict();
        String subdistrict = data.getSubdistrict();
        String state = data.getState();
        String Email = data.getEmail();
        String poa = data.getPoa();
        String  poi = data.getPoi();
        String por = data.getPor();
        String uid  = data.getUid();
        String age = data.getAge();
        String Pincode = data.getPincode();
        String mobile_no  = data.getMobile_no();
        String aadhaar_num  = data.getAadhaar_num();
        String hof_aadharno = data.getHof_aadharno();

        preEnrollId.setText("Pre Enroll ID : "+PreEnroll_id);
        fullname.setText("Full name : "+Full_name);
        email.setText("Email : "+Email);
        mobileno.setText("Mobile number : "+mobile_no);
        address.setText("Address : "+Address);
        pincode.setText("Pincode : "+Pincode);





    }
}