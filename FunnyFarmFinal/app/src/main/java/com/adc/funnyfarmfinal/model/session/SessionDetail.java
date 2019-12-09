package com.adc.funnyfarmfinal.model.session;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SessionDetail implements Parcelable {

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Token")
    @Expose
    private String token;
    @SerializedName("ErrorCode")
    @Expose
    private Integer errorCode;
    public final static Parcelable.Creator<SessionDetail> CREATOR = new Creator<SessionDetail>() {

            @SuppressWarnings({
                    "unchecked"
            })
            public SessionDetail createFromParcel(Parcel in) {
                return new SessionDetail(in);
            }

            public SessionDetail[] newArray(int size) {
                return (new SessionDetail[size]);
            }

          }
        ;

    protected SessionDetail(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public SessionDetail() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(token);
        dest.writeValue(errorCode);
    }

    public int describeContents() {
        return 0;
    }

}
