package com.example.example;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class AadharData implements Parcelable {

    String preEnroll_id ;
    String Full_name  ;
    String  postOffice ;
    String Address ;
    String district ;
    String  subdistrict ;
    String state ;
    String email ;
    String poa;
    String poi;
    String por;
    String uid ;
    String age;
    String pincode;
    String mobile_no ;
    String  aadhaar_num ;
    String  hof_aadharno ;
    String introducer_name;
    String gender;
    Bitmap bitmapImage;


    public AadharData() {

    }


    public String getPreEnroll_id() {
        return preEnroll_id;
    }

    public void setPreEnroll_id(String preEnroll_id) {
        this.preEnroll_id = preEnroll_id;
    }

    public String getFull_name() {
        return Full_name;
    }

    public void setFull_name(String full_name) {
        Full_name = full_name;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoa() {
        return poa;
    }

    public void setPoa(String poa) {
        this.poa = poa;
    }

    public String getPoi() {
        return poi;
    }

    public void setPoi(String poi) {
        this.poi = poi;
    }

    public String getPor() {
        return por;
    }

    public void setPor(String por) {
        this.por = por;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getAadhaar_num() {
        return aadhaar_num;
    }

    public void setAadhaar_num(String aadhaar_num) {
        this.aadhaar_num = aadhaar_num;
    }

    public String getHof_aadharno() {
        return hof_aadharno;
    }

    public void setHof_aadharno(String hof_aadharno) {
        this.hof_aadharno = hof_aadharno;
    }

    public String getIntroducer_name() {
        return introducer_name;
    }

    public void setIntroducer_name(String introducer_name) {
        this.introducer_name = introducer_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Bitmap getBitmapImage() {
        return bitmapImage;
    }

    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }

    public static Creator<AadharData> getCREATOR() {
        return CREATOR;
    }


    protected AadharData(Parcel in) {
        preEnroll_id = in.readString();
        Full_name = in.readString();
        postOffice = in.readString();
        Address = in.readString();
        district = in.readString();
        subdistrict = in.readString();
        state = in.readString();
        email = in.readString();
        poa = in.readString();
        poi = in.readString();
        por = in.readString();
        uid = in.readString();
        age = in.readString();
        pincode = in.readString();
        mobile_no = in.readString();
        aadhaar_num = in.readString();
        hof_aadharno = in.readString();
        introducer_name = in.readString();
        gender = in.readString();
        bitmapImage = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(preEnroll_id);
        dest.writeString(Full_name);
        dest.writeString(postOffice);
        dest.writeString(Address);
        dest.writeString(district);
        dest.writeString(subdistrict);
        dest.writeString(state);
        dest.writeString(email);
        dest.writeString(poa);
        dest.writeString(poi);
        dest.writeString(por);
        dest.writeString(uid);
        dest.writeString(age);
        dest.writeString(pincode);
        dest.writeString(mobile_no);
        dest.writeString(aadhaar_num);
        dest.writeString(hof_aadharno);
        dest.writeString(introducer_name);
        dest.writeString(gender);
        dest.writeValue(bitmapImage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AadharData> CREATOR = new Parcelable.Creator<AadharData>() {
        @Override
        public AadharData createFromParcel(Parcel in) {
            return new AadharData(in);
        }

        @Override
        public AadharData[] newArray(int size) {
            return new AadharData[size];
        }
    };
}