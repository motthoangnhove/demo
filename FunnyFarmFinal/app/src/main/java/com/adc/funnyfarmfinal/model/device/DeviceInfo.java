package com.adc.funnyfarmfinal.model.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class DeviceInfo {
        public static String DEVICEIMEI;
        public static String SIMIMEI;
        public static int ANDROID_SDK_VERSION;
        public static String ANDROID_OS_VERSION;
        public static String MODEL_NUMBER;
        private TelephonyManager mTelephonyMgr;
	public DeviceInfo(Context mContext){
            this.mTelephonyMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            DEVICEIMEI = GetDeviceIMEI();
            SIMIMEI = GetSIMIMEI();
            ANDROID_SDK_VERSION = getDeviceAndroidSDKVersion();
            MODEL_NUMBER = getDeviceModelNumber();
            ANDROID_OS_VERSION = getDeviceAndroidOSVersion();
        }

        /**
         * lấy số imei
         * */
        public String GetSIMIMEI() {
            try {
                String simIMEI = mTelephonyMgr.getSubscriberId();
                // String imei =
                if (TextUtils.isEmpty(simIMEI)) {
                    return "-1";
                } else {
                    return simIMEI;
                }
            } catch (Exception e) {

                return "-1";
            }
        }

        /**
         * get device imei number
         * */
        public String GetDeviceIMEI() {
            try {
                String deviceImei = mTelephonyMgr.getDeviceId(); //*** use for mobiles
                if(deviceImei == null)
                    deviceImei = Build.SERIAL; //*** use for tablets

                if (TextUtils.isEmpty(deviceImei)) {
                    return "-1";
                } else {
                    return deviceImei;
                }
            } catch (Exception e) {

                return "-1";
            }
        }

        /**
         * get device's android sdk version
         * */
        public int getDeviceAndroidSDKVersion() {
            int androidVer = -1;
            try{
                androidVer = Build.VERSION.SDK_INT;
            }
            catch (Exception e){

                androidVer = -1;
            }
            return androidVer;
        }
        /**
         * get device's model number
         * */
        public String getDeviceModelNumber(){
            String modelNum = "-1";
            try{
                modelNum = Build.MODEL;
            }
            catch(Exception e){

                modelNum = "-1";
            }
            return modelNum;
        }

        /**
         * get device's android os version
         * added by vandn, on 14/08/2013
         * */
        public String getDeviceAndroidOSVersion() {
            String androidVer = "-1";
            try{
                androidVer = Build.VERSION.RELEASE;
            }
            catch (Exception e){

                androidVer = "-1";
            }
            return androidVer;
        }
}
