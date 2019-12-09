package com.adc.funnyfarmfinal.model.session;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SessionResult implements Parcelable
{

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<SessionDetail> sessionDetail;
    public final static Parcelable.Creator<SessionResult> CREATOR = new Creator<SessionResult>() {
        @SuppressWarnings({
                "unchecked"
        })
        public SessionResult createFromParcel(Parcel in) {
            return new SessionResult(in);
        }

        public SessionResult[] newArray(int size) {
            return (new SessionResult[size]);
        }

    }
    ;

    protected SessionResult(Parcel in) {
        this.odataContext = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.sessionDetail, (com.adc.funnyfarmfinal.model.session.SessionDetail.class.getClassLoader()));
    }

    public SessionResult() {
    }

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<SessionDetail> getSessionDetail() {
        return sessionDetail;
    }

    public void setSessionDetail(List<SessionDetail> sessionDetail) {
        this.sessionDetail = sessionDetail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(odataContext);
        dest.writeList(sessionDetail);
    }

    public int describeContents() {
        return 0;
    }

}
