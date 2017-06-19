package com.example.shchaura.sugamyatra;

/**
 * Created by kabalasa on 6/16/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by supate on 15-06-2017.
 */

class sendGetRequestTrain extends AsyncTask<formFillData, formFillData, String> {

    protected void onPreExecute(){

    }
    Context context = null;
    formFillData formFillData = null;
    @Override
    protected String doInBackground(formFillData... formData){
        try {
            Log.d("TAG", "Yo");

            if (Looper.myLooper() == null) Looper.prepare();
            context = formData[0].context;
            formFillData = formData[0];
            //    Spinner spinner = (Spinner)findViewById(R.id.classes);
            String temp[] = {"SL","3A"};
            URL url = new URL("http://10.104.248.137:5000/getAllTrains?source=" + formData[0].source + "&dest=" + formData[0].dest +  "&doj=" + formData[0].date );

            Log.d("TAG", url.toString());
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            Log.d("TAG", "Hi");
            System.out.print("Hi");
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();
            InputStream stream = null;
            StringBuffer output = new StringBuffer("");
            System.out.print(httpConnection.getResponseCode());
            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();
            }
            else
                return "No trains";
            BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
            String s = "";
            while ((s = buffer.readLine()) != null)
                output.append(s);
            return output.toString();
        } catch (Exception ex) {
            return ("error"+ex.getMessage());
        }
    }
    protected void onPostExecute(String result){
        if(result == null) Log.d("TAG","null");
        else Log.d("TAG","A"+result);
        if(result.startsWith("error"))
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        else {
            Intent intent = new Intent(context, TrainDetails.class);
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Log.d("TAG", "B"+result);
            intent.putExtra("data",result);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
