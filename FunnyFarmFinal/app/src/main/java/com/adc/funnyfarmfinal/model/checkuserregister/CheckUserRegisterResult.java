package com.adc.funnyfarmfinal.model.checkuserregister;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckUserRegisterResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<Register> registerList;
    public final static Parcelable.Creator<CheckUserRegisterResult> CREATOR = new Creator<CheckUserRegisterResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CheckUserRegisterResult createFromParcel(Parcel in) {
            return new CheckUserRegisterResult(in);
        }

        public CheckUserRegisterResult[] newArray(int size) {
            return (new CheckUserRegisterResult[size]);
        }

    }
            ;

    protected CheckUserRegisterResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.registerList, (com.adc.funnyfarmfinal.model.checkuserregister.Register.class.getClassLoader()));
    }

    public CheckUserRegisterResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<Register> getRegisterList() {
        return registerList;
    }

    public void setRegisterList(List<Register> registerList) {
        this.registerList = registerList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(registerList);
    }

    public int describeContents() {
        return 0;
    }

}
