package com.example.shchaura.sugamyatra;

/**
 * Created by kabalasa on 6/16/2017.
 */

        import android.content.Context;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Looper;
        import android.util.Log;
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

class sendGetRequests extends AsyncTask<formFillData, formFillData, String> {

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
                throw new Exception("Response Error");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
            String s = "";
            while ((s = buffer.readLine()) != null)
                output.append(s);
            JSONObject jsonObject = new JSONObject(output.toString());
            return jsonObject.toString();
        } catch (Exception ex) {
            return ("error"+ex.getMessage());
        }
    }
    protected void onPostExecute(String result){
        if(result.startsWith("error"))
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        else {
            Intent intent = new Intent(context, TrainListScroll.class);
            //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Log.d("TAG", result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray trainNo = jsonObject.getJSONArray("trainIDs");
                JSONArray trains = jsonObject.getJSONArray("trains");
                List<String> trnNo = new ArrayList<>();
                List<String> trns = new ArrayList<>();
                for( int i=0; i<trainNo.length();i++)
                {
                    trnNo.add(trainNo.getString(i));
                    trns.add(trains.getString(i));
                }
                intent.putExtra("date",formFillData.date);
                intent.putExtra("val",formFillData.val);
                intent.putExtra("source",formFillData.source);
                intent.putExtra("dest",formFillData.dest);
                intent.putStringArrayListExtra("trainNo", (ArrayList<String>) trnNo);
                intent.putStringArrayListExtra("trains", (ArrayList<String>) trns);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
