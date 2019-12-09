package com.adc.funnyfarmfinal.model.session;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhoneNumberPost implements Parcelable
{

    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    public final static Parcelable.Creator<PhoneNumberPost> CREATOR = new Creator<PhoneNumberPost>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PhoneNumberPost createFromParcel(Parcel in) {
            return new PhoneNumberPost(in);
        }

        public PhoneNumberPost[] newArray(int size) {
            return (new PhoneNumberPost[size]);
        }

    }
    ;

    protected PhoneNumberPost(Parcel in) {
        this.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PhoneNumberPost() {
    }

    public PhoneNumberPost(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(phoneNumber);
    }

    public int describeContents() {
        return 0;
    }

}