package com.adc.funnyfarmfinal.model.error;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiErrorResult implements Parcelable {
  @SerializedName("error")
  @Expose
  private Error error;
  public final static Parcelable.Creator<ApiErrorResult> CREATOR = new Creator<ApiErrorResult>() {


    @SuppressWarnings({
            "unchecked"
    })
    public ApiErrorResult createFromParcel(Parcel in) {
      return new ApiErrorResult(in);
    }

    public ApiErrorResult[] newArray(int size) {
      return (new ApiErrorResult[size]);
    }

  };

  protected ApiErrorResult(Parcel in) {
    this.error = ((Error) in.readValue((Error.class.getClassLoader())));
  }

  public ApiErrorResult() {
  }

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(error);
  }

  public int describeContents() {
    return 0;
  }
}