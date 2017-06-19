package com.example.shchaura.sugamyatra;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kabalasa on 6/15/2017.
 */

public class TrainList extends Activity{
    ListView lv;
    Context context;

    ArrayList prgmName;
    customAdapter adapter;
    String TAG = "dad";
   public static String [] prgmNameList={"Let Us C","c++","JAVA","Jsp","Microsoft .Net","Android","PHP","Jquery","JavaScript"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainlistview);
        Log.i(TAG,"here");
        context=this;
        /*
        lv=(ListView) findViewById(R.id.listViewtrain);
        lv.setAdapter(new customAdapter(this, prgmNameList));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Button is clicked", Toast.LENGTH_LONG).show();

            }
        });*/

    }


}
