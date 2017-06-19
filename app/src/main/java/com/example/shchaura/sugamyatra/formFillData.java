package com.example.shchaura.sugamyatra;

import android.content.Context;
import android.text.Editable;

import java.util.StringTokenizer;

/**
 * Created by kabalasa on 6/16/2017.
 */

public class formFillData {
    Context context;
    String date,source,dest,cls,quota,trainNo;
    String val;

    public formFillData(Context applicationContext, CharSequence text, Editable text1, Editable text2) {
        context = applicationContext;
        String temp = text.toString();
        StringTokenizer token = new StringTokenizer(temp,"-");
        String day = token.nextToken();
        String month = token.nextToken();
        String year = token.nextToken();
        date = year + month + day;
        source = text1.toString();
        dest = text2.toString();
    }

    public formFillData() {

    }
}
