package com.example.shchaura.sugamyatra;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.IntentService;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A login screen that offers login via email/password.
 */
public class FormFill extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private TextView returnDate;
    private int year;
    private int month;
    private int day;
    static final int DATE_PICKER_ID = 1111;

    private AutoCompleteTextView sourceStation, destinationStation;
    private String countries[] =  {"ABR","AGA ","ADI ","AII","AWR","AMI","ASR ","ADH","ASN","AWB","BDM","BLS ","BDTS","SBC","BC","BEAS","BIA","BPL ","BBS","BKN ","BKSC ","BVI ","BPQ","CLT ","CNO","CDG","CC","MAS","CTND","CDM","CHTS","CBE","CTC","DR","DJ","DDN","DLI","DEE","DSJ","DHN","DWK","ERS","ED","FBD","FTS","GAYA ","GZB","GKP","GGN","GHY ","GWL","NZM ","HW","HGY","HPT ","HSRA ","HWH","UBL","HYB","INDM","JP","JSM ","JUC","JAT ","JHS","JU ","CJ ","KGRA ","CNB ","CAPE","KKDI ","KEI ","KEA ","KOTA ","LJN","LKO","LC","LDH","MAO","MDU","MJO","MAQ","MMR","MTC","BCT","CSTM","MBVT ","MYS","NGP ","NDLS","PNP","PNBE","PDY","PBR","PUNE","PURI ","R","RJT","RMM","RNT","RKSH","ROK","ROU","SA","SC","SGUJ","SML","ST ","TNA","TPTY","TVC","UDZ","UJN","BRC","BSB ","VSKP","WL"};
    private ArrayAdapter<String> adapterSource, adapterDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_fill);
        returnDate= (TextView) findViewById(R.id.returnDate);

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        day   = c.get(Calendar.DAY_OF_MONTH);
        String smonth = Integer.toString(month);
        String sday = Integer.toString(day);
        if(month<=9)
            smonth = "0" + smonth;
        if(day<=9)
            sday = "0" + sday;

        returnDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(sday).append("-").append(smonth).append("-")
                .append(year));

        returnDate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_PICKER_ID);

            }

        });
        sourceStation = (AutoCompleteTextView) findViewById(R.id.sourceStation);
        adapterSource = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countries);
        sourceStation.setAdapter(adapterSource);

        destinationStation = (AutoCompleteTextView) findViewById(R.id.destinationStation);
        adapterDestination = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countries);
        destinationStation.setAdapter(adapterDestination);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }

    public void onClickSearchTrain(View v){
        Log.d("TAG","OnCLick");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Toast.makeText(getBaseContext(), "CLICKED"+v.getId(), Toast.LENGTH_LONG).show();
        formFillData data = new formFillData(getApplicationContext(),returnDate.getText(),sourceStation.getText(),destinationStation.getText());
        data.val = "1";
        new sendGetRequests().execute(data,null,null);
    }

    public void onClickSearchTrain1(View v){
        Log.d("TAG","OnCLick");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Toast.makeText(getBaseContext(), "CLICKED"+v.getId(), Toast.LENGTH_LONG).show();
        formFillData data = new formFillData(getApplicationContext(),returnDate.getText(),sourceStation.getText(),destinationStation.getText());
        data.val = "2";
        new sendGetRequests().execute(data,null,null);
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth+1;
            day   = selectedDay;
            String smonth = Integer.toString(month);
            String sday = Integer.toString(day);
            if(month<=9)
                smonth = "0" + smonth;
            if(day<=9)
                sday = "0" + sday;


            // Show selected date
            returnDate.setText(new StringBuilder().append(sday).append("-").append(smonth)
                    .append("-").append(year));

        }
    };

}

