package com.adc.funnyfarmfinal.model.registerphonenumber;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserRegisterResult implements Parcelable
{
    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<UserRegister> userRegisterList;
    public final static Parcelable.Creator<UserRegisterResult> CREATOR = new Creator<UserRegisterResult>() {

        @SuppressWarnings({
                "unchecked"
        })
        public UserRegisterResult createFromParcel(Parcel in) {
            return new UserRegisterResult(in);
        }

        public UserRegisterResult[] newArray(int size) {
            return (new UserRegisterResult[size]);
        }

    }
            ;

    protected UserRegisterResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.userRegisterList, (com.adc.funnyfarmfinal.model.registerphonenumber.UserRegister.class.getClassLoader()));
    }

    public UserRegisterResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<UserRegister> getUserRegisterList() {
        return userRegisterList;
    }

    public void setUserRegisterList(List<UserRegister> userRegisterList) {
        this.userRegisterList = userRegisterList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(userRegisterList);
    }

    public int describeContents() {
        return 0;
    }

}
