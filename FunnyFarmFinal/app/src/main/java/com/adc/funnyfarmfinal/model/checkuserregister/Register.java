package com.adc.funnyfarmfinal.model.checkuserregister;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register implements Parcelable
{

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Registered")
    @Expose
    private Integer registered;
    @SerializedName("ErrorCode")
    @Expose
    private Integer errorCode;
    public final static Parcelable.Creator<Register> CREATOR = new Creator<Register>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Register createFromParcel(Parcel in) {
            return new Register(in);
        }

        public Register[] newArray(int size) {
            return (new Register[size]);
        }

    }
            ;

    protected Register(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.registered = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Register() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRegistered() {
        return registered;
    }

    public void setRegistered(Integer registered) {
        this.registered = registered;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(registered);
        dest.writeValue(errorCode);
    }

    public int describeContents() {
        return 0;
    }

}