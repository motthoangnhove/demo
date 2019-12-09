package com.adc.funnyfarmfinal.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TranslateText {
    // format lấy số điện thoại nhập vào 0988-120-201 -> 0988120201
    public static String  getNumberPhoneFromMask(String numberMask){
            String numberPhone = "";
            StringTokenizer maskElements = new StringTokenizer(numberMask, " - ");
            List<String> numberPhoneElements = new ArrayList<String>();
            while (maskElements.hasMoreTokens()) {
                numberPhoneElements.add(maskElements.nextToken());
            }
            for(int i = 0;i<numberPhoneElements.size();i++){
                numberPhone += numberPhoneElements.get(i);
            }
            return numberPhone;
    }

}
