package com.example.shchaura.sugamyatra;

/**
 * Created by kabalasa on 6/15/2017.
 */

import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.shchaura.sugamyatra.R.id.returnDate;

public class customAdapter extends BaseAdapter {
    String TAG = "dad";
    ArrayList result,trainNo;
    formFillData formFillData = null;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public customAdapter(TrainListScroll mainActivity, ArrayList prgmNameList, com.example.shchaura.sugamyatra.formFillData train, ArrayList trnNo) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        trainNo = trnNo;
        formFillData=train;
        inflater = ( LayoutInflater ) formFillData.context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Log.i("resultsp",result.get(position) + ""+position+" "+result.size());
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.singletrain, null);
        holder.tv=(TextView) rowView.findViewById(R.id.trainno);
        holder.tv.setText(result.get(position).toString());
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Toast.makeText(formFillData.context, "You Clicked "+result.get(position), Toast.LENGTH_LONG).show();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Toast.makeText(formFillData.context, "CLICKED", Toast.LENGTH_LONG).show();
                formFillData.trainNo = trainNo.get(position).toString();
                new sendGetRequestTrain().execute(formFillData,null,null);
            }
        });
        return rowView;
    }

}