package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity1 extends AppCompatActivity {

    TextView tv_dob;
    private EditText edt_dob;
    DatePickerDialog.OnDateSetListener setListener;
    EditText edt_fullname , edt_email ,edt_mobileno , edt_address,edt_aadhaarno;
    private EditText edt_uid , edt_preEnrollid , edt_housenumber , edt_street , edt_landmark ,edt_state ,edt_area , edt_village ,edt_postoffice , edt_introducerName;
    private EditText edt_district , edt_subdistrict , edt_poi , edt_poa , edt_por , edt_dobdocbased , edt_age ,edt_pincode , edt_hofaadharnum;
    Button submitbtn;
    RadioGroup radiogroup_gender;
    RadioButton radiobtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

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
        edt_dobdocbased = findViewById(R.id.EditText_docBased);
        edt_housenumber = findViewById(R.id.EditText_houseNumber);
        edt_street = findViewById(R.id.EditText_street);
        edt_landmark = findViewById(R.id.EditText_landmark);
        edt_state = findViewById(R.id.EditText_state);
        edt_area = findViewById(R.id.EditText_area);
        edt_village = findViewById(R.id.EditText_village);
        edt_postoffice = findViewById(R.id.EditText_postOffice);
        edt_age = findViewById(R.id.EditText_age);
        edt_pincode = findViewById(R.id.EditText_pincode);
        edt_hofaadharnum = findViewById(R.id.EditText_hofbasedaadharnumber);
        edt_introducerName = findViewById(R.id.EditText_introducerORhof_name);



        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

      edt_dob.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              DatePickerDialog datePickerDialog = new DatePickerDialog(
                      MainActivity1.this, new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int month, int day) {
                       month = month + 1;
                       String date = day + "/" + month + "/" + year;
                       edt_dob.setText(date);
                  }
              },year,month,day);
             //edt_dob set tag(1)
                      //edt .get tag == null //error in parceble
              datePickerDialog.show();

          }
      });

        edt_fullname = findViewById(R.id.EditText_fullname);
        edt_aadhaarno = findViewById(R.id.EditText_hofbasedaadharnumber);
        edt_address = findViewById(R.id.EditText_address);
        edt_mobileno = findViewById(R.id.EditText_mobileno);
        edt_email = findViewById(R.id.EditText_email);

        submitbtn = findViewById(R.id.submit);
        radiogroup_gender = findViewById(R.id.radio_group_gender);
        final String address = edt_address.getText().toString() + " " + edt_housenumber.getText().toString() + " " + edt_street.getText().toString()+" "+
                          edt_landmark.getText().toString() + " " + edt_area.getText().toString() + " " + edt_village.getText().toString();

       /* int selectedId = radiogroup_gender.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radiobtn = (RadioButton) findViewById(selectedId);
        final String gender = (String) radiobtn.getText();

*/
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

                if(radiogroup_gender.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(getApplicationContext(), "Gender is not selected",
                            Toast.LENGTH_LONG).show();
                }

                final String dob = edt_dob.getText().toString();
                if(dob.length()==0){
                    edt_dob.setError("date of birth is required");}

                if(address.length()==0)
                    edt_address.setError("Address is required");

                AadharData data = new AadharData(edt_preEnrollid.getText().toString(),edt_fullname.getText().toString(),edt_postoffice.getText().toString(),
                                                 address , edt_district.getText().toString(), edt_subdistrict.getText().toString() ,
                                                 edt_state.getText().toString() , edt_email.getText().toString() , edt_poa.getText().toString(),
                        edt_poi.getText().toString(), edt_por.getText().toString() , edt_uid.getText().toString() , edt_age.getText().toString(),
                        edt_pincode.getText().toString(), edt_mobileno.getText().toString() , edt_aadhaarno.getText().toString(),edt_hofaadharnum.getText().toString(),
                        edt_introducerName.getText().toString());

                Intent intent = new Intent(MainActivity1.this, SecondActivity.class);
                intent.putExtra("Deta", data);
                startActivity(intent);


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
}