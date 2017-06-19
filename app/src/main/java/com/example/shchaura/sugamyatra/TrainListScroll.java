package com.example.shchaura.sugamyatra;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TrainListScroll extends AppCompatActivity {

    ListView lv;
    Context context;

    ArrayList trns,trnNo;
    customAdapter adapter;
    String TAG = "dad",date,source,dest;
    public static String [] prgmNameList={"Let Us C","c++","JAVA","Jsp","Microsoft .Net","Android","PHP","Jquery","JavaScript"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list_scroll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        source = intent.getStringExtra("source");
        dest = intent.getStringExtra("dest");
        trnNo = intent.getStringArrayListExtra("trainNo");
        trns = intent.getStringArrayListExtra("trains");
        formFillData data = new formFillData();
        data.source=source;
        data.dest=dest;
        data.date=date;
        data.context=this;
        data.val = intent.getStringExtra("val");

        Spinner classSpinner = (Spinner) findViewById(R.id.classes);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.class_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(adapter1);
        Spinner spinner = (Spinner) findViewById(R.id.quotas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.quota_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        context=this;
        lv=(ListView) findViewById(R.id.listViewTrain);
        lv.setAdapter(new customAdapter(this, trns,data,trnNo));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Button is clicked", Toast.LENGTH_LONG).show();

            }
        });
    }
}
