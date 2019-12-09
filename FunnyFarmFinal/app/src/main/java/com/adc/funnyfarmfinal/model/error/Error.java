package com.adc.funnyfarmfinal.model.error;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error implements Parcelable {

  @SerializedName("code")
  @Expose
  private String code;
  @SerializedName("message")
  @Expose
  private String message;
  public final static Parcelable.Creator<Error> CREATOR = new Creator<Error>() {


    @SuppressWarnings({
            "unchecked"
    })
    public Error createFromParcel(Parcel in) {
      return new Error(in);
    }

    public Error[] newArray(int size) {
      return (new Error[size]);
    }

  };

  protected Error(Parcel in) {
    this.code = ((String) in.readValue((String.class.getClassLoader())));
    this.message = ((String) in.readValue((String.class.getClassLoader())));
  }

  public Error() {
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(code);
    dest.writeValue(message);
  }

  public int describeContents() {
    return 0;
  }
}
