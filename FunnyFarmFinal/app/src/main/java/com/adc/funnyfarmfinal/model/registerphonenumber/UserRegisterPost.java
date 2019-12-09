package com.adc.funnyfarmfinal.model.registerphonenumber;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegisterPost implements Parcelable
{
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("DeviceId")
    @Expose
    private String deviceId;
    @SerializedName("OSType")
    @Expose
    private String oSType;
    public final static Parcelable.Creator<UserRegisterPost> CREATOR = new Creator<UserRegisterPost>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserRegisterPost createFromParcel(Parcel in) {
            return new UserRegisterPost(in);
        }

        public UserRegisterPost[] newArray(int size) {
            return (new UserRegisterPost[size]);
        }

    }
            ;

    protected UserRegisterPost(Parcel in) {
        this.fullName = ((String) in.readValue((String.class.getClassLoader())));
        this.phoneNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.deviceId = ((String) in.readValue((String.class.getClassLoader())));
        this.oSType = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserRegisterPost() {
    }
    public UserRegisterPost(String fullName, String phoneNumber, String deviceId, String osType) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.deviceId = deviceId;
        this.oSType = osType;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOSType() {
        return oSType;
    }

    public void setOSType(String oSType) {
        this.oSType = oSType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(fullName);
        dest.writeValue(phoneNumber);
        dest.writeValue(deviceId);
        dest.writeValue(oSType);
    }

    public int describeContents() {
        return 0;
    }

}
