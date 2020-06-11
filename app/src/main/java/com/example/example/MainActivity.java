package com.example.example;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    TextView tv_dob;
    private EditText edt_dob;
    EditText edt_fullname , edt_email ,edt_mobileno , edt_address,edt_aadhaarno;
    private EditText edt_uid , edt_preEnrollid , edt_housenumber , edt_street , edt_landmark
            ,edt_state ,edt_area , edt_village ,edt_postoffice , edt_introducerName;
    private EditText edt_district , edt_subdistrict , edt_poi , edt_poa , edt_por , edt_age ,edt_pincode ;
    Button submitbtn;
    private RadioGroup radiogroup_gender;

    TextView  tv_sign_verifier;
    TextView  tv_sign_applicant;
    private ImageView imageView_applicant;
    Bitmap photo;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int pic_id = 123;
    private String gender="";
    private ImageView img_sign_verifier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        initView();
        setClickListeners();
    }

    private void setClickListeners() {
        radiogroup_gender.setOnCheckedChangeListener(this);
        edt_dob.setOnClickListener(this);
        tv_sign_verifier.setOnClickListener(this);
        tv_sign_applicant.setOnClickListener(this);
        submitbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id =view.getId();
        switch (id){
            case R.id.EditText_dob : openDatePicker(); break;
            case R.id.sign_applicant : cameraPick(); break;
            case R.id.sign_verifier : galleryPick();break;
            case R.id.submit : if(!validateData()){
                AadharData data = setData();
                startNewActivity(data);
            }break;

        }
    }

    private void startNewActivity(AadharData data) {
        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
        intent.putExtra("Deta", data);
        startActivity(intent);
    }

    private AadharData setData() {
        final String address = edt_address.getText().toString() + " " + edt_housenumber.getText().toString() + " " + edt_street.getText().toString()+" "+
                edt_landmark.getText().toString() + " " + edt_area.getText().toString() + " " + edt_village.getText().toString();

        AadharData data = new AadharData();
        data.setAadhaar_num(edt_aadhaarno.getText().toString());
        data.setAddress(address);
        data.setAge(edt_age.getText().toString());
        data.setBitmapImage(photo);
        data.setDistrict(edt_district.getText().toString());
        data.setEmail(edt_email.getText().toString());
        data.setFull_name(edt_fullname.getText().toString());
        data.setHof_aadharno(edt_aadhaarno.getText().toString());
        data.setIntroducer_name(edt_introducerName.getText().toString());
        data.setMobile_no(edt_mobileno.getText().toString());
        data.setPincode(edt_pincode.getText().toString());
        data.setPoa(edt_poa.getText().toString());
        data.setPoi(edt_poi.getText().toString());
        data.setPor(edt_por.getText().toString());
        data.setPostOffice(edt_postoffice.getText().toString());
        data.setPreEnroll_id(edt_preEnrollid.getText().toString());
        data.setState(edt_state.getText().toString());
        data.setSubdistrict(edt_subdistrict.getText().toString());
        data.setUid(edt_uid.getText().toString());
        data.setGender(gender);
        return data;
    }

    private boolean validateData() {
        boolean failFlag = false;

        final String mobileNumber = edt_mobileno.getText().toString();
        final String aadharNumber = edt_aadhaarno.getText().toString();
        final String name = edt_fullname.getText().toString();
        final String email = edt_email.getText().toString();
        final String dob = edt_dob.getText().toString();
        final String address = edt_address.getText().toString() + " " + edt_housenumber.getText().toString() + " " + edt_street.getText().toString()+" "+
                edt_landmark.getText().toString() + " " + edt_area.getText().toString() + " " + edt_village.getText().toString();

        if( mobileNumber.length() == 0 ){
            failFlag = true;
            edt_mobileno.setError( "Mobile Number is required!" );}

        if( mobileNumber.length()<10){
            failFlag = true;
            edt_mobileno.setError( getString(R.string.error_mobile) );}

        if( aadharNumber.length() == 0 ){
            failFlag = true;
            edt_aadhaarno.setError( "Aadhar number is required!" );}

        if( name.length() == 0 ){
            failFlag = true;
            edt_fullname.setError( "Name is required!" );}

        if (!isValidEmail(email)) {
            failFlag = true;
            edt_email.setError("Invalid Email");
        }

        if(radiogroup_gender.getCheckedRadioButtonId() == -1)
        {
            failFlag = true;
            Toast.makeText(getApplicationContext(), "Gender is not selected",
                    Toast.LENGTH_LONG).show();
        }
        if(dob.length()==0){
            failFlag = true;
            edt_dob.setError("date of birth is required");}

        if(address.length()==0){
            failFlag = true;
            edt_address.setError("Address is required");}
        return failFlag;
    }

    private void galleryPick() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    private void cameraPick() {
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, pic_id);
    }

    private void openDatePicker() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                edt_dob.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        RadioButton radioButton
                = (RadioButton)radioGroup
                .findViewById(i);

        if(radioButton !=null){
            gender =radioButton.getText().toString();
        }

    }
    private void initView() {
        tv_dob = findViewById(R.id.dob);
        edt_dob = findViewById(R.id.EditText_dob);
        edt_uid = findViewById(R.id.EditText_UID);
        edt_preEnrollid = findViewById(R.id.EditText_preEnroll_id);
        edt_aadhaarno = findViewById(R.id.EditText_hofbasedaadharnumber);
        edt_district = findViewById(R.id.EditText_district);
        edt_subdistrict = findViewById(R.id.EditText_subdistrict);
        edt_poi = findViewById(R.id.EditText_poi);
        edt_poa = findViewById(R.id.EditText_poa);
        edt_por = findViewById(R.id.EditText_por);
        edt_housenumber = findViewById(R.id.EditText_houseNumber);
        edt_street = findViewById(R.id.EditText_street);
        edt_landmark = findViewById(R.id.EditText_landmark);
        edt_state = findViewById(R.id.EditText_state);
        edt_area = findViewById(R.id.EditText_area);
        edt_village = findViewById(R.id.EditText_village);
        edt_postoffice = findViewById(R.id.EditText_postOffice);
        edt_age = findViewById(R.id.EditText_age);
        edt_pincode = findViewById(R.id.EditText_pincode);
        edt_introducerName = findViewById(R.id.EditText_introducerORhof_name);
        edt_fullname = findViewById(R.id.EditText_fullname);
        edt_address = findViewById(R.id.EditText_address);
        edt_mobileno = findViewById(R.id.EditText_mobileno);
        edt_email = findViewById(R.id.EditText_email);
        submitbtn = findViewById(R.id.submit);
        radiogroup_gender = findViewById(R.id.radio_group_gender);
        img_sign_verifier = (ImageView) findViewById(R.id.imageView_sign_verifier);
        tv_sign_verifier = findViewById(R.id.sign_verifier);
        tv_sign_applicant = findViewById(R.id.sign_applicant);
        imageView_applicant = (ImageView)this.findViewById(R.id.imageView_sign_applicant);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && null != data){
            switch (requestCode){
                case pic_id : photo = (Bitmap)data.getExtras().get("data");
                    imageView_applicant.setImageBitmap(photo);
                    break;

                case RESULT_LOAD_IMAGE:  Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    File imgFile = new File(picturePath);
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    // img_sign_verifier.setImageURI(Uri.fromFile(imgFile));
                    img_sign_verifier.setImageBitmap(myBitmap);
                    break;
                default: break;
            }
        }
    }


    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}