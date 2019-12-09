package com.adc.funnyfarmfinal.model.registerphonenumber;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegister implements Parcelable
{
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("UserId")
    @Expose
    private Object userId;
    @SerializedName("ErrorCode")
    @Expose
    private Integer errorCode;
    public final static Parcelable.Creator<UserRegister> CREATOR = new Creator<UserRegister>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserRegister createFromParcel(Parcel in) {
            return new UserRegister(in);
        }

        public UserRegister[] newArray(int size) {
            return (new UserRegister[size]);
        }

    }
            ;

    protected UserRegister(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public UserRegister() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(userId);
        dest.writeValue(errorCode);
    }

    public int describeContents() {
        return 0;
    }

}